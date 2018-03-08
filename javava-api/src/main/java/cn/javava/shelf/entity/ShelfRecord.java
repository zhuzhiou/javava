package cn.javava.shelf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * create by wlrllr 2018/3/5
 * 记录操作记录
 */
@Table(name = "jww_shelf_record")
@Entity
@lombok.Data
public class ShelfRecord {
    @Id
    @Column(name="ID")
    private Long id;
    /**
     * 货架ID
     */
    @Column(name="SHELF_ID")
    private Long shelfId;
    /**
     * 格口ID
     */
    @Column(name="BOX_ID")
    private Long boxId;
    /**
     * 操作人
     */
    @Column(name="OPERATOR")
    private String operator;
    /**
     * 操作时间
     */
    @Column(name="OPERATE_TIME")
    private Date operateTime;
    /**
     * 操作类型
     */
    @Column(name="OPERATE_TYPE")
    private String operateType;

    /**
     * 操作结果
     */
    @Column(name="OPERATE_RESULT")
    private String operateResult;

    /**
     * 描述
     */
    @Column(name="REMARK",length=500)
    private String remark;
}
