package com.harishkannarao.springboot.gradledemo;

import com.harishkannarao.springboot.gradledemo.common.restassured.RestAssuredFactory;
import com.harishkannarao.springboot.gradledemo.properties.TestProperties;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class AbstractBaseAcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractBaseAcceptanceTest.class);

    protected final String environment = resolveEnvironment();

    protected final TestProperties testProperties = new TestProperties(String.format("properties/%s.properties", environment));

    @BeforeEach
    void beforeTestStart(TestInfo testInfo) {
        log.info("Test Context :: Starting test: {}", testInfo.getDisplayName());
    }

    @AfterEach
    void afterTestComplete(TestInfo testInfo) {
        log.info("Test Context :: Completing test: {}", testInfo.getDisplayName());
    }

    protected RequestSpecification createRequestSpec() {
        return createRequestSpec(true);
    }

    protected RequestSpecification createRequestSpec(boolean followRedirect) {
        return RestAssuredFactory.createRequestSpec(testProperties.applicationBaseUrl(), followRedirect);
    }

    private String resolveEnvironment() {
        return Optional.ofNullable(System.getenv("TEST_ENVIRONMENT"))
                .orElseGet(() -> Optional.ofNullable(System.getProperty("testEnvironment"))
                        .orElse("local"));
    }
}
