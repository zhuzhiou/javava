package com.jeorgio.javava.authc.web;

import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.StringUtils.join;

@RequestMapping("/authc")
@RestController
@Api(tags = "用户登陆", description = "支持微信扫码登陆，其他的接口酌情开发。")
public class AuthenticationController {

    @Value("${javava.jwt.secret}")
    private String jwtSecret;

    @Value("${weixin.api.appId}")
    private String appId;

    @Value("${weixin.authz.redirect_uri}")
    private String redirect_uri;

    @ApiOperation(value = "扫码登陆", tags = "扫码登陆", notes = "使用微信的oauth2授权机制，为方便控制外观客户端自行生成QRCODE。")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> authc() {
        String ticket = join(randomAlphabetic(2) + randomAlphanumeric(30));
        String qrcode = join("https://open.weixin.qq.com/connect/oauth2/authorize",
                "?appid=", appId,
                "&redirect_uri=", UriUtils.encode(redirect_uri, StandardCharsets.UTF_8),
                "&response_type=code&scope=snsapi_userinfo",
                "&state=", ticket, "#wechat_redirect");
        return ImmutableMap.of("ticket", ticket, "qrcode", qrcode);
    }

    @ApiOperation(value = "扫码登陆结果查询", notes = "轮询检测用户是否已经扫码成功，成功返回200状态码")
    @ApiResponses({
            @ApiResponse(code = 102, message = "等待扫码结果"),
            @ApiResponse(code = 200, message = "成功", response = String.class),
            @ApiResponse(code = 404, message = "票据不正确")
    })
	@GetMapping(path = "{ticket}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String authcResult(@PathVariable String ticket) {
        String compactJwt = Jwts.builder()
                .setSubject("Joe")
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return compactJwt;
	}
}
