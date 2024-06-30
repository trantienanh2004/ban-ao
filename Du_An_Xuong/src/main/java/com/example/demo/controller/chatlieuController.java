package com.example.demo.controller;

import com.example.demo.entity.ChatLieu;
import com.example.demo.repository.ChatLieuRepository;
import com.example.demo.service.ChatLieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/sanpham")
public class chatlieuController {

    @Autowired
    ChatLieuService chatLieuService;

    @GetMapping("/ChatLieuForm")
    public String listCL(Model model , @RequestParam(name = "pageNo",defaultValue = "0") int pageNo){
        model.addAttribute("chatlieu",new ChatLieu());
        model.addAttribute("listchatlieu",chatLieuService.chatLieuPage(pageNo));
        return "SanPham/chat_lieu";
    }

    @PostMapping("/addCL")
    public String addCL(Model model,@Valid @ModelAttribute("chatlieu")ChatLieu chatLieu, BindingResult result){

        if(result.hasErrors()){
            model.addAttribute("listchatlieu",chatLieuService.chatLieuPage(0));
            return "SanPham/chat_lieu";
        }
        chatLieu.setNgay_tao(java.time.LocalDate.now());
        chatLieuService.addCL(chatLieu);
        return "redirect:/sanpham/ChatLieuForm";
    }
    @GetMapping("/xoaCL")
    public String xoaCL(@RequestParam("id") int id, RedirectAttributes redirectAttributes){
       chatLieuService.xoaCL(id);
        redirectAttributes.addFlashAttribute("message", "Xóa thành công!");
        return "redirect:/sanpham/ChatLieuForm";
    }
    @GetMapping("/detail/{id}")
    public String detail( Model model,@PathVariable("id") int id){
        model.addAttribute("chatlieu", chatLieuService.chatLieuByID(id));
        return "SanPham/chat_lieu_add";
    }
    @PostMapping("/updateCL/{id}")
    public String updateCL(@Valid @ModelAttribute("chatlieu")ChatLieu chatLieu,
                           BindingResult result ,
                           @PathVariable("id") int id
                           ){

        if(result.hasErrors()){
            return "SanPham/chat_lieu_add";
        }
        chatLieu.setId(id);
        chatLieu.setNgay_tao(chatLieuService.chatLieuByID(id).getNgay_tao());
        chatLieu.setNgay_sua(java.time.LocalDate.now());
        chatLieuService.addCL(chatLieu);
        return "redirect:/sanpham/ChatLieuForm";
    }
}
