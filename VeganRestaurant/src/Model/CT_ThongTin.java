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
public class CT_ThongTin {
    private int maban;
    private String tenKhachHang;
    private int SDT;
    private Date thoiGianDate;

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public Date getThoiGianDate() {
        return thoiGianDate;
    }

    public void setThoiGianDate(Date thoiGianDate) {
        this.thoiGianDate = thoiGianDate;
    }
    
}
