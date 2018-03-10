package cn.javava.weixin.authc.dao.impl;

import cn.javava.weixin.authc.dao.UserDao;
import cn.javava.weixin.authc.vo.UserVo;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static org.apache.commons.lang3.StringUtils.remove;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Override
    public int save(UserVo userVo) {
        return jdbcTemplate.update(
                "insert into jww_user(USER_ID, OPENID, NICKNAME, SEX, PROVINCE, CITY, COUNTRY, HEADIMGURL, UNIONID, CREATE_DATE, UPDATE_COUNT) values(?, ?, ?, ?, ?, ?, ?, ?, ?, now(), 0) on duplicate key update NICKNAME = values(NICKNAME), SEX = values(SEX), PROVINCE = values(PROVINCE), CITY = values(CITY), COUNTRY = values(COUNTRY), HEADIMGURL = values(HEADIMGURL), UNIONID = values(UNIONID), UPDATE_COUNT = UPDATE_COUNT + 1, LAST_UPDATE_DATE = now()",
                (pss) -> {
                    pss.setString(1, remove(timeBasedGenerator.generate().toString(), "-"));
                    pss.setString(2, userVo.getOpenid());
                    pss.setString(3, userVo.getNickname());
                    pss.setString(4, userVo.getSex());
                    pss.setString(5, userVo.getProvince());
                    pss.setString(6, userVo.getCity());
                    pss.setString(7, userVo.getCountry());
                    pss.setString(8, userVo.getHeadimgurl());
                    pss.setString(9, userVo.getUnionid());
                }
        );
    }
}
