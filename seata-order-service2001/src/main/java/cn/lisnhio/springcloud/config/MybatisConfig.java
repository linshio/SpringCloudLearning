package cn.lisnhio.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.lisnhio.springcloud.dao")
public class MybatisConfig {
}
