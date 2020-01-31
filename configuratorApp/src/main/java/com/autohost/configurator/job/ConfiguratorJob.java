package com.autohost.configurator.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.autohost.configurator.job.tasklet.ExecuteCommandTasklet;
import com.autohost.configurator.job.tasklet.RetrieveCommandTasklet;

/**
 * Job chargé de configuré une instance
 * 
 * @author mehdi
 *
 */
@Component
public class ConfiguratorJob {

	 @Autowired
	 private JobBuilderFactory 		jobBuilderFactory;
	 @Autowired
	 private StepBuilderFactory 	stepBuilderFactory;
	 @Autowired 
	 private RetrieveCommandTasklet retrieveCommandTasklet;
	 @Autowired 
	 private ExecuteCommandTasklet 	executeCommandTasklet;
	 
	    @Bean(name="configInstance")
	    public Job job() {
	    	return jobBuilderFactory.get("job")
	                .start(step1())
	                .next(step2())
	                .build();
	    }
	 
	    @Bean
	    protected Step step1() {
	        return stepBuilderFactory.get("step1")
	            .tasklet(retrieveCommandTasklet)
	                .build();
	    }
	    
	    @Bean
	    protected Step step2() {
	        return stepBuilderFactory.get("step2")
	            .tasklet(executeCommandTasklet)
	                .build();
	    }
	 
}
