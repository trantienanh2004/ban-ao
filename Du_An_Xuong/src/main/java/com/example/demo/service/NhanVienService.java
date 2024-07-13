package com.example.demo.service;

import com.example.demo.entity.NhanVien;
import com.example.demo.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NhanVienService {
    private  final NhanVienRepository nhanVienRepository;

    public List<NhanVien> findAll(){
        return nhanVienRepository.findAll();
    }

    public NhanVien findById(Integer id){
        return nhanVienRepository.findById(id).get();
    }

    public NhanVien createNhanVien(NhanVien nhanVien){
        return nhanVienRepository.save(nhanVien);
    }

    public NhanVien updateNhanVien(Integer id, NhanVien nhanVien){
        NhanVien existNV = findById(id);
        existNV.setTenNhanVien(nhanVien.getTenNhanVien());
        existNV.setMaNhanVien(nhanVien.getMaNhanVien());
        existNV.setTenTaiKhoan(nhanVien.getTenTaiKhoan());
        existNV.setMatKhau(nhanVien.getMatKhau());
        existNV.setNgaySua(nhanVien.getNgaySua());
        existNV.setChucVu(nhanVien.getChucVu());

        nhanVienRepository.save(existNV);
        return existNV;
    }

    public void deleteNhanVIen(Integer id){
        NhanVien nhanVien = findById(id);
        nhanVien.setTrangThai(false);
    }
}
