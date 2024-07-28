package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.entity.KichThuoc;

import com.example.demo.Ban_Ao.repository.Kichthuocepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/kichThuoc")
public class KichthuocController {
    @Autowired
    Kichthuocepository kichthuocepository;

    Page<KichThuoc> getpage(int p){
        Pageable pageable = PageRequest.of(p,2);
        return kichthuocepository.findAll(pageable);
    }
    @GetMapping("/hienthi")
    public String hienthi(@RequestParam(name = "page",defaultValue = "0") int page, Model model){
        model.addAttribute("lskichthuoc",getpage(page));
        model.addAttribute("KichThuoc", new KichThuoc());
        return"View/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id){
        kichthuocepository.deleteById(id);
        return"redirect:/kichthuoc/hienthi";
    }

    @PostMapping("/Add")
    public String add(@ModelAttribute("KichThuoc") KichThuoc kt, Model model,RedirectAttributes rs){
        // lấy thời gian hiện tại
        LocalDate now = LocalDate.now();
        kt.setNgay_tao(now);
        String ten = kt.getTen_kich_thuoc();
        String ma = kt.getMa_kich_thuoc();
        if(ten.isBlank()||ma.isBlank()){
            model.addAttribute("loi","Không được để trống");
            model.addAttribute("lskichthuoc",getpage(0));
            model.addAttribute("KichThuoc", new KichThuoc());
            return"View/index";
        }
        kichthuocepository.save(kt);
        return"redirect:/kichthuoc/hienthi";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Integer id,Model model){
        model.addAttribute("ktdt",kichthuocepository.findById(id).get());
        model.addAttribute("KichThuoc",new KichThuoc());
        return"View/index";
    }

    @PostMapping("/Update")
    public String update(@ModelAttribute("KichThuoc") KichThuoc kt, Model model,RedirectAttributes rs){
        // lấy thời gian hiện tại
        LocalDate now = LocalDate.now();
        kt.setNgay_sua(now);
        String ten = kt.getTen_kich_thuoc();
        String ma = kt.getMa_kich_thuoc();
        if(ten.isBlank()||ma.isBlank()){
            model.addAttribute("loi","Không được để trống");
            model.addAttribute("lskichthuoc",getpage(0));
            model.addAttribute("KichThuoc", new KichThuoc());
            return"View/index";
        }
        kichthuocepository.save(kt);
        return"redirect:/kichthuoc/hienthi";
    }
}
