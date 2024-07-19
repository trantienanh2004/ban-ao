package com.example.demo.Ban_Ao.repository;

import com.example.demo.Ban_Ao.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu,Integer> {
}
