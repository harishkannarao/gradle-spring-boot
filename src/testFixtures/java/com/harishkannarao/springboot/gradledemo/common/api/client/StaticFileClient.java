package com.harishkannarao.springboot.gradledemo.common.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class StaticFileClient {

    private final RequestSpecification requestSpecification;

    public StaticFileClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response get(String path) {
        return given()
                .spec(requestSpecification)
                .basePath(path)
                .get();
    }
}
