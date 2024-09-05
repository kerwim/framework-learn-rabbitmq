package com.Noctifloroused.work;


import com.Noctifloroused.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {

    public static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        /**
         *  queue      参数1：队列名称
         *  durable    参数2：是否定义持久化队列，当 MQ 重启之后还在
         *  exclusive  参数3：是否独占本次连接。若独占，只能有一个消费者监听这个队列且 Connection 关闭时删除这个队列
         *  autoDelete 参数4：是否在不使用的时候自动删除队列，也就是在没有Consumer时自动删除
         *  arguments  参数5：队列其它参数
         */
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        for (int i = 1; i <= 10; i++) {

            String body = i+"hello rabbitmq~~~";

            /**
             * 参数1：使用默认交换机（""
             * 参数2：路由键（也就是队列名）为 QUEUE_NAME
             * 参数3：消息属性使用默认值（null）
             * 参数4：消息体为 body 字符串的字节数组形式
             */
            channel.basicPublish("",QUEUE_NAME,null,body.getBytes());

            System.out.println("已发送消息：" + body);

        }

        channel.close();

        connection.close();

    }
}
