package cn.javava.authc.endpoint;

import cn.javava.authc.service.UserIdentificationService;
import cn.javava.authc.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @Value("${javava.jwt.secret}")
    private String jwtSecret;

    @Autowired
    private UserIdentificationService userIdentificationService;

    @GetMapping(path = "/scanQRCodeComplete")
    public String scanQRCode_complete(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String state,
            Model model
    ) {
        UserVo userVo = userIdentificationService.identify(code, state);
        model.addAttribute("userVo", userVo);
        return "authc";
    }


}
