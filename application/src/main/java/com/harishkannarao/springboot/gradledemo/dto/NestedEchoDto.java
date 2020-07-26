package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.validation.constraints.PositiveOrZero;

@Value.Style(overshadowImplementation = true, jdkOnly = true)
@Value.Immutable
@JsonSerialize(as = ImmutableNestedEchoDto.class)
@JsonDeserialize(as = ImmutableNestedEchoDto.class)
public abstract class NestedEchoDto {
    @PositiveOrZero
    public abstract Long getLongProperty();
    @Value.Default
    public String getDefaultStringProperty(){
        return "defaultValue";
    }

    public static ImmutableNestedEchoDto.Builder builder() {
        return ImmutableNestedEchoDto.builder();
    }
}
