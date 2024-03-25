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

    private int maMonAn;
    private String tenMonAn;
    private double donGia;
    private String loaiMonAn;
    private String hinhAnh;
    private String trangThai;
    private String ngayPhucVu;
    private int maLoaiMon;
    private int soLuong;

//    public String getNgayPhucVu() {
//        return ngayPhucVu;
//    }
    public MonAn() {
    }

    public MonAn(int maMonAn, String tenMonAn, double donGia, String loaiMonAn, String hinhAnh, String trangThai, String ngayPhucVu, int maLoaiMon, int soLuong) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.donGia = donGia;
        this.loaiMonAn = loaiMonAn;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
        this.ngayPhucVu = ngayPhucVu;
        this.maLoaiMon = maLoaiMon;
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

//    public MonAn(int maMonAn, String tenMonAn, double donGia, String hinhAnh, String trangThai, int maLoaiMon) {
//        this.maMonAn = maMonAn;
//        this.tenMonAn = tenMonAn;
//        this.donGia = donGia;
//        this.hinhAnh = hinhAnh;
//        this.trangThai = trangThai;
//        this.maLoaiMon = maLoaiMon;
//    }

    public int getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public String getNgayPhucVu() {
        return ngayPhucVu;
    }

    public int getMaLoaiMon() {
        return maLoaiMon;
    }

    public void setMaLoaiMon(int maLoaiMon) {
        this.maLoaiMon = maLoaiMon;
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
//
//    public String getMaMonAn() {
//        return maMonAn;
//    }
//
//    public void setMaMonAn(String maMonAn) {
//        this.maMonAn = maMonAn;
//    }

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
