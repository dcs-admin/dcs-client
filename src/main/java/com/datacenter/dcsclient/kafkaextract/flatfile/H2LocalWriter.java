package com.datacenter.dcsclient.kafkaextract.flatfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.datacenter.dcsclient.domain.UserObj;
import com.datacenter.dcsclient.kafkaextract.flatfile.repository.ExtractUsersRepository;

@Component
public class H2LocalWriter {

	@Autowired
	private ExtractUsersRepository repo;
	 
	@Transactional("extractTransactionManager")
	public void write(List<? extends UserObj> userObjs) throws Exception { 
		 repo.save(userObjs);
	}
	
}
