package com.example.demo.service;

import com.example.demo.entity.ChatLieu;
import com.example.demo.repository.ChatLieuRepository;
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
