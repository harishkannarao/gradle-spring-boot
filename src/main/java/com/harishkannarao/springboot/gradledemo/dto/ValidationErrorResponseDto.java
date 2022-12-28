package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Style(overshadowImplementation = true, jdkOnly = true)
@Value.Immutable
@JsonSerialize(as = ImmutableValidationErrorResponseDto.class)
@JsonDeserialize(as = ImmutableValidationErrorResponseDto.class)
public abstract class ValidationErrorResponseDto {
    public abstract List<ValidationErrorDto> getValidationErrors();
    public String getMessage() {
        return "Validation Error";
    }

    public static ImmutableValidationErrorResponseDto.Builder builder() {
        return ImmutableValidationErrorResponseDto.builder();
    }
}
