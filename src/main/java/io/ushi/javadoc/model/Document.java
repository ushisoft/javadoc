package io.ushi.javadoc.model;

/**
 * Maven包
 *
 * Created by zhouleibo on 2017/8/30.
 */
public class Document {

    String groupId;

    String artifactId;

    String version;

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
}
