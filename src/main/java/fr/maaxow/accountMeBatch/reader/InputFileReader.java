package fr.maaxow.accountMeBatch.reader;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.ResourcesItemReader;
import org.springframework.core.io.Resource;

import java.util.Arrays;

public class InputFileReader extends ResourcesItemReader {

    public static Logger LOGGER = (Logger) LoggerFactory.getLogger(InputFileReader.class);

    public InputFileReader(Resource[] resources) {
        super();
        setResources(resources);
        Arrays.stream(resources).toList().stream().map(Resource::getFilename).forEach(System.out::println);
        LOGGER.info("Found " + resources.length + " files");
    }
}
