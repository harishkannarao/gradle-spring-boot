package com.harishkannarao.springboot.gradledemo.util.validation;

import org.immutables.value.Value;

@Value.Style(overshadowImplementation = true, jdkOnly = true)
@Value.Immutable
public abstract class Violation {
    public abstract String getPropertyPath();
    public abstract String getMessage();

    public static ImmutableViolation.Builder builder() {
        return ImmutableViolation.builder();
    }
}
