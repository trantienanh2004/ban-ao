package com.example.demo.Ban_Ao.service;

import com.example.demo.Ban_Ao.entity.NhanVien;
import com.example.demo.Ban_Ao.repository.nhanvienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class nhanvienService {
    @Autowired
    nhanvienRepository nhanvienRepository;
    public NhanVien timkiemnv(Integer id){
        return nhanvienRepository.findById(id).orElse(null);
    }

}
