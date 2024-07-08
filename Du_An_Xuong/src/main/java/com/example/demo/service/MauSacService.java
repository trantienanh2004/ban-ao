package com.example.demo.service;

import com.example.demo.entity.KichThuoc;
import com.example.demo.entity.MauSac;
import com.example.demo.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacService {
    @Autowired
    MauSacRepository mauSacRepository;

    public List<MauSac> listMauSac(){
        return mauSacRepository.findAll();
    }

    public List<MauSac> listtheoid(List<Integer> id){
        return mauSacRepository.findAllById(id);
    }
}
