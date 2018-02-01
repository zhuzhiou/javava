package cn.javava.thirdparty.zego.util;

import java.util.Arrays;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;
import static org.apache.commons.lang3.StringUtils.join;

public final class ZegoUtil {

    private ZegoUtil() {
    }

    public static String createSign(String secret, String timestamp, String nonce) {
        String[] params = new String[] {secret, timestamp, nonce};
        Arrays.sort(params);
        String temp = sha1Hex(join(params, ""));
        System.out.println("签名：" + temp);
        return temp;
    }
}
