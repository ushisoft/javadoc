package io.ushi.javadoc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 没有写接口，有复杂场景时重构
 *
 * Created by zhouleibo on 2017/8/30.
 */
@Service
public class DocumentCollectService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentCollectService.class);

    public void sayHello() {
        logger.info("Hello World!");
    }
}
