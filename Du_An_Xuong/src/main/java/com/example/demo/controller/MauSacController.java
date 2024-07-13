package com.example.demo.controller;



import com.example.demo.entity.MauSac;
import com.example.demo.service.MauSacService;
import jakarta.validation.Valid;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin("*")
@Controller
@RequestMapping("/mausac")
public class MauSacController {
    @Autowired
    MauSacService mauSacService;

//    @GetMapping("/store")
//    public String store(){
//        return "mausac/list";
//    }

    @PostMapping("/create")
    public String createMauSac(@Valid @ModelAttribute("ms") MauSac ms, BindingResult result, Model model){
        MauSac mauSac = mauSacService.findByCode(ms.getMa_mau_sac());
        if(result.hasErrors()){
            List<MauSac> mauSacList = mauSacService.listMauSac();
            System.out.println(mauSacList.size());
            model.addAttribute("list", mauSacList);
            return "mausac/list";
        }

        ms.setNgay_tao(LocalDate.now());
        ms.setNgay_sua(LocalDate.now());
       mauSacService.createMauSac(ms);
        System.out.println(ms.getTen_mau_sac()+" -"+ms.getMa_mau_sac());
       return "redirect:/mausac/getall";
    }

    @GetMapping("/getall")
    public String getAll(Model model){

        List<MauSac> mauSacList = mauSacService.listMauSac();
        System.out.println(mauSacList.size());
        model.addAttribute("list", mauSacList);
        model.addAttribute("ms", new MauSac());
        return "mausac/list";
    }

    @GetMapping("/update/{id}")
    public String updateMauSac(@PathVariable("id") Integer id, Model model){

        MauSac exsit = mauSacService.findById(id);
        model.addAttribute("ms", exsit);
        List<MauSac> mauSacList = mauSacService.listMauSac();
        model.addAttribute("list", mauSacList);
          return "mausac/list";
    }

    @PostMapping("/update")
    public String update( @Valid @ModelAttribute("ms") MauSac mauSac, BindingResult result, Model model){
        if(result.hasErrors()){
            List<MauSac> mauSacList = mauSacService.listMauSac();
            System.out.println(mauSacList.size());
            model.addAttribute("list", mauSacList);
            return "mausac/list";
        }
            MauSac mausac= mauSacService.findById(mauSac.getId());
            mauSac.setNgay_tao(mausac.getNgay_tao());
            mauSac.setNgay_sua(LocalDate.now());

        mauSacService.updateMauSac(mauSac);
        return "redirect:/mausac/getall";
    }

    //
    @GetMapping("/delete/{id}")
    public String deleteMauSac(@PathVariable("id") Integer id){
        mauSacService.deleteMauSac(id);
        return "redirect:/mausac/getall";
    }
}
