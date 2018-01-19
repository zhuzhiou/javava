package cn.javava.pay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


public class WxPayUtil {

    private static final Logger logger = LoggerFactory.getLogger(WxPayUtil.class);

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }
}
