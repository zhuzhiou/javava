package cn.javava.thirdparty.zego.dao.impl;

import cn.javava.thirdparty.zego.dao.StreamHisDao;
import cn.javava.thirdparty.zego.vo.CloseStreamVo;
import com.google.common.primitives.Longs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.Instant;

@Repository
public class StreamHisDaoImpl implements StreamHisDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(CloseStreamVo vo) {
        jdbcTemplate.update(
                "insert into jww_live_stream_his(ID, CHANNEL_ID, STREAM_ALIAS, PUBLISH_ID, PUBLISH_NAME, RTMP_URL, HLS_URL, HDL_URL, PIC_URL, CREATE_DATE, CLOSE_DATE, CLOSE_REASON) select ID, CHANNEL_ID, STREAM_ALIAS, PUBLISH_ID, PUBLISH_NAME, RTMP_URL, HLS_URL, HDL_URL, PIC_URL, CREATE_DATE, ?, ? from jww_live_stream where CHANNEL_ID = ? and STREAM_ALIAS = ?",
                (PreparedStatement ps) -> {
                    ps.setTimestamp(1, Timestamp.from(Instant.ofEpochSecond(Longs.tryParse(vo.getTimestamp()))));
                    ps.setString(2, String.valueOf(vo.getType()));
                    ps.setString(3, vo.getChannelId());
                    ps.setString(4, vo.getStreamAlias());
                }
        );
    }
}
