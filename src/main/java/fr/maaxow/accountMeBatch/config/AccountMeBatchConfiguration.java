package fr.maaxow.accountMeBatch.config;

import fr.maaxow.accountMeBatch.reader.InputFileReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@EnableElasticsearchRepositories(
        basePackages = "org.springframework.data.elasticsearch.repository"
)
public class AccountMeBatchConfiguration /*extends ElasticsearchConfiguration*/ {

   /*
    Stuff's list:
    1. Extract list of file from 'account-me.directory.input' Read the pdf
    2. Determine the user
    3. Determine the Account
     */

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Bean
    public Job accountMeBatchJob(JobRepository jobRepository, Step readStep) {
        return new JobBuilder("accountMeJob", jobRepository)
                .start(readStep)
                .build();
    }

    @Bean
    public Step readStep(JobRepository jobRepository,
                         PlatformTransactionManager platformTransactionManager,
                         @Value("${account-me.directory.input}") String inputDirectory) {
        return new StepBuilder("readStep", jobRepository)
                .chunk(10, platformTransactionManager)
                .reader(new InputFileReader(inputDirectory))
                //.processor()
                .writer(new FlatFileItemWriter<>())
                .build();
    }
}
