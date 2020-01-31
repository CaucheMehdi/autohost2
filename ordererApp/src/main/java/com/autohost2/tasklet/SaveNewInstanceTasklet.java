package com.autohost2.tasklet;

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

import com.autohost2.repository.HostRepository;
import com.autohost2.vultr.ServerCreationOption;
import com.autohost2.vultr.VultrCommunicator;
import com.autohost2.vultr.VultrInstance;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SaveNewInstanceTasklet implements Tasklet, StepExecutionListener {

    @Autowired
    private VultrCommunicator    vultrAPI;
    @Autowired
    private ServerCreationOption serverParameter;
    @Autowired
    private HostRepository       repository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // TODO Auto-generated method stub

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // TODO Auto-generated method stub
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String subid = (String) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("SUBID");
        ResponseEntity<String> res = vultrAPI.get(serverParameter.getServerState(subid));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        VultrInstance instance = mapper.readValue(res.getBody(), VultrInstance.class);
        // TO-DO set as a enum
        instance.setConfiguration("initial");
        repository.save(instance);
        // setting the ip og the new ressource
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("ip", instance.getMain_ip());
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("pwd", instance.getDefaultpassword());

        return RepeatStatus.FINISHED;
    }

}
