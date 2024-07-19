package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.repository.ChatLieuRepository;
import com.example.demo.Ban_Ao.repository.CoAoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ChatLieu")
public class ChatLieuController {
    @Autowired
    ChatLieuRepository chatLieuRepository;

    @GetMapping("/HienThi")
    public String ShowList(Model model){
        model.addAttribute("listCL", chatLieuRepository.findAll());
        return "/View/ChatLieu";
    }
}
