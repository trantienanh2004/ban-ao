package com.example.demo.Ban_Ao.service;

import com.example.demo.Ban_Ao.entity.CoAo;
import com.example.demo.Ban_Ao.entity.SanPham;
import com.example.demo.Ban_Ao.repository.CoAoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoAoService {
    @Autowired
    CoAoRepository coAoRepository;

    public Page<CoAo> getAllByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 4);
        return coAoRepository.findAll(pageable);
    }

    public List<CoAo> getAllCA() {
        return coAoRepository.findAll();
    }

    public void add(CoAo co) {
        coAoRepository.save(co);
    }

    public void delete(Integer id) {
        coAoRepository.deleteById(id);
    }

    public void update(CoAo co) {
        coAoRepository.save(co);
    }

    public CoAo share(Integer id) {
        return coAoRepository.findById(id).get();
    }


}
