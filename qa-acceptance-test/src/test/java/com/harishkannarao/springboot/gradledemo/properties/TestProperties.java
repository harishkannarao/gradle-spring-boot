package com.harishkannarao.springboot.gradledemo.properties;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class TestProperties {
    private final Properties properties;

    public TestProperties(String location) {
        this.properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(location)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String applicationBaseUrl() {
        return this.properties.getProperty("application.baseUrl");
    }
}
