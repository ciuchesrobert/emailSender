package com.example.emailSender.services;

import com.example.emailSender.model.MailModel;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    void  sendEmail(MailModel model) throws MessagingException, IOException, TemplateException;
}
