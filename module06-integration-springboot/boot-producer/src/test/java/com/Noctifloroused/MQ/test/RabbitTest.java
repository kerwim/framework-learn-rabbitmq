package com.Noctifloroused.MQ.test;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RabbitTest {

    //实际项目中肯定是要抽出来的

    //交换机名称
    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    //routing key
    public static final String ROUTING_KEY = "order";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 该函数testSendMessage()使用rabbitTemplate对象向RabbitMQ消息队列发送消息：
     * EXCHANGE_DIRECT：指定直连交换机名称。
     * ROUTING_KEY：设置路由键。
     * "Hello rabbit!"：作为消息内容发送给RabbitMQ。
     */
    @Test
    public void testSendMessage() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_DIRECT,
                ROUTING_KEY,
                "Hello rabbit!");
    }

}
