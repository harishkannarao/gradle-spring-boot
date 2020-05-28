package com.harishkannarao.springboot.gradledemo.dto;

import com.harishkannarao.springboot.gradledemo.util.validation.ValidationUtil;
import com.harishkannarao.springboot.gradledemo.util.validation.Violation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class EchoDtoValidationTest {

    @Test
    public void return_constraint_violation_for_empty_string() {
        EchoDto input = EchoDto.builder()
                .intProperty(1)
                .stringProperty(" ")
                .addPrimitiveList("list1")
                .bigDecimal(new BigDecimal("20.01"))
                .dateTime(OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC))
                .build();

        Set<Violation> result = ValidationUtil.validate(input);

        Violation expected = Violation.builder()
                .propertyPath("stringProperty")
                .message("string_property::must not be blank")
                .build();

        assertThat(result, containsInAnyOrder(expected));
    }

    @Test
    public void return_constraint_violation_for_null() {
        EchoDto input = EchoDto.builder()
                .intProperty(1)
                .stringProperty(Optional.empty())
                .addPrimitiveList("list1")
                .bigDecimal(new BigDecimal("20.01"))
                .dateTime(OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC))
                .build();

        Set<Violation> result = ValidationUtil.validate(input);

        Violation expected = Violation.builder()
                .propertyPath("stringProperty")
                .message("string_property::must not be blank")
                .build();

        assertThat(result, containsInAnyOrder(expected));
    }

    @Test
    public void return_constraint_violation_for_empty_list() {
        EchoDto input = EchoDto.builder()
                .intProperty(1)
                .stringProperty("test-value")
                .primitiveSet(Collections.emptyList())
                .bigDecimal(new BigDecimal("20.01"))
                .dateTime(OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC))
                .build();

        Set<Violation> result = ValidationUtil.validate(input);

        Violation expected = Violation.builder()
                .propertyPath("primitiveList")
                .message("must not be empty")
                .build();

        assertThat(result, containsInAnyOrder(expected));
    }

    @Test
    public void return_constraint_violation_for_nested_object() {
        EchoDto input = EchoDto.builder()
                .intProperty(1)
                .stringProperty("test-value")
                .addPrimitiveList("list2")
                .bigDecimal(new BigDecimal("20.01"))
                .dateTime(OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC))
                .addObjectList(
                        NestedEchoDto.builder()
                                .longProperty(-1L)
                                .build()
                )
                .build();

        Set<Violation> result = ValidationUtil.validate(input);

        Violation expected = Violation.builder()
                .propertyPath("objectList[0].longProperty")
                .message("must be greater than or equal to 0")
                .build();

        assertThat(result, containsInAnyOrder(expected));
    }
}
