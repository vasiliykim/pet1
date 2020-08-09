package com.example.pet1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/home")
    public String home(){
        System.out.println("home");
        return "index";
    }
}
