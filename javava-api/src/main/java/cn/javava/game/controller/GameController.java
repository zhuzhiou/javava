package cn.javava.game.controller;

import cn.javava.game.util.AES;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Random;

@RequestMapping("/games")
@RestController
public class GameController {

    @PostMapping
    public JSONObject begin(
            @RequestParam("session_id") String session_id,
            @RequestParam("confirm") Integer confirm) {
        long time_stamp = Instant.now().toEpochMilli();
        JSONObject game_config = new JSONObject();
        game_config.put("game_time", 30); //游戏总时长
        game_config.put("claw_power_grab", 67); // 表示抓起爪力(1—100)，指下爪时，抓住娃娃的爪力，建议这个值设置大一点
        game_config.put("claw_power_up", 33); // 表示到顶爪力(1—100)，指天车提起娃娃到 up_height 指定的高度后将使用该爪力值直至天车到达顶部
        game_config.put("claw_power_move", 21); // 表示移动爪力(1—100)，指天车到达顶部后，移动过程中的爪力
        game_config.put("up_height", 7); // 抓起高度（0–10）底部到顶部分成10份，爪子到达该值指定的高度时就会将爪力减小至到顶爪力

        JSONObject authority_info = new JSONObject();
        authority_info.put("session_id", session_id);
        authority_info.put("confirm", confirm);
        authority_info.put("time_stamp", time_stamp);
        authority_info.put("custom_token", RandomStringUtils.randomAlphabetic(32));

        JSONObject config = new JSONObject();
        config.put("game_config", game_config);
        config.put("authority_info", authority_info);

        JSONObject wrapper = new JSONObject();
        wrapper.put("code", 0);
        wrapper.put("message", "成功");
        wrapper.put("config", AES.encrypt(config.toJSONString()));
        wrapper.put("time_stamp", time_stamp);
        return wrapper;
    }

    @PostMapping(path = "/lottery")
    public JSONObject lottery(
            @RequestParam("session_id") String session_id,
            @RequestParam("custom_token") String custom_token) {
        Random random = new Random();
        JSONObject wrapper = new JSONObject();
        wrapper.put("code", 0);
        wrapper.put("message", "成功");
        wrapper.put("position", Math.abs((random.nextInt()) % 12) + 1);
        return wrapper;
    }
}
