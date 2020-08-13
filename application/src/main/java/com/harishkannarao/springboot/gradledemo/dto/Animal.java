package com.harishkannarao.springboot.gradledemo.dto;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Animal {
    public abstract String getName();
    public abstract int getNoOfLegs();
    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_Animal.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setName(String value);
        public abstract Builder setNoOfLegs(int value);
        public abstract Animal build();
    }
}
