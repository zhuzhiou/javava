package cn.javava.user.service;

import cn.javava.user.dto.UserCriteria;
import cn.javava.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getUser(String userid);

    Page<User> getUsers(UserCriteria criteria, Pageable pageable);

}
