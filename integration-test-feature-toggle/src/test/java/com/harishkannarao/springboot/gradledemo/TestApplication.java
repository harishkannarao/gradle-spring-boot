package com.harishkannarao.springboot.gradledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestApplication {

    private ConfigurableApplicationContext applicationContext;
    private boolean runningWithDefaultProperties = false;

    public TestApplication() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (applicationContext != null && applicationContext.isRunning()) {
                applicationContext.stop();
            }
        }));
    }

    public void restartWithDefaultProperties() {
        if (!runningWithDefaultProperties) {
            stop();
            start(convertToSpringBootArgs(createDefaultProperties()));
            runningWithDefaultProperties = true;
        }
    }

    public void restartWithProperties(List<String> properties) {
        List<String> resolvedProperties = Stream.concat(
                createDefaultProperties().stream(),
                properties.stream())
                .collect(Collectors.toList());
        stop();
        start(convertToSpringBootArgs(resolvedProperties));
        runningWithDefaultProperties = false;
    }

    public ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Environment getEnvironment() {
        return applicationContext.getEnvironment();
    }

    private void stop() {
        if (applicationContext != null && applicationContext.isRunning()) {
            applicationContext.stop();
        }
    }

    private void start(String[] args) {
        applicationContext = SpringApplication.run(
                GradleDemoApplication.class,
                args
        );
    }

    private List<String> createDefaultProperties() {
        return List.of(
                "spring.profiles.active=local,integration-test"
        );
    }

    private String[] convertToSpringBootArgs(List<String> input) {
        return input
                .stream()
                .map(s -> "--" + s)
                .collect(Collectors.toList())
                .toArray(new String[]{});
    }
}
