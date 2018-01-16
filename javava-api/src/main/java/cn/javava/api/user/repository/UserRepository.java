package cn.javava.api.user.repository;

import cn.javava.api.user.entity.UserPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<UserPo, String> {

    UserPo getUser(String userid);

    Page<UserPo> findUsers(Pageable pageable);
}
