package com.dice.Weather_Report.model;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

@Data
@MappedSuperclass   // this class is base class for entities and mapped super class
public class AuditStamp
{

    @CreationTimestamp   // entity creation time stamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @CreatedBy             // capture user who created entity
    private String createdBy;


    @LastModifiedBy
    private String updatedBy;

    @Version                    // optimistic lock to handle concurrent updates on entities
    private int version;

}