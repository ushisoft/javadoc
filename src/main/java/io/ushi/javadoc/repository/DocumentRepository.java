package io.ushi.javadoc.repository;

import io.ushi.javadoc.domain.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by zhouleibo on 2017/9/5.
 */
public interface DocumentRepository extends MongoRepository<Document, String>, DocumentRepositoryCustom {

    List<Document> findByGroupIdAndArtifactId(String groupId, String artifactId);

}
