package com.datacenter.dcsclient.kafkaload.postgres;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;

//import org.springframework.batch.item.kafka.KafkaItemReader;

import com.datacenter.dcsclient.domain.UserObj;

public class LoadReader extends IteratorItemReader<UserObj> {
	
	public LoadReader(Iterable<UserObj> iterable) {
		super(iterable);
		// TODO Auto-generated constructor stub
	}

//extends KafkaItemReader<Long, User> {
	
	@Override
	public UserObj read() throws Exception, UnexpectedInputException, ParseException {
		 
		
		
		
		return super.read();
	} 
}
