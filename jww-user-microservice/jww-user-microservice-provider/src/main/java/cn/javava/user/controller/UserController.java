package cn.javava.user.controller;

import cn.javava.user.service.UserService;
import cn.javava.user.vo.UserCriteria;
import cn.javava.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<UserVo> getUsers(UserCriteria criteria, Pageable pageable) {
        return userService.findUsers(criteria, pageable);
    }

    @GetMapping(path = "/{userid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserVo getUser(@PathVariable String userid) {
        return userService.getUser(userid);
    }
}
