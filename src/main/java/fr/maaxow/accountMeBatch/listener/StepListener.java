package fr.maaxow.accountMeBatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

@Component
public class StepListener implements StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepListener.class);

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.info("Before running Step[{}]", stepExecution.getStepName());
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        LOGGER.info("After running Step[{}]", stepExecution.getStepName());
        return ExitStatus.COMPLETED;
    }
}
