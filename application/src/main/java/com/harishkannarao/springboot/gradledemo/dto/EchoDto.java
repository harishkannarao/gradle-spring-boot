package com.harishkannarao.springboot.gradledemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
@Value.Immutable
@JsonSerialize(as = ImmutableEchoDto.class)
@JsonDeserialize(as = ImmutableEchoDto.class)
public abstract class EchoDto implements WithEchoDto {
    public abstract int getIntProperty();
    @JsonProperty("string_property")
    public abstract String getStringProperty();
    public abstract Optional<String> getOptionalString();
    public abstract List<String> getPrimitiveList();
    public abstract Set<String> getPrimitiveSet();
    public abstract OffsetDateTime getDateTime();
    public abstract BigDecimal getBigDecimal();
    public abstract List<NestedEchoDto> getObjectList();

    @JsonIgnore
    public String getIntAsString() {
        return String.valueOf(getIntProperty());
    }

    @SuppressWarnings("Convert2MethodRef")
    @JsonProperty("constructed_property")
    public Optional<String> getConstructedProperty() {
        return getOptionalString()
                .map(s -> "Constructed::".concat(s));
    }

    public static class Builder extends ImmutableEchoDto.Builder {}

    public static Builder builder() {
        return new Builder();
    }
}
