package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.entity.CoAo;
import com.example.demo.Ban_Ao.repository.CoAoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/CoAo")
public class CoAoController {
    @Autowired
    CoAoRepository coAoRepository;

    @GetMapping(value = "/HienThi")
    public String viewCoAo(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 2);
        Page<CoAo> listCA = coAoRepository.findAll(pageable);
        model.addAttribute("totalPage", listCA.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listca", listCA);
        model.addAttribute("CoAo", new CoAo());
        return "/View/CoAo";
//        return "redirect:/TrangChu/CoAo";
    }

    @PostMapping("/Add")
    public String AddCoAo(@Valid @ModelAttribute("CoAo") CoAo coAo) {
            coAo.setNgaytao(new Date());
//            coAo.setNgaysua(new Date());
            coAoRepository.save(coAo);
            return "redirect:/CoAo/HienThi";
    }

    @GetMapping("remove/{id}")
    public String removePB(@PathVariable Integer id) {
        coAoRepository.deleteById(id);
        return "redirect:/CoAo/HienThi";
    }

    @GetMapping("/Update/{id}")
    public String detailPB(@PathVariable Integer id, Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 2);
        Page<CoAo> listCA = coAoRepository.findAll(pageable);
        model.addAttribute("CoAo", coAoRepository.getReferenceById(id));
        model.addAttribute("totalPage", listCA.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listca", listCA);
        return "View/CoAoUpdate";
    }

    @PostMapping("/Update")
    public String update(@Valid  @RequestParam("id") Integer id ,@ModelAttribute("CoAo") CoAo coAo) {
        List<CoAo> listca = coAoRepository.findAll();
        Date ngayTao = null;
        for (CoAo ca : listca) {
            if (ca.getId() == id) {
                ngayTao = ca.getNgaytao();
            }
        }
        coAo.setNgaytao(ngayTao);
        coAo.setNgaysua(new Date());
        coAoRepository.save(coAo);
        return "redirect:/CoAo/HienThi";
    }
    @GetMapping("search")
    public String search(@RequestParam("key") String key,Model model){
        List<CoAo> listCA = coAoRepository.findByTen(key);
        model.addAttribute("listca",listCA);
        model.addAttribute("CoAo",new CoAo());
        return "/View/CoAo";
    }
    @GetMapping("/back")
    public String back() {
        return "redirect:/CoAo/HienThi";
    }

}
