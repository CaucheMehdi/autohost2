package com.autohost.managerApp.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.autohost.managerApp.exitstatus.CustomStatus;
import com.autohost.managerApp.tasklet.CheckAvailabilityRessourceTasklet;
import com.autohost.managerApp.tasklet.OrderNewInstanceTasklet;
import com.autohost.managerApp.tasklet.SendToClientTasklet;

/**
 * Try to find a ressource with the given parameter in the context
 * If no ressource available, then command the modules to order , configure, register a ressource
 * and finally sent it back to the customer
 * @author mehdi
 *
 */

@Configuration
@EnableBatchProcessing
public class RessourceDisposerJob {

    @Autowired
    private JobBuilderFactory                 jobBuilderFactory;
    @Autowired
    private StepBuilderFactory                stepBuilderFactory;
    @Autowired
    private CheckAvailabilityRessourceTasklet ressourceCheckavailabilityRessourceTasklet;
    @Autowired
    private SendToClientTasklet               sendToClientTasklet;
    @Autowired
    private OrderNewInstanceTasklet           ordernewinstanceTasklet;

    @Bean(name = "getInstance")
    public Job job() {
        return jobBuilderFactory.get("job").start(checkRessourceAvalaibleStep()).on(CustomStatus.RESSOURCEFOUND).to(sendRessourceToCustomerAppStep())
                        .from(checkRessourceAvalaibleStep()).on(CustomStatus.RESSOURCENOTFOUND).to(orderNewInstanceStep()).from(orderNewInstanceStep())
                        .on(CustomStatus.RESSOURCEORDERED).to(sendRessourceToConfigurerAppStep()).end().build();
    }

    private Step sendRessourceToConfigurerAppStep() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Check if ressource requested is available
     * @return
     */
    @Bean
    protected Step checkRessourceAvalaibleStep() {
        return stepBuilderFactory.get("step1").tasklet(ressourceCheckavailabilityRessourceTasklet).build();
    }

    /**
     * Send ressource to customer App
     * @return
     */
    protected Step sendRessourceToCustomerAppStep() {
        return stepBuilderFactory.get("step2").tasklet(sendToClientTasklet).build();
    }

    /**
     * Ask to order ressource
     *
     */
    protected Step orderNewInstanceStep() {
        return stepBuilderFactory.get("step3").tasklet(ordernewinstanceTasklet).build();
    }

}
