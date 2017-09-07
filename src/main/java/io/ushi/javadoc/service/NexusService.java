package io.ushi.javadoc.service;

import io.ushi.javadoc.config.NexusProperties;
import io.ushi.javadoc.domain.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.Element;

import javax.xml.transform.Source;
import java.net.URI;
import java.util.List;

/**
 * calling nexus rest api
 * <p>
 * Created by zhouleibo on 2017/9/5.
 */
@Service
public class NexusService {

    private static final Logger logger = LoggerFactory.getLogger(NexusService.class);

    @Autowired
    private NexusProperties nexusProperties;

    private final RestTemplate restTemplate;

    private XPathOperations xPathTemplate = new Jaxp13XPathTemplate();

    public NexusService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Document> findAllJavadoc() {

        // build uri
        URI uri = UriComponentsBuilder.fromUriString(nexusProperties.getUrl())
                .path("/service/local/lucene/search")
                .queryParam("g", "com.qjdchina")
                .queryParam("repositoryId", "releases")
                .queryParam("c", "javadoc")
                .build()
                .encode()
                .toUri();

        logger.info("access nexus url: {}", uri.toString());

        // rest access
        Source artifacts = restTemplate.getForObject(uri, Source.class);

        // xml parse
        List<Document> documentList = xPathTemplate.evaluate("//data/artifact", artifacts, (node, i) -> {
            Document document = new Document();
            Element element = (Element) node;
            document.setGroupId(element.getElementsByTagName("groupId").item(0).getTextContent());
            document.setArtifactId(element.getElementsByTagName("artifactId").item(0).getTextContent());
            document.setVersion(element.getElementsByTagName("version").item(0).getTextContent());
            return document;
        });

        logger.info("get javadoc list success. size of list: {}", documentList.size());

        return documentList;
    }
}
