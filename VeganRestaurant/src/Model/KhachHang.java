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
  private int maKhachHang;
  private String tenKhachHang;
  private String SDT;
  private Date ngaySinh;

    public KhachHang() {
    }

    public KhachHang(int maKhachHang, String tenKhachHang, String SDT, Date ngaySinh) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.SDT = SDT;
        this.ngaySinh = ngaySinh;
    }

    public int getMaKhachHang() {
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

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
  
}

