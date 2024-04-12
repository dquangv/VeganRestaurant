/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author Võ Thanh Tùng
 */
public class PhieuDatBan {

    private int maPhieuDatBan;
//    private Date thoiGianDat;
    private Timestamp thoiGianDat;

    private int MaKhachHang;

    public PhieuDatBan() {
    }

//    public PhieuDatBan(int maPhieuDatBan, Date thoiGianDat, int MaKhachHang) {
//        this.maPhieuDatBan = maPhieuDatBan;
//        this.thoiGianDat = thoiGianDat;
//        this.MaKhachHang = MaKhachHang;
//    }
    @Override
    public String toString() {
        return " (" + this.thoiGianDat + ")";
    }

    public int getMaPhieuDatBan() {
        return maPhieuDatBan;
    }

    public void setMaPhieuDatBan(int maPhieuDatBan) {
        this.maPhieuDatBan = maPhieuDatBan;
    }

    public Timestamp getThoiGianDat() {
        return thoiGianDat;
    }

//    public Date getThoiGianDat() {
//        return thoiGianDat;
//    }
//
//    public void setThoiGianDat(Date thoiGianDat) {
//        this.thoiGianDat = thoiGianDat;
//    }
    public void setThoiGianDat(Timestamp thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(int MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

}
