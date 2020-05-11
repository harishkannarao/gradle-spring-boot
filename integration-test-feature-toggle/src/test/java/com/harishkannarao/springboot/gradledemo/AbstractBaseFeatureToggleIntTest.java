package com.harishkannarao.springboot.gradledemo;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.BeansException;

import java.util.List;

public abstract class AbstractBaseFeatureToggleIntTest {

    @BeforeEach
    void globalSetUp() {
        restartWithDefaultProperties();
    }

    protected void restartWithDefaultProperties() {
        TestApplication.INSTANCE.restartWithDefaultProperties();
    }

    protected void restartWithProperties(List<String> properties) {
        TestApplication.INSTANCE.restartWithProperties(properties);
    }

    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return TestApplication.INSTANCE.getApplicationContext().getBean(requiredType);
    }

}
