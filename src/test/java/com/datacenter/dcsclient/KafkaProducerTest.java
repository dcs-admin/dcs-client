package com.datacenter.dcsclient;

import com.datacenter.dcsclient.domain.Person;
import com.datacenter.dcsclient.kafkaextract.flatfile.KafkaWriter;
import com.datacenter.dcsclient.load.postgres.KafkaReader;

public class KafkaProducerTest {

	public static void main(String[] args) {

		String TOPIC = "devi1";
		KafkaWriter kafkaWriter = new KafkaWriter();
		Person person = new Person();
		person.setUserId(200l);
		person.setName("ANJI");
		person.setDept("CSE");
		// userObj.setAccount("100000");
		

		kafkaWriter.pushDataToKafka(TOPIC, person);
		System.out.println("Data Pushed to Topic : "+TOPIC);
		kafkaWriter.closeProducer();

	}
}
