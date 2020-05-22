# Multi-Module Spring Boot application with Gradle

This repository is to show sample of multi-module spring boot application with Gradle

## Github Actions Build status
[![Build Status](https://github.com/harishkannarao/gradle-spring-boot/workflows/CI-master/badge.svg)](https://github.com/harishkannarao/gradle-spring-boot/actions?query=workflow%3ACI-master)

## Required Software, Tools and Version
* Java JDK Version: 11 Adopt OpenJDK (`java -version`)
* Git Client: Any latest version (`git --version`)
* Integrated Development Environment: Any version of IntelliJ Idea or Eclipse (with `Annotations Processing` enabled for `Immutables` library)

## Highlights

* `Swagger UI` and `OpenAPI docs`
* Using `Immutables` java library for request and response dto in REST apis
* `Thymeleaf` templates in server side web or html
* `Logback` for application logs
* `Logback Access` for tomcat access logs
* `Rest Assured` library for testing api with `request`, `response` and `curl` commands captured in console and gradle test reports
* `Feature toggle` integration test for spring application
* Multi-module structure which `reuses test classes` between `unit`, `integration` and `acceptance` tests

## Run unit and developer integration tests

    ./gradlew clean build
    
## Run the application

    ./gradlew clean bootRun
    
Open the urls in the browser

    http://localhost:8081
    
    http://localhost:8081/swagger-ui.html
    
    http://localhost:8081/api-docs
    
## Run the jar file

    java -jar application/build/libs/application.jar
    
## Run the qa acceptance test against target environment

    ./gradlew clean qaAcceptanceTest -DtestEnvironment=development
    
or

    TEST_ENVIRONMENT='development' ./gradlew clean qaAcceptanceTest
    
## Run the qa acceptance test against local

Run the application in a terminal

      ./gradlew clean bootRun
      
Run the tests in another terminal window

    ./gradlew clean qaAcceptanceTest