/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author buimi
 */
public class KhachHang {
    private String maKhachHang;
    private String tenKhachHang;
    private String SDT;
    private Date ngayDKTHANHVIEN = new Date();
    private double diemThuong;
    private String sdt;
    private Date ngayDkThanhVien;

    public Date getNgayDKTHANHVIEN() {
        return ngayDKTHANHVIEN;
    }

    public void setNgayDKTHANHVIEN(Date ngayDKTHANHVIEN) {
        this.ngayDKTHANHVIEN = ngayDKTHANHVIEN;
    }

    public double getDiemThuong() {
        return diemThuong;
    }

    public void setDiemThuong(double diemThuong) {
        this.diemThuong = diemThuong;
    }


    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;}
    
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgayDkThanhVien() {
        return ngayDkThanhVien;
    }

    public void setNgayDkThanhVien(Date ngayDkThanhVien) {
        this.ngayDkThanhVien = ngayDkThanhVien;
    }

    
}

