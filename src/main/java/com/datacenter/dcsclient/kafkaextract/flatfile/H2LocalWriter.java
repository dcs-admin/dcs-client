package com.datacenter.dcsclient.kafkaextract.flatfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.datacenter.dcsclient.domain.Person;
import com.datacenter.dcsclient.extract.repository.ExtractUsersRepository;

@Component
public class H2LocalWriter {

	@Autowired
	private ExtractUsersRepository repo;
	 
	@Transactional("extractTransactionManager")
	public void write(List<? extends Person> persons) throws Exception { 
		 repo.saveAll(persons);
	}
	
}
