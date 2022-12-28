package com.harishkannarao.springboot.gradledemo.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class SampleApiControllerTest {
    private final SampleApiController underTest = new SampleApiController();
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();

    @Test
    void verify_response() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/sample"))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus(), equalTo(200));
        DocumentContext jsonResult = JsonPath.parse(response.getContentAsString());
        assertThat(jsonResult.read("$.message"), equalTo("My Sample Api Message"));
    }
}
