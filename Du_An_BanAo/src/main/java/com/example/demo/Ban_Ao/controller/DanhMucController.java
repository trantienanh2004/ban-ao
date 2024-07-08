package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/DanhMuc")
public class DanhMucController {
    @Autowired
    DanhMucRepository danhMucRepository;

    @GetMapping("/HienThi")
    public String ShowList(){
        return "/View/DanhMuc";
    }
}
