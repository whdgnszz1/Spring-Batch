package com.example.demo.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@RequiredArgsConstructor // Lombok을 사용하여 final 필드에 대한 생성자를 자동으로 생성
public class SimpleMessageTasklet implements Tasklet {
    private final String message; // 출력할 메시지를 저장하는 final 필드

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Message: " + message); // 메시지를 콘솔에 출력
        return RepeatStatus.FINISHED; // 작업이 완료되었음을 나타내는 상태 반환
    }
}
