package com.example.demo.Ban_Ao.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VOUCHER")
@Entity
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "MA_KHUYEN_MAI")
    private String ma;

    @Column(name = "TEN_KHUYEN_MAI")
    private String ten;

    @Column(name = "SO_LUONG")
    private String soLuong;

    @Column(name = "MUC_GIAM")
    private String mucGiam;

    @Column(name = "MO_TA")
    private String mota;

    @Column(name = "LOAI")
    private boolean loai;

    @Column(name = "DIEU_KIEN_KHUYEN_MAI")
    private String dieuKien;


    @Column(name = "MA_AP_DUNG")
    private String maApDung;

    @Column(name = "NGAY_BAT_DAU")
    private Date ngaybatdau;

    @Column(name = "NGAY_KET_THUC")
    private Date ngayketthuc;

    @Column(name = "TRANG_THAI")
    private boolean trangThai;
}
