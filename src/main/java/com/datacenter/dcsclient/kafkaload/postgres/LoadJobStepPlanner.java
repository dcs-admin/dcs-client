package com.datacenter.dcsclient.kafkaload.postgres;

 

import java.util.Properties;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
//import org.springframework.batch.item.kafka.KafkaItemReader;
//import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.datacenter.dcsclient.domain.UserObj;

 

@Component
public class LoadJobStepPlanner extends JobExecutionListenerSupport {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${input.file}") 
	Resource resource;
	
	@Autowired
	LoadProcessor loadProcessor;
	
	@Autowired
	LoadWriter loadWriter;
	
	@Bean(name = "loadJob")
	public Job loadJobPlanner() {
/*
		Step step = stepBuilderFactory.get("step-1")
				.<User, User> chunk(1)
				.reader(new LoadReader(null))
				.processor(loadProcessor)
				.writer(loadWriter)
				.build();
		
		
		Job job = jobBuilderFactory.get("loadJob")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();

		return job;*/
		
		return null;
	}
	
	
/*
	@Bean
	KafkaItemReader<Long, User> kafkaItemReader() {
		Properties props = new Properties();
		props.putAll(props);

		return new KafkaItemReaderBuilder<Long, Customer>()
			.partitions(0)
			.consumerProperties(props)
			.name("customers-reader")
			.saveState(true)
			.topic("customers")
			.build();
	}*/
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("LOAD BATCH JOB COMPLETED SUCCESSFULLY");
		}else{
			System.out.println("LOAD BATCH JOB FAILED");	
		}
	}

}
