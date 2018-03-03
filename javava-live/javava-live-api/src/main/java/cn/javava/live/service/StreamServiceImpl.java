package cn.javava.live.service;

import cn.javava.live.dto.StreamCriteria;
import cn.javava.live.entity.Stream;
import cn.javava.live.repository.StreamRepository;
import cn.javava.live.specification.StreamSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StreamServiceImpl implements StreamService {

    @Autowired
    private StreamRepository streamRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Page<Stream> findStreams(StreamCriteria criteria, Pageable pageable) {
        Specification specification = new StreamSpecification(criteria);
        Page<Stream> page = streamRepository.findAll(specification, pageable);
        return page;
    }

}
