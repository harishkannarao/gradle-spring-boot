package com.harishkannarao.springboot.gradledemo.controller;

import com.harishkannarao.springboot.gradledemo.dto.SampleResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api/sample"})
public class SampleApiController {

    @GetMapping(path = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SampleResponseDto> getSampleResponse() {
        SampleResponseDto entity = SampleResponseDto.builder()
                .message("My Sample Api Message")
                .build();
        return ResponseEntity.ok().body(entity);
    }
}
