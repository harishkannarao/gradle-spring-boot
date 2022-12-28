package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.api.client.SecurityReferenceApiClient;
import com.harishkannarao.springboot.gradledemo.common.api.client.dto.ValidationErrorResponseTestDto;
import com.harishkannarao.springboot.gradledemo.common.api.client.dto.ValidationErrorTestDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class SecurityReferenceApiIntTest extends AbstractDefaultProfileIntegrationTest {

    @Test
    void returns_success_for_valid_request() {
        Response response = new SecurityReferenceApiClient(createRequestSpec())
                .get("valid_token", "test-id");

        assertThat(response.statusCode(), equalTo(204));
    }

    @Test
    void returns_validation_error_for_empty_id() {
        Response response = new SecurityReferenceApiClient(createRequestSpec())
                .get("valid_token", "");

        assertThat(response.statusCode(), equalTo(400));

        ValidationErrorResponseTestDto responseDto = response.jsonPath().getObject("", ValidationErrorResponseTestDto.class);
        assertThat(responseDto.getMessage(), equalTo("Validation Error"));
        ValidationErrorTestDto idError = ValidationErrorTestDto.builder()
                .propertyPath("test.id")
                .message("size must be between 3 and 20")
                .build();
        assertThat(responseDto.getValidationErrors(), containsInAnyOrder(idError));
    }

    @Test
    void returns_authentication_error_for_unauthenticated_request() {
        Response response = new SecurityReferenceApiClient(createRequestSpec())
                .get("");

        assertThat(response.statusCode(), equalTo(401));
        assertThat(response.jsonPath().getString("path"), equalTo("/test/api/reference/security"));
    }

    @Test
    void returns_authentication_error_for_invalid_token() {
        Response response = new SecurityReferenceApiClient(createRequestSpec())
                .get("invalid_token", "");

        assertThat(response.statusCode(), equalTo(403));
        assertThat(response.jsonPath().getString("path"), equalTo("/test/api/reference/security"));
    }
}
