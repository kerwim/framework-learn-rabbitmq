package com.Noctifloroused.consumer;

import com.Noctifloroused.utils.ConnectionUtil;
import com.rabbitmq.client.*;
import java.io.IOException;  
  
public class Consumer2 {  
  
    public static void main(String[] args) throws Exception {  
  
        Connection connection = ConnectionUtil.getConnection();
  
        Channel channel = connection.createChannel();  
  
        String queue2Name = "test_fanout_queue2";

        /**
         * 参数 1 队列名
         * 参数 2 是否持久化
         * 参数 3 是否自动删除
         * 参数 4 没有额外的声明参数
         */
        channel.queueDeclare(queue2Name,true,false,false,null);

        /**
         * 定义了一个Consumer实例，用于处理消息队列中的消息
         */
        Consumer consumer = new DefaultConsumer(channel){  
  
            @Override  
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {  
  
                System.out.println("body："+new String(body));  
                System.out.println("队列 2 消费者 2 将日志信息打印到控制台.....");  
  
            }  
  
        };  
  
        channel.basicConsume(queue2Name,true,consumer);  
  
    }  
  
}