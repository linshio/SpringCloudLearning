package cn.linshio.springcloud.myhandler;


import cn.linshio.springcloud.entities.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {

    public static CommonResult handlerException1(BlockException exception){
        return new CommonResult(444,"兜底方法进行处理------>1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,"兜底方法进行处理------>2");
    }
}
