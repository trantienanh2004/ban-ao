package com.example.demo.controller;

import com.example.demo.entity.KichThuoc;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.SanPhamChiTiet;
import com.example.demo.repository.ChatLieuRepository;
import com.example.demo.repository.KichThuocRepository;
import com.example.demo.repository.MauSacRepository;
import com.example.demo.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/sanpham")
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

int id = 1;


    @GetMapping("/formSPCT")
    public String spct(Model model, @RequestParam(name = "x", defaultValue = "0") int x){
        model.addAttribute("SanPhamChiTiet" ,new SanPhamChiTiet());
        model.addAttribute("mauSacList",mauSacService.listMauSac());
        model.addAttribute("kichThuocList", kichThuocService.listKichThuoc());
        model.addAttribute("chatlieuList",chatLieuService.ListChatlieu());
        model.addAttribute("SanPhamChiTietList",sanPhamChiTietService.sanPhamChiTietPage(x));
        model.addAttribute("sanphamdetam",sanPhamChiTietService.SPCT_de_tam);
        model.addAttribute("listsanpham",sanPhamService.getAllsp());
        return "SanPham/san-pham-chi-tiet";
    }

    @PostMapping("/spctadd")
    public String spctAdd(Model model, @Valid @ModelAttribute("SanPhamChiTiet") SanPhamChiTiet sanPhamChiTiet) {
    sanPhamChiTiet.setId(id);
    sanPhamChiTiet.setSo_luong_san_pham(10);
    sanPhamChiTiet.setDon_gia(10000.0);
    sanPhamChiTiet.setAnh_san_pham_chi_tiet("chưa có");
    id++;
        sanPhamChiTietService.SPCT_de_tam.add(sanPhamChiTiet);

        return "redirect:/sanpham/formSPCT";
    }
    @GetMapping("/xoaSPCT")
    public String xoaSPCT(@RequestParam("id")int id) {
      sanPhamChiTietService.xoaSPCT(id);

        return "redirect:/sanpham/formSPCT";
    }
    @GetMapping("/xoaSPCTtam")
    public String xoaSPCTtam(@RequestParam("id")int id) {
        sanPhamChiTietService.xoaSPCTtam(id);

        return "redirect:/sanpham/formSPCT";
    }
    @GetMapping("/themSPCT")
    public String themSPCT() {
        for (SanPhamChiTiet spct: sanPhamChiTietService.SPCT_de_tam) {
             spct.setId(null);
            sanPhamChiTietService.addSPCT(spct);

        }
        sanPhamChiTietService.SPCT_de_tam.clear();
        return "redirect:/sanpham/formSPCT";
    }
}
