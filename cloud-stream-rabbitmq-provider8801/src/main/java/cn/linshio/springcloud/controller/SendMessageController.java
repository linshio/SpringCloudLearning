package cn.linshio.springcloud.controller;

import cn.linshio.springcloud.service.impl.MessageProviderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {

    @Resource
    private MessageProviderImpl messageProvider;

    @GetMapping("/sendMessage")
    public String send(){
       return  messageProvider.send();
    }
}
