package com.jeorgio.javava.authc.endpoint;

import com.jeorgio.javava.authc.service.TicketService;
import com.jeorgio.javava.authc.vo.QrcodeLoginVo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.join;

@RequestMapping("/authc")
@RestController
@Api(tags = "用户登陆", description = "支持微信扫码登陆，其他的接口酌情开发。")
public class AuthenticationController {

    @Value("${javava.jwt.secret}")
    private String jwtSecret;

    @Autowired
    private TicketService ticketService;

    @ApiOperation(value = "扫码登陆", tags = "扫码登陆", notes = "使用微信的oauth2授权机制，为方便控制外观客户端自行生成QRCODE。")
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public QrcodeLoginVo authc() {
        return ticketService.createTicket();
    }

    @ApiOperation(value = "扫码登陆结果查询", notes = "轮询检测用户是否已经扫码成功，成功返回200状态码")
    @ApiParam(name = "ticket", value = "票据", required = true, type = "path")
    @ApiResponses({
            @ApiResponse(code = 102, message = "等待扫码结果"),
            @ApiResponse(code = 200, message = "成功", response = String.class),
            @ApiResponse(code = 404, message = "票据不正确")
    })
	@GetMapping(path = "{ticket}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> authcResult(@PathVariable String ticket) {
        String subject = ticketService.getTicket(ticket);
        if (subject == null) {
            return ResponseEntity.status(404).build();
        } else if (isBlank(subject)) {
            return ResponseEntity.status(102).build();
        } else {
            String compactJwt = Jwts.builder()
                    .setSubject(subject)
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
            return ResponseEntity.ok(join("\"", compactJwt, "\""));
        }
	}
}
