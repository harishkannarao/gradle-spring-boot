package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.api.client.SampleApiClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

class SampleApiIntTest extends AbstractDefaultProfileIntegrationTest {

	@Test
	void verify_sample_api() {
		Response response = new SampleApiClient(createRequestSpec()).get();

		assertThat(response.statusCode(), equalTo(200));
		assertThat(response.contentType(), containsString("application/json"));
		JsonPath jsonPath = response.jsonPath();
		String actualMessage = jsonPath.get("message");
		assertThat(actualMessage, equalTo("My Sample Api Message"));
	}
}
