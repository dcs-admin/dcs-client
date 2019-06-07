package com.datacenter.dcsclient.kafkaextract.flatfile;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.datacenter.dcsclient.domain.UserObj;

@Component
public class Processor implements ItemProcessor<UserObj, UserObj> {

	//@Autowired
	//private UsersRepository userRepo;

	@Override
	public UserObj process(UserObj userObj) throws Exception {
		//Optional<Users> userFromDb = userRepo.findById(user.getUserId());
		//if(userFromDb.isPresent()) {
		//	user.setAccount(user.getAccount().add(userFromDb.get().getAccount()));
		//}
		return userObj;
	}

}
