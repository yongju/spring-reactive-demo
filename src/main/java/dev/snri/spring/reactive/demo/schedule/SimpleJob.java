package dev.snri.spring.reactive.demo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleJob extends QuartzJobBean implements InterruptableJob {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("execute()");
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.info("interrupt()");
    }
}
