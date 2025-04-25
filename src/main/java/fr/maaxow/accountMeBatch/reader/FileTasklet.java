package fr.maaxow.accountMeBatch.reader;

import ch.qos.logback.classic.Logger;
import fr.maaxow.accountMeBatch.config.ExecutionContextKey;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTasklet implements Tasklet {

    public static Logger LOGGER = (Logger) LoggerFactory.getLogger(FileTasklet.class);

    private final ResourceLoader resourceLoader;
    private final String pathPattern;

    public FileTasklet(String pathPattern, ResourceLoader resourceLoader) {
        this.pathPattern = pathPattern;
        this.resourceLoader = resourceLoader;
        LOGGER.info("FileTasklet(PathPattern: {} )", pathPattern);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("Executing task " + this.getClass().getSimpleName());
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources(pathPattern);
        List<File> files = new ArrayList<>(0);
        for(Resource resource : resources) {
            files.add(resource.getFile());
        }
        contribution.getStepExecution().getJobExecution()
                .getExecutionContext()
                .put(ExecutionContextKey.NUMBER_OF_FILES, files.size());
        contribution.getStepExecution().getJobExecution()
                .getExecutionContext()
                .put(ExecutionContextKey.LIST_OF_FILES, files);

        return RepeatStatus.FINISHED;
    }
}
