package com.datacenter.dcsclient.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.datacenter.dcsclient.domain.UserObj;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDeserializer implements Deserializer<UserObj> {

	@Override
	public UserObj deserialize(String arg0, byte[] devBytes) {
		ObjectMapper mapper = new ObjectMapper();
		UserObj userObj = null;
		try {
			userObj = mapper.readValue(devBytes, UserObj.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return userObj;
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
