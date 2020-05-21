package com.harishkannarao.springboot.gradledemo.common.api.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SampleWebPageClient {

    private final RequestSpecification requestSpecification;

    public SampleWebPageClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response get() {
        return given()
                .spec(requestSpecification)
                .basePath("/web/sample")
                .get();
    }
}
