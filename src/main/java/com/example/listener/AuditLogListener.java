package com.example.listener;

import com.example.jms.dto.AuditMessage;
import com.example.model.AuditLog;
import com.example.repository.AuditLogRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AuditLogListener {

    private final AuditLogRepository repository;

    public AuditLogListener(AuditLogRepository repository) {
        this.repository = repository;
    }

    @JmsListener(destination = "auditQueue")
    public void receive(AuditMessage msg) {

        AuditLog log = new AuditLog();
        log.setEntity(msg.getEntity());
        log.setEntityId(msg.getEntityId());
        log.setAction(msg.getAction());
        log.setDetails(msg.getDetails());
        log.setCreatedAt(java.time.LocalDateTime.now());

        repository.save(log);
    }
}
