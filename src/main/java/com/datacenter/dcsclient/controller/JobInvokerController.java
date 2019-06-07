package com.datacenter.dcsclient.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.dcsclient.kafkaextract.flatfile.KafkaWriter;
import com.datacenter.dcsclient.load.postgres.KafkaReader;
import com.datacenter.dcsclient.util.Constants;
 
@RestController
public class JobInvokerController {
 
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    @Qualifier("extractJob")
    Job extractJob;
    
    @Autowired
    @Qualifier("loadJob")
    Job loadJob;
    
    @Autowired
    KafkaWriter kafkaWriter;
    
    @Autowired
    KafkaReader kafkaReader;
    
    @RequestMapping("/run-extract-job/{topic}/{test}")
    public String extractJob(@PathVariable("topic") String topic, @PathVariable("test") String test) throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder()
            								.addString("source", topic)
            								.addString("dummy", test)
            								.toJobParameters();
            kafkaWriter.setTopic(topic);
            jobLauncher.run(extractJob, jobParameters);
            
        return "Extract Batch job has been invoked";
    }
    
    
    @RequestMapping("/run-load-job/{topic}")
    public String loadJob(@PathVariable("topic") String topic) throws Exception {
 
           /* JobParameters jobParameters = new JobParametersBuilder()
            								.addString("source", "loadJob")
            								.toJobParameters();
            jobLauncher.run(loadJob, jobParameters);*/
    	
    	kafkaReader.setTopic(topic);
    	kafkaReader.loadDataFromKafka();
            
        return "Load Batch job has been invoked";
    }
}