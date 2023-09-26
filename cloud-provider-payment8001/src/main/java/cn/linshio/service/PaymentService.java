package cn.linshio.service;


import cn.linshio.springcloud.entities.Payment;

public interface PaymentService {
    //创建
    int create(Payment payment);

    //根据id进行读取
    Payment getPaymentById(Long id);
}
