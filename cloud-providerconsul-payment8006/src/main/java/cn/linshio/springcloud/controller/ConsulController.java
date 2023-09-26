package cn.linshio.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ConsulController {
    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/payment/consul")
    public String paymentZk(){
        return "springCloud with consul "+serverPort+"\t"+ UUID.randomUUID();
    }
}
