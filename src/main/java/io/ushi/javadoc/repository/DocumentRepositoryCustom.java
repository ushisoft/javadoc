package io.ushi.javadoc.repository;

import java.util.List;

/**
 * Created by zhouleibo on 2017/9/5.
 */
public interface DocumentRepositoryCustom {

    /**
     * get all group
     * @return
     */
    List distinctGroupIds();

    /**
     * get all artifact by group
     * @param groupId groupId
     * @return
     */
    List distinctArtifacts(String groupId);
}
