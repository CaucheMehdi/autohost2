package com.autohost2.tasklet;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.autohost2.vultr.VultrInstance;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.autohost2.repository.HostRepository;
import com.autohost2.vultr.ServerCreationOption;
import com.autohost2.vultr.VultrCommunicator;

/**
 * Check that the instance is ready to be configured
 * @author mehdi
 *
 */
@Component
public class CheckerInstanceTasklet implements Tasklet, StepExecutionListener {
	
	@Autowired
	private VultrCommunicator vultrAPI; 
	
	@Autowired
    private ServerCreationOption serverParameter;
	@Autowired
	private HostRepository repository;


	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return ExitStatus.COMPLETED;
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("checker method invoked");
		Boolean status = false;
		String subid = (String) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("SUBID");
		VultrInstance instance = null;
		while(!status) {
			ResponseEntity<String> res = vultrAPI.get(serverParameter.getServerState(subid));
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			instance = mapper.readValue(res.getBody(), VultrInstance.class);
				if(instance.getStatus().equals("active")) {
					status = true;
				}
			Thread.sleep(5000);
		}
		repository.updateStatusVultrHost(subid, instance.getStatus());
		return RepeatStatus.FINISHED;
	}
	
	

}
