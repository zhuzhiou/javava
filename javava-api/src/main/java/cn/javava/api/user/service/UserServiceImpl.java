package cn.javava.api.user.service;

import cn.javava.api.user.entity.UserPo;
import cn.javava.api.user.entity.UserPo_;
import cn.javava.api.user.repository.UserRepository;
import cn.javava.api.user.vo.UserCriteria;
import cn.javava.api.user.vo.UserVo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.replaceAll;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public UserVo getUser(String userid) {
        UserPo userPo = userRepository.getOne(userid);
        return mapperFacade.map(userPo, UserVo.class);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Page<UserVo> findUsers(UserCriteria criteria, Pageable pageable) {
        Specification specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (isNotBlank(criteria.getNicknameLike())) {
                Predicate predicate = cb.like(root.get(UserPo_.nickname), replaceAll(criteria.getNicknameLike(), "*", "%"));
                list.add(predicate);
            }
            if (isNotBlank(criteria.getSexEq())) {
                Predicate predicate = cb.equal(root.get(UserPo_.sex), criteria.getSexEq());
                list.add(predicate);
            }
            if (isNotBlank(criteria.getCountryEq())) {
                Predicate predicate = cb.equal(root.get(UserPo_.country), criteria.getCountryEq());
                list.add(predicate);
            }
            if (isNotBlank(criteria.getProvinceLike())) {
                Predicate predicate = cb.like(root.get(UserPo_.province), replaceAll(criteria.getProvinceLike(), "*", "%"));
                list.add(predicate);
            }
            if (isNotBlank(criteria.getCityLike())) {
                Predicate predicate = cb.like(root.get(UserPo_.city), replaceAll(criteria.getCityLike(), "*", "%"));
                list.add(predicate);
            }
            Predicate[] predicates = new Predicate[list.size()];
			//return cb.and(predicates);
            return query.where(list.toArray(predicates)).getRestriction();
        };

        Page<UserPo> page = userRepository.findAll(specification, pageable);
        List<UserVo> list = new ArrayList<>();
        UserVo vo;
        for (UserPo po : page.getContent()) {
            vo = mapperFacade.map(po, UserVo.class);
            list.add(vo);
        }
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }
}
