package Model;

import java.math.BigDecimal;

public class NguyenVatLieu {
    private String maNguyenVatLieu;
    private String tenNguyenVatLieu;
    private int soLuong;
    private String donViTinh;
    private BigDecimal donGia;

    public NguyenVatLieu() {
    }

    public NguyenVatLieu(String maNguyenVatLieu, String tenNguyenVatLieu, int soLuong, String donViTinh, BigDecimal donGia) {
        this.maNguyenVatLieu = maNguyenVatLieu;
        this.tenNguyenVatLieu = tenNguyenVatLieu;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
    }
    
    public String getMaNguyenVatLieu() {
        return maNguyenVatLieu;
    }

    public void setMaNguyenVatLieu(String maNguyenVatLieu) {
        this.maNguyenVatLieu = maNguyenVatLieu;
    }

    public String getTenNguyenVatLieu() {
        return tenNguyenVatLieu;
    }

    public void setTenNguyenVatLieu(String tenNguyenVatLieu) {
        this.tenNguyenVatLieu = tenNguyenVatLieu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "NguyenVatLieu{" +
                "maNguyenVatLieu='" + maNguyenVatLieu + '\'' +
                ", tenNguyenVatLieu='" + tenNguyenVatLieu + '\'' +
                ", soLuong=" + soLuong +
                ", donViTinh='" + donViTinh + '\'' +
                ", donGia=" + donGia +
                '}';
    }

    public void setMaNgyuenVatLieu(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void initTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void LoadDataToArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void fillTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
