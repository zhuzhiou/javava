package cn.javava.live.service;

import cn.javava.live.vo.LiveCriteria;
import cn.javava.live.vo.LiveVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LiveService {

    Page<LiveVo> findLives(LiveCriteria criteria, Pageable pageable);

}
