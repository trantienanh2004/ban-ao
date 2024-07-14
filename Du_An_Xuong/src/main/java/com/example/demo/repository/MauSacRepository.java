package com.example.demo.repository;
import com.example.demo.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface MauSacRepository extends JpaRepository<MauSac,Integer> {
    @Query(value = "select * from Mau_Sac where Ma_mau_sac like ?1",nativeQuery = true)
    public MauSac findMauSacByMa_mau_sac(String code);
}
