package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.api.client.EchoApiClient;
import com.harishkannarao.springboot.gradledemo.common.api.client.dto.EchoTestDto;
import com.harishkannarao.springboot.gradledemo.common.api.client.dto.NestedEchoTestDto;
import com.harishkannarao.springboot.gradledemo.common.api.client.dto.ValidationErrorResponseTestDto;
import com.harishkannarao.springboot.gradledemo.common.api.client.dto.ValidationErrorTestDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class EchoApiIntTest extends AbstractDefaultProfileIntegrationTest {

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
				.post("test-id", input);

		assertThat(response.statusCode(), equalTo(200));
		assertThat(response.contentType(), containsString("application/json"));
		List<EchoTestDto> result = response.jsonPath().getList("", EchoTestDto.class);
		Map<Integer, EchoTestDto> resultMap = result.stream().collect(Collectors.toMap(EchoTestDto::intProperty, echoTestDto -> echoTestDto));
		EchoTestDto actualEcho1 = resultMap.get(1);
		EchoTestDto actualEcho2 = resultMap.get(2);

		assertThat(actualEcho1.stringProperty(), equalTo(Optional.of("String1")));
		assertThat(actualEcho1.bigDecimal(), equalTo(new BigDecimal("20.01")));
		assertThat(actualEcho1.typedDateTime(), equalTo(echo1.typedDateTime()));
		assertThat(actualEcho1.optionalString(), equalTo(Optional.empty()));
		assertThat(actualEcho1.constructedOptionalString(), equalTo(Optional.empty()));
		assertThat(actualEcho1.primitiveList(), containsInAnyOrder("list1", "list2"));
		assertThat(actualEcho1.primitiveSet(), containsInAnyOrder("set1", "set2"));
		assertThat(actualEcho1.objectList(), empty());

		assertThat(actualEcho2.optionalString(), equalTo(Optional.of("optional-string2")));
		assertThat(actualEcho2.constructedOptionalString(), equalTo(Optional.of("Constructed::optional-string2")));
		Map<Long, NestedEchoTestDto> nestedEchoTestDtoMap = actualEcho2.objectList().stream().collect(Collectors.toMap(NestedEchoTestDto::longProperty, dto -> dto));
		NestedEchoTestDto nestedEchoTestDto1 = nestedEchoTestDtoMap.get(3L);
		NestedEchoTestDto nestedEchoTestDto2 = nestedEchoTestDtoMap.get(4L);
		assertThat(nestedEchoTestDto1.defaultStringProperty(), equalTo(Optional.of("defaultValue")));
		assertThat(nestedEchoTestDto2.defaultStringProperty(), equalTo(Optional.of("overridden-default-value")));
	}

	@Test
	public void returns_validation_error_for_request_payload() {
		EchoTestDto echo = EchoTestDto.builder()
				.intProperty(1)
				.stringProperty(Optional.empty())
				.primitiveList(Collections.emptyList())
				.bigDecimal(new BigDecimal("20.01"))
				.dateTime(OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC))
				.addObjectList(
						NestedEchoTestDto.builder()
								.longProperty(-1L)
								.build()
				)
				.build();

		Response response = new EchoApiClient(createRequestSpec())
				.post("test-id", List.of(echo));

		assertThat(response.statusCode(), equalTo(400));

		ValidationErrorResponseTestDto responseDto = response.jsonPath().getObject("", ValidationErrorResponseTestDto.class);
		assertThat(responseDto.getMessage(), equalTo("Validation Error"));
		ValidationErrorTestDto listError = ValidationErrorTestDto.builder()
				.propertyPath("echo.input[0].primitiveList")
				.message("must not be empty")
				.build();
		ValidationErrorTestDto stringError = ValidationErrorTestDto.builder()
				.propertyPath("echo.input[0].stringProperty")
				.message("string_property::must not be blank")
				.build();
		ValidationErrorTestDto nestedObjectError = ValidationErrorTestDto.builder()
				.propertyPath("echo.input[0].objectList[0].longProperty")
				.message("must be greater than or equal to 0")
				.build();
		assertThat(responseDto.getValidationErrors(), containsInAnyOrder(listError, stringError, nestedObjectError));
	}

	@Test
	public void returns_validation_error_for_empty_input() {
		Response response = new EchoApiClient(createRequestSpec())
				.post("test-id", Collections.emptyList());

		assertThat(response.statusCode(), equalTo(400));

		ValidationErrorResponseTestDto responseDto = response.jsonPath().getObject("", ValidationErrorResponseTestDto.class);
		assertThat(responseDto.getMessage(), equalTo("Validation Error"));
		ValidationErrorTestDto listError = ValidationErrorTestDto.builder()
				.propertyPath("echo.input")
				.message("must not be empty")
				.build();
		assertThat(responseDto.getValidationErrors(), containsInAnyOrder(listError));
	}

	@Test
	public void returns_validation_error_for_path_variable() {
		Response response = new EchoApiClient(createRequestSpec())
				.post("i", Collections.emptyList());

		assertThat(response.statusCode(), equalTo(400));

		ValidationErrorResponseTestDto responseDto = response.jsonPath().getObject("", ValidationErrorResponseTestDto.class);
		assertThat(responseDto.getMessage(), equalTo("Validation Error"));
		ValidationErrorTestDto idError = ValidationErrorTestDto.builder()
				.propertyPath("echo.id")
				.message("size must be between 2 and 20")
				.build();
		assertThat(responseDto.getValidationErrors(), hasItem(idError));
	}
}
