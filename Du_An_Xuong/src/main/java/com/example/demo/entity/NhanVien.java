package com.example.demo.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_nhan_vien")
    private String tenNhanVien;

    @Column(name = "ma_nhan_vien")
    private String maNhanVien;

    @Column(name = "ten_tai_khoan")
    private String tenTaiKhoan;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "chuc_vu")
    private Boolean chucVu;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_sua")
    private LocalDate ngaySua;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}
