package com.example.demo.Ban_Ao.controller;

import com.example.demo.Ban_Ao.entity.HoaDon;
import com.example.demo.Ban_Ao.entity.HoaDonCT;
import com.example.demo.Ban_Ao.entity.NhanVien;
import com.example.demo.Ban_Ao.entity.SanPhamChiTiet;
import com.example.demo.Ban_Ao.service.SanPhamChiTietService;
import com.example.demo.Ban_Ao.service.hoadonService;
import com.example.demo.Ban_Ao.service.nhanvienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        if (hoadonService.idhoadon == null ) {
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
            hoaDonCTTonTai.setSoluong(hoaDonCTTonTai.getSoluong() + 1);
            Double tongdongia = hoaDonCTTonTai.getSanPhamChiTiet().getDon_gia() * hoaDonCTTonTai.getSoluong();
            hoaDonCTTonTai.setTongtien(tongdongia);

            hoadonService.addHDCT(hoaDonCTTonTai);
        } else {
            HoaDonCT hoaDonCTMoi = new HoaDonCT();
            hoaDonCTMoi.setHoaDon(hoaDonHienTai);
            Double tongdongia = sanPhamChiTiet.getDon_gia() * 1;
            hoaDonCTMoi.setTongtien(tongdongia);
            hoaDonCTMoi.setHinhthucthanhtoan("tại quầy");
            hoaDonCTMoi.setSanPhamChiTiet(sanPhamChiTiet);
            hoaDonCTMoi.setMahoadonct(hoadonService.randomMa_HDCT());
            hoaDonCTMoi.setNhanVien(nhanvienService.timkiemnv(1));
            hoaDonCTMoi.setNgaytao(LocalDate.now());
            hoaDonCTMoi.setSoluong(1);
            hoaDonCTMoi.setTrangthai(1);
            hoadonService.addHDCT(hoaDonCTMoi);
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
    public String xoaSP_gioHang(@RequestParam("id")int id){
         hoadonService.deleteHDCT(id);
        return "redirect:/banhang/HienThi";
    }
}
