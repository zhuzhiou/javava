package cn.javava.weixin.component.util;

import org.apache.commons.codec.binary.Base64;

public class WXBizMsgCrypt {

    byte[] encodingAESKey;

    String token;

    public WXBizMsgCrypt(String token, String encodingAESKey) {
        this.token = token;
        this.encodingAESKey = Base64.decodeBase64(encodingAESKey);
    }

    /*public String decryptMsg(String msgSignature, String timeStamp, String nonce, String postData)
            throws AesException {

        // 密钥，公众账号的app secret
        // 提取密文
        Object[] encrypt = XMLParse.extract(postData);

        // 验证安全签名
        String signature = SHA1.getSHA1(token, timeStamp, nonce, encrypt[1].toString());

        // 和URL中的签名比较是否相等
        // System.out.println("第三方收到URL中的签名：" + msg_sign);
        // System.out.println("第三方校验签名：" + signature);
        if (!signature.equals(msgSignature)) {
            throw new AesException(AesException.ValidateSignatureError);
        }

        // 解密
        String result = decrypt(encrypt[1].toString());
        return result;
    }*/
}
