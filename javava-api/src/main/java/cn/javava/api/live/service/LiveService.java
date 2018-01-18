package cn.javava.api.live.service;

import cn.javava.api.live.vo.LiveCriteria;
import cn.javava.api.live.vo.LiveVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LiveService {

    Page<LiveVo> findLives(LiveCriteria criteria, Pageable pageable);

}
