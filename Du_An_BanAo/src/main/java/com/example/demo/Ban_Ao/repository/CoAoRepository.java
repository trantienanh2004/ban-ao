package com.example.demo.Ban_Ao.repository;

import com.example.demo.Ban_Ao.entity.CoAo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoAoRepository extends JpaRepository<CoAo,Integer> {
    List<CoAo> findByTen(String keyword);
}
