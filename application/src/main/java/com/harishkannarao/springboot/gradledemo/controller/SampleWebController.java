package com.harishkannarao.springboot.gradledemo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = {"/web/sample"})
public class SampleWebController {

    @GetMapping(path = {""}, produces = {MediaType.TEXT_HTML_VALUE})
    public ModelAndView getSampleHtml() {
        ModelAndView modelAndView = new ModelAndView("web_template/sample_page");
        modelAndView.addObject("message", "My Sample Web Message");
        return modelAndView;
    }
}
