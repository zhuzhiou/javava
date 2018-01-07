package com.jeorgio.javava.authc.endpoint;

import com.jeorgio.javava.authc.service.UserIdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/authz")
@RestController
public class AuthorizationController {

    @Autowired
    private UserIdentificationService userIdentificationService;

    @GetMapping("")
    public ResponseEntity<?> doGet(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        userIdentificationService.identify(code, state);
        //identify方法为异步调用，所以返回都很快。
        return ResponseEntity.ok("OK");
    }
}
