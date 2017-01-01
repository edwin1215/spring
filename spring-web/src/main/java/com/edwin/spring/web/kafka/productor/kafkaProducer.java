package com.edwin.spring.web.kafka.productor;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

public class kafkaProducer implements Runnable {

	private String topic;

	private static final int MSG_NUM = 1;

	public kafkaProducer(String topic) {
		this.topic = topic;
	}

	@Override
	public void run() {
		Producer<String, String> producer = createProducer();
		int i = 0;
		while (i++ >= MSG_NUM) {
			producer.send(new KeyedMessage<String, String>(topic, "message: "
					+ i));
		}

	}

	private Producer<String, String> createProducer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "192.168.1.11:2181");// 声明zk
		properties.put("serializer.class", StringEncoder.class.getName());
		properties.put("metadata.broker.list", "192.168.1.11:9092");// 声明kafka
		return new Producer<String, String>(new ProducerConfig(properties));
	}

	public static void main(String[] args) {
		new Thread(new kafkaProducer("test")).start();// 使用kafka集群中创建好的主题 test

	}
}
