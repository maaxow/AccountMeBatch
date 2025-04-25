package fr.maaxow.accountMeBatch.processor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;

public class InputFileProcess implements ItemProcessor<Resource, String> {

    public static Logger logger = LoggerFactory.getLogger(InputFileProcess.class);

    @Override
    public String process(@NonNull Resource item) throws Exception {
        logger.info("Processing Item: {}", item);
        return item.getFilename();
    }
}
