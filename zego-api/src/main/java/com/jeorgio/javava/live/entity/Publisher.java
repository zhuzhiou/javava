package com.jeorgio.javava.live.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 发布人
 */
@Table(name = "jww_live_publisher")
@Entity
@DynamicUpdate
@Data
public class Publisher implements Serializable {

    @Column(name = "ID")
    @Id
    private String id;

    @Column(name = "PUBLISH_ID")
    private String publishId;

    @Column(name = "PUBLISH_NAME")
    private String publishName;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
}
