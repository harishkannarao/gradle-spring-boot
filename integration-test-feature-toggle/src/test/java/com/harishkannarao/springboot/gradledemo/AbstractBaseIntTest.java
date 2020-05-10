package com.harishkannarao.springboot.gradledemo;

import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractBaseIntTest {
    protected static final TestApplication testApplication = new TestApplication();

    @BeforeEach
    void globalSetUp() {
        testApplication.restartWithDefaultProperties();
    }
}
