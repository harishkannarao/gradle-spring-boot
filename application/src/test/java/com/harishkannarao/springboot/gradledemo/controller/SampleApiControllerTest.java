package com.harishkannarao.springboot.gradledemo.controller;

import com.harishkannarao.springboot.gradledemo.dto.SampleResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SuppressWarnings("ConstantConditions")
public class SampleApiControllerTest {
    private final SampleApiController underTest = new SampleApiController();

    @Test
    void verify_response() {
        ResponseEntity<SampleResponseDto> result = underTest.getSampleResponse();
        assertThat(result.getStatusCodeValue(), equalTo(200));
        assertThat(result.getBody().message(), equalTo("My Sample Api Message"));
    }
}
