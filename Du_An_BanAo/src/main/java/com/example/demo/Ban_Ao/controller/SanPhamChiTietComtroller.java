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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    List<SanPhamChiTiet> sanPhamChiTietlist = new ArrayList<>();

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
        model.addAttribute("listsanpham",sanPhamService.getAllsp());

        // Thêm danh sách vào model để hiển thị trong view
        model.addAttribute("spdt", sanPhamChiTietlist);

        return "/View/ChiTietSanPham";
    }

    @PostMapping("sanphamdetam")
    public String sanphamdetam(Model model,
                               @RequestParam("sanphamid") int sanphamid,
                               @RequestParam("chatlieuid") int chatlieuid,
                               @RequestParam("kichThuocIds") List<Integer> kichThuocIds,
                               @RequestParam("mauSacIds") List<Integer> mauSacIds) {
        
        boolean exists = sanPhamChiTietlist.stream().anyMatch(s -> {
            boolean isSameProduct = s.getSanPham().getId() == sanphamid;
            boolean isSamechatlieugigido = s.getChatLieu().getId() == chatlieuid;
            boolean hasSameKichThuoc = kichThuocIds.contains(s.getKichThuoc().getId());
            boolean hasSameMauSac = mauSacIds.contains(s.getMauSac().getId());
            return isSameProduct && hasSameKichThuoc && hasSameMauSac && isSamechatlieugigido;
        });
        if (!exists) {
            for (Integer kichThuocId : kichThuocIds) {
                for (Integer mauSacId : mauSacIds) {
                    SanPhamChiTiet newSpct = new SanPhamChiTiet();
                    newSpct.setId(new Random().nextInt(1000) + 100);
                    newSpct.setAnh_san_pham_chi_tiet(null);
                    newSpct.setDon_gia(1000.0);
                    newSpct.setSo_luong_san_pham(1);
                    newSpct.setChatLieu(chatLieuService.timtheoid(chatlieuid));
                    newSpct.setNgay_tao(LocalDate.now());
                    newSpct.setSanPham(sanPhamService.getOnesp(sanphamid));
                    if (kichThuocId != null) {
                        KichThuoc kichThuoc = kichThuocService.timtheoid(kichThuocId);
                        newSpct.setKichThuoc(kichThuoc);
                    }
                    if (mauSacId != null) {
                        MauSac mauSac = mauSacService.timtheoid(mauSacId);
                        newSpct.setMauSac(mauSac);
                    }
                    sanPhamChiTietlist.add(newSpct);
                }
            }
        }
        model.addAttribute("spdt", sanPhamChiTietlist);

        return "redirect:/ChiTietSanPham/HienThi";
    }
@GetMapping("addSPCT")
    public String addSPCT(){
    for (SanPhamChiTiet spctadd: sanPhamChiTietlist ) {


    }
    sanPhamChiTietlist.clear();
    return "redirect:/ChiTietSanPham/HienThi";
}
}
