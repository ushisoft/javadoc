package io.ushi.javadoc.service;

import io.ushi.javadoc.config.JavadocProperties;
import io.ushi.javadoc.config.NexusProperties;
import io.ushi.javadoc.domain.Document;
import io.ushi.javadoc.repository.DocumentRepository;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 没有写接口，有复杂场景时重构
 * <p>
 * Created by zhouleibo on 2017/8/30.
 */
@Service
public class DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

    @Autowired
    private NexusProperties nexusProperties;

    @Autowired
    private JavadocProperties javadocProperties;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private NexusService nexusService;

    public void sayHello() {

        logger.info("Hello World!");
    }

    public void fetchAll() throws IOException {

        // find javadoc list from nexus
        List<Document> allJavadoc = nexusService.findAllJavadoc();

        // create new directory
        File outputRoot = new File(javadocProperties.getOutput());
        outputRoot.mkdirs();

        File artifactPath;
        for (Document document : allJavadoc) {
            logger.info("resolve artifact[{}]...", makeId(document));
            // 已存在的略过
            if (exist(document)) {
                logger.info("artifact already exist.");
                continue;
            }
            artifactPath = new File(outputRoot, makePath(document));
            artifactPath.mkdirs();
            resolve(document, artifactPath);
            logger.info("download completed.");
            save(document);
            logger.info("mongo save completed.");
        }

        logger.info("nexus lookup end.");
    }

    public void fetchOne(Document document) throws IOException {

        File artifactPath = new File(javadocProperties.getOutput(), makePath(document));
        FileUtils.deleteDirectory(artifactPath);
        artifactPath.mkdirs();

        resolve(document, artifactPath);
        save(document);
    }

    private void resolve(Document document, File destination) throws IOException {

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(nexusProperties.getUrl())
                .append("content/repositories/releases/") // focus in releases repository
                .append(makeNexusPath(document));

        URL url = new URL(urlBuilder.toString());
        ZipInputStream zin = new ZipInputStream(url.openStream());
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            File f = new File(destination, ze.getName());

            // if dir, nothing to to
            if (ze.isDirectory()) {
                f.mkdirs();
                zin.closeEntry();
                continue;
            }

            // Let's start writing file
            OutputStream os = new FileOutputStream(f);
            ReadableByteChannel in = Channels.newChannel(zin);
            WritableByteChannel out = Channels.newChannel(os);
            ByteBuffer buffer = ByteBuffer.allocate(65536);
            while (in.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }

            zin.closeEntry();
        }
    }

    private void save(Document document) throws UnknownHostException {
        document.setId(makeId(document));
        documentRepository.save(document);
    }

    private boolean exist(Document documentQuery) {
        String id = documentQuery.getId();
        if (StringUtils.isEmpty(id)) {
            id = makeId(documentQuery);
        }
        return documentRepository.findOne(id) != null;
    }

    private String makeId(Document document) {
        return new StringBuilder(document.getGroupId())
                .append(":")
                .append(document.getArtifactId())
                .append(":")
                .append(document.getVersion())
                .toString();
    }

    private String makePath(Document document) {
        return new StringBuilder(document.getGroupId())
                .append("/")
                .append(document.getArtifactId())
                .append("/")
                .append(document.getVersion())
                .toString();
    }

    private String makeNexusPath(Document document) {
        return new StringBuilder().append(document.getGroupId().replaceAll("\\.", "/"))
                .append("/")
                .append(document.getArtifactId())
                .append("/")
                .append(document.getVersion())
                .append("/")
                .append(document.getArtifactId())
                .append("-")
                .append(document.getVersion())
                .append("-javadoc.jar")
                .toString();
    }
}
