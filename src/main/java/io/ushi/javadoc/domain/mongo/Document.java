package io.ushi.javadoc.domain.mongo;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;

/**
 * Created by zhouleibo on 2017/9/5.
 */
@org.springframework.data.mongodb.core.mapping.Document
public class Document {

    @Id
    private String id;

    private String groupId;

    private String artifactId;

    private String version;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("groupId", groupId)
                .add("artifactId", artifactId)
                .add("version", version)
                .toString();
    }
}
