package com.example.emailSender.services;

import com.example.emailSender.model.MailModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender emailSender;
    private final Configuration emailConfig;

    public EmailServiceImpl(JavaMailSender emailSender, @Qualifier(value = "emailConfigBean") Configuration emailConfig) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    @Override
    public void sendEmail(MailModel mailModel) throws MessagingException, IOException, TemplateException {
        Map model = new HashMap();
        model.put("firstName", mailModel.getFirstName());
        model.put("lastName", mailModel.getLastName());
        model.put("location", mailModel.getLocation());
        model.put("year", String.valueOf(mailModel.getYear()));
        model.put("name", mailModel.getName());
        model.put("signature", "Bogdan Gal");
        model.put("content", mailModel.getContent());

        mailModel.setModel(model);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Template template = emailConfig.getTemplate("email.ftl.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getModel());

        mimeMessageHelper.setTo(mailModel.getTo());
        mimeMessageHelper.setFrom(mailModel.getFrom());
        mimeMessageHelper.setSubject(mailModel.getSubject());
        mimeMessageHelper.setText(html, true);

        emailSender.send(message);

    }
}