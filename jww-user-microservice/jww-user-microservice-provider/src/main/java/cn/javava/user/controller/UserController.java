package cn.javava.user.controller;

import cn.javava.user.api.UserApi;
import cn.javava.user.service.UserService;
import cn.javava.user.vo.UserCriteria;
import cn.javava.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public Page<UserVo> getUsers(UserCriteria criteria, Pageable pageable) {
        return userService.findUsers(criteria, pageable);
    }

    @Override
    public UserVo getUser(@PathVariable String userid) {
        return userService.getUser(userid);
    }
}
