package cn.javava.user.repository;

import cn.javava.user.entity.UserPo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<UserPo, String>, JpaSpecificationExecutor<UserPo> {

    UserPo getOne(String userid);
}
