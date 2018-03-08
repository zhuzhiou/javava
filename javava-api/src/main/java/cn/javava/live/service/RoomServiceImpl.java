package cn.javava.live.service;

import cn.javava.live.dto.RoomCriteria;
import cn.javava.live.entity.Room;
import cn.javava.live.repository.RoomRepository;
import cn.javava.live.specification.RoomSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Page<Room> findRooms(RoomCriteria criteria, Pageable pageable) {
        Specification specification = new RoomSpecification(criteria);
        Page<Room> page = roomRepository.findAll(specification, pageable);
        return page;
    }

}
