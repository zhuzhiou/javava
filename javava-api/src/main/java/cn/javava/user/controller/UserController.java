package cn.javava.user.controller;

import cn.javava.common.wrap.PageWrapper;
import cn.javava.common.wrap.Wrapper;
import cn.javava.user.dto.UserCriteria;
import cn.javava.user.entity.User;
import cn.javava.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public PageWrapper<User> getUsers(UserCriteria criteria, Pageable pageable) {
        Page<User> page = userService.getUsers(criteria, pageable);
        PageWrapper<User> wrapper = new PageWrapper<>(0, "成功", page);
        return wrapper;
    }

    @GetMapping("/{userid}")
    public Wrapper<User> getUser(@PathVariable String userid) {
        User user = userService.getUser(userid);
        Wrapper<User> wrapper = new Wrapper<>(0, "成功", user);
        return wrapper;
    }
}
