package com.harishkannarao.springboot.gradledemo.test.util;

import com.harishkannarao.springboot.gradledemo.GradleDemoApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestApplication {

    public static final TestApplication INSTANCE = new TestApplication();

    private ConfigurableApplicationContext applicationContext;
    private boolean runningWithDefaultProperties = false;

    private TestApplication() {
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

    private void stop() {
        if (applicationContext != null && applicationContext.isRunning()) {
            SpringApplication.exit(applicationContext);
        }
    }

    private void start(String[] args) {
        applicationContext = SpringApplication.run(GradleDemoApplication.class, args);
    }

    private List<String> createDefaultProperties() {
        return List.of(
                "spring.profiles.active=local,integration-test-feature-toggle"
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
