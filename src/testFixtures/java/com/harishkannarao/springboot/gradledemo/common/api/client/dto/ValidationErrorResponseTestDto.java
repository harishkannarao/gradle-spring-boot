package com.harishkannarao.springboot.gradledemo.common.api.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Style(overshadowImplementation = true, jdkOnly = true)
@Value.Immutable
@JsonSerialize(as = ImmutableValidationErrorResponseTestDto.class)
@JsonDeserialize(as = ImmutableValidationErrorResponseTestDto.class)
public abstract class ValidationErrorResponseTestDto {
    public abstract List<ValidationErrorTestDto> getValidationErrors();
    public abstract String getMessage();

    public static ImmutableValidationErrorResponseTestDto.Builder builder() {
        return ImmutableValidationErrorResponseTestDto.builder();
    }
}
