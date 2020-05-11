package com.harishkannarao.springboot.gradledemo;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SampleAcceptanceTest extends AbstractBaseAcceptanceTest {
    @Test
    void sample_acceptance_test() {
        assertThat(true, equalTo(true));
    }
}
