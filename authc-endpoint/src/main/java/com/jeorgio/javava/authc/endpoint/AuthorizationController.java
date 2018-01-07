package com.jeorgio.javava.authc.endpoint;

import com.jeorgio.javava.authc.service.UserIdentificationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "微信网页授权", description = "通过微信网页授权机制，来获取用户基本信息。")
@RestController
public class AuthorizationController {

    @Autowired
    private UserIdentificationService userIdentificationService;

    @GetMapping("/authz")
    public ResponseEntity<?> doGet(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        userIdentificationService.identify(code, state);
        //identify方法为异步调用，所以返回都很快。
        return ResponseEntity.ok("OK");
    }
}
