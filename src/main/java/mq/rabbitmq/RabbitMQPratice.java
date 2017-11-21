package mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by Grady on 2017/9/25.
 */
public class RabbitMQPratice {
    static String QUEUE_NAME = "test";
    public static void main(String[] args) throws Exception{
        helloworld();
    }

    static void helloworld() throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.1.1.186");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        //工厂创建连接
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
