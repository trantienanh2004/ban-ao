package com.example.demo.Ban_Ao.repository;

import com.example.demo.Ban_Ao.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface hoaDonRepository extends JpaRepository<HoaDon,Integer> {
    public List<HoaDon> findByMahoadon(String ma);
}
