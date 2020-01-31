package com.autohost.configurator.job.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autohost.configurator.config.implementation.HostConfiguration;
import com.autohost.configurator.config.type.ubuntu.Configuration;

/**
 * Récupère des paramètres (os, configuration)
 * et récupère une liste de commande à éxecuter, puis les passe dans le contexte pour
 * la tasklet qui les executera
 * @author mehdi
 *
 */
@Component
public class RetrieveCommandTasklet implements Tasklet, StepExecutionListener {

    // parameter passed in jobParameter to indicate which config to launch
    private String configurationToLaunch;
    private String osOfTheHost;

    @Autowired
    private HostConfiguration hostConfiguration;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        JobParameters parameters = stepExecution.getJobExecution().getJobParameters();
        configurationToLaunch = parameters.getString("configuration");
        osOfTheHost = parameters.getString("os");

        List<Configuration> list = new ArrayList();
        Configuration c = new Configuration("ls -al", "test", 1, "ubuntu");
        list.add(c);
        Configuration d = new Configuration("apt-get update", "test", 2, "ubuntu");
        list.add(d);
        Configuration f = new Configuration("apt-get install -y mc", "test", 3, "ubuntu");
        list.add(f);

        hostConfiguration.saveListOfCommand(list);

    }

    // Put in the context the list of command to be executed by the next step
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // retrieve the jobContext to pass value for next step, here it pass a list of command to execute
        ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        List listCommand = hostConfiguration.listOfCommand(configurationToLaunch, osOfTheHost);
        System.out.println("RetrieveComand says: " + listCommand);
        jobContext.put("command", listCommand);
        return RepeatStatus.FINISHED;

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

}
