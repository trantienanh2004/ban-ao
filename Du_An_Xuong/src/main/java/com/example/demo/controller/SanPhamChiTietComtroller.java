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
    public String spctAdd(Model model, @Valid @ModelAttribute("SanPhamChiTiet") SanPhamChiTiet sanPhamChiTiet,
                          @RequestParam("mauSac") List<Integer> mauSacIds,
                          @RequestParam("kichThuoc") List<Integer> kichThuocIds) {


        List<MauSac> mauSacs = mauSacService.listtheoid(mauSacIds);

        // Đảm bảo sanPhamChiTiet đã được khởi tạo
        if (sanPhamChiTiet == null) {
            sanPhamChiTiet = new SanPhamChiTiet(); // hoặc khởi tạo theo cách khác
        }

        // Thiết lập từng MauSac vào sanPhamChiTiet
        for (MauSac mauSac : mauSacs) {
            sanPhamChiTiet.setMauSac(mauSac);
        }

        // Lấy danh sách các KichThuoc từ Service
        List<KichThuoc> kichThuocs = kichThuocService.listtheoid(kichThuocIds);

        // Thiết lập từng KichThuoc vào sanPhamChiTiet
        for (KichThuoc kichThuoc : kichThuocs) {
            sanPhamChiTiet.setKichThuoc(kichThuoc); // Đảm bảo rằng phương thức addKichThuoc() phải tồn tại trong đối tượng SanPhamChiTiet
        }

        sanPhamChiTietService.SPCT_de_tam.add(sanPhamChiTiet);

        return "redirect:/sanpham/formSPCT";
    }


}
