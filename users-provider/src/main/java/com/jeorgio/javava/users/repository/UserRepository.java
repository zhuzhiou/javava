package com.jeorgio.javava.users.repository;

import com.jeorgio.javava.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User getUserByOpenid(String openid);

}
