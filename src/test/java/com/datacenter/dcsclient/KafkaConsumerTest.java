package com.datacenter.dcsclient;

import com.datacenter.dcsclient.load.postgres.KafkaReader;

public class KafkaConsumerTest {

	public static void main(String[] args) {
		String TOPIC = "devi1";
		
		KafkaReader KafkaReader = new KafkaReader(TOPIC);
		KafkaReader.loadDataFromKafka();
		
		System.out.println("Data Pulled from Topic : "+TOPIC);	// TODO Auto-generated method stub

	}

}
