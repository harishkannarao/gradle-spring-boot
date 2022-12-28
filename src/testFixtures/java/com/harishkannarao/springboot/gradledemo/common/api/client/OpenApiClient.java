package com.harishkannarao.springboot.gradledemo.common.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class OpenApiClient {

    private final RequestSpecification requestSpecification;

    public OpenApiClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response getOpenApi() {
        return given()
                .spec(requestSpecification)
                .basePath("/api-docs")
                .accept(ContentType.JSON)
                .get();
    }
}
