package com.datacenter.dcsclient.kafkaload.postgres;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datacenter.dcsclient.domain.UserObj;
import com.datacenter.dcsclient.extract.repository.ExtractUsersRepository;

@Component
public class LoadProcessor implements ItemProcessor<UserObj, UserObj> {

	@Autowired
	private ExtractUsersRepository userRepo;

	@Override
	public UserObj process(UserObj userObj) throws Exception {
		//Optional<Users> userFromDb = userRepo.findById(user.getUserId());
		//if(userFromDb.isPresent()) {
		//	user.setAccount(user.getAccount().add(userFromDb.get().getAccount()));
		//}
		return userObj;
	}

}
