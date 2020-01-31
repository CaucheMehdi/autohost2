package com.autohost2.controller;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.autohost2.listener.CustomSpringEvent;

import entityDTO.RessourceDTO;

/**
 * Controlleur Rest qui déclenche des jobs sur des appels rest
 *
 * @author mehdi
 *
 */
@EnableAsync
@RestController
public class OrdererController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/server/order/instance")
    public String createServer(@RequestAttribute RessourceDTO ressource)
                    throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        // parameters for server creation
        // ici j' appelerais le job
        CustomSpringEvent checkRessource = new CustomSpringEvent(this, CustomSpringEvent.Signal.ORDER_RESSOURCE, ressource);
        applicationEventPublisher.publishEvent(checkRessource);
        return "Batch job has been invoked";
    }

    /**

    @throws JobParametersInvalidException
     * @throws JobInstanceAlreadyCompleteException
     * @throws JobRestartException
     * @throws JobExecutionAlreadyRunningException
     * @PostConstruct
    private void init() {
    	support = new PropertyChangeSupport(this);
    	this.addPropertyChangeListener(checker);
    	}
    
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    	}

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    	}

    public void setHost(VultrHost value) {
        support.firePropertyChange("newhost",this.newhost, value);
        this.newhost = value;
    	}
    */

}
