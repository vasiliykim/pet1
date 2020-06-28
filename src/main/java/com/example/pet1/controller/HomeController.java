package com.example.pet1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String home(){
        System.out.println("home");
        return "index";
    }
}
