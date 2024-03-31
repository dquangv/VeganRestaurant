/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ChiTietGoiMon;
import Model.KhachHang;
import Model.PhieuDatBan;
import Utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author buimi
 */
public class ChiTietGoiMonDAO {

    String SELECT_SQL = "SELECT KhachHang.TenKhachHang, PhieuDatBan.ThoiGianDat, MonAn.TenMonAn, ChiTietGM.SoLuong, MonAn.DonGia, (ChiTietGM.SoLuong * MonAn.DonGia) as ThanhTien, ChiTietGM.GhiChu\n"
            + "FROM MonAn "
            + "RIGHT JOIN ChiTietGM ON MonAn.MaMonAn = ChiTietGM.MaMonAn "
            + "JOIN PhieuDatBan ON ChiTietGM.MaPhieuDatBan = PhieuDatBan.MaPhieuDatBan "
            + "JOIN KhachHang ON KhachHang.MaKhachHang = PhieuDatBan.MaKhachHang "
            + "WHERE ChiTietGM.MaPhieuDatBan = ?";

    String SELECT_KH_TG_SQL = "SELECT KhachHang.TenKhachHang, PhieuDatBan.ThoiGianDat "
            + "FROM PhieuDatBan "
            + "JOIN ChiTietDatBan ON ChiTietDatBan.MaPhieuDatBan = PhieuDatBan.MaPhieuDatBan "
            + "LEFT JOIN KhachHang ON KhachHang.MaKhachHang = PhieuDatBan.MaKhachHang "
            + "WHERE PhieuDatBan.MaPhieuDatBan = ?";
    String query = "INSERT INTO ChiTietGM (MaPhieuDatBan, MaMonAn, SoLuong, GhiChu, MaDanhGia) VALUES (?, ?, ?, ?, ?)";

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

    public ChiTietGoiMon selectKHTGByMaPhieuDatBan(int maPhieuDatBan) {
        ChiTietGoiMon chiTiet = null; // Khởi tạo đối tượng ChiTietGoiMon

        try {
            ResultSet rs = XJdbc.executeQuery(SELECT_KH_TG_SQL, maPhieuDatBan);
            if (rs.next()) {
                chiTiet = new ChiTietGoiMon();
                chiTiet.setTenKH(rs.getString("TenKhachHang"));
                chiTiet.setThoiGianDat(rs.getTimestamp("ThoiGianDat"));
            }

            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return chiTiet; // Trả về đối tượng ChiTietGoiMon
    }

    public void inserts(int maPDB, ChiTietGoiMon chiTiet) {
        try {
            String queryGetMaMonAn = "SELECT MaMonAn FROM MonAn WHERE TenMonAn = ?";
            PreparedStatement preparedStatementGetMaMonAn = XJdbc.preparedStatement(queryGetMaMonAn, chiTiet.getTenMonAn());
            ResultSet resultSet = preparedStatementGetMaMonAn.executeQuery();

            int maMonAn = 0;
            if (resultSet.next()) {
                maMonAn = resultSet.getInt("MaMonAn");
            } else {
                System.out.println("Không tìm thấy mã món ăn cho món ăn: " + chiTiet.getTenMonAn());
                return;
            }

            String queryInsert = "INSERT INTO ChiTietGM (MaPhieuDatBan, MaMonAn, SoLuong, GhiChu, MaDanhGia) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatementInsert = XJdbc.preparedStatement(queryInsert, maPDB, maMonAn, chiTiet.getSoLuong(), chiTiet.getGhiChu(), null);
            preparedStatementInsert.executeUpdate();
            resultSet.close();
            preparedStatementGetMaMonAn.close();
            preparedStatementInsert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByMaPDB(int maPDB) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = XJdbc.getConnection();
            String deleteQuery = "DELETE FROM ChiTietGM WHERE MaPhieuDatBan = ?";
            pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, maPDB);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete data.", e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    // Log or handle exception
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    // Log or handle exception
                }
            }
        }
    }

}
