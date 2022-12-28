package com.harishkannarao.springboot.gradledemo.common.api.client;

import com.harishkannarao.springboot.gradledemo.common.api.client.dto.EchoTestDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class EchoApiClient {

    private final RequestSpecification requestSpecification;

    public EchoApiClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response post(String id, List<EchoTestDto> input) {
        return given()
                .spec(requestSpecification)
                .basePath("/api/echo/".concat(id))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(input)
                .post();
    }

}
