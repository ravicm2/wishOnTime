package com.wish.ontime.batch_remainder.config;

import com.wish.ontime.batch_remainder.chunk.EventWriter;
import com.wish.ontime.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
@EnableBatchProcessing
public class RemainderBatchConfiguration {

    private static final String EVENT_REMAINDER_STEP_NAME = "eventRemainderStep";

    private static final String EVENT_REMAINDER_JOB_NAME = "eventRemainderJobName";

    private static final String CSV_READER = "CSVReader";

    private int chunkSize=100;

    @Bean
    public Job jobAndStepCreation(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                                  ItemReader itemReader, EventWriter eventWriter){

        Step step = stepBuilderFactory.get(EVENT_REMAINDER_STEP_NAME)
                .<User,User>chunk(chunkSize)
                .reader(itemReader)
                .processor(new PassThroughItemProcessor<>())
                .writer(eventWriter)
                .build();


        return jobBuilderFactory.get(EVENT_REMAINDER_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
    @Bean
    public FlatFileItemReader<User> fileItemReader(@Value("${input}") Resource resource){
        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName(CSV_READER);
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());

        return flatFileItemReader;
    }

    private LineMapper<User> lineMapper() {
        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]
                {"fromAdd","toAdd","senderName","receiverName","remainder","eventType","eventDate","personalMessage"});

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}
