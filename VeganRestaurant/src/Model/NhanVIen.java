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
   private int maNhanVien;
    private String tenNhanVIen;
    private boolean chucVu;
    private String trangThai;
    private boolean gioiTinh;
    private String sDT;
    private String email;
//    private String tenTaikhoan;
    private String hinhAnh;


    public NhanVIen() {
    }

    public NhanVIen(int maNhanVien, String tenNhanVIen, boolean chucVu, String trangThai, boolean gioiTinh, String sDT, String email, String hinhAnh) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVIen = tenNhanVIen;
        this.chucVu = chucVu;
        this.trangThai = trangThai;
        this.gioiTinh = gioiTinh;
        this.sDT = sDT;
        this.email = email;
        this.hinhAnh = hinhAnh;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
}
