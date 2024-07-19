package com.example.demo.controller;

import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/nhanvien")
@RequiredArgsConstructor
public class NhanVienController {

    private final NhanVienService service;

    @GetMapping("/getall")
    public String getAll(Model model) {
        List<NhanVien> listNV = service.findAll();
        model.addAttribute("list", listNV);
        model.addAttribute("nv", new NhanVien());
        return "nhanvien/listnv";
    }

    @PostMapping("/create")
    public String createNhanVien(@Valid @ModelAttribute("nv") NhanVien nhanVien, BindingResult result, Model model) {
        NhanVien nv = service.findByMaNhanVien(nhanVien.getMaNhanVien());
        if (nv != null) {
            model.addAttribute("message", "Mã nhân viên đã tồn tại");
            List<NhanVien> listNV = service.findAll();
            model.addAttribute("list", listNV);
            return "nhanvien/listnv";
        }

        if (result.hasErrors()) {
            List<NhanVien> listNV = service.findAll();
            model.addAttribute("list", listNV);
            return "nhanvien/listnv";
        }
        nhanVien.setNgayTao(LocalDate.now());
        nhanVien.setNgaySua(LocalDate.now());
        nhanVien.setTrangThai(true);
        service.createNhanVien(nhanVien);
        return "redirect:/nhanvien/getall";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        service.deleteNhanVIen(id);
        System.out.println(id);
        return "redirect:/nhanvien/getall";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        NhanVien nv = service.findById(id);
        model.addAttribute("nv", nv);
        List<NhanVien> listNV = service.findAll();
        model.addAttribute("list", listNV);
        return "nhanvien/listnv";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("nv") NhanVien nhanVien, BindingResult result, Model model) {
        NhanVien exist = service.findById(nhanVien.getId());
        if (result.hasErrors()) {
            List<NhanVien> listNV = service.findAll();
            model.addAttribute("list", listNV);
            return "nhanvien/listnv";
        }
        nhanVien.setNgayTao(exist.getNgayTao());
        service.updateNhanVien(nhanVien);
        return "redirect:/nhanvien/getall";
    }
}
