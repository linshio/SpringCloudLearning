package cn.linshio.springcloud.controller;

import cn.linshio.springcloud.entities.CommonResult;
import cn.linshio.springcloud.entities.Payment;
import cn.linshio.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        return paymentFeignService.getPaymentById(id);
    }

    //测试openFeign超时3秒，openFeign超时一秒就会报错
    @GetMapping("/consumer/payment/timeout")
    public String openFeignTimeout(){
        return paymentFeignService.openFeignTimeout();
    }
}
