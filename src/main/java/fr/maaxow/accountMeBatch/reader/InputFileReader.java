package fr.maaxow.accountMeBatch.reader;

import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class InputFileReader extends MultiResourceItemReader<String> {

    public InputFileReader(String inputDirectory) {
        super();
        Resource resource = new ClassPathResource(inputDirectory);
        Resource[] resources = new Resource[] {resource};
        setResources(resources);
        System.out.println("########" + inputDirectory);
    }
}
