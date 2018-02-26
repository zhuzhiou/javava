package cn.javava.thirdparty.zego.dao;

import cn.javava.thirdparty.zego.vo.CloseStreamVo;
import cn.javava.thirdparty.zego.vo.OpenStreamVo;

public interface RoomDao {

    int count(OpenStreamVo vo);

    int insert(OpenStreamVo vo);

    int update(OpenStreamVo vo);

    int count(CloseStreamVo vo);

    int insert(CloseStreamVo vo);

    int update(CloseStreamVo vo);
}
