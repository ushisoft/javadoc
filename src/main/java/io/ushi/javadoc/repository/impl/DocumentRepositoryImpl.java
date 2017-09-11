package io.ushi.javadoc.repository.impl;

import io.ushi.javadoc.repository.DocumentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepositoryCustom {

    private static final String COLLECTION_NAME = "document";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List distinctGroupIds() {
        return mongoTemplate.getCollection(COLLECTION_NAME).distinct("groupId");
    }

    @Override
    public List distinctArtifacts(String groupId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("groupId").is(groupId));
        return mongoTemplate.getCollection(COLLECTION_NAME)
                .distinct("artifactId", query.getQueryObject());
    }
}
