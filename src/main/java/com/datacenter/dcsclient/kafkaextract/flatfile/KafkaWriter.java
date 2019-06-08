package com.datacenter.dcsclient.kafkaextract.flatfile;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.datacenter.dcsclient.domain.Person;
import com.datacenter.dcsclient.extract.repository.ExtractUsersRepository;
import com.datacenter.dcsclient.kafkaextract.CustomExtractor;

@Component
public class KafkaWriter extends CustomExtractor implements ItemWriter<Person>{
	 
	private String topic;
	
	@Autowired
	H2LocalWriter h2LocalWriter;
	

	@Override 
	public void write(List<? extends Person> persons) throws Exception {
		
		
		for(Person person : persons){
			super.pushDataToKafka(this.topic, person);
		}
		
		h2LocalWriter.write(persons);
		//super.closeProducer(); 
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	} 
	
	
}
