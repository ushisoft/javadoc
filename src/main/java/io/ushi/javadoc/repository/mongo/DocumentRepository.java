package io.ushi.javadoc.repository.mongo;

import io.ushi.javadoc.domain.mongo.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by zhouleibo on 2017/9/5.
 */
public interface DocumentRepository extends MongoRepository<Document, Long> {

    List<Document> findByGroupId(String groupId);

}
