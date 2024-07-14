package com.example.demo.controller;

import com.example.demo.entity.KichThuoc;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import com.example.demo.repository.*;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/sanpham")
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
    @GetMapping("/hienthi")
    public String hienthisp(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        model.addAttribute("hienthi", sanPhamService.getAllByPage(page));
        return "SanPham/san-pham";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id")int id ,Model model){
        model.addAttribute("onesp",sanPhamService.getOnesp(id));
        model.addAttribute("lsdanhmuc",danhMucRepository.findAll(sort));
        model.addAttribute("lsnsx",nhaSanXuatRepository.findAll(sort));
        model.addAttribute("lsthuonghieu",thuongHieuRepository.findAll(sort));
        model.addAttribute("lscoao",coAoRepository.findAll(sort));
        return "SanPham/sanpham-update";
    }

    @GetMapping("viewadd")
    public String viewadd(Model model){
        model.addAttribute("lsdanhmuc",danhMucRepository.findAll(sort));
        model.addAttribute("lsnsx",nhaSanXuatRepository.findAll(sort));
        model.addAttribute("lsthuonghieu",thuongHieuRepository.findAll(sort));
        model.addAttribute("lscoao",coAoRepository.findAll(sort));
        return "SanPham/sanpham-add";
    }

    @GetMapping("/spct")
    public String spct(Model model){
        return "redirect:/sanpham/formSPCT";
    }

    @PostMapping("update")
    public String update(SanPham sp){
        sanPhamService.update(sp);
        return "redirect:/sanpham/hienthi";
    }

    @PostMapping("add")
    public String add(SanPham sp){
        sanPhamService.add(sp);
        return "redirect:/sanpham/hienthi";
    }
}
