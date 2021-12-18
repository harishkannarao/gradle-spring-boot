package com.harishkannarao.springboot.gradledemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import static java.util.Objects.requireNonNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class SampleWebControllerTest {
    private final SampleWebController underTest = new SampleWebController();
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();

    @Test
    void verify_response() throws Exception {
        ModelAndView result = mockMvc.perform(get("/web/sample"))
                .andReturn()
                .getModelAndView();
        assertThat(requireNonNull(result).getViewName(), equalTo("web_template/sample_page"));
        assertThat(result.getModel().get("message"), equalTo("My Sample Web Message"));
    }
}
