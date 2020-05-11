package com.harishkannarao.springboot.gradledemo.dto;

public class SampleResponseDto {
    private final String message;

    public SampleResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
