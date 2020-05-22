package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
@Value.Immutable
@JsonSerialize(as = ImmutableEchoDto.class)
@JsonDeserialize(as = ImmutableEchoDto.class)
public abstract class EchoDto implements WithEchoDto {
    public abstract int intProperty();
    @JsonProperty("string_property")
    public abstract String stringProperty();

    public String intAsString() {
        return String.valueOf(intProperty());
    }

    public static class Builder extends ImmutableEchoDto.Builder {}

    public static Builder builder() {
        return new Builder();
    }
}
