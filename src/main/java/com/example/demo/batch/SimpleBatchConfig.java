package com.example.demo.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration // Spring 설정 클래스를 나타냄
@RequiredArgsConstructor // Lombok을 사용하여 final 필드에 대한 생성자를 자동으로 생성
public class SimpleBatchConfig {

    @Bean
    public Job myjob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        System.out.println("myJob"); // Job 시작을 알리는 메시지 출력
        return new JobBuilder("myJob", jobRepository) // Job 빌더를 사용하여 Job 생성
                .start(helloStep(jobRepository, platformTransactionManager)) // 첫 번째 Step 지정
                .next(worldStep(jobRepository, platformTransactionManager)) // 두 번째 Step 지정
                .next(timeStep(jobRepository, platformTransactionManager)) // 세 번째 Step 지정
                .build(); // Job 빌드
    }

    @Bean
    public Step helloStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        System.out.println("helloStep"); // Step 시작을 알리는 메시지 출력
        return new StepBuilder("myHelloStep", jobRepository) // Step 빌더를 사용하여 Step 생성
                .tasklet(new SimpleMessageTasklet("Hello!"), platformTransactionManager) // Tasklet 지정
                .build(); // Step 빌드
    }

    @Bean
    public Step worldStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        System.out.println("worldStep"); // Step 시작을 알리는 메시지 출력
        return new StepBuilder("myWorldStep", jobRepository) // Step 빌더를 사용하여 Step 생성
                .tasklet(new SimpleMessageTasklet("world!"), platformTransactionManager) // Tasklet 지정
                .build(); // Step 빌드
    }

    @Bean
    public Step timeStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        System.out.println("timeStep"); // Step 시작을 알리는 메시지 출력
        return new StepBuilder("myTimeStep", jobRepository) // Step 빌더를 사용하여 Step 생성
                .tasklet(new SimpleMessageTasklet("Now is a timeStep."), platformTransactionManager) // Tasklet 지정
                .build(); // Step 빌드
    }
}
