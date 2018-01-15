package cn.javava.thirdparty.zego.dao;

import cn.javava.thirdparty.zego.vo.CloseLiveVo;
import cn.javava.thirdparty.zego.vo.OpenLiveVo;

public interface LiveDao {

    int create(OpenLiveVo vo);

    int update(CloseLiveVo vo);
}
