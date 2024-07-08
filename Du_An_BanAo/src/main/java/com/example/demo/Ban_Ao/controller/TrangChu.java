package com.example.demo.Ban_Ao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("TrangChu")
public class TrangChu {

    @GetMapping("HienThi")
    public String load(){
        return "/TrangChu";
    }
}
