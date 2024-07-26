package com.example.demo.Ban_Ao.service;

import com.example.demo.Ban_Ao.entity.KichThuoc;
import com.example.demo.Ban_Ao.repository.KichThuocRepository;
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

    public KichThuoc timtheoid(int id){
        return kichThuocRepository.findById(id).get();
    }

}
