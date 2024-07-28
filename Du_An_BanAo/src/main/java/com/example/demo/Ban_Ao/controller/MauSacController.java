package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.repository.CoAoRepository;
import com.example.demo.Ban_Ao.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MauSac")
public class MauSacController {
    @Autowired
    MauSacRepository mauSacRepository;

    @GetMapping("/HienThi")
    public String ShowList(Model model){
        model.addAttribute("listMS", mauSacRepository.findAll());
        return "View/MauSac";
    }
}
