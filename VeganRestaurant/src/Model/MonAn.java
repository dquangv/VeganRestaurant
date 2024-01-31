/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.DecimalFormat;

/**
 *
 * @author Võ Thanh Tùng
 */
public class MonAn {

    private String maMonAn;
    private String tenMonAn;
    private double donGia;
    private String loaiMonAn;
    private String hinhAnh;
    private String trangThai;
    private String ngayPhucVu;

    public String getNgayPhucVu() {
        return ngayPhucVu;
    }

    public MonAn() {
    }

    public MonAn(String tenMonAn, String loaiMonAn, String hinhAnh, String ngayPhucVu) {
        this.tenMonAn = tenMonAn;
        this.loaiMonAn = loaiMonAn;
        this.hinhAnh = hinhAnh;
        this.ngayPhucVu = ngayPhucVu;
    }

    public void setNgayPhucVu(String ngayPhucVu) {
        this.ngayPhucVu = ngayPhucVu;
    }

    public String getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        this.maMonAn = maMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public double getDonGia() {
        return donGia;
    }

    // Phương thức mới trả về giá trị đã được định dạng
    public String getFormattedDonGia() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(donGia);
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getLoaiMonAn() {
        return loaiMonAn;
    }

    public void setLoaiMonAn(String loaiMonAn) {
        this.loaiMonAn = loaiMonAn;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
