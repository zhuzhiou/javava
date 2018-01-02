package com.jeorgio.javava.authc.web;

import com.jeorgio.javava.authc.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/authorize")
@RestController
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("")
    public ResponseEntity<?> doGet(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");


        authorizationService.userRegister(code, state);

        return ResponseEntity.ok("OK");
    }
}
