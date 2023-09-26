package cn.linshio.controller;
import cn.linshio.service.PaymentService;
import cn.linshio.springcloud.entities.CommonResult;
import cn.linshio.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sound.sampled.Line;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("=========插入返回的结果[{}]=========",result);
        //创建成功就返回成功的CommonResult对象
        if (result>0){
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("++++++++++查询结果[{}],哈哈++++++",payment);
        //查询成功就返回改对象
        if (payment!=null){
            return new CommonResult<>(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult<>(444,"没有对应的记录，ID："+id,null);
        }
    }


    //服务信息
    @GetMapping("/payment/discovery")
    public Object discovery(){
        //获取服务列表
        for (String service : discoveryClient.getServices()) {
            log.info("******element: {}",service);
        }

        //获取服务的主机信息
        List<ServiceInstance> instances =
                discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

    //测试自己写的轮询算法
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }


}
