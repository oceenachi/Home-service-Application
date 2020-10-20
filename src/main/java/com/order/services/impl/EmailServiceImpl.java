package com.order.services.impl;

import com.order.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${app.email}")
    private String adminEmail;

    @Override
    public void sendEmail(String requesterName, String serviceType, String resumptionTime) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("New Service Request");
        message.setText(String.format("Hello admin, new request from %s. \n Request type %s, resumption time %s", requesterName, serviceType, resumptionTime ));

        javaMailSender.send(message);
    }

}
