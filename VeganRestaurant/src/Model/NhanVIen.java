/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Võ Thanh Tùng
 */
public class NhanVIen {
    private String maNhanVien;
    private String tenNhanVIen;
    private boolean chucVu;
    private boolean trangThai;
    private boolean gioiTinh;
    private String sDT;
    private String email;
    private double luong;
    private String tenTaikhoan;
    private String hinhAnh;


    public NhanVIen() {
    }

    public NhanVIen(String maNhanVien, String tenNhanVIen, boolean chucVu, boolean trangThai, boolean gioiTinh, String sDT, String email, double luong, String tenTaikhoan, String hinhAnh) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVIen = tenNhanVIen;
        this.chucVu = chucVu;
        this.trangThai = trangThai;
        this.gioiTinh = gioiTinh;
        this.sDT = sDT;
        this.email = email;
        this.luong = luong;
        this.tenTaikhoan = tenTaikhoan;
        this.hinhAnh = hinhAnh;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVIen() {
        return tenNhanVIen;
    }

    public void setTenNhanVIen(String tenNhanVIen) {
        this.tenNhanVIen = tenNhanVIen;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getTenTaikhoan() {
        return tenTaikhoan;
    }

    public void setTenTaikhoan(String tenTaikhoan) {
        this.tenTaikhoan = tenTaikhoan;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }


    
    
}
