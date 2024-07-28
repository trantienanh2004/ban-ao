package com.example.demo.Ban_Ao.repository;

import com.example.demo.Ban_Ao.entity.HoaDonCT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoaDonctRepository extends JpaRepository<HoaDonCT,Integer> {

    @Query(value = "SELECT * FROM HOA_DON_CHI_TIET ct where ct.ID_HOA_DON = :id",nativeQuery = true)
    List<HoaDonCT> findbyhdct(@Param("id") int id);
}
