package com.hridoykrisna.me.BDNews.project.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(updatable = false)
    private int createdBy;
    @Column(updatable = false)
    private Date createdAt;
    private int updateBy;
    private Date updateAt;
    private Boolean isActive;

    @PrePersist
    public void setPreInsertData() {
        this.createdAt = new Date();
        System.out.println(createdAt);
        this.isActive = true;
    }

    @PreUpdate
    public void setPostUpdateData() {
        this.updateAt = new Date();
    }
}
