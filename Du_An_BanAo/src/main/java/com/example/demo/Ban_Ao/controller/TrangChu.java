package com.example.demo.Ban_Ao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("TrangChu")
public class TrangChu {

    @GetMapping("/SanPham")
    public String product_san_pham() {
        return "redirect:/sanpham/hienthi";
    }
    @GetMapping("/CoAo")
    public String product_co_ao() {
        return "redirect:/CoAo/HienThi";
    }
    @GetMapping("/MauSac")
    public String product_mau_sac() {
        return "View/MauSac";
    }

}
