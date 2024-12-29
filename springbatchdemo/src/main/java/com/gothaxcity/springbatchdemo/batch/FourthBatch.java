package com.gothaxcity.springbatchdemo.batch;

import com.gothaxcity.springbatchdemo.entity.AfterEntity;
import com.gothaxcity.springbatchdemo.repository.AfterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class FourthBatch {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final AfterRepository afterRepository;


    @Bean
    public Job fourthJob() {
        System.out.println("Fourth Job");

        return new JobBuilder("fourthJob", jobRepository)
                .start(fourthStep())
                .build();
    }


    @Bean
    public Step fourthStep() {
        System.out.println("Fourth Step");
        return new StepBuilder("fourthStep", jobRepository)
                .chunk(10, transactionManager)
                .reader(new CsvReader())
                .writer(new Csv2DBWriter(afterRepository))
                .build();
    }
}
