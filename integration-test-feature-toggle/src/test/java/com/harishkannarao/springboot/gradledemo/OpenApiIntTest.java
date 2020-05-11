package com.harishkannarao.springboot.gradledemo;

import com.harishkannarao.springboot.gradledemo.common.api.client.OpenApiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class OpenApiIntTest extends AbstractBaseFeatureToggleIntTest {

	@Test
	void verify_open_api_and_swagger_ui_are_enabled() {
		OpenApiClient openApiClient = new OpenApiClient(createRequestSpec());
		Response openApiResponse = openApiClient.getOpenApi();
		Response swaggerUiResponse = openApiClient.getSwaggerUi();

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

		OpenApiClient openApiClient = new OpenApiClient(createRequestSpec());
		Response openApiResponse = openApiClient.getOpenApi();
		Response swaggerUiResponse = openApiClient.getSwaggerUi();

		assertThat(openApiResponse.statusCode(), equalTo(404));
		assertThat(swaggerUiResponse.statusCode(), equalTo(404));
	}
}
