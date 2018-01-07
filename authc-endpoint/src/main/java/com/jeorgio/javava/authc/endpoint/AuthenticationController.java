package com.jeorgio.javava.authc.endpoint;

import com.jeorgio.javava.authc.service.QrcodeTokenService;
import com.jeorgio.javava.authc.vo.QrcodeToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.join;

@RequestMapping("/authc")
@RestController
@Api(tags = "微信扫码登陆", description = "第1个版本仅支持微信扫码。")
public class AuthenticationController {

    @Value("${javava.jwt.secret}")
    private String jwtSecret;

    @Value("${javava.api.secret}")
    private String apiSecret;

    @Autowired
    private QrcodeTokenService qrcodeTokenService;

    @ApiOperation(value = "扫码登陆", notes = "使用微信的oauth2授权机制，为方便控制外观客户端自行生成QRCODE。")
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public QrcodeToken authcRequest() {
        return qrcodeTokenService.generateQrcodeToken();
    }

    @ApiOperation(value = "扫码登陆结果查询", notes = "轮询检测用户是否已经扫码成功，成功返回200状态码")
    @ApiParam(name = "qrcode", value = "票据", required = true, type = "path")
    @ApiResponses({
            @ApiResponse(code = 204, message = "等待扫码结果"),
            @ApiResponse(code = 200, message = "成功", response = String.class),
            @ApiResponse(code = 403, message = "签名不正确"),
            @ApiResponse(code = 404, message = "票据不正确")
    })
	@GetMapping(path = "/{qrcode}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> authcResult(@PathVariable String qrcode, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String sign) {
        String[] params = new String[] {qrcode, timestamp, nonce, apiSecret};
        Arrays.sort(params);
        String sha1Hex = sha1Hex(join(params, ""));
        if (!StringUtils.equals(sign, sha1Hex)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        String subject = qrcodeTokenService.obtainPrincipal(qrcode);
        if (subject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (isBlank(subject)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            String compactJwt = Jwts.builder()
                    .setSubject(subject)
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
            return ResponseEntity.ok(compactJwt);
        }
	}
}
