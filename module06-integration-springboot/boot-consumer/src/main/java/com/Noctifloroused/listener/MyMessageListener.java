package com.Noctifloroused.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyMessageListener {

    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String ROUTING_KEY = "order";
    public static final String QUEUE_NAME = "queue.order";

    //写法 1 创建队列 交换机 监听
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = QUEUE_NAME,declare = "true"), //指定队列并持久化
//            exchange = @Exchange(value = EXCHANGE_DIRECT), //指定交换机
//            key = {ROUTING_KEY} //指定 routing key
//    ))

    //写法 2 监听
    @RabbitListener(queues = {QUEUE_NAME})
    public void onMessage(String dataString,
                          Message message,
                          Channel channel) {
        System.out.println("接收到消息：" + dataString);

    }
}