package com.harishkannarao.springboot.gradledemo;

public class SimpleClass {
    private final String field1;
    private final String field2;

    public SimpleClass(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }
}
