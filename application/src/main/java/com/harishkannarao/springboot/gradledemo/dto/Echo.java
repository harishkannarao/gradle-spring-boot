package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableEcho.class)
@JsonDeserialize(as = ImmutableEcho.class)
public interface Echo {
    int intProperty();
    @JsonProperty("string_property") String stringProperty();

    default String intAsString() {
        return String.valueOf(intProperty());
    }

    static ImmutableEcho.Builder builder() {
        return ImmutableEcho.builder();
    }
}
