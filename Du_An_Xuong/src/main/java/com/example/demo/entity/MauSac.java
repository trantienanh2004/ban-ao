package com.example.demo.entity;

import jakarta.persistence.*;
<<<<<<< HEAD
=======
import jakarta.validation.constraints.NotBlank;
>>>>>>> thuoctinhsanpham
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Mau_Sac")
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
<<<<<<< HEAD
  @Column(name = "MA_MAU_SAC")
    private String ma_mau_sac;
    @Column(name = "TEN_MAU_SAC")
=======
    @Column(name = "MA_MAU_SAC")
    @NotBlank(message="Chua nhap ma")
    private String ma_mau_sac;

    @Column(name = "TEN_MAU_SAC")
    @NotBlank(message="Chua nhap ten")
>>>>>>> thuoctinhsanpham
    private String ten_mau_sac;
    @Column(name = "NGAY_TAO")
    private LocalDate ngay_tao;
    @Column(name = "NGAY_SUA")
    private LocalDate ngay_sua;
    @Column(name = "TRANG_THAI")
    private boolean trang_thai;
}
