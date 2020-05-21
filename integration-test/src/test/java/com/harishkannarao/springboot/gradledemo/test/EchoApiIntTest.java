package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.api.client.EchoApiClient;
import com.harishkannarao.springboot.gradledemo.common.api.client.EchoApiClient.TestEcho;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class EchoApiIntTest extends AbstractBaseIntTest {

	@Test
	void verify_echo_api() {
		TestEcho echo1 = TestEcho.builder()
				.intProperty(1)
				.stringProperty("String1")
				.build();
		TestEcho echo2 = TestEcho.builder()
				.intProperty(2)
				.stringProperty("String2")
				.build();
		List<TestEcho> input = List.of(
				echo1,
				echo2
		);

		Response response = new EchoApiClient(createRequestSpec())
				.post(input);

		assertThat(response.statusCode(), equalTo(200));
		assertThat(response.contentType(), containsString("application/json"));
		List<TestEcho> result = response.jsonPath().getList("", TestEcho.class);
		assertThat(result, containsInAnyOrder(echo1, echo2));
	}
}
