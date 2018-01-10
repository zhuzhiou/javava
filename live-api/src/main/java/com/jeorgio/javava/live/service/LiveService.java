package com.jeorgio.javava.live.service;

import com.jeorgio.javava.live.vo.LiveVo;
import com.jeorgio.javava.live.vo.RoomVo;

public interface LiveService {

    void saveRoom(RoomVo vo);

    void saveLive(LiveVo vo);
}
