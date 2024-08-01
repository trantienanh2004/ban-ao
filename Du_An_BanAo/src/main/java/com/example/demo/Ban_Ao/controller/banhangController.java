package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.entity.HoaDon;
import com.example.demo.Ban_Ao.entity.HoaDonCT;
import com.example.demo.Ban_Ao.entity.NhanVien;
import com.example.demo.Ban_Ao.entity.SanPhamChiTiet;
import com.example.demo.Ban_Ao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/banhang")
public class banhangController {
    @Autowired
    SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    hoadonService hoadonService;
    @Autowired
    nhanvienService nhanvienService;
    @Autowired
    ChatLieuService chatLieuService;
    @Autowired
    MauSacService mauSacService;
    @Autowired
    KichThuocService kichThuocService;
    @GetMapping("/HienThi")
    public String banhang_form(Model model,@RequestParam(name = "x", defaultValue = "0") int x){
        Page<SanPhamChiTiet> sanPhamChiTietPage = sanPhamChiTietService.sanPhamChiTietPage(x);
        model.addAttribute("SanPhamChiTietList", sanPhamChiTietPage.getContent());
        model.addAttribute("currentPage", x);
        model.addAttribute("totalPages", sanPhamChiTietPage.getTotalPages());
        model.addAttribute("hoadon",hoadonService.hoaDons());
        model.addAttribute("idhd",hoadonService.idhoadon);
        model.addAttribute("tongtien",hoadonService.Tongtien);
        model.addAttribute("tenkhachhang",hoadonService.tenkhachhang);
        model.addAttribute("giohang",hoadonService.hoaDonCTS(hoadonService.idhoadon));
        model.addAttribute("chatLieuList",chatLieuService.ListChatlieu());
        model.addAttribute("mauSacList",mauSacService.listMauSac());
        model.addAttribute("kichThuocList",kichThuocService.listKichThuoc());
        return "View/BanHang";
    }
    @GetMapping("tronhd")
    public String dat_ten_lai_sau_haha(@RequestParam("id") int id) {
        if (hoadonService.idhoadon == null || hoadonService.idhoadon != id) {
            hoadonService.Tongtien = 0.0;
            hoadonService.idhoadon = id;
        }
        double tongTienMoi = 0.0;
        for (HoaDonCT hoaDonCT : hoadonService.hoaDonCTS(hoadonService.idhoadon)) {
            tongTienMoi += hoaDonCT.getSanPhamChiTiet().getDon_gia() * hoaDonCT.getSoluong();
        }
        hoadonService.Tongtien = tongTienMoi;

        return "redirect:/banhang/HienThi";
    }



