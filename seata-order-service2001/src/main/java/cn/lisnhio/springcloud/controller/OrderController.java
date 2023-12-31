package cn.lisnhio.springcloud.controller;


import cn.lisnhio.springcloud.domain.CommonResult;
import cn.lisnhio.springcloud.domain.Order;
import cn.lisnhio.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
