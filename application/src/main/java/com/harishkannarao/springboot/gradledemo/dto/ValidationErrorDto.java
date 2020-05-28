package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
@Value.Immutable
@JsonSerialize(as = ImmutableValidationErrorDto.class)
@JsonDeserialize(as = ImmutableValidationErrorDto.class)
public abstract class ValidationErrorDto {

    public abstract String getPropertyPath();
    public abstract String getMessage();

    public static class Builder extends ImmutableValidationErrorDto.Builder {}

    public static Builder builder() {
        return new Builder();
    }
}
