package com.harishkannarao.springboot.gradledemo;

public class SimpleClassBuilder {
    private String field1;
    private String field2;

    public SimpleClassBuilder setField1(String field1) {
        this.field1 = field1;
        return this;
    }

    public SimpleClassBuilder setField2(String field2) {
        this.field2 = field2;
        return this;
    }

    public SimpleClass build() {
        return new SimpleClass(field1, field2);
    }
}
