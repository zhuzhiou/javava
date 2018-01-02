package com.jeorgio.javava.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 视频发布人
 */
@Table(name = "jww_live_publisher")
@Entity
@DynamicUpdate
public class LivePublisher implements Serializable {

    private String id;

    private String publishId;

    private String publishName;

    private LocalDateTime createTime;

    @Column(name = "ID")
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "PUBLISH_ID")
    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    @Column(name = "PUBLISH_NAME")
    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    @Column(name = "CREATE_TIME")
    //@Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
