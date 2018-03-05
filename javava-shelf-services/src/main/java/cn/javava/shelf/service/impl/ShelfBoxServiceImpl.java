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
    public void online(int boxNum, String shelfId) {
        //查询是否已经存在box
        ShelfBox box = new ShelfBox();
        box.setShelfId(shelfId);
        List<ShelfBox> list = shelfBoxRepository.findAll(Example.of(box));
        if (list == null || list.size() <= 0) {
            list = new ShelfBox().create(boxNum, shelfId);
        } else {
            for (ShelfBox b : list) {
                b.setIsAvailable(ShelfConstants.COMMON_Y);
            }
        }
        shelfBoxRepository.save(list);
    }

    @Override
    public void offline(String shelfId) {
        ShelfBox box = new ShelfBox();
        box.setShelfId(shelfId);
        List<ShelfBox> list = shelfBoxRepository.findAll(Example.of(box));
        if (list != null && list.size() > 0) {
            for (ShelfBox b : list) {
                b.setIsAvailable(ShelfConstants.COMMON_N);
            }
            shelfBoxRepository.save(list);
        }
    }

    @Override
    public void deliver(String boxId) {
        updateStatus(boxId,ShelfConstants.COMMON_N);
    }

    @Override
    public void restock(String boxId) {
        updateStatus(boxId,ShelfConstants.COMMON_Y);
    }

    @Override
    public void updateStatus(String boxId,String isAvailable) {
        ShelfBox box = shelfBoxRepository.findOne(boxId);
        box.setIsAvailable(isAvailable);
        shelfBoxRepository.saveAndFlush(box);
    }
}
