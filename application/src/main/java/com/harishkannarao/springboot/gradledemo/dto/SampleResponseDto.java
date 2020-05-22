package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
@Value.Immutable
@JsonSerialize(as = ImmutableSampleResponseDto.class)
@JsonDeserialize(as = ImmutableSampleResponseDto.class)
public abstract class SampleResponseDto {
    public abstract String message();

    public static class Builder extends ImmutableSampleResponseDto.Builder {}

    public static Builder builder() {
        return new Builder();
    }
}
