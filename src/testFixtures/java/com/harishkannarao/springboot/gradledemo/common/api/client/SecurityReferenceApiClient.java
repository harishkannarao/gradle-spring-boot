package com.harishkannarao.springboot.gradledemo.common.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class SecurityReferenceApiClient {

    private final RequestSpecification requestSpecification;

    public SecurityReferenceApiClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response get(String securityToken, String id) {
        RequestSpecification request = given()
                .spec(requestSpecification)
                .basePath("/test/api/reference/security")
                .queryParam("id", id)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
        Optional.ofNullable(securityToken)
                .ifPresent(s -> request.header("Authorization", s));
        return request.get();
    }

    public Response get(String id) {
        return get(null, id);
    }
}
