package io.ushi.javadoc.service;

import io.ushi.javadoc.domain.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NexusServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(NexusServiceTest.class);

    @Autowired
    private NexusService nexusService;

    @Test
    public void findAllJavadoc() {

        List<Document> allJavadoc = nexusService.findAllJavadoc();
        for (Document document : allJavadoc) {
            logger.info("{}:{}:{}", document.getGroupId(), document.getArtifactId(), document.getVersion());
        }
    }

}