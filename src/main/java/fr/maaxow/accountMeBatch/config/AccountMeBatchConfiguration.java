package fr.maaxow.accountMeBatch.config;

import fr.maaxow.accountMeBatch.listener.JobListener;
import fr.maaxow.accountMeBatch.listener.StepListener;
import fr.maaxow.accountMeBatch.reader.FileTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = "org.springframework.data.elasticsearch.repository"
)
public class AccountMeBatchConfiguration {

   /*
    Stuff's list:
    1. Extract list of file from 'account-me.directory.input' Read the pdf
    2. Determine the user
    3. Determine the Account
     */

//    @Bean
//    public JobRepository jobRepository(DataSource elasticsearchDatasource) throws Exception {
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(elasticsearchDatasource);
//        factory.setDatabaseType("elasticsearch");
//        //factory.setTransactionManager(transactionManager);
//        return factory.getObject();
//    }

//    @Bean
//    public DataSource elasticsearchDatasource(@Value("${spring.elasticsearch.username}") String username,
//                                              @Value("${spring.elasticsearch.password}") String password) {
//        String url = "jdbc:es://localhost:9200";
//        //DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//        //dataSourceBuilder.driverClassName("org.elasticsearch.xpack.sql.jdbc.EsDriver");
//        //dataSourceBuilder.url(url);
//        //dataSourceBuilder.username(username);
//        //dataSourceBuilder.password(password);
//        EsDataSource esDataSource = new EsDataSource();
//        esDataSource.setUrl(url);
//        Properties properties = new Properties();
//        //properties.put("driver-class-name", "org.elasticsearch.xpack.sql.jdbc.EsDriver");
//        properties.put("user", username);
//        properties.put("password", password);
//        esDataSource.setProperties(properties);
//        return esDataSource;
//    }

//    @Bean
//    public JdbcTransactionManager batchTransactionManager(DataSource dataSource) {
//        return new JdbcTransactionManager(dataSource);
//    }

//    @Bean
//    public JobLauncher jobLauncher(JobRepository jobRepository) {
//        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
//        jobLauncher.setJobRepository(jobRepository);
//        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        try {
//            jobLauncher.afterPropertiesSet();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return jobLauncher;
//    }

    @Bean
    public Job accountMeBatchJob(JobRepository jobRepository,
                                 JobListener jobListener,
                                 Step extractFileName) {
        return new JobBuilder("accountMeJob", jobRepository)
                .listener(jobListener)
                .start(extractFileName)
                .build();
    }

    @Bean
    public Step extractFileName(JobRepository jobRepository,
                                PlatformTransactionManager platformTransactionManager,
                                StepListener stepListener,
                                ResourceLoader resourceLoader,
                                @Value("${account-me.directory.input-pattern}") String filePattern){
        return new StepBuilder("extractFileNameStep", jobRepository)
                .tasklet(new FileTasklet(filePattern, resourceLoader), platformTransactionManager)
                .listener(stepListener)
                .build();
    }

//    @Bean
//    public Step readStep(JobRepository jobRepository,
//                         PlatformTransactionManager platformTransactionManager,
//                         StepListener stepListener,
//                         ItemReader<Resource> reader,
//                         ItemProcessor<Resource, String> processor,
//                         ItemWriter<Resource> writer) {
//        return new StepBuilder("readStep", jobRepository)
//                .chunk(10, platformTransactionManager)
//                .listener(stepListener)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                .build();
//    }

//    @Bean
//    public ItemReader<Resource> reader(@Value("classpath:${account-me.directory.input-pattern}") Resource[] resources) {
//        return new InputFileReader(resources);
//    }
//
//    @Bean
//    public ItemProcessor<Resource, String> processor() {
//        return new InputFileProcess();
//    }
//
//    @Bean
//    public ItemWriter<Resource> writer() {
//        return new InputFileWriter2();
//    }
}
