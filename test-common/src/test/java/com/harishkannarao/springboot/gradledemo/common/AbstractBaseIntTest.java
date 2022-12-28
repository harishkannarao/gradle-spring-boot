package com.harishkannarao.springboot.gradledemo.common;

import com.harishkannarao.springboot.gradledemo.common.restassured.RestAssuredFactory;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.util.TestSocketUtils;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public abstract class AbstractBaseIntTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseIntTest.class);
    private static final int RANDOM_SERVER_PORT = TestSocketUtils.findAvailableTcpPort();

    @DynamicPropertySource
    static void registerTestProperties(DynamicPropertyRegistry registry) {
        registry.add("server.port", () -> String.valueOf(RANDOM_SERVER_PORT));
    }

    @Value("${test.application.url}")
    private String testApplicationUrl;

    @BeforeEach
    void beforeTestStart(TestInfo testInfo) {
        LOG.info("Test Context :: Starting test: {}", testInfo.getDisplayName());
    }

    @AfterEach
    void afterTestComplete(TestInfo testInfo) {
        LOG.info("Test Context :: Completing test: {}", testInfo.getDisplayName());
    }

    protected RequestSpecification createRequestSpec() {
        return createRequestSpec(true);
    }

    protected RequestSpecification createRequestSpec(boolean followRedirect) {
        return RestAssuredFactory.createRequestSpec(testApplicationUrl, followRedirect);
    }
}
