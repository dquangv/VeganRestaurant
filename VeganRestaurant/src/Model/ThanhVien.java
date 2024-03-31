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
public class ThanhVien {

    private Date ngayDkThanhVien;
    private double diemThuong;
    private String maKhachHang;


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

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
}
