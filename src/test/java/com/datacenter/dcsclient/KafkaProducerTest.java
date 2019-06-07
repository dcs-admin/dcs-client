package com.datacenter.dcsclient;

import com.datacenter.dcsclient.domain.UserObj;
import com.datacenter.dcsclient.kafkaextract.flatfile.KafkaWriter;
import com.datacenter.dcsclient.load.postgres.KafkaReader;

public class KafkaProducerTest {

	public static void main(String[] args) {

		String TOPIC = "devi1";
		KafkaWriter kafkaWriter = new KafkaWriter();
		UserObj userObj = new UserObj();
		userObj.setUserId(200l);
		userObj.setName("ANJI");
		userObj.setDept("CSE");
		// userObj.setAccount("100000");
		

		kafkaWriter.pushDataToKafka(TOPIC, userObj);
		System.out.println("Data Pushed to Topic : "+TOPIC);
		kafkaWriter.closeProducer();

	}
}