    @GetMapping("chonsp")
    public String chonsp(@RequestParam("id") int id) {
        if (hoadonService.idhoadon == null) {
            return "redirect:/banhang/HienThi";
        }
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.timkiemspct(id);
        HoaDon hoaDonHienTai = hoadonService.timkiemhddo(hoadonService.idhoadon);
        List<HoaDonCT> hoaDonCTList = hoadonService.findByHoaDonId(hoaDonHienTai.getId());

        HoaDonCT hoaDonCTTonTai = hoaDonCTList.stream()
                .filter(hoaDonCT -> hoaDonCT.getSanPhamChiTiet().getId() == sanPhamChiTiet.getId())
                .findFirst()
                .orElse(null);
        if (hoaDonCTTonTai != null) {
            int soLuongConLai = sanPhamChiTiet.getSo_luong_san_pham();
            int soLuongThem = hoaDonCTTonTai.getSoluong() + 1;

            if (soLuongConLai >= soLuongThem) {
                hoaDonCTTonTai.setSoluong(soLuongThem);
                sanPhamChiTiet.setSo_luong_san_pham(soLuongConLai - 1);
                sanPhamChiTietService.addspct(sanPhamChiTiet);
                Double tongDongia = hoaDonCTTonTai.getSanPhamChiTiet().getDon_gia() * hoaDonCTTonTai.getSoluong();
                hoaDonCTTonTai.setTongtien(tongDongia);
                hoadonService.addHDCT(hoaDonCTTonTai);
            } else {
                return "redirect:/banhang/HienThi";
            }
        } else {
            HoaDonCT hoaDonCTMoi = new HoaDonCT();
            hoaDonCTMoi.setHoaDon(hoaDonHienTai);
            Double tongDongia = sanPhamChiTiet.getDon_gia() * 1;
            hoaDonCTMoi.setTongtien(tongDongia);
            hoaDonCTMoi.setHinhthucthanhtoan("tại quầy");
            hoaDonCTMoi.setSanPhamChiTiet(sanPhamChiTiet);
            hoaDonCTMoi.setMahoadonct(hoadonService.randomMa_HDCT());
            hoaDonCTMoi.setNhanVien(nhanvienService.timkiemnv(1));
            hoaDonCTMoi.setNgaytao(LocalDate.now());
            hoaDonCTMoi.setSoluong(1);
            hoaDonCTMoi.setTrangthai(1);
            hoadonService.addHDCT(hoaDonCTMoi);

            sanPhamChiTiet.setSo_luong_san_pham(sanPhamChiTiet.getSo_luong_san_pham() - 1);
            sanPhamChiTietService.addspct(sanPhamChiTiet);
        }

        double tongTienMoi = 0.0;
        for (HoaDonCT hoaDonCT : hoadonService.hoaDonCTS(hoadonService.idhoadon)) {
            tongTienMoi += hoaDonCT.getSanPhamChiTiet().getDon_gia() * hoaDonCT.getSoluong();
        }
        hoadonService.Tongtien = tongTienMoi;

        return "redirect:/banhang/HienThi";
    }



    @GetMapping("taohd")
    public String taohd(Model model){
        long soHoaDonChuaThanhToan = hoadonService.countByTrangThai(1);
        if (soHoaDonChuaThanhToan >= 3) {
            return "redirect:/banhang/HienThi";
        }
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgaytao(LocalDate.now());
        hoaDon.setMahoadon(hoadonService.randomMa_HDCT());
        hoaDon.setNhanVien(nhanvienService.timkiemnv(1));
        hoaDon.setTrangthai(1);
        hoadonService.addHD(hoaDon);
        return "redirect:/banhang/HienThi";
    }
    @GetMapping("xoaSP_gioHang")
    public String xoaSP_gioHang(@RequestParam("id") int id) {
        HoaDonCT hdct = hoadonService.timkiemHDCT(id);
        if (hdct != null) {
            int soLuongSanPham = hdct.getSoluong();
            SanPhamChiTiet spct = hdct.getSanPhamChiTiet();
            if (spct != null) {
                spct.setSo_luong_san_pham(spct.getSo_luong_san_pham() + soLuongSanPham);
                sanPhamChiTietService.addspct(spct);
            }
            hoadonService.deleteHDCT(id);
        }
        return "redirect:/banhang/HienThi";
    }
    @PostMapping ("updategiohang")
    public String updategiohang(@RequestParam("id") int id, @RequestParam("soluongsp") Integer soluong) {
        HoaDonCT hoaDonCT = hoadonService.timkiemHDCT(id);

        if (hoaDonCT != null && soluong != null && soluong >= 1) {
            int soluong_cu = hoaDonCT.getSoluong();
            hoaDonCT.setSoluong(soluong);

            Double donGia = hoaDonCT.getSanPhamChiTiet().getDon_gia();
            hoaDonCT.setTongtien(donGia * soluong);

            hoadonService.addHDCT(hoaDonCT);

            SanPhamChiTiet sanPhamChiTiet = hoaDonCT.getSanPhamChiTiet();
            int soluong_moi = sanPhamChiTiet.getSo_luong_san_pham();

            int soluong_hientai = soluong_moi + soluong_cu - soluong;
            sanPhamChiTiet.setSo_luong_san_pham(soluong_hientai);
            sanPhamChiTietService.addspct(sanPhamChiTiet);

        }
        double tongTienMoi = 0.0;
        for (HoaDonCT hdonCT : hoadonService.hoaDonCTS(hoadonService.idhoadon)) {
            tongTienMoi += hoaDonCT.getSanPhamChiTiet().getDon_gia() * hoaDonCT.getSoluong();
        }
        hoadonService.Tongtien = tongTienMoi;
        return "redirect:/banhang/HienThi";
    }

