package com.example.demo.service;

import com.example.demo.entity.KichThuoc;
import com.example.demo.entity.MauSac;
import com.example.demo.repository.KichThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KichThuocService {

    @Autowired
    KichThuocRepository kichThuocRepository;

    public List<KichThuoc> listKichThuoc(){
        return kichThuocRepository.findAll();
    }
    public List<KichThuoc> listtheoid(List<Integer> id){
        return kichThuocRepository.findAllById(id);
    }
}
