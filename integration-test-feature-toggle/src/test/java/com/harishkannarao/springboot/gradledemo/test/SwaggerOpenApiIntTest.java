package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.api.client.OpenApiClient;
import com.harishkannarao.springboot.gradledemo.common.api.client.SwaggerUiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class SwaggerOpenApiIntTest extends AbstractBaseFeatureToggleIntTest {

	@Test
	void verify_open_api_and_swagger_ui_are_enabled() {
		Response openApiResponse = new OpenApiClient(createRequestSpec()).getOpenApi();
		Response swaggerUiResponse = new SwaggerUiClient(createRequestSpec()).getSwaggerUi();

		assertThat(openApiResponse.statusCode(), equalTo(200));
		assertThat(swaggerUiResponse.statusCode(), equalTo(200));
	}

	@Test
	void verify_open_api_and_swagger_ui_are_disabled() {
		restartWithProperties(
				List.of(
						"springdoc.api-docs.enabled=false"
				)
		);

		Response openApiResponse = new OpenApiClient(createRequestSpec()).getOpenApi();
		Response swaggerUiResponse = new SwaggerUiClient(createRequestSpec()).getSwaggerUi();

		assertThat(openApiResponse.statusCode(), equalTo(404));
		assertThat(swaggerUiResponse.statusCode(), equalTo(404));
	}
}
