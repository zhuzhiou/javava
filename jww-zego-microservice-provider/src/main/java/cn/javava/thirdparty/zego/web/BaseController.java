package cn.javava.thirdparty.zego.web;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public abstract class BaseController {

    protected final int CODE_SUCCESS = 1;

    protected final int CODE_DATETIME_MISMATCH = 2;

    protected final int CODE_ILLEGAL_SIGN = 3;

}
