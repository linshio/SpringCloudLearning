package cn.linshio.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String configInfo()
    {
        return "serverPort: "+serverPort+"\t\n\n configInfo: "+configInfo;
    }
//    @Value("${config.info}")
//    private String configInfo;
//
//    @GetMapping("/configInfo")
//    public String getConfigInfo(){
//        return configInfo;
//    }
    //docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 -v /root/dockerInstanceConfig/rabbitmq:/var/lib/rabbitmq rabbitmq
}
