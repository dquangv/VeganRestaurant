
package Model;

import java.util.Date;


public class KhuyenMai {
    
    private String maKhuyenMai;
    private String tenKhuyenMai;
    private Integer phanTram;
    private Integer soLuong;
    private String loaiMa;    
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public KhuyenMai() {
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public Integer getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(Integer phanTram) {
        this.phanTram = phanTram;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getLoaiMa() {
        return loaiMa;
    }

    public void setLoaiMa(String loaiMa) {
        this.loaiMa = loaiMa;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayThucKet(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
