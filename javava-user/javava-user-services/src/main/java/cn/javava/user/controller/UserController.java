package cn.javava.user.controller;

import cn.javava.user.dto.UserCriteria;
import cn.javava.user.entity.User;
import cn.javava.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Page<User> getUsers(UserCriteria criteria, Pageable pageable) {
        return userService.getUsers(criteria, pageable);
    }

    @GetMapping("/users/{userid}")
    public User getUser(@PathVariable String userid) {
        return userService.getUser(userid);
    }
}
