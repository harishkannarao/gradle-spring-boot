package com.harishkannarao.springboot.gradledemo;

import com.harishkannarao.springboot.gradledemo.controller.SampleWebController;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SampleWebControllerTest {
    private final SampleWebController underTest = new SampleWebController();

    @Test
    void verify_response() {
        ModelAndView result = underTest.getSampleHtml();
        assertThat(result.getViewName(), equalTo("web_template/sample_page"));
        assertThat(result.getModel().get("message"), equalTo("My Sample Web Message"));
    }
}
