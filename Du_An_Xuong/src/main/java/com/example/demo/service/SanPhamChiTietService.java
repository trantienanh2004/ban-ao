package com.example.demo.service;

import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPhamChiTiet;

import com.example.demo.repository.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SanPhamChiTietService {
@Autowired
SanPhamChiTietRepository sanPhamChiTietRepository;

    public List<SanPhamChiTiet> SPCT_de_tam = new ArrayList<>();

    public int size = 2;
  public Page<SanPhamChiTiet> sanPhamChiTietPage (int x){
      Sort s = Sort.by(Sort.Direction.ASC,"id");
      Pageable page = PageRequest.of(x,size,s);
      return sanPhamChiTietRepository.findAll(page);
  }
  public SanPhamChiTiet timkiemspct(int id){
      return sanPhamChiTietRepository.findById(id).orElse(null);
  }
  public void xoaSPCT(int id){
      sanPhamChiTietRepository.deleteById(id);
  }
    public void xoaSPCTtam(int id) {
        SanPhamChiTiet spctToRemove = null;
        for (SanPhamChiTiet spct : SPCT_de_tam) {
            if (spct.getId() == id) {
                spctToRemove = spct;
                break;
            }
        }
        if (spctToRemove != null) {
            SPCT_de_tam.remove(spctToRemove);
        }
    }
    public void addSPCT(SanPhamChiTiet sanPhamChiTiet){
      sanPhamChiTietRepository.save(sanPhamChiTiet);
    }
}
