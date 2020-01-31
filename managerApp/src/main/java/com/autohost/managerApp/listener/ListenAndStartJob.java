package com.autohost.managerApp.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.autohost.managerApp.listener.CustomSpringEvent.Signal;

@Component
public class ListenAndStartJob implements ApplicationListener<CustomSpringEvent> {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job ressourceDisposerjob;

    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        Signal eventReceived = event.getMessage();
        switch (eventReceived) {
        case CHECK_RESSOURCE_AVAILABILITY:
            try {
                startJobCheckRessourceAvailability(event);
            } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            break;

        case CONFIGURE_INSTANCE:
            startJobConfigureInstance(event);
            break;

        }

    }

    private void startJobConfigureInstance(CustomSpringEvent event) {
        // TODO Auto-generated method stub envoyé au configurator la ressource à configurer
        // send the ressource to the configurator
    }

    private ExitStatus startJobCheckRessourceAvailability(CustomSpringEvent event)
                    throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        JobParameters paramaters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("size", event.getRessource().getSize())
                        .addString("os", event.getRessource().getOs()).addString("configuration", event.getRessource().getApplication())
                        .addString("provider", event.getRessource().getProvider()).addString("place", event.getRessource().getPlace())
                        .addString("trackerId", event.getRessource().getTrackingId()).toJobParameters();

        JobExecution execution = jobLauncher.run(ressourceDisposerjob, paramaters);
        return execution.getExitStatus();

    }

}
