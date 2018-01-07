package com.jeorgio.javava.authc.service;


import com.jeorgio.javava.authc.vo.QrcodeToken;
import com.jeorgio.javava.users.vo.UserVo;

public interface QrcodeTokenService {

    /**
     * 微信网页授权
     */
    QrcodeToken generateQrcodeToken();

    /**
     * 扫码登陆成功
     */
    void scanQrcodeSuccess(String qrcode, UserVo userVo);

    /**
     * 查询登陆用户
     */
    String obtainPrincipal(String qrcode);

}
