package com.datacenter.dcsclient;

import java.math.BigDecimal;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.datacenter.dcsclient.domain.Address;
import com.datacenter.dcsclient.domain.Developer;
import com.datacenter.dcsclient.kafkaextract.CustomExtractor;
import com.datacenter.dcsclient.kafkaextract.Extractor;
import com.datacenter.dcsclient.kafkaload.CustomTarget;
import com.datacenter.dcsclient.kafkaload.Target;
import com.datacenter.dcsclient.util.Constants;

@SpringBootApplication
@EnableBatchProcessing
public class DcsClientApplication implements ApplicationRunner {

	@Autowired
	private TaskExecutor taskExecutor;

	public static void main(String[] args) {
		SpringApplication.run(DcsClientApplication.class, args);
	}

	@Bean
	public TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		String start_as;
		String topic;
		String msg = null;

		if (args.containsOption(Constants.OPTION_START_AS)) {

			start_as = args.getOptionValues(Constants.OPTION_START_AS).get(0);

			if (args.containsOption(Constants.OPTION_TOPIC)) {

				topic = args.getOptionValues(Constants.OPTION_TOPIC).get(0);
				
				if (args.containsOption(Constants.OPTION_MESSAGE)) {
					msg = args.getOptionValues(Constants.OPTION_MESSAGE).get(0);
				}

				switch (start_as) {
				case Constants.OPTION_TARGET:
					taskExecutor.execute(new Target(topic));
					break;
				case Constants.OPTION_CUSTOM_TARGET:
					taskExecutor.execute(new CustomTarget(topic));
					break;
				case Constants.OPTION_SOURCE:
					taskExecutor.execute(new Extractor(topic, msg));
					break;
				case Constants.OPTION_CUSTOM_SOURCE:
					taskExecutor.execute(new CustomExtractor( ));
					break;
				}
			}
		}

	}

	private Developer getDummyDeveloper(String msg) {
		Developer d = new Developer();
		d.setId(1298L);
		d.setName(msg);
		d.setSalary(new BigDecimal("123.45"));
		Address a = new Address();
		a.setCountry("India");
		a.setState("Maharashtra");
		a.setZipcode("411028");
		d.setAddress(a);
		return d;
	}
}
