package cn.javava.live.web;

import cn.javava.live.vo.LiveRoomVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(value = "直播接口")
@RequestMapping("/api/live")
@RestController
public class LiveRoomController {

    @ApiOperation(value = "获取直播房间列表")
    @GetMapping(path = "/rooms")
    public Page<LiveRoomVo> doGet(@ApiParam Pageable pageable) {
        return new PageImpl<LiveRoomVo>(Collections.EMPTY_LIST, pageable, 0);
    }
}
