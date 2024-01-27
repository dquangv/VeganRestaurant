/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author buimi
 */
import Model.KhachHang;
import com.utils.XJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    private XJdbc xJdbc;

    public KhachHangDAO(XJdbc xJdbc) {
        this.xJdbc = xJdbc;
    }

    public KhachHangDAO() {
    }

    // Thêm khách hàng vào cơ sở dữ liệu
    public void addKhachHang(KhachHang khachHang) {
        String sql = "INSERT INTO KhachHang (MaKhachHang, TenKhachHang, SDT, NgayDkThanhVien) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = xJdbc.preparedStatement(sql, khachHang.getMaKhachHang(), khachHang.getTenKhachHang(), khachHang.getSdt(), new java.sql.Date(khachHang.getNgayDkThanhVien().getTime()))) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật khách hàng vào cơ sở dữ liệu
    public void updateKhachHang(KhachHang khachHang) {
        String sql = "UPDATE KhachHang SET SDT = ?, NgayDkThanhVien = ? "
                + "WHERE MaKhachHang = ?";

        try (PreparedStatement pstmt = xJdbc.preparedStatement(sql)) {
            pstmt.setString(1, khachHang.getSdt());
            pstmt.setDate(2, new java.sql.Date(khachHang.getNgayDkThanhVien().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy thông tin khách hàng dựa trên mã khách hàng
    public KhachHang getKhachHangById(String maKhachHang) {
        String sql = "SELECT * FROM KhachHang WHERE MaKhachHang = ?";
        KhachHang khachHang = null;

        try (PreparedStatement pstmt = xJdbc.preparedStatement(sql, maKhachHang); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                khachHang = extractKhachHangFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return khachHang;
    }

    // Lấy danh sách tất cả khách hàng
    public List<KhachHang> getAllKhachHang() {
        String sql = "SELECT * FROM KhachHang";
        List<KhachHang> khachHangList = new ArrayList<>();
        try (ResultSet rs = xJdbc.executeQuery(sql)) {
            while (rs.next()) {
                KhachHang khachHang = extractKhachHangFromResultSet(rs);
                khachHangList.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHangList;
    }

    private KhachHang extractKhachHangFromResultSet(ResultSet rs) throws SQLException {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKhachHang(rs.getString("MaKhachHang"));
        khachHang.setTenKhachHang(rs.getString("TenKhachHang"));
        khachHang.setSdt(rs.getString("SDT"));
        khachHang.setNgayDkThanhVien(rs.getDate("NgayDkThanhVien"));
        khachHang.setDiemThuong(rs.getFloat("DiemThuong"));
        return khachHang;
    }

    public  List<Model.KhachHang> searchKhachHangByKeyword(String tenKhachHang, String sdt) {
        String SQL = "SELECT * FROM KhachHang WHERE TenKhachHang LIKE ? AND SDT LIKE ?";
        String likeTenKhachHang = "%" + tenKhachHang + "%";
        String likeSDT = "%" + sdt + "%";
        return this.selectBySQL(SQL, likeTenKhachHang, likeSDT);
    }

    public List<KhachHang> selectBySQL(String SQL, Object... args) {
        List<KhachHang> result = new ArrayList<>();

        try (PreparedStatement pstmt = xJdbc.preparedStatement(SQL, args); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                KhachHang khachHang = extractKhachHangFromResultSet(rs);
                result.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
