package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_SampleResponseDto.Builder.class)
public abstract class SampleResponseDto {
    public static Builder builder() {
        return new AutoValue_SampleResponseDto.Builder();
    }

    public abstract String getMessage();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "set")
    public static abstract class Builder {
        public abstract Builder setMessage(String value);
        public abstract SampleResponseDto build();
    }
}
