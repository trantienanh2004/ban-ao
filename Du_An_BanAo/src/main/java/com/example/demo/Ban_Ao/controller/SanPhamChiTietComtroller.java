package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.entity.KichThuoc;
import com.example.demo.Ban_Ao.entity.MauSac;
import com.example.demo.Ban_Ao.entity.SanPhamChiTiet;
import com.example.demo.Ban_Ao.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ChiTietSanPham")
public class SanPhamChiTietComtroller {
    @Autowired
    MauSacService mauSacService;
    @Autowired
    KichThuocService kichThuocService;
    @Autowired
    ChatLieuService chatLieuService;
    @Autowired
    SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    SanPhamService sanPhamService;


    @GetMapping("/back")
    public String back(){
        return "/View/SanPham";
    }
    @GetMapping("/HienThi")
    public String spct(Model model, @RequestParam(name = "x", defaultValue = "0") int x){
        model.addAttribute("SanPhamChiTiet" ,new SanPhamChiTiet());
        model.addAttribute("mauSacList",mauSacService.listMauSac());
        model.addAttribute("kichThuocList", kichThuocService.listKichThuoc());
        model.addAttribute("chatlieuList",chatLieuService.ListChatlieu());
        model.addAttribute("SanPhamChiTietList",sanPhamChiTietService.sanPhamChiTietPage(x));
        model.addAttribute("sanphamdetam",sanPhamChiTietService.SPCT_de_tam);
        model.addAttribute("listsanpham",sanPhamService.getAllsp());
        return "/View/ChiTietSanPham";
    }
}
