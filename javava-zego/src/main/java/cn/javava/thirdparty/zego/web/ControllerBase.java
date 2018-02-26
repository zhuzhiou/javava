package cn.javava.thirdparty.zego.web;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;
import static org.apache.commons.lang3.StringUtils.join;

@RequestMapping("/zego")
public abstract class ControllerBase {

    protected final int CODE_SUCCESS = 1;

    protected final int CODE_DATETIME_MISMATCH = 2;

    protected final int CODE_ILLEGAL_SIGN = 3;

    public String createSign(String secret, String timestamp, String nonce) {
        String[] params = new String[] {secret, timestamp, nonce};
        Arrays.sort(params);
        String temp = sha1Hex(join(params, ""));
        System.out.println("签名：" + temp);
        return temp;
    }
}
