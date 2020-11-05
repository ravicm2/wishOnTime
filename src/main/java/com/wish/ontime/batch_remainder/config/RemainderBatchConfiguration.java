package com.wish.ontime.batch_remainder.config;

import com.wish.ontime.Entity.UserEntity;
import com.wish.ontime.batch_remainder.chunk.EventReader;
import com.wish.ontime.batch_remainder.chunk.EventWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class RemainderBatchConfiguration {

    private static final String EVENT_REMAINDER_STEP_NAME = "eventRemainderStep";

    private static final String EVENT_REMAINDER_JOB_NAME = "eventRemainderJobName";

    private int chunkSize=100;

    @Bean
    public Job jobAndStepCreation(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                   EventReader eventReader, EventWriter eventWriter){

        Step step = stepBuilderFactory.get(EVENT_REMAINDER_STEP_NAME)
                .<UserEntity,UserEntity>chunk(chunkSize)
                .reader(eventReader)
                .processor(new PassThroughItemProcessor<>())
                .writer(eventWriter)
                .build();


        return jobBuilderFactory.get(EVENT_REMAINDER_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

}
