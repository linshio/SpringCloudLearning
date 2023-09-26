package cn.lisnhio.springcloud.service.impl;

import cn.lisnhio.springcloud.dao.OrderDao;
import cn.lisnhio.springcloud.domain.Order;
import cn.lisnhio.springcloud.service.AccountService;
import cn.lisnhio.springcloud.service.OrderService;
import cn.lisnhio.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @Override
    public void create(Order order) {
        log.info("<<<订单开始生成创建>>>");
        orderDao.create(order);

        //rpc 远程调用库存减去库存的数量。。。
        log.info("<<<库存进行扣减start>>>");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("<<<库存进行扣减end>>>");

        //rpc 账户余额要进行响应的扣减
        log.info("<<<账户余额开始扣减start>>>");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("<<<账户余额开始扣减end>>>");

        //修改订单的状态
        log.info("<<<修改订单的状态start>>>");
        orderDao.update(order.getUserId(),0);
        log.info("<<<修改订单的状态end>>>");

        log.info("<<<下订单整体结束～～～>>>");
    }
}
