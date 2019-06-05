package com.datacenter.dcsclient.kafkaextract.flatfile;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datacenter.dcsclient.domain.User;
import com.datacenter.dcsclient.repository.UsersRepository;

@Component
public class Processor implements ItemProcessor<User, User> {

	@Autowired
	private UsersRepository userRepo;

	@Override
	public User process(User user) throws Exception {
		//Optional<Users> userFromDb = userRepo.findById(user.getUserId());
		//if(userFromDb.isPresent()) {
		//	user.setAccount(user.getAccount().add(userFromDb.get().getAccount()));
		//}
		return user;
	}

}
