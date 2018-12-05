package com.fading.puppy.controller;

import com.fading.puppy.entity.Auth_user;
import com.fading.puppy.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/dologin/")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    public LoginController(){
    }

    @RequestMapping(value = "getlogin")
    public ModelAndView getLogin(){
        return new ModelAndView("jsp/login");
    }

    @RequestMapping(value = "toindex")
    public String toIndex(String username, String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
          List<Auth_user> list = this.loginService.getUsers();
        for(Auth_user user : list){
            if(username.equals(user.getUsername())&& password.equals(user.getPwd())){
                return "redirect:/index";
            }
        }
        request.setAttribute("error", "用户名不存在或密码错误！");
        return "jsp/login";
    }

}
