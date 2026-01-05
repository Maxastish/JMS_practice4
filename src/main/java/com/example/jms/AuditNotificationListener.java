package com.example.jms;

import com.example.jms.dto.AuditMessage;
import com.example.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AuditNotificationListener {

    private final EmailService emailService;

    public AuditNotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(destination = "auditQueue")
    public void notify(AuditMessage msg) {

        if (!msg.getEntity().equals("Student")) return;
        if (!msg.getAction().equals("DELETE")) return;

        emailService.sendEmail(
                "kpuk8956@gmail.com",
                "Student changed",
                "Student ID: " + msg.getEntityId() + "\n" + msg.getDetails()
        );
    }
}