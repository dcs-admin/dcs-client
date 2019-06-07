package com.datacenter.dcsclient.kafkaload.postgres;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.datacenter.dcsclient.domain.UserObj;
import com.datacenter.dcsclient.kafkaextract.CustomExtractor;
import com.datacenter.dcsclient.load.repository.LoadUsersRepository;

@Component
@EnableCaching
public class LoadWriter extends CustomExtractor implements ItemWriter<UserObj>{
	
	//@Autowired
	//private LoadUsersRepository repo; 
	

	@Override
	//@Transactional("loadTransactionManager")
	public void write(List<? extends UserObj> userObjs) throws Exception {
		//repo.save(userObjs);
		
		for(UserObj userObj : userObjs){
			super.pushDataToKafka(null,userObj);
		}
		
		super.closeProducer();
		
	}
	
}
