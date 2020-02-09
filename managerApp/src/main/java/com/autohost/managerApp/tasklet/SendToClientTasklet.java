package com.autohost.managerApp.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import entityDTO.dto.RessourceDTO;
import entityDTO.dto.UrlEndpoint;

@Component
public class SendToClientTasklet implements Tasklet, StepExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(SendToClientTasklet.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // launching parameter from JobContext
        logger.info("Starting tasklet SendtoClientTaskler at {} ", System.currentTimeMillis());

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("Ending tasklet SentToClientTasklet at {} ", System.currentTimeMillis());
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ExecutionContext e = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        RessourceDTO res = new RessourceDTO();

        res.setTrackingId(e.getString("trackerId"));
        res.setIp(e.getString("ip"));
        res.setPlace(e.getString("place"));
        res.setProvider(e.getString("provider"));
        res.setApplication(e.getString("configuration"));
        res.setOs(e.getString("os"));
        res.setSize(e.getString("size"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RessourceDTO> entity = new HttpEntity<>(res, headers);
        // renvoie au client l'entité avec un status à PENDING
        restTemplate.postForEntity(UrlEndpoint.CUSTOMER_RECEIVE_ORDER_ENDPOINT, entity, RessourceDTO.class);
        return RepeatStatus.FINISHED;
    }

}
