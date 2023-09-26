package cn.linshio.springcloud.controller;

import cn.linshio.springcloud.entities.CommonResult;
import cn.linshio.springcloud.entities.Payment;
import cn.linshio.springcloud.myhandler.CustomerBlockHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "HandlerException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称进行限流测试ok",new Payment(2020L,"serial1001"));
    }

    public CommonResult HandlerException(BlockException e){
        return new CommonResult(444,e.getClass().getCanonicalName()+"\t 服务不可用");
    }

    @GetMapping("/rateLimit/byUri")
    @SentinelResource(value = "byUri")
    public CommonResult byUri(){
        return new CommonResult(200,"按Uri进行限流测试ok",new Payment(2021L,"serial1002"));
    }


    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按用户自定义的内容进行处理",new Payment(2022L,"serial1003"));
    }
}
