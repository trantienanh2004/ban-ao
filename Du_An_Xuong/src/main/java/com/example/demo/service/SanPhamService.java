package com.example.demo.service;

import com.example.demo.entity.SanPham;
import com.example.demo.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SanPhamService {
    @Autowired
    SanPhamRepository sanPhamRepository;

    public Sort sapXepById() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return sort;
    }

    public Page<SanPham> getAllByPage(int numberpage) {
        Pageable pageable = PageRequest.of(numberpage, 5);
        return sanPhamRepository.findAll(pageable);
    }

    public List<SanPham> getAllsp() {
        return sanPhamRepository.findAll();
    }

    public void add(SanPham sp) {
        sanPhamRepository.save(sp);
    }

    public void delete(Integer id) {
        sanPhamRepository.deleteById(id);
    }

    public void update(SanPham sp) {
        sanPhamRepository.save(sp);
    }

    public SanPham getOnesp(Integer id) {
        return sanPhamRepository.findById(id).get();
    }

    public List<SanPham> findByName(String tensp) {
        return sanPhamRepository.findSanPhamByTensp(tensp);
    }

//    public List<SanPham> findByTrangThai(String tensp){
//        return sanPhamRepository.findSanPhamByTensp(tensp);
//    }
}
