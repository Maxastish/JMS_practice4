package com.example.jms.dto;

import java.io.Serializable;

public class AuditMessage implements Serializable {

    private String entity;
    private Integer entityId;
    private String action;
    private String details;

    public AuditMessage() {}

    public AuditMessage(String entity, Integer entityId, String action, String details) {
        this.entity = entity;
        this.entityId = entityId;
        this.action = action;
        this.details = details;
    }

    public String getEntity() { return entity; }
    public Integer getEntityId() { return entityId; }
    public String getAction() { return action; }
    public String getDetails() { return details; }
}
