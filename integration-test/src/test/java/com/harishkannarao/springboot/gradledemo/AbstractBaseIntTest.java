package com.harishkannarao.springboot.gradledemo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ActiveProfiles(profiles = {"local","integration-test"})
public abstract class AbstractBaseIntTest {
}
