package cn.javava.weixin.component.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;
import static org.apache.commons.lang3.StringUtils.join;

@RequestMapping("/authorization_events")
@RestController
public class AuthorizationEventsController {

    private String token = "hlUmF2UCfEpMYTqjiyBH9ko3lQLqP";

    /**
     * signature: 6cce794238f8ee3282e4f9445acfdea4f4a4a66b
     * timestamp: 1521038383
     * nonce: 1750984517
     * encrypt_type: aes
     * msg_signature: 5173c0bc6741f0e0d43458a82fd96d3df9a6feaa
     * <xml>
     * <AppId><![CDATA[wx72013888560db55f]]></AppId>
     * <Encrypt><![CDATA[qLB1HPxnzqfGVL/0yHfYf5LErhKgu5cWkXA5YiNfIoKjvwU/JH+MEQRYvn8zBXMB0XzV7y4GRKG3pJdvmm5aO1JrU193Lqb/abS1j0uvspxEMeeaiHeh11G8g63zbzLNibdza8aC0nza8MHCaSCcDHI0yGnZ2cGNcfMO5uRGnqQsr9TS22xa77tVfTqc8OPc0RgySUAvPxoCPXp6/9HDjjjG2fSrHOevKCAZZRh9kDAJlugCGlXhkfWbGC7Wbn6QiJSPRCznsNipreaoaKFz1tqrnnOX/5GoFvxVaNSo/2dZCEYn2DrXxj+Oc2T/R+qDr1ron9BH/uyY/gxKRTzkFFDPdvp7pkzgaBTxYLHLm4f+E6gFPIroVojsOV5X62/GHEdkjF0ZXonzUZ1lXTq1pQ6/4z7adJWSl1HN38eeUzel9iK6BB5anNXbY3rOOzlMLz0Y3dqU8DpP+GTDma1QTA==]]></Encrypt>
     * </xml>
     */
    @PostMapping(consumes = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String doPost(HttpServletRequest request) throws IOException, XMLStreamException {
        //String signature = request.getParameter("signature"); 不知道用途
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        //String encrypt_type = request.getParameter("encrypt_type");
        String msg_signature = request.getParameter("msg_signature");

        Map<String, String> msg_encrypt = parseMsg(request);
        String[] params = new String[] {token, timestamp, nonce, msg_encrypt.get("Encrypt")};
        Arrays.sort(params);
        String dev_msg_signature = sha1Hex(join(params, ""));
        if (!StringUtils.equals(dev_msg_signature, msg_signature)) {
            return "";
        }
        //解密并存储到redis
        return "success";
    }

    @Autowired
    private XMLInputFactory xmlInputFactory;

    private Map<String, String> parseMsg(HttpServletRequest request) throws XMLStreamException, IOException {
        Map<String, String> hashMap = new HashMap<>();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(request.getInputStream(), "UTF-8");
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                String tagName = event.asStartElement().getName().toString();
                if(!"xml".equals(tagName)) {
                    hashMap.put(tagName, reader.getElementText());
                }
            }
        }
        return hashMap;
    }
}
