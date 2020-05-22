package com.harishkannarao.springboot.gradledemo.common.api.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
@Value.Immutable
@JsonSerialize(as = ImmutableEchoTestDto.class)
@JsonDeserialize(as = ImmutableEchoTestDto.class)
public abstract class EchoTestDto implements WithEchoTestDto {
    public abstract int intProperty();
    @JsonProperty("string_property")
    public abstract String stringProperty();

    public static class Builder extends ImmutableEchoTestDto.Builder {}

    public static EchoTestDto.Builder builder() {
        return new EchoTestDto.Builder();
    }
}
