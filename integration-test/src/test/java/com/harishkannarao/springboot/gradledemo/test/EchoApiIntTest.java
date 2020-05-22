package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.api.client.EchoApiClient;
import com.harishkannarao.springboot.gradledemo.common.api.client.dto.EchoTestDto;
import com.harishkannarao.springboot.gradledemo.common.api.client.dto.NestedEchoTestDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class EchoApiIntTest extends AbstractBaseIntTest {

	@Test
	void verify_echo_api() {
		EchoTestDto echo1 = EchoTestDto.builder()
				.intProperty(1)
				.stringProperty("String1")
				.addPrimitiveList("list1", "list2")
				.addPrimitiveSet("set1", "set2")
				.bigDecimal(new BigDecimal("20.01"))
				.dateTime(OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC))
				.build();
		EchoTestDto echo2 = echo1
				.withIntProperty(2)
				.withStringProperty("String2")
				.withOptionalString("optional-string2")
				.withObjectList(
						NestedEchoTestDto.builder()
								.longProperty(3L)
								.build(),
						NestedEchoTestDto.builder()
								.longProperty(4L)
								.defaultStringProperty("overridden-default-value")
								.build()
				);
		List<EchoTestDto> input = List.of(echo1, echo2);

		Response response = new EchoApiClient(createRequestSpec())
				.post(input);

		assertThat(response.statusCode(), equalTo(200));
		assertThat(response.contentType(), containsString("application/json"));
		List<EchoTestDto> result = response.jsonPath().getList("", EchoTestDto.class);
		Map<Integer, EchoTestDto> resultMap = result.stream().collect(Collectors.toMap(EchoTestDto::intProperty, echoTestDto -> echoTestDto));
		EchoTestDto actualEcho1 = resultMap.get(1);
		EchoTestDto actualEcho2 = resultMap.get(2);

		assertThat(actualEcho1.stringProperty(), equalTo("String1"));
		assertThat(actualEcho1.bigDecimal(), equalTo(new BigDecimal("20.01")));
		assertThat(actualEcho1.typedDateTime(), equalTo(echo1.typedDateTime()));
		assertThat(actualEcho1.optionalString(), equalTo(Optional.empty()));
		assertThat(actualEcho1.primitiveList(), containsInAnyOrder("list1", "list2"));
		assertThat(actualEcho1.primitiveSet(), containsInAnyOrder("set1", "set2"));
		assertThat(actualEcho1.objectList(), empty());

		assertThat(actualEcho2.optionalString(), equalTo(Optional.of("optional-string2")));
		Map<Long, NestedEchoTestDto> nestedEchoTestDtoMap = actualEcho2.objectList().stream().collect(Collectors.toMap(NestedEchoTestDto::longProperty, dto -> dto));
		NestedEchoTestDto nestedEchoTestDto1 = nestedEchoTestDtoMap.get(3L);
		NestedEchoTestDto nestedEchoTestDto2 = nestedEchoTestDtoMap.get(4L);
		assertThat(nestedEchoTestDto1.defaultStringProperty(), equalTo(Optional.of("defaultValue")));
		assertThat(nestedEchoTestDto2.defaultStringProperty(), equalTo(Optional.of("overridden-default-value")));
	}
}