    @GetMapping("thanhtoan")
public String thanhtoan() {
  HoaDon hoaDon = hoadonService.timkiemhddo(hoadonService.idhoadon);
  List<HoaDonCT> danhSachChiTiet = hoadonService.findByHoaDonId(hoadonService.idhoadon);
    if (danhSachChiTiet.isEmpty()) {
        //làm đéo gì có món hàng nào mà thanh toán
        return "redirect:/banhang/HienThi";
    }
    if(hoaDon != null){
        hoaDon.setTrangthai(0);
       // hoaDon.setKhachHang();
        hoaDon.setTongtien(hoadonService.Tongtien);
        hoaDon.setNgaymua(LocalDate.now());
        hoadonService.addHD(hoaDon);
        hoadonService.idhoadon = 0;
        hoadonService.Tongtien = 0.0;
        hoadonService.tenkhachhang = 0;

    }
    return "redirect:/banhang/HienThi";
}

//  khu vực nghiên cứu
@GetMapping("/locsp")
    public String locSanPham(
            @RequestParam(name = "tenSanPham",required = false) String tenSanPham,
            @RequestParam(name = "chatLieu",required = false) String chatLieu,
            @RequestParam(name = "mauSac",required = false) String mauSac,
            @RequestParam(name = "kichThuoc",required = false) String kichThuoc,
            RedirectAttributes redirectAttributes) {

        // Thêm các tham số tìm kiếm vào redirectAttributes
        redirectAttributes.addAttribute("tenSanPham", tenSanPham);
        redirectAttributes.addAttribute("chatLieu", chatLieu);
        redirectAttributes.addAttribute("mauSac", mauSac);
        redirectAttributes.addAttribute("kichThuoc", kichThuoc);

        // Redirect tới trang hiển thị sản phẩm
        return "redirect:/banhang/HienThiv2";
    }
    @GetMapping("/HienThiv2")
    public String banhang_form(
            @RequestParam(name = "x", defaultValue = "0") int x,
            @RequestParam(required = false) String tenSanPham,
            @RequestParam(required = false) String chatLieu,
            @RequestParam(required = false) String mauSac,
            @RequestParam(required = false) String kichThuoc,
            Model model) {

        // Gọi service với các tham số tìm kiếm để lấy danh sách sản phẩm
        Page<SanPhamChiTiet> sanPhamChiTietPage = sanPhamChiTietService.sanPhamChiTietPageVer2(tenSanPham, chatLieu, mauSac, kichThuoc, x);

        model.addAttribute("SanPhamChiTietList", sanPhamChiTietPage.getContent());
        model.addAttribute("currentPage", x);
        model.addAttribute("totalPages", sanPhamChiTietPage.getTotalPages());
        model.addAttribute("hoadon", hoadonService.hoaDons());
        model.addAttribute("idhd", hoadonService.idhoadon);
        model.addAttribute("tongtien", hoadonService.Tongtien);
        model.addAttribute("tenkhachhang", hoadonService.tenkhachhang);
        model.addAttribute("giohang", hoadonService.hoaDonCTS(hoadonService.idhoadon));
        model.addAttribute("chatLieuList", chatLieuService.ListChatlieu());
        model.addAttribute("mauSacList", mauSacService.listMauSac());
        model.addAttribute("kichThuocList", kichThuocService.listKichThuoc());

        return "View/BanHang";
    }





    /// chưa làm tìm kiếm vì chưa có data khách hàng :>
}
