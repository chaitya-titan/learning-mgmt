package com.crio.learning_system.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    String id;

    @CreatedDate()
    @Column(name = "created_at", updatable = false)
    Date createdAt;
}
