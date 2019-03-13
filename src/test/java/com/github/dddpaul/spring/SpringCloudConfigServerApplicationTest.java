package com.github.dddpaul.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {
        "spring.cloud.config.server.git.uri=http://localhost:80"
})
public class SpringCloudConfigServerApplicationTest {

    @Test
    public void contextLoads() {
    }
}
