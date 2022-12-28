package com.harishkannarao.springboot.gradledemo.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.Size;

@RestController
@RequestMapping(path = {"/test/api/reference/security"})
@Validated
public class SecurityReferenceTestController {

    @GetMapping()
    @RolesAllowed(value = {"valid_token"})
    public ResponseEntity<Void> test(
            @Size(min=3, max=20) @RequestParam("id") String id
    ) {
        return ResponseEntity.noContent().build();
    }
}
