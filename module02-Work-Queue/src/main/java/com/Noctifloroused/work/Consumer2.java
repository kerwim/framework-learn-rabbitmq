package com.Noctifloroused.work;


import com.Noctifloroused.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {

    static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        Consumer consumer = new DefaultConsumer(channel){

            /**
             *
             * @param consumerTag 标识消费者的标签，可以用来取消消费
             * @param envelope 包含了消息的元信息，比如交换机名称、路由键等
             * @param properties 包含了消息的属性，例如消息的优先级、内容类型等
             * @param body 消息的实际内容，是一个字节数组
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("Consumer2 body：" +new String(body));

            }

        };

        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
