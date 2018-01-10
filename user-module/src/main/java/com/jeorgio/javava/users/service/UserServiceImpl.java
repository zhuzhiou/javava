package com.jeorgio.javava.users.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.jeorgio.javava.users.entity.User;
import com.jeorgio.javava.users.repository.UserRepository;
import com.jeorgio.javava.users.vo.UserVo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.remove;

@Service(version = "1.0")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void save(UserVo userVo) {
        User user = userRepository.getUserByOpenid(userVo.getOpenid());
        if (user == null) {
            user = mapperFacade.map(userVo, User.class);
            user.setId(remove(timeBasedGenerator.generate().toString(), "-"));
        } else {
            mapperFacade.map(userVo, user);
        }
        userRepository.save(user);
    }
}
