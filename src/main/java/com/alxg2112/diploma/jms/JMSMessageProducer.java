package com.alxg2112.diploma.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class JMSMessageProducer {

    @Value("${jms.broker.url}")
    private String brokerUrl;

    @Value("${jms.downstream.destination}")
    private String destinationName;

    private MessageProducer producer;

    public JMSMessageProducer() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(destinationName);
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    public void sendMessage(Message message) throws JMSException {
        producer.send(message);
    }
}
