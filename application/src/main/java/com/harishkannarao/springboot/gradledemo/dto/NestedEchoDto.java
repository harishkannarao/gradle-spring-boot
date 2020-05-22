package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
@Value.Immutable
@JsonSerialize(as = ImmutableNestedEchoDto.class)
@JsonDeserialize(as = ImmutableNestedEchoDto.class)
public abstract class NestedEchoDto {
    public abstract Long longProperty();
    @Value.Default
    public String defaultStringProperty(){
        return "defaultValue";
    }

    public static class Builder extends ImmutableNestedEchoDto.Builder {}

    public static Builder builder() {
        return new Builder();
    }
}
