package com.example.demo.Ban_Ao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Home")
public class HomeController {
    @GetMapping("/HienThi")
    public String get(){
        return "/Home";
    }
}
