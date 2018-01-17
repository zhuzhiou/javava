package cn.javava.api.live.service;

import cn.javava.api.live.entity.LivePo;
import cn.javava.api.live.entity.LiveRoomPo;
import cn.javava.api.live.repository.LiveRepository;
import cn.javava.api.live.repository.LiveRoomRepository;
import cn.javava.api.live.vo.LiveCriteria;
import cn.javava.api.live.vo.LiveRoomCriteria;
import cn.javava.api.live.vo.LiveRoomVo;
import cn.javava.api.live.vo.LiveVo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LiveServiceImpl implements LiveService {

    @Autowired
    private LiveRepository liveRepository;

    @Autowired
    private LiveRoomRepository liveRoomRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Page<LiveVo> findLives(LiveCriteria criteria, Pageable pageable) {
        Page<LivePo> page = liveRepository.findAll(pageable);
        List<LiveVo> list = new ArrayList<>();
        LiveVo vo;
        for (LivePo po : page.getContent()) {
            vo = mapperFacade.map(po, LiveVo.class);
            list.add(vo);
        }
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Page<LiveRoomVo> findRooms(LiveRoomCriteria criteria, Pageable pageable) {
        Page<LiveRoomPo> page = liveRoomRepository.findAll(pageable);
        List<LiveRoomVo> list = new ArrayList<>();
        LiveRoomVo vo;
        for (LiveRoomPo po : page.getContent()) {
            vo = mapperFacade.map(po, LiveRoomVo.class);
            list.add(vo);
        }
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

}
