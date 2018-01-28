package cn.javava.thirdparty.zego.service.impl;

import cn.javava.thirdparty.zego.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.split;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${javava.notification.consignees}")
    private String consignees;

    @Value("${spring.mail.username}")
    private String consignor;

    @Override
    public void sendMail(String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setFrom(consignor);
        message.setTo(split(consignees, ","));
        message.setText(content);
        javaMailSender.send(message);
    }
}
