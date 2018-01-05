package com.jeorgio.javava.authc.web;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.StringUtils.join;

@RequestMapping("/authc")
@RestController
@Api(tags = "用户登陆", description = "支持微信扫码登陆，其他的接口酌情开发。")
public class AuthenticationController {

    @ApiOperation(value = "", tags = "扫码登陆", notes = "使用微信的oauth2授权机制，为方便控制外观客户端自行生成QRCODE。")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> authc() {
        String ticket = join(randomAlphabetic(2) + randomAlphanumeric(30));
        String qrcode = join("https://open.weixin.qq.com/connect/oauth2/authorize?appid=",
                "&redirect_uri=http%3A%2F%2Fauthc.javava.cn%2Fweixin-authc%2Fauthorize",
                "&response_type=code&scope=snsapi_userinfo",
                "&state=", ticket, "#wechat_redirect");
        return ImmutableMap.of("ticket", ticket, "qrcode", qrcode);
    }


	@GetMapping(path = "{ticket}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String authcResult(@PathVariable String ticket) {
        return ticket;
	}
}
