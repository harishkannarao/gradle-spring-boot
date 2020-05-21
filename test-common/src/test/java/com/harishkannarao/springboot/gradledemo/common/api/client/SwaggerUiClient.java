package com.harishkannarao.springboot.gradledemo.common.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SwaggerUiClient {

    private final RequestSpecification requestSpecification;

    public SwaggerUiClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response getSwaggerUi() {
        return given()
                .spec(requestSpecification)
                .basePath("/swagger-ui.html")
                .get();
    }
}
