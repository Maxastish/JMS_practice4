package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String entity; // имя сущности: Student, Course, StudentCourse

    @Column(nullable = false)
    private Integer entityId; // id сущности

    @Column(nullable = false)
    private String action; // INSERT, UPDATE, DELETE

    @Column(columnDefinition = "TEXT")
    private String details; // JSON или текст с изменениями

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public AuditLog() {}

    public AuditLog(String entity, Integer entityId, String action, String details) {
        this.entity = entity;
        this.entityId = entityId;
        this.action = action;
        this.details = details;
        this.createdAt = LocalDateTime.now();
    }

    // геттеры/сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
