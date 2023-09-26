package cn.linshio.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String TestA(){
        return "------TestA";
    }

    @GetMapping("/testB")
    public String TestsB(){
        log.info(Thread.currentThread().getName()+"\t....testB");
        return "------TestB";
    }

    @GetMapping("/testD")
    public String TestD(){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        int error = 1/0;
        log.info("testD 测试RT");
        return "------TestD";
    }

    @GetMapping("/testE")
    public String TestE(){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        int error = 1/0;
        log.info("testE 测试异常数");
        return "------TestE";
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey",blockHandler = "deal_testHotkey")
    public String testHotkey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
//        int age = 10/0;
        return "------TestHotKey";
    }

    public String deal_testHotkey(String p1, String p2, BlockException exception){
        return "------deal_testHotkey 😭";
    }
}
