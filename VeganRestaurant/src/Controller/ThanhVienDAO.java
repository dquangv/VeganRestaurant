/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ThanhVien;
import Utils.XJdbc;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author buimi
 */
public class ThanhVienDAO {

    private final String INSERT_TV = "INSERT INTO ThanhVien(NgayDangKy, DiemThuong, MaKhachHang) VALUES (?, ?, ?)";

    public void addThanhVien(ThanhVien thanhVien) {
        try {
            PreparedStatement pstmt = XJdbc.getConnection().prepareStatement(INSERT_TV);
            pstmt.setDate(1, new java.sql.Date(thanhVien.getNgayDkThanhVien().getTime()));
            pstmt.setDouble(2, thanhVien.getDiemThuong());
            pstmt.setString(3, thanhVien.getMaKhachHang());
            pstmt.executeUpdate();
            pstmt.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
