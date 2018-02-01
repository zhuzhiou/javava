package cn.javava.thirdparty.zego.dao.impl;

import cn.javava.thirdparty.zego.dao.LiveDao;
import cn.javava.thirdparty.zego.vo.CloseLiveVo;
import cn.javava.thirdparty.zego.vo.OpenLiveVo;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.google.common.primitives.Ints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;

import static org.apache.commons.lang3.ArrayUtils.getLength;
import static org.apache.commons.lang3.StringUtils.remove;

@Repository
public class LiveDaoImpl implements LiveDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Override
    public void create(OpenLiveVo vo) {
        int maximum = Ints.max(getLength(vo.getRtmpUrl()), getLength(vo.getHdlUrl()), getLength(vo.getHlsUrl()), getLength(vo.getPicUrl()));
        for (int i = 0; i < maximum; i++) {
            final int j = i;
            jdbcTemplate.update(
                    "insert into jww_live(ID, LIVE_ID, CHANNEL_ID, STREAM_ALIAS, PUBLISH_ID, PUBLISH_NAME, RTMP_URL, HLS_URL, HDL_URL, PIC_URL, CREATE_DATE) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())",
                    (PreparedStatement ps) -> {
                        ps.setString(1, remove(timeBasedGenerator.generate().toString(), "-"));
                        ps.setString(2, vo.getLiveId());
                        ps.setString(3, vo.getChannelId());
                        ps.setString(4, vo.getStreamAlias());
                        ps.setString(5, vo.getPublishId());
                        ps.setString(6, vo.getPublishName());
                        if (getLength(vo.getRtmpUrl()) > j) {
                            ps.setString(7, vo.getRtmpUrl()[j]);
                        } else {
                            ps.setNull(7, Types.VARCHAR);
                        }
                        if (getLength(vo.getHdlUrl()) > j) {
                            ps.setString(8, vo.getHdlUrl()[j]);
                        } else {
                            ps.setNull(8, Types.VARCHAR);
                        }
                        if (getLength(vo.getHlsUrl()) > j) {
                            ps.setString(9, vo.getHlsUrl()[j]);
                        } else {
                            ps.setNull(9, Types.VARCHAR);
                        }
                        if (getLength(vo.getPicUrl()) > j) {
                            ps.setString(10, vo.getPicUrl()[j]);
                        } else {
                            ps.setNull(10, Types.VARCHAR);
                        }
                        //ps.setTimestamp(10, Timestamp.from(ofEpochMilli(vo.getCreateTime()*1000)));
                    }
            );
        }
    }

    @Override
    public void update(CloseLiveVo vo) {
        jdbcTemplate.update(
                "update jww_live set CLOSE_DATE = now(), CLOSE_REASON = ? where CHANNEL_ID = ? and STREAM_ALIAS = ? and (CLOSE_REASON is null or CLOSE_REASON = '')",
                (pss) -> {
                    pss.setString(1, String.valueOf(vo.getType()));
                    pss.setString(2, vo.getChannelId());
                    pss.setString(3, vo.getStreamAlias());
                }
        );
    }
}
