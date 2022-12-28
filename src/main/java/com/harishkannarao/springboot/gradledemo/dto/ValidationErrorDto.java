package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Style(overshadowImplementation = true, jdkOnly = true)
@Value.Immutable
@JsonSerialize(as = ImmutableValidationErrorDto.class)
@JsonDeserialize(as = ImmutableValidationErrorDto.class)
public abstract class ValidationErrorDto {

    public abstract String getPropertyPath();
    public abstract String getMessage();

    public static ImmutableValidationErrorDto.Builder builder() {
        return ImmutableValidationErrorDto.builder();
    }
}
