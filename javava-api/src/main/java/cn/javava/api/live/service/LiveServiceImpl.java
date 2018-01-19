package cn.javava.api.live.service;

import cn.javava.api.live.entity.LivePo;
import cn.javava.api.live.repository.LiveRepository;
import cn.javava.api.live.vo.LiveCriteria;
import cn.javava.api.live.vo.LiveVo;
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
public class LiveServiceImpl implements LiveService {

    @Autowired
    private LiveRepository liveRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Page<LiveVo> findLives(LiveCriteria criteria, Pageable pageable) {
        Specification specification = new LiveSpecification(criteria);
        Page<LivePo> page = liveRepository.findAll(specification, pageable);
        List<LiveVo> list = new ArrayList<>();
        LiveVo vo;
        for (LivePo po : page.getContent()) {
            vo = mapperFacade.map(po, LiveVo.class);
            list.add(vo);
        }
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

}