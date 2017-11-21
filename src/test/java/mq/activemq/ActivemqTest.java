package mq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * Created by Grady on 2017/8/27.
 */
public class ActivemqTest {
    /**
     * 生产者
     *
     * @throws Exception
     */

    @Test
    public void producer() throws Exception {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.43.128:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination queue = session.createQueue("test-queue");

        Destination topic = session.createTopic("test-topic");

//        MessageProducer producer = session.createProducer(queue);
        MessageProducer tproducer = session.createProducer(topic);
//        TextMessage textMessage = new ActiveMQTextMessage();
//        textMessage.setText("test queue textmessage");
        for(int i = 0; i<10;i++) {

            TextMessage textMessage1 = session.createTextMessage("test queue -- "+i);
            Thread.sleep(1000);
//            producer.send(textMessage1);
            tproducer.send(textMessage1);
        }
        tproducer.close();
        session.close();
        connection.close();
    }

    @Test
    public void consumer() throws Exception {
        //创建一个连接工厂对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.1.1.186:61616");
        //使用连接工厂对象创建一个连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用连接对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用Session创建一个Destination，Destination应该和消息的发送端一致。
        Destination queue = session.createQueue("test-queue");
        Destination topic = session.createTopic("test_topic");
        //使用Session创建一个Consumer对象
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        String text = textMessage.getText();
                        System.out.println("text = " + text);
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //系统等待接收消息
        /*while(true) {
			Thread.sleep(100);
		}*/
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
