package com.autohost2.tasklet;

import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.autohost2.vultr.ServerCreationOption;
import com.autohost2.vultr.VultrCommunicator;

/**
 * Tasklet qui commande une instance Vultr
 * @author mehdi
 *
 */
@Component
public class OrderInstanceTasklet implements Tasklet, StepExecutionListener {

    @Autowired
    private VultrCommunicator    communicatorAPI;
    @Autowired
    private ServerCreationOption serverParameter;
    private JsonParser           springParser;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // todo recuperer du contexte les paramètres pour commander l'instance
        ExecutionContext e = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        ResponseEntity<String> res = communicatorAPI
                        .post(serverParameter.getPostOptionForCreation(e.getString("place"), e.getString("size"), e.getString("os")));
        Map<String, Object> mapFromResponse = springParser.parseMap(res.getBody());
        // passing data to next step, need to store in jobcontext
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("SUBID", mapFromResponse.get("SUBID"));
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        springParser = JsonParserFactory.getJsonParser();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

}
