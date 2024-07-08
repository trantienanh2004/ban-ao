package com.example.demo.Ban_Ao.repository;

import com.example.demo.Ban_Ao.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham,Integer> {
    public List<SanPham> findSanPhamByTensp(String ten);
    //public List<SanPham> findSanPhamByTran(String ten);
}
