package com.jeorgio.javava.authc.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface AuthorizationService {

    @Async
    Future<String> userRegister(String code, String state);
}
