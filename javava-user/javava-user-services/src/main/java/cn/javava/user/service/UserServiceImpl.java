package cn.javava.user.service;

import cn.javava.user.dto.UserCriteria;
import cn.javava.user.entity.User;
import cn.javava.user.entity.User_;
import cn.javava.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public User getUser(String userid) {
        User user = userRepository.getOne(userid);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Page<User> getUsers(UserCriteria criteria, Pageable pageable) {
        Specification<User> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (isNotBlank(criteria.getNickname())) {
                Predicate predicate = cb.equal(root.get(User_.nickname), criteria.getNickname());
                list.add(predicate);
            }
            if (isNotBlank(criteria.getSex())) {
                Predicate predicate = cb.equal(root.get(User_.sex), criteria.getSex());
                list.add(predicate);
            }
            if (isNotBlank(criteria.getCountry())) {
                Predicate predicate = cb.equal(root.get(User_.country), criteria.getCountry());
                list.add(predicate);
            }
            if (isNotBlank(criteria.getProvince())) {
                Predicate predicate = cb.equal(root.get(User_.province), criteria.getProvince());
                list.add(predicate);
            }
            if (isNotBlank(criteria.getCity())) {
                Predicate predicate = cb.equal(root.get(User_.city), criteria.getCity());
                list.add(predicate);
            }
            Predicate[] predicates = new Predicate[list.size()];
            return query.where(list.toArray(predicates)).getRestriction();
        };

        Page<User> page = userRepository.findAll(specification, pageable);
        return page;
    }
}
