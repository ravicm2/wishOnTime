package com.wish.ontime.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
public class EventJobRunner {

    private static final Logger LOG = LoggerFactory.getLogger(EventJobRunner.class);

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job job;

    @Scheduled(cron = "0 0 0 * * ?")
    public void runJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        LOG.info("Remainder Job Executing");
        try {
            Map<String, JobParameter> maps = new HashMap<>();
            maps.put("time", new JobParameter(System.currentTimeMillis()));
            maps.put("date", new JobParameter(LocalDate.now().toString()));

            JobParameters jobParameters = new JobParameters(maps);
            JobExecution execution = this.launcher.run(this.job, jobParameters);

            LOG.info("Job Execution Status{}:", execution.getStatus());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            LOG.error("Error while scheduler executes");
            throw e;
        }
    }
}
