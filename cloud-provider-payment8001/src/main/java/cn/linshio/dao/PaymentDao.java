package cn.linshio.dao;

import cn.linshio.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    //创建
    int create(Payment payment);

    //根据id进行读取
    Payment getPaymentById(@Param("id") Long id);
}
