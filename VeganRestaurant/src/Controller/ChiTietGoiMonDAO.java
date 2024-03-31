/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ChiTietGoiMon;
import Model.KhachHang;
import Utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author buimi
 */
public class ChiTietGoiMonDAO {

    String SELECT_SQL = """
                        Select  KhachHang.TenKhachHang, PhieuDatBan.ThoiGianDat, MonAn.TenMonAn, ChiTietGM.SoLuong, MonAn.DonGia, (ChiTietGM.SoLuong*MonAn.DonGia) as ThanhTien, ChiTietGM.GhiChu
                        from MonAn
                        right join ChiTietGM on MonAn.MaMonAn = ChiTietGM.MaMonAn
                        join PhieuDatBan on ChiTietGM.MaPhieuDatBan = PhieuDatBan.MaPhieuDatBan 
                        join KhachHang on KhachHang.MaKhachHang = PhieuDatBan.MaKhachHang
                        where ChiTietGM.MaPhieuDatBan = ?""";

    public List<ChiTietGoiMon> selectByMaPhieuDatBan(int maPhieuDatBan) {
        List<ChiTietGoiMon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(SELECT_SQL, maPhieuDatBan);
            while (rs.next()) {
                ChiTietGoiMon chiTiet = new ChiTietGoiMon();
                chiTiet.setTenKH(rs.getString("TenKhachHang"));
                chiTiet.setThoiGianDat(rs.getTimestamp("ThoiGianDat"));
                chiTiet.setTenMonAn(rs.getString("TenMonAn"));
                chiTiet.setSoLuong(rs.getInt("SoLuong"));
                chiTiet.setDonGia(rs.getDouble("DonGia"));
                chiTiet.setThanhTien(rs.getDouble("ThanhTien"));
                chiTiet.setGhiChu(rs.getString("GhiChu"));
                list.add(chiTiet);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
