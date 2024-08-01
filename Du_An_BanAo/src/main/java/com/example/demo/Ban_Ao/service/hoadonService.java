package com.example.demo.Ban_Ao.service;

import com.example.demo.Ban_Ao.entity.HoaDon;
import com.example.demo.Ban_Ao.entity.HoaDonCT;
import com.example.demo.Ban_Ao.repository.HoaDonctRepository;
import com.example.demo.Ban_Ao.repository.hoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class hoadonService {
    @Autowired
    hoaDonRepository hoaDonRepository;
    @Autowired
    HoaDonctRepository hoaDonctRepository;
    public  Integer idhoadon = null ;
    public  Double Tongtien = 0.0;
    public  String tenkhachhang = null;
    public List<HoaDon> hoaDons(){
        return hoaDonRepository.findAll();
    }
    public List<HoaDonCT> hoaDonCTS(Integer id){
        List<HoaDonCT> hoaDonCT= new ArrayList<>();
        if(id == null){
            return hoaDonCT;
        }
        return hoaDonctRepository.findbyhdct(id);
    }
    public HoaDon timkiemhddo(Integer id){
        return hoaDonRepository.findById(id).get();
    }
    public String randomMa_HDCT() {
        String randomString;
        int length = 6;
        Random rd = new Random();
        //những chữ được quyền truy c
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = rd.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        randomString = sb.toString();
        System.out.println("Chuỗi ngẫu nhiên được tạo ra là: " + randomString);
        return randomString;
    }
    public List<HoaDonCT> findByHoaDonId(int hoaDonId) {
        return hoaDonctRepository.findByHoaDonId(hoaDonId);
    }
    public long countByTrangThai(int trangThai) {
        return hoaDonRepository.countByTrangthai(trangThai);
    }
    public void addHDCT(HoaDonCT hoaDonCT){
        hoaDonctRepository.save(hoaDonCT);
    }
    public void addHD(HoaDon hoaDon){
        hoaDonRepository.save(hoaDon);
    }
    public void deleteHDCT(int id){
        hoaDonctRepository.deleteById(id);
    }
}
