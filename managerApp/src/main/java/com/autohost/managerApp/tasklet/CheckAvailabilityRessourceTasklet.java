package com.autohost.managerApp.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.autohost.managerApp.entity.Ressource;
import com.autohost.managerApp.exitstatus.CustomStatus;
import com.autohost.managerApp.repository.RessourceRepository;

@Component
public class CheckAvailabilityRessourceTasklet implements Tasklet, StepExecutionListener {

    @Autowired
    private RessourceRepository ressourceRepository;
    private static final Logger logger = LoggerFactory.getLogger(CheckAvailabilityRessourceTasklet.class);
    private String              size;
    private String              os;
    private String              provider;
    private String              configuration;
    private String              place;
    private ExitStatus          status;

    /**
     * recupere dans le contexte les parametres pour le job
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {
        // launching parameter from JobContext
        logger.info("Starting tasklet CheckAvailabilityRessourceTasklet at {} ", System.currentTimeMillis());
        JobParameters parameters = stepExecution.getJobExecution().getJobParameters();
        // paramètre de la ressource demandée
        size = parameters.getString("size");
        os = parameters.getString("os");
        provider = parameters.getString("provider");
        configuration = parameters.getString("configuration");
        place = parameters.getString("place");
        status = stepExecution.getExitStatus();
    }

    /**
     *
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("Ending tasklet CheckAvailabilityRessourceTasklet at {} ", System.currentTimeMillis());
        return status;
    }

    /**
     * Check in the repository if a type of resource is available and put its data in the context
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        // ask to repository to find a type of resource
        Ressource ressourceAvailable = ressourceRepository.findBySizeAndOsAndProviderAndConfigurationAndAttribuedAndPlace(size, os, provider, configuration,
                        false, place);

        if (null == ressourceAvailable) {
            logger.info("pas de ressource trouvé");
            status = new ExitStatus(CustomStatus.RESSOURCENOTFOUND);

        } else {
            // put in the context the ressource found
            ExecutionContext e = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

            e.put("size", ressourceAvailable.getSize());
            e.put("os", ressourceAvailable.getOs());
            e.put("configuration", ressourceAvailable.getConfiguration());
            e.put("provider", ressourceAvailable.getProvider());
            e.put("place", ressourceAvailable.getPlace());
            e.put("ip", ressourceAvailable.getIp());
            e.put("trackerId", ressourceAvailable.getTrackerId());

        }
        return RepeatStatus.FINISHED;

    }

}
