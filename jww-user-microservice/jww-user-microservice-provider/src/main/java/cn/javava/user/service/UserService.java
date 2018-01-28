package cn.javava.user.service;

import cn.javava.user.vo.UserCriteria;
import cn.javava.user.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserVo getUser(String userid);

    Page<UserVo> findUsers(UserCriteria criteria, Pageable pageable);

}
