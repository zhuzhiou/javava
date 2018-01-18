package cn.javava.api.live.service;

import cn.javava.api.live.entity.LiveRoomPo;
import cn.javava.api.live.repository.LiveRoomRepository;
import cn.javava.api.live.vo.LiveRoomCriteria;
import cn.javava.api.live.vo.LiveRoomVo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LiveRoomServiceImpl implements LiveRoomService {

    @Autowired
    private LiveRoomRepository liveRoomRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Page<LiveRoomVo> findLiveRooms(LiveRoomCriteria criteria, Pageable pageable) {
        Specification specification = new LiveRoomSpecification(criteria);
        Page<LiveRoomPo> page = liveRoomRepository.findAll(specification, pageable);
        List<LiveRoomVo> list = new ArrayList<>();
        LiveRoomVo vo;
        for (LiveRoomPo po : page.getContent()) {
            vo = mapperFacade.map(po, LiveRoomVo.class);
            list.add(vo);
        }
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

}
