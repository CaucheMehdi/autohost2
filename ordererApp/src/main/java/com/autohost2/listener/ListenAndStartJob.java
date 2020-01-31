package com.autohost2.listener;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ListenAndStartJob implements ApplicationListener<CustomSpringEvent> {
    @Autowired
    @Qualifier("orderInstance")
    Job orderInstance;

    @Autowired
    JobLauncher jobLauncher;

    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        com.autohost2.listener.CustomSpringEvent.Signal eventReceived = event.getMessage();
        switch (eventReceived) {
        case ORDER_RESSOURCE:
            try {
                orderNewInstance(event);
            } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            break;

        }

    }

    private ExitStatus orderNewInstance(CustomSpringEvent event)
                    throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        JobParameters paramaters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("size", event.getRessource().getSize())
                        .addString("os", event.getRessource().getOs()).addString("configuration", event.getRessource().getApplication())
                        .addString("provider", event.getRessource().getProvider()).addString("place", event.getRessource().getPlace())
                        .addString("trackerId", event.getRessource().getTrackingId()).toJobParameters();

        JobExecution execution = jobLauncher.run(orderInstance, paramaters);
        return execution.getExitStatus();

    }

}
