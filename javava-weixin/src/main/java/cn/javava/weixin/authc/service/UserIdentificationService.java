package cn.javava.weixin.authc.service;

import cn.javava.weixin.authc.vo.UserVo;

public interface UserIdentificationService {

    UserVo identify(String code, String state);
}
