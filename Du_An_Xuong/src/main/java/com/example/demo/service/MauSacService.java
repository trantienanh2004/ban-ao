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
<<<<<<< HEAD
=======

    public MauSac findById(Integer id){return mauSacRepository.findById(id).get();}

    public MauSac createMauSac(MauSac mauSac){

        mauSacRepository.save(mauSac);
        return mauSac;
    }

    public MauSac updateMauSac( MauSac mauSac){


        mauSacRepository.save(mauSac);
        return mauSac;
    };

    public MauSac findByCode(String code){return mauSacRepository.findMauSacByMa_mau_sac(code);}

    public void deleteMauSac(Integer id){mauSacRepository.deleteById(id);}
>>>>>>> thuoctinhsanpham
}
