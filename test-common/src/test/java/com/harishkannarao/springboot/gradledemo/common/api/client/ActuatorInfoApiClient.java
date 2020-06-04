package com.harishkannarao.springboot.gradledemo.common.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ActuatorInfoApiClient {

    private final RequestSpecification requestSpecification;

    public ActuatorInfoApiClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response get() {
        return given()
                .spec(requestSpecification)
                .basePath("/info")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get();
    }

}
