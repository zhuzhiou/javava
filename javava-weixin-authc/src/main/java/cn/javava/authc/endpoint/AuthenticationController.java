package cn.javava.authc.endpoint;

import cn.javava.authc.service.UserIdentificationService;
import cn.javava.authc.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserIdentificationService userIdentificationService;

    @GetMapping(path = "/scanQRCodeComplete")
    public String scanQRCode_complete(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state", required = false) String state,
            Model model
    ) {
        UserVo userVo = userIdentificationService.identify(code, state);
        if (userVo == null) {
            return "error";
        }
        String userJson;
        try {
            userJson = objectMapper.writeValueAsString(userVo);
        } catch (JsonProcessingException e) {
            return "error";
        }
        model.addAttribute("userJson", userJson);
        return "authc";
    }

}
