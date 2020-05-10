package com.harishkannarao.springboot.gradledemo;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SimpleClassBuilderTest {

    @Test
    void testBuilder() {
        SimpleClass result = new SimpleClassBuilder()
                .setField1("value1")
                .setField2("value2")
                .build();

        assertThat(result.getField1(), equalTo("value1"));
        assertThat(result.getField2(), equalTo("value2"));
    }
}
