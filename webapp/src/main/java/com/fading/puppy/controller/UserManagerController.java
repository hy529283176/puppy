package com.fading.puppy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("doUserManager/")
public class UserManagerController {
    @RequestMapping("getIndex")
    public ModelAndView getIndex(){
        return new ModelAndView("user");
    }

    @RequestMapping("updatePwdIndex")
    public ModelAndView updatePwdIndex(){
        return new ModelAndView("updatePwd");
    }
}
