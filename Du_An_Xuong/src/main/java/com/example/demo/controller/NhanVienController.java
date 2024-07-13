package com.example.demo.controller;

import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    private  NhanVienService service;

    @GetMapping("/getall")
    public String getAll(Model model){
        List<NhanVien> listNV= service.findAll();
        System.out.println(listNV.size());
        model.addAttribute("list",listNV);
        return "nhanvien/listnv";
    }
}
