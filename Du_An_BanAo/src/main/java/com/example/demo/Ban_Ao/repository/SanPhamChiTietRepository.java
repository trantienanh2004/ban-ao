package com.example.demo.Ban_Ao.repository;

import com.example.demo.Ban_Ao.entity.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet,Integer> {
    @Query("SELECT spct FROM SanPhamChiTiet spct " +
            "JOIN spct.sanPham sp " +
            "JOIN spct.kichThuoc kt " +
            "JOIN spct.mauSac ms " +
            "JOIN sp.chatLieu cl " +
            "WHERE (:tenSanPham IS NULL OR sp.tensp LIKE %:tenSanPham%) " +
            "AND (:kichThuoc IS NULL OR kt.ten_kich_thuoc = :kichThuoc) " +
            "AND (:mauSac IS NULL OR ms.ten_mau_sac = :mauSac) " +
            "AND (:chatLieu IS NULL OR cl.ten_chat_lieu = :chatLieu)")
    List<SanPhamChiTiet> findByCriteria(
            @Param("tenSanPham") String tenSanPham,
            @Param("kichThuoc") String kichThuoc,
            @Param("mauSac") String mauSac,
            @Param("chatLieu") String chatLieu
    );
    @Query("SELECT spct FROM SanPhamChiTiet spct " +
            "INNER JOIN spct.sanPham sp " +
            "INNER JOIN spct.kichThuoc kt " +
            "INNER JOIN spct.mauSac ms " +
            "INNER JOIN sp.chatLieu cl " +
            "WHERE (sp.tensp LIKE %:tenSanPham% OR :tenSanPham IS NULL) " +
            "AND (kt.ten_kich_thuoc = :kichThuoc OR :kichThuoc IS NULL) " +
            "AND (ms.ten_mau_sac = :mauSac OR :mauSac IS NULL) " +
            "AND (cl.ten_chat_lieu = :chatLieu OR :chatLieu IS NULL)")
    Page<SanPhamChiTiet> findByFilter(
            @Param("tenSanPham") String tenSanPham,
            @Param("chatLieu") String chatLieu,
            @Param("mauSac") String mauSac,
            @Param("kichThuoc") String kichThuoc,
            Pageable pageable
    );


}
