package cn.javava.live.controller;

import cn.javava.common.wrap.PageWrapper;
import cn.javava.live.dto.StreamCriteria;
import cn.javava.live.entity.Stream;
import cn.javava.live.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/streams")
@RestController
public class StreamController {

    @Autowired
    private StreamService streamService;

    @GetMapping
    public PageWrapper<Stream> getStreams(StreamCriteria criteria, @PageableDefault Pageable pageable) {
        Page<Stream> page = streamService.findStreams(criteria, pageable);
        PageWrapper<Stream> wrapper = new PageWrapper<>(0, "成功", page);
        return wrapper;
    }

}
