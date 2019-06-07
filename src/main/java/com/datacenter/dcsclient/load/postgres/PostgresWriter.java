package com.datacenter.dcsclient.load.postgres;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.datacenter.dcsclient.domain.UserObj;
import com.datacenter.dcsclient.load.repository.LoadUsersRepository;

@Component
public class PostgresWriter {
	
	@Autowired
	private LoadUsersRepository repo; 
	 
 
	@Transactional("loadTransactionManager")
	public void write(List<? extends UserObj> userObjs) throws Exception {
		repo.save(userObjs); 
	}
	
	@Transactional("loadTransactionManager")
	public void write( UserObj  userObj) throws Exception {
		repo.save(userObj); 
	}
	
}
