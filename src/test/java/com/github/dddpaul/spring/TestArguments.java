package com.github.dddpaul.spring;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestArguments {
    static Stream<Arguments> decryptRequests() {
        return Stream.of(
                Arguments.of("GET", "/decrypt"),
                Arguments.of("POST", "/decrypt")
        );
    }

    static Stream<Arguments> otherRequests() {
        return Stream.of(
                Arguments.of("GET", "/encrypt"),
                Arguments.of("POST", "/encrypt"),
                Arguments.of("GET", "/application/default"),
                Arguments.of("POST", "/application/default"),
                Arguments.of("GET", "/"),
                Arguments.of("POST", "/")
        );
    }
}
