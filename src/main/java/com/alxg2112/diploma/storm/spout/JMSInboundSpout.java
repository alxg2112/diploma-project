package com.alxg2112.diploma.storm.spout;

import com.alxg2112.diploma.jms.JMSMessageConsumer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.HashMap;
import java.util.Map;

@Component
public class JMSInboundSpout extends BaseRichSpout {

    private static final Logger logger = LoggerFactory.getLogger(JMSInboundSpout.class);

    private SpoutOutputCollector collector;
    private HashMap<Object, TextMessage> cache;
    private JMSMessageConsumer jmsMessageConsumer;

    @Autowired
    public JMSInboundSpout(JMSMessageConsumer jmsMessageConsumer) {
        this.jmsMessageConsumer = jmsMessageConsumer;
    }

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        collector = spoutOutputCollector;
        cache = new HashMap<>();
    }

    public void nextTuple() {
        Message message = jmsMessageConsumer.receive();
        if (message == null) return;
        TextMessage textMessage = (TextMessage) message;
        try {
            Object msgId = textMessage.getJMSMessageID();
            cache.put(msgId, textMessage);
            collector.emit(new Values(textMessage.getText()), msgId);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("body"));
    }

    public void ack(Object msgId) {
        TextMessage textMessage = cache.get(msgId);
        try {
            textMessage.acknowledge();
            logger.info("Message with id {} acknowledged", msgId);
            cache.remove(msgId);
        } catch (JMSException e) {
            logger.error("{} during message acknowledgement with id {}", e, msgId);
        }
    }

    public void fail(Object msgId) {
        TextMessage textMessage = cache.get(msgId);
        try {
            collector.emit(new Values(textMessage.getText()), msgId);
            logger.info("Message with id {} failed and being redelivered", msgId);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
