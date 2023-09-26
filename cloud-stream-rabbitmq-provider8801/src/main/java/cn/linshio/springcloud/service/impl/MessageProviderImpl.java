package cn.linshio.springcloud.service.impl;

import cn.linshio.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)//一个消息的发送管道
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    //消息发送的管道
    @Resource
    private MessageChannel output;


    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());//创建并发送消息
        log.info("******serial {}",serial);
        return serial;
    }
}

