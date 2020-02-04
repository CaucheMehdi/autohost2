package com.autohost.managerApp.controller;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autohost.managerApp.listener.CustomSpringEvent;

import entityDTO.dto.RessourceDTO;

@RestController
public class ManagerRestController {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * Entry Point for client (Client Manager to request a ressource)
     *
     * Process: 1 - Check ressource availability 2 - If ressource available, send
     * ressource to Client Manager, update ressource status 3- If no ressource
     * available 4- Send request to Orderer 5- Register New ressource 6- Send
     * request to configurator 7- Update New ressource 8- Send ressource to Client
     * Manager 9- Update ressource status 10- Send request to Monitorer
     *
     * @param ressourceSize
     * @param configuration
     * @param os
     * @throws JobExecutionAlreadyRunningException
     * @throws JobRestartException
     * @throws JobInstanceAlreadyCompleteException
     * @throws JobParametersInvalidException
     */

    @PostMapping("manager/post/ressource")
    public RessourceDTO sendRessource(@RequestBody RessourceDTO ressource)
                    throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        // here instead of running the job from here, we will send a signal to the class
        // responsible to start the job, with as a parameter, and ID to track this
        // ressource
        // this ID is immediately returned so the client can be advertised later that
        // the job is finished
        // send signal that indicates to check ressource
        if (ressource != null && !ressource.getApplication().isEmpty() && !ressource.getOs().isEmpty() && !ressource.getPlace().isEmpty()
                        && !ressource.getProvider().isEmpty() && !ressource.getSize().isEmpty() && !ressource.getClientTid().isEmpty()
                        && !ressource.getTrackingId().isEmpty()) {

            CustomSpringEvent checkRessource = new CustomSpringEvent(this, CustomSpringEvent.Signal.CHECK_RESSOURCE_AVAILABILITY, ressource);
            applicationEventPublisher.publishEvent(checkRessource);
        } else {
            // send empty ressource if request is invalid
            return new RessourceDTO();
        }
        // send ressource with TrackerId
        return ressource;
    }

    @PostMapping("manager/configure/ressource")
    public RessourceDTO configureRessource(@RequestBody RessourceDTO ressource)
                    throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        // here instead of running the job from here, we will send a signal to the class
        // responsible to start the job, with as a parameter, and ID to track this
        // ressource
        // this ID is immediately returned so the client can be advertised later that
        // the job is finished
        // send signal that indicates to check ressource
        if (ressource != null && !ressource.getApplication().isEmpty() && !ressource.getOs().isEmpty() && !ressource.getIp().isEmpty()
                        && !ressource.getPwd().isEmpty()) {

            CustomSpringEvent configRessource = new CustomSpringEvent(this, CustomSpringEvent.Signal.CONFIGURE_INSTANCE, ressource);
            applicationEventPublisher.publishEvent(configRessource);
        } else {
            // send empty ressource if request is invalid
            return new RessourceDTO();
        }
        // send ressource with TrackerId
        return ressource;
    }

}