package cn.javava.api.user.service;

import cn.javava.api.user.vo.UserCriteria;
import cn.javava.api.user.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserVo getUser(String userid);

    Page<UserVo> findUsers(UserCriteria criteria, Pageable pageable);

}
