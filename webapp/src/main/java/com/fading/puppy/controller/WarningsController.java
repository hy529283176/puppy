package com.fading.puppy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("doWaring/")
public class WarningsController {
    @RequestMapping("getIndex")
    public ModelAndView getIndex(){
        return new ModelAndView("warning");
    }
}
