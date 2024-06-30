package com.example.demo.controller;

import com.example.demo.batch.SimpleBatchConfig;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DemoController {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    SimpleBatchConfig simpleBatchConfig;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @GetMapping("/job")
    public String runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(
                simpleBatchConfig.myjob(jobRepository, platformTransactionManager),
                new JobParametersBuilder()
                        .addString("dummy", LocalDateTime.now().toString()).toJobParameters()
        );
        return "OK";
    }
}
