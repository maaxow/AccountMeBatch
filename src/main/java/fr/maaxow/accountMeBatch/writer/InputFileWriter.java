package fr.maaxow.accountMeBatch.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class InputFileWriter implements ItemWriter<String> {

    public static Logger LOGGER = LoggerFactory.getLogger(InputFileWriter.class);

    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        chunk.getItems().forEach(item -> LOGGER.info("Item: {}", item));
    }
}
