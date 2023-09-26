package cn.linshio.springcloud.service;

import org.springframework.stereotype.Component;

@Component
//服务降级逻辑 写在实现业务接口的类里面 降低耦合
public class PaymentServiceFallback implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-------CLOUD-PROVIDER-HYSTRIX-PAYMENT 服务宕机"+"\t"+"paymentInfo_OK";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "-------CLOUD-PROVIDER-HYSTRIX-PAYMENT 服务宕机"+"\t"+"paymentInfo_timeout";
    }
}
