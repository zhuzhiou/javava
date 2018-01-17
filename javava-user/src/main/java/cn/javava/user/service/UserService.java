package cn.javava.user.service;

import cn.javava.user.vo.UserVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    void getUser(String userid);

    List<UserVo> getUsers(Pageable pageable);
}
