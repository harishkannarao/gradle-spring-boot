package com.harishkannarao.springboot.gradledemo.common.restassured;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class RestAssuredFactory {
    public static RequestSpecification createRequestSpec(String baseUrl, boolean followRedirect) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setConfig(createRestAssuredConfig(followRedirect))
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    private static RestAssuredConfig createRestAssuredConfig(boolean followRedirect) {
        RestAssuredConfig restAssuredConfig = RestAssuredConfig.config()
                .redirect(createRedirectConfig(followRedirect));
        return CurlLoggingRestAssuredConfigFactory.updateConfig(restAssuredConfig);
    }

    private static RedirectConfig createRedirectConfig(boolean followRedirect) {
        return RedirectConfig.redirectConfig().followRedirects(followRedirect);
    }
}
