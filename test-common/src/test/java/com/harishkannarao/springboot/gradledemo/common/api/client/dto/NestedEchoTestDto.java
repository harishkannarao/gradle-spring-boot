package com.harishkannarao.springboot.gradledemo.common.api.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
@Value.Immutable
@JsonSerialize(as = ImmutableNestedEchoTestDto.class)
@JsonDeserialize(as = ImmutableNestedEchoTestDto.class)
public abstract class NestedEchoTestDto {
    public abstract Long longProperty();
    public abstract Optional<String> defaultStringProperty();

    public static class Builder extends ImmutableNestedEchoTestDto.Builder {}

    public static Builder builder() {
        return new Builder();
    }
}
