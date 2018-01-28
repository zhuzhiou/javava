package cn.javava.pay.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface WxController {
    @PostMapping(value="/generateQRCode")
    JSONObject generatePrepayUrl(String userId, Integer money);

    @GetMapping(value = "/{tradeNo}")
    JSONObject respStatus(@PathVariable String tradeNo);
}
