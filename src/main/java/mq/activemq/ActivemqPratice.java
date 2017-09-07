package mq.activemq;

import com.sun.org.apache.xml.internal.security.transforms.Transforms;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

/**
 *Activemq 练习
 * Created by Grady on 2017/8/26.
 */
public class ActivemqPratice {

    public static void main(String[] args)throws Exception {
        ActivemqPratice.producer();
    }
    /**
     * 生产者
     * @throws Exception
     */
    static void producer()throws Exception{

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.43.128:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("test-queue1");

        MessageProducer producer = session.createProducer(queue);

        TextMessage textMessage = new ActiveMQTextMessage();
        textMessage.setText("test queue textmessage");

        TextMessage textMessage1 = session.createTextMessage("test queue session");


        producer.send(textMessage);
        producer.send(textMessage1);

        producer.close();
        session.close();
        connection.close();
    }

}
