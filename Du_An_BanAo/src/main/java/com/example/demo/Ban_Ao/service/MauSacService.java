package com.example.demo.Ban_Ao.service;

import com.example.demo.Ban_Ao.entity.MauSac;
import com.example.demo.Ban_Ao.repository.MauSacRepository;
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
