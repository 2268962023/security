package com.badboy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/toMain")
    public String toMain() {
        return "redirect:/main.html";
    }

    @RequestMapping("fail")
    public String fail() {
        return "redirect:/fail.html";
    }

}
