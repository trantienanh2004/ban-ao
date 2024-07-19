package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Chưa nhập tên nhân viên")
    private String tenNhanVien;

    @Column(name = "ma_nhan_vien")
    @NotBlank(message = "Chưa nhập mã nhân viên")
    private String maNhanVien;

    @Column(name = "ten_tai_khoan")
    @NotBlank(message = "Chưa nhập tài khoản nhân viên")
    private String tenTaiKhoan;

    @Column(name = "mat_khau")
    @NotBlank(message = "Chưa nhập mật khẩu nhân viên")
    private String matKhau;

    @Column(name = "chuc_vu")
    @NotNull(message = "Chưa chọn chức vụ")
    private Boolean chucVu;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_sua")
    private LocalDate ngaySua;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}
