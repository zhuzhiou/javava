package com.jeorgio.javava.authc.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface AuthorizationService {

    @Async
    Future<String> registerIfNecessary(String code, String state);
}
