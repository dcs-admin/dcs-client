package com.datacenter.dcsclient.kafkaextract.flatfile;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.datacenter.dcsclient.domain.Person;

@Component
public class Processor implements ItemProcessor<Person, Person> {

	//@Autowired
	//private UsersRepository userRepo;

	@Override
	public Person process(Person person) throws Exception {
		//Optional<Users> userFromDb = userRepo.findById(user.getUserId());
		//if(userFromDb.isPresent()) {
		//	user.setAccount(user.getAccount().add(userFromDb.get().getAccount()));
		//}
		return person;
	}

}
