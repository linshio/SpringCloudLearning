package cn.linshio.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    //开启Feign的详细日志
    @Bean
    public Logger.Level getLogger(){
        return Logger.Level.FULL;
    }
}
