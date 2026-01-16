package com.example.service;

import com.example.jms.dto.AuditMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.example.config.JmsConfig;

@Service
public class AuditService {

    private final JmsTemplate jmsTemplate;

    public AuditService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void logChange(String entity, Integer entityId, String action, String details) {
        AuditMessage msg = new AuditMessage(entity, entityId, action, details);
        jmsTemplate.convertAndSend(JmsConfig.AUDIT_TOPIC_NAME, msg);
    }
}
