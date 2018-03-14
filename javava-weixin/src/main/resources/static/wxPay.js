/**
 * Created by w_zhanglong on 2018/3/12.
 */
var wx = new Object;
var pay = new Object();
wx.unifiedOrder = function(param,url,returnUrl) {
    $.ajax({
        url: url,
        type: 'POST',
        data: param,
        dataType: 'json',
        success: function (res) {
            if (res.code == '0') {
                if (typeof WeixinJSBridge == "undefined") {
                    if (document.addEventListener) {
                        document.addEventListener('WeixinJSBridgeReady', wx.onBridgeReady(res,returnUrl), false);
                    } else if (document.attachEvent) {
                        document.attachEvent('WeixinJSBridgeReady', wx.onBridgeReady(res,returnUrl));
                        document.attachEvent('onWeixinJSBridgeReady', wx.onBridgeReady(res,returnUrl));
                    }
                } else {
                    wx.onBridgeReady(res,returnUrl);
                }
            } else {
                alert('下单失败');
            }
        }
    })
};

wx.getClientIp = function(){

}

/**
 * 支付授权
 */
wx.onBridgeReady = function(data,returnUrl) {
    var params = {
        appId : data.appId,
        timeStamp : data.timeStamp,
        nonceStr : data.nonceStr,
        package : data.package,
        signType : data.signType,
        paySign : data.paySign
    };
    WeixinJSBridge.invoke('getBrandWCPayRequest', params, function(res) {
        if (res.err_msg == "get_brand_wcpay_request:ok") {
            if(returnUrl != ""){
                window.location.href = returnUrl;
            }
        } else if (res.err_msg == "get_brand_wcpay_request:fail") {
            alert("支付出现问题,请稍后再试");
        }
    });
};