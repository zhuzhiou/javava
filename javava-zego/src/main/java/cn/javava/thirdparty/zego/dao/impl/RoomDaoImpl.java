package cn.javava.thirdparty.zego.dao.impl;

import cn.javava.thirdparty.zego.dao.RoomDao;
import cn.javava.thirdparty.zego.vo.CloseStreamVo;
import cn.javava.thirdparty.zego.vo.OpenStreamVo;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static org.apache.commons.lang3.StringUtils.remove;

@Repository
public class RoomDaoImpl implements RoomDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Override
    public int count(OpenStreamVo vo) {
        int rows = jdbcTemplate.queryForObject(
                "select count(1) from jww_live_room where CHANNEL_ID = ?",
                Integer.class,
                vo.getChannelId()
        );
        return rows;
    }

    @Override
    public int insert(OpenStreamVo vo) {
        int rows = jdbcTemplate.update(
                "insert into jww_live_room(ID, CHANNEL_ID, STATE, CREATE_DATE) values(?, ?, ?, now())",
                (pss) -> {
                    pss.setString(1, remove(timeBasedGenerator.generate().toString(), "-"));
                    pss.setString(2, vo.getChannelId());
                    pss.setString(3, "DeviceOnline");
                }
        );
        return rows;
    }

    @Override
    public int update(OpenStreamVo vo) {
        int rows = jdbcTemplate.update(
                "update jww_live_room set STATE = 'DeviceOnline' where CHANNEL_ID = ?",
                vo.getChannelId()
        );
        return rows;
    }

    @Override
    public int count(CloseStreamVo vo) {
        int rows = jdbcTemplate.queryForObject(
                "select count(1) from jww_live_room where CHANNEL_ID = ?",
                Integer.class,
                vo.getChannelId()
        );
        return rows;
    }

    @Override
    public int insert(CloseStreamVo vo) {
        int rows = jdbcTemplate.update(
                "insert into jww_live_room(ID, CHANNEL_ID, STATE) values(?, ?, ?)",
                (pss) -> {
                    pss.setString(1, remove(timeBasedGenerator.generate().toString(), "-"));
                    pss.setString(2, vo.getChannelId());
                    pss.setString(3, "DeviceOffline");
                }
        );
        return rows;
    }

    @Override
    public int update(CloseStreamVo vo) {
        int rows = jdbcTemplate.update(
                "update jww_live_room set STATE = 'DeviceOffline' where CHANNEL_ID = ?",
                vo.getChannelId()
        );
        return rows;
    }
}
