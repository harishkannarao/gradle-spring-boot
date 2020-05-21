package com.harishkannarao.springboot.gradledemo.common.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SampleApiClient {

    private final RequestSpecification requestSpecification;

    public SampleApiClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response get() {
        return given()
                .spec(requestSpecification)
                .basePath("/api/sample")
                .accept(ContentType.JSON)
                .get();
    }
}
