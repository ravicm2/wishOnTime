package com.wish.ontime.batch_remainder.config;

import com.wish.ontime.batch_remainder.chunk.EventRemainderWriter;
import com.wish.ontime.batch_wishPeople.chunk.EventWishWriter;
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
import org.springframework.batch.item.file.transform.FlatFileFormatException;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.dao.DeadlockLoserDataAccessException;


@Configuration
@EnableBatchProcessing
public class RemainderBatchConfiguration {

    private static final String EVENT_REMAINDER_STEP_NAME = "eventRemainderStep";

    private static final String EVENT_WISH_STEP_NAME = "eventRemainderStep";

    private static final String EVENT_JOB_NAME = "eventJobName";

    private static final String CSV_READER = "CSVReader";

    @Bean
    public Job jobAndStepCreation(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                                  ItemReader<User> itemReader, EventRemainderWriter eventRemainderWriter,
                                  EventWishWriter eventWishWriter){

        Step step = stepBuilderFactory.get(EVENT_REMAINDER_STEP_NAME)
                .<User,User>chunk(100)
                .reader(itemReader)
                .processor(new PassThroughItemProcessor<>())
                .writer(eventRemainderWriter)
                .faultTolerant()
                .skipLimit(10)
                .skip(FlatFileFormatException.class)
                .retryLimit(3)
                .retry(DeadlockLoserDataAccessException.class)
                .build();

        Step step2 = stepBuilderFactory.get(EVENT_WISH_STEP_NAME)
                .<User,User>chunk(100)
                .reader(itemReader)
                .processor(new PassThroughItemProcessor<>())
                .writer(eventWishWriter)
                .faultTolerant()
                .skipLimit(10)
                .skip(FlatFileFormatException.class)
                .retryLimit(3)
                .retry(DeadlockLoserDataAccessException.class)
                .build();

        return jobBuilderFactory.get(EVENT_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .next(step2)
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
        lineTokenizer.setNames("fromAdd","toAdd","senderName","receiverName","remainder","eventType","eventDate","personalMessage");

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}
