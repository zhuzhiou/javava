package cn.javava.thirdparty.zego.dao;

import cn.javava.thirdparty.zego.vo.CloseLiveVo;
import cn.javava.thirdparty.zego.vo.OpenLiveVo;

public interface LiveRoomDao {

    int count(OpenLiveVo vo);

    int create(OpenLiveVo vo);

    int update(OpenLiveVo vo);

    int count(CloseLiveVo vo);

    int create(CloseLiveVo vo);

    int update(CloseLiveVo vo);
}
