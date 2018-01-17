package cn.javava.api.live.controller;

import cn.javava.api.commons.controller.BaseApiController;
import cn.javava.api.live.vo.LiveVo;
import cn.javava.api.user.vo.UserVo;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("${javava.api.path}/lives")
@RestController
public class LiveController extends BaseApiController {

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<LiveVo> getLives(Pageable pageable) {
        return null;
    }

    @GetMapping(path = "/rooms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserVo getUsers(Pageable pageable) {
        return null;
    }
}
