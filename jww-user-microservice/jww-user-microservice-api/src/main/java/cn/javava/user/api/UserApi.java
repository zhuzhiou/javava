package cn.javava.user.api;

import cn.javava.user.vo.UserCriteria;
import cn.javava.user.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public interface UserApi {

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Page<UserVo> getUsers(UserCriteria criteria, Pageable pageable);

    @GetMapping(path = "/{userid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    UserVo getUser(@PathVariable String userid);
}
