package com.harishkannarao.springboot.gradledemo;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ActiveProfiles(profiles = {"local","integration-test"})
public abstract class AbstractBaseIntTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseIntTest.class);

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
        return new RequestSpecBuilder()
                .setBaseUri(testApplicationUrl)
                .setConfig(createRestAssuredConfig(followRedirect))
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    private RestAssuredConfig createRestAssuredConfig(boolean followRedirect) {
        RestAssuredConfig restAssuredConfig = RestAssuredConfig.config()
                .redirect(createRedirectConfig(followRedirect));
        return CurlLoggingRestAssuredConfigFactory.updateConfig(restAssuredConfig);
    }

    private RedirectConfig createRedirectConfig(boolean followRedirect) {
        return RedirectConfig.redirectConfig().followRedirects(followRedirect);
    }
}
