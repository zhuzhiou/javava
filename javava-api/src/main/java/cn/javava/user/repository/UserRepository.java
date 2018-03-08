package cn.javava.user.repository;

import cn.javava.user.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String>, JpaSpecificationExecutor<User> {

    User getOne(String userid);
}
