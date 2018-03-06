package cn.javava.shelf.service.impl;

import cn.javava.shelf.constant.ShelfConstants;
import cn.javava.shelf.entity.ShelfBox;
import cn.javava.shelf.repository.ShelfBoxRepository;
import cn.javava.shelf.service.ShelfBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wlrllr on 2018/3/5.
 */
@Service
public class ShelfBoxServiceImpl implements ShelfBoxService {

    @Autowired
    private ShelfBoxRepository shelfBoxRepository;

    @Override
    public int online(int boxNum, Long shelfId) {
        //查询是否已经存在box
        ShelfBox box = new ShelfBox();
        box.setShelfId(shelfId);
        List<ShelfBox> list = shelfBoxRepository.findAll(Example.of(box));
        if (list == null || list.size() <= 0) {
            list = ShelfBox.create(boxNum, shelfId);
        } else {
            for (ShelfBox b : list) {
                if (ShelfConstants.BOX_STATUS_NORMAL.equals(b.getStatus()))
                    b.setIsAvailable(ShelfConstants.COMMON_Y);
            }
        }
        List<ShelfBox> boxes = shelfBoxRepository.save(list);
        return boxes != null ? boxes.size() : 0;
    }

    @Override
    public int offline(Long shelfId) {
        ShelfBox box = new ShelfBox();
        box.setShelfId(shelfId);
        List<ShelfBox> list = shelfBoxRepository.findAll(Example.of(box));
        if (list != null && list.size() > 0) {
            for (ShelfBox b : list) {
                b.setIsAvailable(ShelfConstants.COMMON_N);
            }
            List<ShelfBox> boxes = shelfBoxRepository.save(list);
            return boxes != null ? boxes.size() : 0;
        }
        return 0;
    }

    @Override
    public void deliver(Long boxId) {
        ShelfBox box = shelfBoxRepository.findOne(boxId);
        box.setIsAvailable(ShelfConstants.COMMON_N);
        shelfBoxRepository.saveAndFlush(box);
    }

    @Override
    public void restock(Long boxId) {
        ShelfBox box = shelfBoxRepository.findOne(boxId);
        box.setIsAvailable(ShelfConstants.COMMON_Y);
        shelfBoxRepository.saveAndFlush(box);
    }

    @Override
    public ShelfBox updateStatus(Long boxId, String status) {
        ShelfBox box = shelfBoxRepository.findOne(boxId);
        box.setStatus(status);
        return shelfBoxRepository.save(box);
    }

    @Override
    public List<ShelfBox> findByShelfId(Long shelfId) {
        return shelfBoxRepository.findByShelfId(shelfId);
    }
}
