package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.entity.SanPham;
import com.example.demo.Ban_Ao.repository.*;
import com.example.demo.Ban_Ao.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/SanPham")
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    DanhMucRepository danhMucRepository;
    @Autowired
    ThuongHieuRepository thuongHieuRepository;
    @Autowired
    CoAoRepository coAoRepository;
    @Autowired
    NhaSanXuatRepository nhaSanXuatRepository;
    @Autowired
    MauSacRepository mauSacRepository;
    @Autowired
    KichThuocRepository kichThuocRepository;
    Sort sort = Sort.by(Sort.Direction.DESC,"id");
    @GetMapping("/HienThi")
    public String hienthisp(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        model.addAttribute("listSP", sanPhamService.getAllByPage(page));
        return "View/SanPham";

    }

}
