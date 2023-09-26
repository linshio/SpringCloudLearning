package cn.linshio.springcloud.controller;

import cn.linshio.springcloud.entities.CommonResult;
import cn.linshio.springcloud.entities.Payment;
import cn.linshio.springcloud.service.PaymentService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    //url
    public static final String SERVER_URL="http://nacos-payment-provider";
    //restTemplate
    @Resource
    private RestTemplate restTemplate;
    //controller

    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")
    @SentinelResource(value = "fallback",blockHandler = "blockHandler")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) throws IllegalAccessException {
        CommonResult<Payment> result = restTemplate.getForObject(SERVER_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id==4){
            throw new IllegalAccessException("IllegalAccessException,非法参数异常");
//            log.info("非法参数异常");
        }else if (result.getData()==null){
            throw new NullPointerException("NullPointerException,该id没有对应的记录,空指针异常");
        }
        return result;
    }

    //fallback
//    public CommonResult<Payment> handlerFallback(Long id,Throwable e){
//        Payment payment = new Payment(id, "null");
//        return new CommonResult<>(555,"兜底方法处理异常,exception:"+e.getMessage(),payment);
//    }

    //block
    public CommonResult<Payment> blockHandler(Long id, BlockException e){
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(555,"限流，无此流水,exception:"+e.getMessage(),payment);
    }

    //OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        if (id==4){
            throw new RuntimeException("没有该ID");
        }
        return paymentService.paymentSQL(id);
    }

}
