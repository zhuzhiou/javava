package cn.javava.live.service;

import cn.javava.live.dto.StreamCriteria;
import cn.javava.live.entity.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StreamService {

    Page<Stream> findStreams(StreamCriteria criteria, Pageable pageable);

}
