package cn.javava.authc.service;


import cn.javava.authc.vo.QrcodeToken;
import cn.javava.authc.vo.UserVo;

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
