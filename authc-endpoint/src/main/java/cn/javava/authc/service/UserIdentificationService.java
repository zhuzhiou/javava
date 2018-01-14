package cn.javava.authc.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface UserIdentificationService {

    @Async
    Future<String> identify(String code, String state);
}
