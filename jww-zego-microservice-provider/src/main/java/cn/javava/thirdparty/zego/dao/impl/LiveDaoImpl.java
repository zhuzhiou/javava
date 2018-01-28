package cn.javava.thirdparty.zego.dao.impl;

import cn.javava.thirdparty.zego.dao.LiveDao;
import cn.javava.thirdparty.zego.vo.CloseLiveVo;
import cn.javava.thirdparty.zego.vo.OpenLiveVo;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static org.apache.commons.lang3.ArrayUtils.getLength;
import static org.apache.commons.lang3.StringUtils.remove;

@Repository
public class LiveDaoImpl implements LiveDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Override
    public int create(OpenLiveVo vo) {
        int rows = jdbcTemplate.update(
                "insert into jww_live(ID, CHANNEL_ID, STREAM_ALIAS, PUBLISH_ID, PUBLISH_NAME, RTMP_URLS, HLS_URLS, HDL_URLS, PIC_URLS, CREATE_DATE) values(?, ?, ?, ?, ?, ?, ?, ?, ?, now())",
                (pss) -> {
                    pss.setString(1, remove(timeBasedGenerator.generate().toString(), "-"));
                    pss.setString(2, vo.getChannelId());
                    pss.setString(3, vo.getStreamAlias());
                    pss.setString(4, vo.getPublishId());
                    pss.setString(5, vo.getPublishName());
                    pss.setInt(6, getLength(vo.getRtmpUrl()));
                    pss.setInt(7, getLength(vo.getHdlUrl()));
                    pss.setInt(8, getLength(vo.getHlsUrl()));
                    pss.setInt(9, getLength(vo.getPicUrl()));
                    //pss.setTimestamp(10, Timestamp.from(ofEpochMilli(vo.getCreateTime()*1000)));
                }
        );
        return rows;
    }

    @Override
    public int update(CloseLiveVo vo) {
        int rows = jdbcTemplate.update(
                "update jww_live set CLOSE_DATE = now(), CLOSE_REASON = ? where ROOM_ID = ? and STREAM_ALIAS = ? and (CLOSE_REASON is null or CLOSE_REASON = '')",
                (pss) -> {
                    pss.setString(1, String.valueOf(vo.getType()));
                    pss.setString(2, vo.getChannelId());
                    pss.setString(3, vo.getStreamAlias());
                }
        );
        return rows;
    }
}
