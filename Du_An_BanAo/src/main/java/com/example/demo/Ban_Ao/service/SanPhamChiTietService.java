package com.example.demo.Ban_Ao.service;


import com.example.demo.Ban_Ao.entity.SanPhamChiTiet;
import com.example.demo.Ban_Ao.repository.SanPhamChiTietRepository;
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

    public int size = 5;
  public Page<SanPhamChiTiet> sanPhamChiTietPage (int x){
      Sort s = Sort.by(Sort.Direction.ASC,"id");
      Pageable page = PageRequest.of(x,size,s);
      return sanPhamChiTietRepository.findAll(page);
  }
//  nguy hiểm
    public Page<SanPhamChiTiet> sanPhamChiTietPageVer2(String tenSanPham, String chatLieu, String mauSac, String kichThuoc, int x) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(x, size, sort);

        return sanPhamChiTietRepository.findByFilter(tenSanPham, chatLieu, mauSac, kichThuoc, pageable);
    }

    public SanPhamChiTiet timkiemspct(int id){
     return sanPhamChiTietRepository.findById(id).orElse(new SanPhamChiTiet());
   }
 public List<SanPhamChiTiet> SPCT (){
      return sanPhamChiTietRepository.findAll();
 }
 public void addspct (SanPhamChiTiet spct){
      sanPhamChiTietRepository.save(spct);
 }
 public void xoaspct(int id){
      sanPhamChiTietRepository.deleteById(id);
 }
    //  nguy hiểm
 public List<SanPhamChiTiet> locspct(String tenSanPham,String chatLieu,String mauSac,String kichThuoc){
      return sanPhamChiTietRepository.findByCriteria(tenSanPham, chatLieu, mauSac, kichThuoc);
 }

}
