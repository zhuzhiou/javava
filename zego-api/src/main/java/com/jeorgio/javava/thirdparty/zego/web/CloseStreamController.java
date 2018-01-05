package com.jeorgio.javava.thirdparty.zego.web;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.jeorgio.javava.thirdparty.zego.CloseStreamVo;
import com.jeorgio.javava.thirdparty.zego.OpenStreamVo;
import com.jeorgio.javava.thirdparty.zego.config.ApiConfig;
import com.jeorgio.javava.thirdparty.zego.config.ZegoConstants;
import com.jeorgio.javava.thirdparty.zego.service.CloseStreamHandler;
import com.jeorgio.javava.thirdparty.zego.service.OpenStreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.jeorgio.javava.thirdparty.zego.util.ZegoUtil.createSign;
import static java.lang.Math.abs;
import static java.time.LocalDateTime.ofEpochSecond;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@RequestMapping("/v1")
@RestController
public class CloseStreamController {

    private Logger logger = LoggerFactory.getLogger(OpenStreamHandler.class);

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    @Qualifier("closeStreamHandlerChain")
    private CloseStreamHandler closeStreamHandler;

    @PostMapping("/closeStream")
    public int closeStream(HttpServletRequest request) {
        CloseStreamVo vo = assignVo(request);
        LocalDateTime timestamp = ofEpochSecond(Longs.tryParse(vo.getTimestamp()), 0, ZoneOffset.of("+8"));
        Duration duration = Duration.between(LocalDateTime.now(), timestamp);
        if (abs(duration.toDays()) > 1) {
            return ZegoConstants.CODE_DATETIME_MISMATCH;
        }
        String signature = createSign(apiConfig.getSecret(), vo.getTimestamp(), vo.getNonce());
        if (isNotBlank(signature) && signature.equals(vo.getSignature())) {
            closeStreamHandler.handle(vo);
        } else {
            if (logger.isErrorEnabled()) {
                logger.error("关闭流接口签名失败");
            }
            return ZegoConstants.CODE_ILLEGAL_SIGN;
        }
        return ZegoConstants.CODE_SUCCESS;
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
