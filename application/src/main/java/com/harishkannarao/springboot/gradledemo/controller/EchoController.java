package com.harishkannarao.springboot.gradledemo.controller;

import com.harishkannarao.springboot.gradledemo.dto.EchoDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = {"/api/echo"})
@Validated
public class EchoController {

    @PostMapping(path = {"{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<EchoDto>> echo(
            @Size(min=2, max=20) @PathVariable("id") String id,
            @Valid @NotEmpty @RequestBody List<@Valid EchoDto> input
    ) {
        List<EchoDto> output = input.stream()
                .map(echo -> EchoDto.builder()
                        .from(echo)
                        .intProperty(Integer.parseInt(echo.getIntAsString()))
                        .build()
                )
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(output);
    }
}
