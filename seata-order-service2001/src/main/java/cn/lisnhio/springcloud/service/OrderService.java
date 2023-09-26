package cn.lisnhio.springcloud.service;


import cn.lisnhio.springcloud.domain.Order;

public interface OrderService {

    //创建订单
    void create(Order order);
}
