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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.autohost.managerApp.entity.Ressource;
import com.autohost.managerApp.repository.RessourceRepository;

import entityDTO.RessourceDTO;
import entityDTO.UrlEndpoint;

@Component
public class OrderNewInstanceTasklet implements Tasklet, StepExecutionListener {
    @Autowired
    RessourceRepository repo;

    Logger logger = LoggerFactory.getLogger(OrderNewInstanceTasklet.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // TODO Auto-generated method stub
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // retrieve ressource from step parameters
        ExecutionContext e = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        RessourceDTO res = new RessourceDTO();
        res.setTrackingId(e.getString("trackerId"));
        res.setIp(e.getString("ip"));
        res.setPlace(e.getString("place"));
        res.setProvider(e.getString("provider"));
        res.setApplication(e.getString("configuration"));
        res.setOs(e.getString("os"));
        res.setSize(e.getString("size"));
        // create a ressource object and record it
        Ressource resToSave = mapDto(res);
        repo.save(resToSave);
        // set and send request to ORDERER
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RessourceDTO> entity = new HttpEntity<>(res, headers);
        // renvoie au client l'entité avec un status à PENDING
        logger.info("request from Manager to Orderer: order a new ressource : {}", res);
        restTemplate.postForEntity(UrlEndpoint.ORDERER_ORDER_INSTANCE, entity, RessourceDTO.class);
        // should save the ressource
        // when orderer has the ressource, it will send it, refering to the trackind id
        // in manager we get all the instances and their status
        // CUSTOMER_RECEIVED
        // MANAGER_WORKING
        // ORDERER_BUYING
        // CONFIGURATOR_CONFIGURE
        // MANAGER_SENDING
        // CUSTOMER_USE
        return RepeatStatus.FINISHED;

    }

    private Ressource mapDto(RessourceDTO res) {
        Ressource r = new Ressource();
        r.setAttribued(false);
        r.setConfiguration(res.getApplication());
        r.setOs(res.getOs());
        r.setPlace(res.getPlace());
        r.setProvider(res.getProvider());
        r.setSize(res.getSize());
        r.setTrackerId(res.getTrackingId());
        return r;
    }

}
