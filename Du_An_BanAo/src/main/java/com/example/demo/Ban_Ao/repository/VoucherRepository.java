package com.example.demo.Ban_Ao.repository;

import com.example.demo.Ban_Ao.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Integer> {
    List<Voucher> findByTen(String keyword);
}
