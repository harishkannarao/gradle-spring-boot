package com.harishkannarao.springboot.gradledemo.common.api.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Value.Style(overshadowImplementation = true, jdkOnly = true)
@Value.Immutable
@JsonSerialize(as = ImmutableEchoTestDto.class)
@JsonDeserialize(as = ImmutableEchoTestDto.class)
public abstract class EchoTestDto implements WithEchoTestDto {
    public abstract int intProperty();
    @JsonProperty("string_property")
    public abstract Optional<String> stringProperty();
    public abstract Optional<String> optionalString();
    @JsonProperty("constructed_property")
    public abstract Optional<String> constructedOptionalString();
    public abstract List<String> primitiveList();
    public abstract Set<String> primitiveSet();
    public abstract String dateTime();
    public abstract BigDecimal bigDecimal();
    public abstract List<NestedEchoTestDto> objectList();

    public OffsetDateTime typedDateTime() {
        return OffsetDateTime.parse(dateTime());
    }

    public static class Builder extends ImmutableEchoTestDto.Builder {
        public Builder dateTime(OffsetDateTime value) {
            dateTime(value.format(DateTimeFormatter.ISO_DATE_TIME));
            return this;
        }
    }

    public static EchoTestDto.Builder builder() {
        return new EchoTestDto.Builder();
    }
}
