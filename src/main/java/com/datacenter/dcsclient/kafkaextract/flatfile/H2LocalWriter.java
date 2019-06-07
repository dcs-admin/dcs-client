package com.datacenter.dcsclient.kafkaextract.flatfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.datacenter.dcsclient.domain.UserObj;
import com.datacenter.dcsclient.extract.repository.ExtractUsersRepository;

@Component
public class H2LocalWriter {

	@Autowired
	private ExtractUsersRepository repo;
	 
	@Transactional
	public void write(List<? extends UserObj> userObjs) throws Exception { 
		 repo.save(userObjs);
	}
	
}
