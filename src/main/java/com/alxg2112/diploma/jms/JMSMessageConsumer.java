package com.alxg2112.diploma.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class JMSMessageConsumer {

    @Value("${jms.broker.url}")
    private String brokerUrl;

    @Value("${jms.upstream.destination}")
    private String destinationName;

    @Value("${jms.receive.timeout}")
    private int receiveTimeout;

    private MessageConsumer consumer;

    public JMSMessageConsumer() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(destinationName);
        consumer = session.createConsumer(destination);
    }

    public Message getMessage() throws JMSException {
        return consumer.receive(receiveTimeout);
    }
}
