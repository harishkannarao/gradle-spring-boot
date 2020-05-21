package com.harishkannarao.springboot.gradledemo.common.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.immutables.value.Value;

import java.util.List;

import static io.restassured.RestAssured.given;

public class EchoApiClient {

    private final RequestSpecification requestSpecification;

    public EchoApiClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response post(List<TestEcho> input) {
        return given()
                .spec(requestSpecification)
                .basePath("/api/echo")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(input)
                .post();
    }

    @Value.Immutable
    @JsonSerialize(as = ImmutableTestEcho.class)
    @JsonDeserialize(as = ImmutableTestEcho.class)
    public interface TestEcho {
        int intProperty();
        @JsonProperty("string_property") String stringProperty();

        static ImmutableTestEcho.Builder builder() {
            return ImmutableTestEcho.builder();
        }
    }
}
