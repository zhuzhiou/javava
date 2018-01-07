package com.jeorgio.javava.thirdparty.zego.service;

import org.springframework.scheduling.annotation.Async;

public interface NotificationService {

    @Async
    void sendMail(String subject, String content);

}
