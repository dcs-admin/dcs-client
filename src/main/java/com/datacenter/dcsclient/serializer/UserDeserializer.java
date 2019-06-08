package com.datacenter.dcsclient.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.datacenter.dcsclient.domain.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDeserializer implements Deserializer<Person> {

	@Override
	public Person deserialize(String arg0, byte[] devBytes) {
		ObjectMapper mapper = new ObjectMapper();
		Person person = null;
		try {
			person = mapper.readValue(devBytes, Person.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return person;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

}
