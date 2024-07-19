package com.example.demo.service;

import com.example.demo.entity.NhanVien;
import com.example.demo.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;

    public List<NhanVien> findAll() {
        return nhanVienRepository.findAll();
    }

    public NhanVien findById(Integer id) {
        return nhanVienRepository.findById(id).get();
    }

    public NhanVien findByMaNhanVien(String maNhanVien) {
        return nhanVienRepository.findNhanVienByMaNhanVien(maNhanVien);
    }

    public NhanVien createNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    public NhanVien updateNhanVien(NhanVien nhanVien) {
        nhanVien.setNgaySua(LocalDate.now());
        nhanVienRepository.save(nhanVien);
        return nhanVien;
    }


    public void deleteNhanVIen(Integer id) {
        NhanVien nhanVien = findById(id);
        if (nhanVien.getTrangThai()) {
            nhanVien.setTrangThai(false);
            nhanVien.setNgaySua(LocalDate.now());
        } else {
            nhanVien.setTrangThai(true);
            nhanVien.setNgaySua(LocalDate.now());
        }

        nhanVienRepository.save(nhanVien);
    }
}
