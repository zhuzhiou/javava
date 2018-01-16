package cn.javava.api.user.controller;

import cn.javava.api.commons.controller.BaseApiController;
import cn.javava.user.service.UserService;
import cn.javava.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("${javava.api.path}/users")
@RestController
public class UserController extends BaseApiController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserVo getUsers(Pageable pageable) {
        userService.getUsers(pageable);
        return null;
    }

    @GetMapping(path = "/{userid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserVo getUser(@PathVariable String userid) {
        userService.getUser(userid);
        return null;
    }
}
