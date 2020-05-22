package com.harishkannarao.springboot.gradledemo.controller;

import com.harishkannarao.springboot.gradledemo.dto.EchoDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = {"/api/echo"})
public class EchoController {

    @PostMapping(path = {""}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<EchoDto>> echo(
            @RequestBody List<EchoDto> input
    ) {
        List<EchoDto> output = input.stream()
                .map(echo -> EchoDto.builder()
                        .intProperty(Integer.parseInt(echo.intAsString()))
                        .stringProperty(echo.stringProperty())
                        .build()
                )
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(output);
    }
}
