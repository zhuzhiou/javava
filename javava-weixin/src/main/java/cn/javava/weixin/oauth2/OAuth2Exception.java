package cn.javava.weixin.oauth2;

public class OAuth2Exception extends RuntimeException {

    public OAuth2Exception(String message) {
        super(message);
    }

    public OAuth2Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public OAuth2Exception(Throwable cause) {
        super(cause);
    }
}
