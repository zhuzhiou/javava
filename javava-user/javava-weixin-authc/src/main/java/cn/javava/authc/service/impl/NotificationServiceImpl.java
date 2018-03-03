package cn.javava.authc.service.impl;

import cn.javava.authc.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${javava.notification.email}")
    private String email;

    @Value("${spring.mail.username}")
    private String consignor;

    @Override
    public void sendMail(String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setFrom(consignor);
        message.setTo(email);
        message.setText(content);
        javaMailSender.send(message);
    }
}
