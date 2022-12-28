package com.harishkannarao.springboot.gradledemo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(path = {"/"})
public class RootWebController {

    @GetMapping(path = {""}, produces = {MediaType.TEXT_HTML_VALUE})
    public ModelAndView redirectToSampleWeb() {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/web/sample"));
        return modelAndView;
    }
}
