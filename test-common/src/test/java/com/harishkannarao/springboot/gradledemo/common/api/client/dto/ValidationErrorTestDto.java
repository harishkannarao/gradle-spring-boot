package com.harishkannarao.springboot.gradledemo.common.api.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Style(overshadowImplementation = true, jdkOnly = true)
@Value.Immutable
@JsonSerialize(as = ImmutableValidationErrorTestDto.class)
@JsonDeserialize(as = ImmutableValidationErrorTestDto.class)
public abstract class ValidationErrorTestDto {

    public abstract String getPropertyPath();
    public abstract String getMessage();

    public static ImmutableValidationErrorTestDto.Builder builder() {
        return ImmutableValidationErrorTestDto.builder();
    }
}
