package com.datacenter.dcsclient.kafkaload.postgres;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;

//import org.springframework.batch.item.kafka.KafkaItemReader;

import com.datacenter.dcsclient.domain.Person;

public class LoadReader extends IteratorItemReader<Person> {
	
	public LoadReader(Iterable<Person> iterable) {
		super(iterable);
		// TODO Auto-generated constructor stub
	}

//extends KafkaItemReader<Long, User> {
	
	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException {
		 
		
		
		
		return super.read();
	} 
}
