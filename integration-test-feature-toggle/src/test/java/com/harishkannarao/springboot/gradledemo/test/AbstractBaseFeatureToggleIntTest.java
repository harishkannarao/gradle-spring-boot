package com.harishkannarao.springboot.gradledemo.test;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import com.harishkannarao.springboot.gradledemo.test.util.TestApplication;
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
import org.springframework.beans.BeansException;
import org.springframework.core.env.Environment;

import java.util.List;

public abstract class AbstractBaseFeatureToggleIntTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseFeatureToggleIntTest.class);

    @BeforeEach
    void globalSetUp(TestInfo testInfo) {
        LOG.info("Test Context :: Starting test: {}", testInfo.getDisplayName());
        restartWithDefaultProperties();
    }

    @AfterEach
    void afterTestComplete(TestInfo testInfo) {
        LOG.info("Test Context :: Completing test: {}", testInfo.getDisplayName());
    }

    protected void restartWithDefaultProperties() {
        TestApplication.INSTANCE.restartWithDefaultProperties();
    }

    protected void restartWithProperties(List<String> properties) {
        TestApplication.INSTANCE.restartWithProperties(properties);
    }

    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return TestApplication.INSTANCE.getApplicationContext().getBean(requiredType);
    }

    protected RequestSpecification createRequestSpec() {
        return createRequestSpec(true);
    }

    protected RequestSpecification createRequestSpec(boolean followRedirect) {
        String testApplicationUrl = getBean(Environment.class).getRequiredProperty("test.application.url");
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
