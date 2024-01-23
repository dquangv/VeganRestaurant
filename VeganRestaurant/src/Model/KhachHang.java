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
public class KhachHang {
    private String maKhachHang;
    private String tenKhachHang;
    private Date ngayDkThanhVien = new Date();
    private double diemThuong;

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

    public Date getNgayDkThanhVien() {
        return ngayDkThanhVien;
    }

    public void setNgayDkThanhVien(Date ngayDkThanhVien) {
        this.ngayDkThanhVien = ngayDkThanhVien;
    }

    public double getDiemThuong() {
        return diemThuong;
    }

    public void setDiemThuong(double diemThuong) {
        this.diemThuong = diemThuong;
    }
    
}
