package com.datacenter.dcsclient.kafkaload;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datacenter.dcsclient.domain.Developer;

/**
 * @author pavan.solapure
 *
 */
public class CustomTarget implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomTarget.class);
	
	private String topic;
	
	public CustomTarget(String topic) {
		this.topic = topic;
	}

	@Override
	public void run() {
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "100000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "com.datacenter.dcsclient.serializer.DeveloperDeserializer");
		KafkaConsumer<String, Developer> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(this.topic));
		while (true) {
			ConsumerRecords<String, Developer> records = consumer.poll(100);
			for (ConsumerRecord<String, Developer> record : records) {
				logger.info("offset = {}, Dev Id = {}, Dev Name = {}, Dev Salary = {}, Dev Zip = {}", 
						record.offset(),
						record.value().getId(), 
						record.value().getName(),
						record.value().getSalary(),
						record.value().getAddress().getZipcode());
			}
		}
		
	}
}
