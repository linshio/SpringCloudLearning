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
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    //显示一下当前的微服务端口
    @Value("${server.port}")
    private String serverPort;
    /**
     * docker run -d --name zookeeper --privileged=true -p 2181:2181  -v /root/zookeeper/data:/data -v /root/zookeeper/conf:/conf -v /root/zookeeper/logs:/datalog zookeeper:3.5.7
     */
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

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("******element {}",element);
        }

            List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
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

    //openFeign 超时测试
    @GetMapping("/payment/timeout")
    public String openFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi, this is a paymentZipkin server fall back";
    }


}
