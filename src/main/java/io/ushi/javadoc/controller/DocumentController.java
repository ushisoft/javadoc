package io.ushi.javadoc.controller;

import io.ushi.javadoc.domain.Document;
import io.ushi.javadoc.repository.DocumentRepository;
import io.ushi.javadoc.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Doc主页控制类
 * <p>
 * Created by zhouleibo on 2017/8/30.
 */
@RestController
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    DocumentService documentService;

    @RequestMapping(value = "/init")
    @Async
    public Future<String> nexusLookup() throws IOException {
        documentService.fetchAll();
        return new AsyncResult<>("init start...");
    }

    @RequestMapping(value = "/fetch")
    public void fetchOne(@RequestParam("g") String groupId, @RequestParam("a") String artifactId,
                         @RequestParam("v") String version) throws IOException {
        Document document = new Document();
        document.setGroupId(groupId);
        document.setArtifactId(artifactId);
        document.setVersion(version);
        documentService.fetchOne(document);
    }

    @RequestMapping(value = "/group/{gid}/artifacts", method = RequestMethod.GET)
    public List<Document> artifacts(@PathVariable("gid") String groupId) {

        return documentRepository.findByGroupId(groupId);
    }

    @RequestMapping(value = "/group/{gid}/artifact/{aid}/versions", method = RequestMethod.GET)
    public List<String> versions(@PathVariable("gid") String groupId, @PathVariable("aid") String artifactId) {

        List<String> g1 = new ArrayList<String>();
        g1.add("E");
        g1.add("F");

        List<String> g2 = new ArrayList<String>();
        g2.add("G");
        g2.add("H");

        return artifactId.equals("common") ? g1 : g2;
    }
}
