package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.entity.CoAo;
import com.example.demo.Ban_Ao.repository.CoAoRepository;
import com.example.demo.Ban_Ao.service.CoAoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/CoAo")
public class CoAoController {
    @Autowired
    CoAoRepository coAoRepository;
    @Autowired
    CoAoService coAoService;

    @GetMapping("/HienThi")
    public String ShowList(Model model){
//        model.addAttribute("listCA", coAoRepository.findAll());
        return viewCoAo(model, 1);
    }
    @GetMapping(value= "/HienThi/page/{pageNumber}")
    public String viewCoAo(Model model, @PathVariable("pageNumber") int pageNumber){
        Page<CoAo> page = coAoService.getAllByPage(pageNumber);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("listca", page.getContent());
        model.addAttribute("CoAo", new CoAo());
        return "/View/CoAo";
    }
    @PostMapping("/Add")
    public String AddCoAo(@Valid @ModelAttribute("CoAo") CoAo coAo, BindingResult result, Model model, @RequestParam(name="pageNumber", defaultValue = "1") Integer pageNumber){
       if(result.hasErrors()){
           Page<CoAo> page = coAoService.getAllByPage(pageNumber);
           model.addAttribute("totalPages", page.getTotalPages());
           model.addAttribute("currentPage", pageNumber);
           model.addAttribute("listca", page.getContent());
           return "/View/CoAo";
       }else {
           coAo.setNgaytao(new Date());
           coAo.setNgaysua(new Date());
           coAoService.add(coAo);
           return "redirect:/CoAo/HienThi";
       }
    }

}
