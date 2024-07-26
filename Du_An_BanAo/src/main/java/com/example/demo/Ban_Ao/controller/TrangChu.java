package com.example.demo.Ban_Ao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("TrangChu")
public class TrangChu {



    @GetMapping("/Home")
    public String product_home() {
        return "/Home";
    }

    @GetMapping("/SanPham")
    public String product_san_pham() {
        return "View/SanPham";
    }


    @GetMapping("/CoAo")
    public String product_co_ao() {
        return "redirect:/CoAo/HienThi";
    }


    @GetMapping("/Voucher")
    public String product_voucher() {
        return "redirect:/Voucher/HienThi";
    }


    @GetMapping("/MauSac")
    public String product_mau_sac() {
        return "View/MauSac";
    }

}
