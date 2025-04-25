package fr.maaxow.accountMeBatch.listener;

import fr.maaxow.accountMeBatch.config.ExecutionContextKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

@Component
public class JobListener implements JobExecutionListener {

    private final Logger LOGGER = LoggerFactory.getLogger(JobListener.class);

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("Before running Job[{}]", jobExecution.getJobInstance().getJobName());
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {

        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("Job[{}] succeed with {} files.",
                    jobExecution.getJobInstance().getJobName(),
                    jobExecution.getExecutionContext().get(ExecutionContextKey.NUMBER_OF_FILES));
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            LOGGER.error("Job[{}] failed.", jobExecution.getJobInstance().getJobName());
        }

    }
}
