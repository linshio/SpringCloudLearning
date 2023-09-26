package cn.linshio.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/payment/zk")
    public String paymentZk(){
        return "springCloud with zookeeper "+serverPort+"\t"+ UUID.randomUUID();
    }

    /**
     * docker run -d --name zookeeper --privileged=true -p 2181:2181  -v /root/zookeeper/data:/data -v /root/zookeeper/conf:/conf -v /root/zookeeper/logs:/datalog zookeeper:3.5.7
     */
}
