package com.example.demo.Ban_Ao.service;

import com.example.demo.Ban_Ao.entity.ChatLieu;
import com.example.demo.Ban_Ao.repository.ChatLieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatLieuService {
@Autowired
    ChatLieuRepository chatLieuRepository;


public List<ChatLieu> ListChatlieu(){
    return chatLieuRepository.findAll();
}
}
