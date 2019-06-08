package com.datacenter.dcsclient.kafkaload.postgres;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.datacenter.dcsclient.domain.Person;
import com.datacenter.dcsclient.kafkaextract.CustomExtractor;
import com.datacenter.dcsclient.load.repository.LoadUsersRepository;

@Component
@EnableCaching
public class LoadWriter extends CustomExtractor implements ItemWriter<Person>{
	
	//@Autowired
	//private LoadUsersRepository repo; 
	

	@Override
	//@Transactional("loadTransactionManager")
	public void write(List<? extends Person> persons) throws Exception {
		//repo.save(userObjs);
		
		for(Person person : persons){
			super.pushDataToKafka(null,person);
		}
		
		super.closeProducer();
		
	}
	
}
