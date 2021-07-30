package com.example.emailSender.controllers;


import com.example.emailSender.model.MailModel;
import com.example.emailSender.services.EmailService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import freemarker.template.TemplateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("api/v1/send")
    public ResponseEntity<?> sendEmail(@RequestBody MailModel mailModel){
        try {
            emailService.sendEmail(mailModel);
            return ResponseEntity.ok().body(mailModel.toString());
        } catch (TemplateException | IOException | javax.mail.MessagingException e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}
