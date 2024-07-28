package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.entity.HoaDon;
import com.example.demo.Ban_Ao.repository.hoaDonRepository;
import com.example.demo.Ban_Ao.repository.HoaDonctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hoadon")
public class HoadonController {
    @Autowired
    hoaDonRepository hoaDonRepository;
    @Autowired
    HoaDonctRepository hoaDonctRepository;

    Page<HoaDon> getpage(int p){
        Pageable pageable = PageRequest.of(p,3);
        return hoaDonRepository.findAll(pageable);
    }
    @GetMapping("hienthi")
    public String hienthi(@RequestParam(name = "page", defaultValue = "0") int page, Model model){
        model.addAttribute("lshoadon",getpage(page));
        model.addAttribute("total",getpage(page).getTotalPages());
        model.addAttribute("curren",page);
        model.addAttribute("HoaDon", new HoaDon());
        return "View/hoadon";
    }
    @GetMapping("detail")
    public String detail(@RequestParam("id") int id,Model model){
        String mahd = hoaDonctRepository.findbyhdct(id).get(1).getHoaDon().getMahoadon();
        System.out.println(mahd);
        model.addAttribute("lshoadonct",hoaDonctRepository.findbyhdct(id));
        model.addAttribute("mahd","Thông tin chi tiết mã hóa đơn : " + mahd);
        return "View/hoadonchitiet";
    }

    @GetMapping("find")
    public String detail(@RequestParam("mahoadon") String ma,Model model){
        model.addAttribute("lshoadon",hoaDonRepository.findByMahoadon(ma));
        model.addAttribute("total",getpage(0));
        model.addAttribute("HoaDon", new HoaDon());
        return "View/hoadon";
    }
}
