package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.api.client.EchoApiClient;
import com.harishkannarao.springboot.gradledemo.common.api.client.dto.EchoTestDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class EchoApiIntTest extends AbstractBaseIntTest {

	@Test
	void verify_echo_api() {
		EchoTestDto echo1 = EchoTestDto.builder()
				.intProperty(1)
				.stringProperty("String1")
				.build();
		EchoTestDto echo2 = echo1
				.withIntProperty(2)
				.withStringProperty("String2");
		List<EchoTestDto> input = List.of(echo1, echo2);

		Response response = new EchoApiClient(createRequestSpec())
				.post(input);

		assertThat(response.statusCode(), equalTo(200));
		assertThat(response.contentType(), containsString("application/json"));
		List<EchoTestDto> result = response.jsonPath().getList("", EchoTestDto.class);
		assertThat(result, containsInAnyOrder(echo1, echo2));
	}
}
