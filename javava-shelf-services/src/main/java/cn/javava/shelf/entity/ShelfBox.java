package cn.javava.shelf.entity;

import cn.javava.shelf.constant.ShelfConstants;
import com.fasterxml.uuid.Generators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * create by wlrllr 2018/3/5
 * 记录货架格口信息
 */
@Table(name = "jww_shelf_box")
@Entity
@lombok.Data
public class ShelfBox {

    @Id
    @Column(name="ID")
    private String id;
    /**
     * 货架ID
     */
    @Column(name="SHELF_ID")
    private String shelfId;
    /**
     * 格口名字
     */
    @Column(name="NAME")
    private String name;
    /**
     * 格口位置
     */
    @Column(name="LOCATION")
    private Integer location;
    /**
     * 商品ID
     */
    @Column(name="GOODS_ID")
    private String goodsId;
    /**
     * 是否可用Y/N
     */
    @Column(name="IS_AVAILABLE")
    private String isAvailable;

    public List<ShelfBox> create(int num, String shelfId){
        List<ShelfBox> bins = new ArrayList<>();
        for(int i=1;i<=num;i++){
            bins.add(new ShelfBox(i,shelfId));
        }
        return bins;
    }

    public ShelfBox(int column, String shelfId) {
        this.id = Generators.timeBasedGenerator().generate().toString();
        this.shelfId = shelfId;
        this.isAvailable= ShelfConstants.COMMON_Y;
        this.location= column;
        this.name=String.format("%03d",column);
    }

    public ShelfBox() {

    }
}
