package com.autohost.configurator;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autohost.configurator.repository.ConfigurationRepository;

import entityDTO.RessourceDTO;

@RestController
public class ConfiguratorController {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("configInstance")
    Job mainJob;

    @Autowired
    ConfigurationRepository configRepo;

    // Invoke a job that take 2 parameters: ip of host, config to launch
    @PostMapping(value = "/configure/instance")
    public void configureInstance(@RequestBody RessourceDTO ressource)
                    throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        JobParameters parameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("configuration", ressource.getApplication())
                        .addString("ip", ressource.getIp()).addString("os", ressource.getOs()).addString("pwd", ressource.getPwd()).toJobParameters();
        jobLauncher.run(mainJob, parameters);

    }

}
