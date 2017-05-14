package com.alxg2112.diploma.storm.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;

import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class JMSInboundSpout extends BaseRichSpout {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private SpoutOutputCollector collector;
    private HashMap<Object, Message> cache;

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {

    }

    public void nextTuple() {

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
