package com.autohost.configurator.job.tasklet;

import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.autohost.configurator.config.sysutils.PingHost;
import com.autohost.configurator.config.type.ubuntu.Configuration;
import com.pastdev.jsch.DefaultSessionFactory;
import com.pastdev.jsch.command.CommandRunner;
import com.pastdev.jsch.command.CommandRunner.ExecuteResult;

/**
 * Récupère une liste de commande passées en paramètre et l'ip de l'instance
 * où les exécuter.
 * @author mehdi
 *
 */
@Component
public class ExecuteCommandTasklet implements Tasklet, StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

    @SuppressWarnings("unchecked")
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ExecutionContext param = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        List<Configuration> listOfCommandToExecute = (List) param.get("command");
        String ip = (String) param.get("ip");
        String pwd = (String) param.get("pwd");

        // if host is reachable, execute command one by one, by checking in between the status of the last command
        // executed

        Integer idCommand = 0;
        Boolean statusCommand = true;
        Configuration command;
        if (PingHost.pingIt(ip)) {
            // get a SSH shell
            DefaultSessionFactory defaultSessionFactory = new DefaultSessionFactory("root", ip, 22);
            defaultSessionFactory.setUsername("root");
            defaultSessionFactory.setPassword(pwd);
            defaultSessionFactory.setConfig("StrictHostKeyChecking", "no");
            CommandRunner commandRunner = new CommandRunner(defaultSessionFactory);
            while (statusCommand && idCommand < listOfCommandToExecute.size()) {
                command = listOfCommandToExecute.get(idCommand);
                ExecuteResult result = commandRunner.execute(command.getCommandLine());
                if (0 == result.getExitCode()) {
                    System.out.println(result.getStdout());
                    idCommand++;
                    statusCommand = true;
                } else {
                    statusCommand = false;
                }
            }
            commandRunner.close();
        }
        System.out.println(listOfCommandToExecute);
        return RepeatStatus.FINISHED;
    }

}
