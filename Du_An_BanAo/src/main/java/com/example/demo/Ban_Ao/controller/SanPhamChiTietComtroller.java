package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.entity.KichThuoc;
import com.example.demo.Ban_Ao.entity.MauSac;
import com.example.demo.Ban_Ao.entity.SanPhamChiTiet;
import com.example.demo.Ban_Ao.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public String spct(Model model, @RequestParam(name = "x", defaultValue = "0") int x) {
        model.addAttribute("SanPhamChiTiet", new SanPhamChiTiet());
        model.addAttribute("mauSacList", mauSacService.listMauSac());
        model.addAttribute("kichThuocList", kichThuocService.listKichThuoc());

        Page<SanPhamChiTiet> sanPhamChiTietPage = sanPhamChiTietService.sanPhamChiTietPage(x);
        model.addAttribute("SanPhamChiTietList", sanPhamChiTietPage.getContent());
        model.addAttribute("currentPage", x);
        model.addAttribute("totalPages", sanPhamChiTietPage.getTotalPages());

        model.addAttribute("listsanpham", sanPhamService.getAllsp());
        model.addAttribute("spdt", sanPhamChiTietlist);

        return "View/ChiTietSanPham";
    }


    @PostMapping("sanphamdetam")
    public String sanphamdetam(Model model,
                               @RequestParam("sanphamid") int sanphamid,
                               @RequestParam("kichThuocIds") List<Integer> kichThuocIds,
                               @RequestParam("mauSacIds") List<Integer> mauSacIds) {
        
        boolean exists = sanPhamChiTietlist.stream().anyMatch(s -> {
            boolean isSameProduct = s.getSanPham().getId() == sanphamid;

            boolean hasSameKichThuoc = kichThuocIds.contains(s.getKichThuoc().getId());
            boolean hasSameMauSac = mauSacIds.contains(s.getMauSac().getId());
            return isSameProduct && hasSameKichThuoc && hasSameMauSac ;
        });
        if (!exists) {
            for (Integer kichThuocId : kichThuocIds) {
                for (Integer mauSacId : mauSacIds) {
                    SanPhamChiTiet newSpct = new SanPhamChiTiet();
                    newSpct.setId(new Random().nextInt(1000) + 100);
                    newSpct.setAnh_san_pham_chi_tiet(null);
                    newSpct.setDon_gia(1000.0);
                    newSpct.setSo_luong_san_pham(1);

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
    public String addSPCT() {
        for (SanPhamChiTiet spctadd : sanPhamChiTietlist) {
            Optional<SanPhamChiTiet> existingProduct = sanPhamChiTietService.SPCT().stream().filter(s -> {
                boolean isSameProduct = s.getSanPham().getId() == spctadd.getSanPham().getId();
                boolean hasSameKichThuoc = spctadd.getKichThuoc().getId() == s.getKichThuoc().getId();
                boolean hasSameMauSac = spctadd.getMauSac().getId() == s.getMauSac().getId();
                return isSameProduct && hasSameKichThuoc && hasSameMauSac;
            }).findFirst();

            if (existingProduct.isPresent()) {
                SanPhamChiTiet spct = existingProduct.get();
                spct.setSo_luong_san_pham(spct.getSo_luong_san_pham() + spctadd.getSo_luong_san_pham());
                sanPhamChiTietService.addspct(spct);
            } else {
                sanPhamChiTietService.addspct(spctadd);
            }
        }
        sanPhamChiTietlist.clear();
        return "redirect:/ChiTietSanPham/HienThi";
    }


    @GetMapping("/xoathanhtro")
    public String xoathanhtro( @RequestParam(name = "id") int x){
        List<SanPhamChiTiet> detamthoihehe = new ArrayList<>();
        for (int i = 0 ;i<sanPhamChiTietlist.size();i++) {
            if(sanPhamChiTietlist.get(i).getId().equals(x)){
                detamthoihehe.add(sanPhamChiTietlist.get(i));
            }
        }
         sanPhamChiTietlist.removeAll(detamthoihehe);
        return "redirect:/ChiTietSanPham/HienThi";
    }

}
