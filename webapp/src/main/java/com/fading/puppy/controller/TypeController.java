package com.fading.puppy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "doType/")
public class TypeController {

    @RequestMapping(value = "getIndex")
    public ModelAndView getTypeView(){

        return new ModelAndView("type");
    }
}
