package com.harishkannarao.springboot.gradledemo.util.validation;

import org.immutables.value.Value;

@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
@Value.Immutable
public abstract class Violation {
    public abstract String getPropertyPath();
    public abstract String getMessage();

    public static class Builder extends ImmutableViolation.Builder {}

    public static Builder builder() {
        return new Builder();
    }
}
