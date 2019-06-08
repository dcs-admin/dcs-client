package com.datacenter.dcsclient.kafkaextract.flatfile;

 

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.datacenter.dcsclient.domain.Person;

 

@Component
public class ExtractJobStepPlanner extends JobExecutionListenerSupport {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${input.file}") 
	Resource resource;
	
	@Autowired
	Processor processor;
	
	@Autowired
	KafkaWriter kafkaWriter;
	
	@Bean(name = "extractJob")
	public Job extractJobPlan() {

		Step step = stepBuilderFactory.get("step-1")
				.<Person, Person> chunk(100)
				.reader(new Reader(resource))
				.processor(processor)
				.writer(kafkaWriter)
				.build();
		
		
		Job job = jobBuilderFactory.get("extractJob")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();

		return job;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("EXTRACT BATCH JOB COMPLETED SUCCESSFULLY");
		}else{
			System.out.println("EXTRACT BATCH JOB FAILED");	
		}
	}

}
