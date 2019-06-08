package com.datacenter.dcsclient.kafkaextract;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datacenter.dcsclient.domain.Developer;
import com.datacenter.dcsclient.domain.Person;
import com.datacenter.dcsclient.util.Constants;

/**
 * @author pavan.solapure
 *
 */
public class CustomExtractor implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomExtractor.class);
	 
	private Properties props = new Properties();
	
	private KafkaProducer<String, Person> producer = null;
	
	public CustomExtractor() {
		 
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "com.datacenter.dcsclient.serializer.UserSerializer");
		
		this.producer = new KafkaProducer<>(props);
	}

	@Override
	public void run() {
		
		this.pushDataToKafka(null, null);
		
	}
	
	
	public void pushDataToKafka(String topic, Person person){ 
		
		producer.send(new ProducerRecord<String, Person>(topic, person)); 
		logger.info("Message sent to topic: {} : Message: {}", topic, person);
		
		
	}
	
	public void closeProducer(){
		producer.close();
	}
	
	
	 
}
