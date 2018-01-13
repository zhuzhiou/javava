package cn.javava.user.service;

import com.fasterxml.uuid.impl.TimeBasedGenerator;
import cn.javava.user.entity.User;
import cn.javava.user.repository.UserRepository;
import cn.javava.user.vo.UserVo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.remove;

@Service
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
