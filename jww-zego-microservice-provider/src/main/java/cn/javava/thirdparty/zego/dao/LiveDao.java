package cn.javava.thirdparty.zego.dao;

import cn.javava.thirdparty.zego.vo.CloseLiveVo;
import cn.javava.thirdparty.zego.vo.OpenLiveVo;

public interface LiveDao {

    void create(OpenLiveVo vo);

    void update(CloseLiveVo vo);
}
