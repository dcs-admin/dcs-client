package com.datacenter.dcsclient.load.postgres;

import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datacenter.dcsclient.domain.UserObj;

/**
 * @author pavan.solapure
 *
 */
@Component
public class KafkaReader  {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaReader.class);
	
	private String topic;
	
	@Autowired
	PostgresWriter postgresWriter;
	
	public KafkaReader(String topic) {
		this.topic = topic;
	}

	public KafkaReader(){}
	 
	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public void loadDataFromKafka() {
		
		try {
			Random ran = new Random();
			Properties props = new Properties();
			props.put("bootstrap.servers", "localhost:9092");
			props.put("group.id", "topic");
			props.put("enable.auto.commit", "true");
			props.put("auto.commit.interval.ms", "3000");
			props.put("enable.partition.eof", "false");
			props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			props.put("value.deserializer", "com.datacenter.dcsclient.serializer.UserDeserializer");
			KafkaConsumer<Object, Object> consumer = new KafkaConsumer<>(props); 
			
			consumer.subscribe(Arrays.asList(this.topic));
			System.out.println("Listening ob Topic: "+topic); 
			
			//print the topic name

			  java.util.Map<String,java.util.List<PartitionInfo>> listTopics = consumer.listTopics();
			  System.out.println("list of topic size :" + listTopics.size());

			  for(String topic : listTopics.keySet()){
			      System.out.println("topic name :"+topic);
			  }
			
			while (true) {
		 
				ConsumerRecords<Object, Object> records = consumer.poll(100);
				for (ConsumerRecord<Object, Object> record : records) {
					logger.info("offset = {}, value = {}", record.offset(), record.value());
					System.out.println("offset = " + record.offset() + ", value = " + record.value());
					
					try {
						postgresWriter.write((UserObj)record.value());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
			//consumer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		
	} 
	 
}
