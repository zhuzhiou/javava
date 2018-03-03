package cn.javava.thirdparty.zego.dao;

import cn.javava.thirdparty.zego.vo.CloseStreamVo;
import cn.javava.thirdparty.zego.vo.OpenStreamVo;

public interface StreamDao {

    void insert(OpenStreamVo vo);

    void delete(CloseStreamVo vo);
}
