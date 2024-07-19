package com.example.demo.service;

import com.example.demo.entity.ChatLieu;

import com.example.demo.repository.ChatLieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatLieuService {
@Autowired
ChatLieuRepository chatLieuRepository;

    int size = 3;

    public Page<ChatLieu> chatLieuPage (int pageNo){
        Sort s = Sort.by(Sort.Direction.ASC,"id");
        Pageable page = PageRequest.of(pageNo,size,s);
        return chatLieuRepository.findAll(page);
    }

    public List<ChatLieu> ListChatlieu(){
    return chatLieuRepository.findAll();
}
public void xoaCL(int id){
        chatLieuRepository.deleteById(id);
}
public void addCL(ChatLieu chatLieu){
    chatLieuRepository.save(chatLieu);
}
public ChatLieu chatLieuByID(int id){
       return chatLieuRepository.findById(id).orElse(null);
}
}
