package cn.javava.live.service;

import cn.javava.live.vo.LiveRoomVo;
import cn.javava.live.vo.LiveVo;

public interface LiveService {

    void saveRoom(LiveRoomVo vo);

    void saveLive(LiveVo vo);
}
