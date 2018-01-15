package cn.javava.authc.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@lombok.Data
@ApiModel
public class QrcodeToken {

    @ApiModelProperty(name = "state", value = "app根据这个state查询登陆结果。")
    private String state;

    @ApiModelProperty(name = "qrcode", value = "登陆地址，app客房端生成二维码。")
    private String qrcode;
}
