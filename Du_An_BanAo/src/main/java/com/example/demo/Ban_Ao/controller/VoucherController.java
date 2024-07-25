package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.entity.Voucher;
import com.example.demo.Ban_Ao.repository.VoucherRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Voucher")
public class VoucherController {
    @Autowired
    VoucherRepository voucherRepository;

    @GetMapping("/HienThi")
    public String viewVC(Model model,  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo - 1, 2);
        Page<Voucher> listVC = voucherRepository.findAll(pageable);
        model.addAttribute("totalPage", listVC.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listvc", listVC);
        model.addAttribute("Voucher", new Voucher());
        return "/View/Voucher";
    }
    @PostMapping("/Add")
    public String AddVC(@Valid @ModelAttribute("Voucher") Voucher voucher) {
        voucherRepository.save(voucher);
        return "redirect:/Voucher/HienThi";
    }
    @GetMapping("remove/{id}")
    public String removePB(@PathVariable Integer id) {
        voucherRepository.deleteById(id);
        return "redirect:/Voucher/HienThi";
    }
    @GetMapping("/Update/{id}")
    public String detailPB(@PathVariable Integer id, Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 2);
        Page<Voucher> listVC = voucherRepository.findAll(pageable);
        model.addAttribute("Voucher", voucherRepository.getReferenceById(id));
        model.addAttribute("totalPage", listVC.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listvc", listVC);
        return "/View/VoucherUpdate";
    }
    @PostMapping("/Update")
    public String update(@Valid  @RequestParam("id") Integer id ,@ModelAttribute("Voucher") Voucher voucher) {
        voucherRepository.save(voucher);
        return "redirect:/Voucher/HienThi";
    }
    @GetMapping("/search")
    public String search(@RequestParam("key") String key,Model model){
        List<Voucher> listVC = voucherRepository.findByTen(key);
        model.addAttribute("listvc",listVC);
        model.addAttribute("Voucher",new Voucher());
        return "/View/Voucher";
    }
    @GetMapping("/back")
    public String back() {
        return "redirect:/Voucher/HienThi";
    }
}
