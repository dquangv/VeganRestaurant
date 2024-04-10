/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Võ Thanh Tùng
 */
public class HoaDon {

    private Integer maHoaDon;
    private Date ngayLap = new Date();

    private double tienMonAn;
    private double tienGiamDiemThuong;
    private double tienGiamKhuyenMai;
    private double tongTien;

    private boolean phuongThuc;
    private boolean trangThai;
    private Integer maPhieuDatBan;
    private Integer maKhuyenMai;
    private Integer maNhanVien;
    private Integer maKhachHang;
    private String tenBan;

    public Integer getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(Integer maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTienMonAn() {
        return tienMonAn;
    }

    public void setTienMonAn(double tienMonAn) {
        this.tienMonAn = tienMonAn;
    }

    public double getTienGiamDiemThuong() {
        return tienGiamDiemThuong;
    }

    public void setTienGiamDiemThuong(double tienGiamDiemThuong) {
        this.tienGiamDiemThuong = tienGiamDiemThuong;
    }

    public double getTienGiamKhuyenMai() {
        return tienGiamKhuyenMai;
    }

    public void setTienGiamKhuyenMai(double tienGiamKhuyenMai) {
        this.tienGiamKhuyenMai = tienGiamKhuyenMai;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    public boolean getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(boolean phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public Integer getMaPhieuDatBan() {
        return maPhieuDatBan;
    }

    public void setMaPhieuDatBan(Integer maPhieuDatBan) {
        this.maPhieuDatBan = maPhieuDatBan;
    }

    public Integer getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(Integer maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public Integer getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Integer getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(Integer maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }
}
