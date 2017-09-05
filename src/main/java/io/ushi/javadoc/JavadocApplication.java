package io.ushi.javadoc;

import io.ushi.javadoc.domain.mongo.Document;
import io.ushi.javadoc.repository.mongo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavadocApplication implements CommandLineRunner {

    @Autowired
    private DocumentRepository documentRepository;

    public static void main(String[] args) {
        SpringApplication.run(JavadocApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        Document doc = new Document();
        doc.setGroupId("mongoG");
        doc.setArtifactId("mongoA");
        doc.setVersion("mongoV");

        documentRepository.save(doc);
    }
}
