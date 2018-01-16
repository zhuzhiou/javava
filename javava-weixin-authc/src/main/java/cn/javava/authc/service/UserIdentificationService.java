package cn.javava.authc.service;

import cn.javava.authc.vo.UserVo;

public interface UserIdentificationService {

    UserVo identify(String code, String state);
}
