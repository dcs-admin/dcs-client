package com.datacenter.dcsclient.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.datacenter.dcsclient.domain.Developer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeveloperSerializer implements Serializer<Developer> {

	@Override
	public byte[] serialize(String arg0, Developer developer) {
		byte[] serializedBytes = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			serializedBytes = objectMapper.writeValueAsString(developer).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serializedBytes;
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
