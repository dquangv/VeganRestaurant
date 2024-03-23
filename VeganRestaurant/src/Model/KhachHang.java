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
    private Date ngaySinh = new Date();
    private double diemThuong;
    private Date ngayDkThanhVien = new Date();

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
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

    public void setMaKhachHang(int maKhachHang) {
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

}
