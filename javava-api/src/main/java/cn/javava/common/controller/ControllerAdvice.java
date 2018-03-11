package cn.javava.common.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception exception) {
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        builder.put("code", 1);
        builder.put("message", exception.getMessage());
        return builder.build();
    }

    public Map<String, Object> handleOAuth2Exception(OAuth2Exception e) {
        return null;
    }
}
