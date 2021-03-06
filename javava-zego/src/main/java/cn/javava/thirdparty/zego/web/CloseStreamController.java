package cn.javava.thirdparty.zego.web;

import cn.javava.thirdparty.zego.service.NotificationService;
import cn.javava.thirdparty.zego.service.impl.CloseStreamHandlerChain;
import cn.javava.thirdparty.zego.vo.CloseStreamVo;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.lang.Math.abs;
import static java.time.LocalDateTime.ofEpochSecond;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@RestController
public class CloseStreamController extends ControllerBase {

    private Logger logger = LoggerFactory.getLogger(CloseStreamController.class);

    @Value("${zego.api.secret}")
    private String secret;

    @Autowired
    @Qualifier("closeStreamHandlerChain")
    private CloseStreamHandlerChain closeStreamHandler;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/close_stream")
    public int closeStream(HttpServletRequest request) {
        CloseStreamVo vo = assignVo(request);
        LocalDateTime timestamp = ofEpochSecond(Longs.tryParse(vo.getTimestamp()), 0, ZoneOffset.of("+8"));
        Duration duration = Duration.between(LocalDateTime.now(), timestamp);
        if (abs(duration.toDays()) > 1) {
            notificationService.sendMail("[加一联萌] 时间调整通知", "关闭流回调的娃娃机时间与业务服务器的时间不一致，请运维人员调整娃娃机、服务器的时间。");
            return CODE_DATETIME_MISMATCH;
        }
        String signature = createSign(secret, vo.getTimestamp(), vo.getNonce());
        if (isNotBlank(signature) && signature.equals(vo.getSignature())) {
            closeStreamHandler.handle(vo);
            notificationService.sendMail("[加一联萌] 娃娃机关闭通知", "娃娃机已关闭，请知晓。");
        } else {
            notificationService.sendMail("[加一联萌] 签名错误通知", "关闭流回调的签名不正确，请核查密钥是否已修改。");
            if (logger.isErrorEnabled()) {
                logger.error("关闭流回调接口 -> 签名不一致");
            }
            return CODE_ILLEGAL_SIGN;
        }
        return CODE_SUCCESS;
    }

    private CloseStreamVo assignVo(HttpServletRequest request) {
        CloseStreamVo vo = new CloseStreamVo();

        String id = request.getParameter("id");
        if (isNotBlank(id)) {
            vo.setId(Ints.tryParse(id));
        }

        String channel_id = request.getParameter("channel_id");
        if (isNotBlank(channel_id)) {
            vo.setChannelId(channel_id);
        }

        String stream_alias = request.getParameter("stream_alias");
        if (isNotBlank(stream_alias)) {
            vo.setStreamAlias(stream_alias);
        }

        String timestamp = request.getParameter("timestamp");
        if (isNotBlank(timestamp)) {
            vo.setTimestamp(timestamp);
        }

        String nonce = request.getParameter("nonce");
        if (isNotBlank(nonce)) {
            vo.setNonce(nonce);
        }

        String signature = request.getParameter("signature");
        if (isNotBlank(signature)) {
            vo.setSignature(signature);
        }

        return vo;
    }
}
