package com.github.dddpaul.spring;


import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "spring.cloud.config.server.git.uri=http://localhost:80"
})
public class DefaultConfigurationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @MethodSource("com.github.dddpaul.spring.TestArguments#decryptRequests")
    void decrypt_endpoint_should_be_forbidden_by_default(String method, String url) {
        ResponseEntity<String> entity = restTemplate
                .exchange(url, HttpMethod.resolve(method), HttpEntity.EMPTY, String.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @ParameterizedTest
    @MethodSource("com.github.dddpaul.spring.TestArguments#otherRequests")
    void other_endpoints_should_be_accessible(String method, String url) {
        ResponseEntity<String> entity = restTemplate
                .exchange(url, HttpMethod.resolve(method), HttpEntity.EMPTY, String.class);

        assertThat(entity.getStatusCode()).isNotEqualTo(HttpStatus.FORBIDDEN);
    }
}