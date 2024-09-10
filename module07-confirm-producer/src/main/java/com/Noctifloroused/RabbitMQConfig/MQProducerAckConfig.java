package com.Noctifloroused.RabbitMQConfig;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: RabbitMQ-test-framework
 * @package: com.Noctifloroused.RabbitMQConfig
 * @className: MQProducerAckConfig
 * @author: kerwim
 * @description: TODO
 * @date: 2024/9/9 21:38
 * @version: 1.0
 */
@Configuration
@Slf4j
public class MQProducerAckConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * PostConstruct 注解的方法会在依赖注入完成后自动执行，用于初始化一些配置
     * 这个类构造后 -> 配置好回调函数 ->再把配置好的回调函数注入到rabbitTemplate中，
     * 这样当消息发送成功或失败时，就会调用对应的回调函数进行处理。也就是rabbitTemplate得到增强!
     */
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * 方法在消息发送成功时被调用，记录日志信息
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息发送成功:correlationData={},ack={},cause={}",correlationData,ack,cause);
    }

    /**
     * 方法在消息未被队列接收时调用，记录未投递成功消息的详细信息。
     * @param returnedMessage
     */
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("消息主体: " + new String(returnedMessage.getMessage().getBody()));
        log.info("应答码: " + returnedMessage.getReplyCode());
        log.info("描述：" + returnedMessage.getReplyText());
        log.info("消息使用的交换器 exchange : " + returnedMessage.getExchange());
        log.info("消息使用的路由键 routing : " + returnedMessage.getRoutingKey());
    }
}
