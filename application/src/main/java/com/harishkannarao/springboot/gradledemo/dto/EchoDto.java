package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
@Value.Immutable
@JsonSerialize(as = ImmutableEchoDto.class)
@JsonDeserialize(as = ImmutableEchoDto.class)
public abstract class EchoDto {
    public abstract int intProperty();
    @JsonProperty("string_property")
    public abstract String stringProperty();
    public abstract Optional<String> optionalString();
    public abstract List<String> primitiveList();
    public abstract Set<String> primitiveSet();
    public abstract OffsetDateTime dateTime();
    public abstract BigDecimal bigDecimal();
    public abstract List<NestedEchoDto> objectList();

    public String intAsString() {
        return String.valueOf(intProperty());
    }

    public static class Builder extends ImmutableEchoDto.Builder {}

    public static Builder builder() {
        return new Builder();
    }
}
