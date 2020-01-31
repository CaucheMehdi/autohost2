package com.autohost2.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.autohost2.tasklet.CheckerInstanceTasklet;
import com.autohost2.tasklet.OrderInstanceTasklet;
import com.autohost2.tasklet.SaveNewInstanceTasklet;
import com.autohost2.tasklet.SendToManagerTasklet;

/**
 * Job qui permet de commander une instance auprès de Vultr
 * @author mehdi
 *
 */

@Component
public class OrderInstanceJob {

    @Autowired
    private JobBuilderFactory  jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    OrderInstanceTasklet       orderInstanceTasklet;
    @Autowired
    SaveNewInstanceTasklet     saveNewInstanceTasklet;
    @Autowired
    CheckerInstanceTasklet     checkerInstanceTasklet;
    @Autowired
    SendToManagerTasklet       sendToManagerTasklet;

    @Bean(name = "orderInstance")
    public Job job() {
        return jobBuilderFactory.get("job").start(orderInstance()).next(saveNewInstance()).next(waitForready()).next(sendToManager()).build();
    }

    @Bean
    protected Step orderInstance() {
        return stepBuilderFactory.get("step1").tasklet(orderInstanceTasklet).build();
    }

    @Bean
    protected Step saveNewInstance() {
        return stepBuilderFactory.get("step2").tasklet(saveNewInstanceTasklet).build();
    }

    @Bean
    protected Step waitForready() {
        return stepBuilderFactory.get("step3").tasklet(checkerInstanceTasklet).build();
    }

    @Bean
    protected Step sendToManager() {
        // TODO Auto-generated method stub
        return stepBuilderFactory.get("step4").tasklet(sendToManagerTasklet).build();
    }

}
