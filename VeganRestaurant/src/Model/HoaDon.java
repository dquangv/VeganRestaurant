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
    private String maHoaDon;
    private Date ngayLap = new Date();
    private double tienMonAn;

    private double tienGiamDiemThuong;
    private double tienGiamKhuyenMai;
    private double tongTien;
    private String maBan;
    private String maKhuyenMai;
    private String maNhanVien;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
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

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
   
}
