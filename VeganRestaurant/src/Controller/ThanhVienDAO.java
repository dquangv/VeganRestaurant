/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ThanhVien;
import Utils.XJdbc;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author buimi
 */
public class ThanhVienDAO {

    private final String INSERT_TV = "INSERT INTO ThanhVien(NgayDangKy, DiemThuong, MaKhachHang) VALUES (?, ?, ?)";
    private final String SELECT_BY_ID = "SELECT * FROM ThanhVien WHERE MaKhachHang = ?";
    private final String UPDATE_BY_ID = "UPDATE ThanhVien SET NgayDangKy = ? WHERE MaKhachHang = ?";

    public void addThanhVien(ThanhVien thanhVien) {
        try (
                Connection conn = XJdbc.getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_TV);) {
            pstmt.setDate(1, new java.sql.Date(thanhVien.getNgayDkThanhVien().getTime()));
            pstmt.setDouble(2, thanhVien.getDiemThuong());
            pstmt.setString(3, thanhVien.getMaKhachHang());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ThanhVien getThanhVienByMaKhachHang(String maKhachHang) {
        ThanhVien thanhVien = null;
        try (
                Connection conn = XJdbc.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID);) {
            pstmt.setString(1, maKhachHang);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    thanhVien = new ThanhVien();
                    thanhVien.setMaKhachHang(rs.getInt("MaKhachHang")+"");
                    thanhVien.setNgayDkThanhVien(rs.getDate("NgayDangKy"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thanhVien;
    }

    public void updateThanhVien(ThanhVien thanhVien) {
        try (
                Connection conn = XJdbc.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_BY_ID);) {
            pstmt.setDate(1, new java.sql.Date(thanhVien.getNgayDkThanhVien().getTime()));
            pstmt.setString(2, thanhVien.getMaKhachHang());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
