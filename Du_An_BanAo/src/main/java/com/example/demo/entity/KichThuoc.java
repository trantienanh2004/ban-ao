package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "KICH_THUOC")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KichThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "MA_KICH_THUOC")
    private String makichthuoc;

    @Column(name = "TEN_KICH_THUOC")
    private String tenkichthuoc;

    @Column(name = "TRANG_THAI")
    private int trangthai;

    @Column(name = "NGAY_TAO")
    private LocalDate ngaytao;

    @Column(name = "NGAY_SUA")
    private LocalDate ngaysua;
}
