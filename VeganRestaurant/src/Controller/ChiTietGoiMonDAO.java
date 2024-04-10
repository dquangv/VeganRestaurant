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
            + "left JOIN KhachHang ON KhachHang.MaKhachHang = PhieuDatBan.MaKhachHang "
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

        return chiTiet;
    }

    public int getMaKHMoibyPDB(int PDB) {
        int maKh = 0;
        String sql = "Select MaKhachHang from PhieuDatBan where MaPhieuDatBan = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, PDB);
            if (rs.next()) {
                maKh = rs.getInt("MaKhachHang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maKh;
    }

    public KhachHang getCustomerByPhoneNumber(String phoneNumber) {
        KhachHang customer = null;
        String sql = "SELECT MaKhachHang, TenKhachHang FROM KhachHang WHERE SDT = ?";
        try {
            PreparedStatement ps = XJdbc.preparedStatement(sql);
            ps.setString(1, phoneNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new KhachHang();
                customer.setMaKhachHang(rs.getInt("MaKhachHang"));
                customer.setTenKhachHang(rs.getString("TenKhachHang"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    public void capNhatKhachHangMoi(int maKhMoi, String tenKhachHangMoi, String soDienThoaiMoi) {
        Connection conn = null;
        PreparedStatement pstmtUpdateKhachHang = null;

        try {
            conn = XJdbc.getConnection();

            String updateKhachHangQuery = "UPDATE KhachHang SET TenKhachHang = ?, SDT = ? WHERE MaKhachHang = ?";
            pstmtUpdateKhachHang = conn.prepareStatement(updateKhachHangQuery);
            pstmtUpdateKhachHang.setString(1, tenKhachHangMoi);
            pstmtUpdateKhachHang.setString(2, soDienThoaiMoi);
            pstmtUpdateKhachHang.setInt(3, maKhMoi);
            pstmtUpdateKhachHang.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update customer information.", e);
        } finally {
            try {
                if (pstmtUpdateKhachHang != null) {
                    pstmtUpdateKhachHang.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Failed to close resources.", ex);
            }
        }
    }

    public void capNhatKhachHangCu(Integer maKhCu, Integer maKhMoi) {

        Connection conn = null;
        PreparedStatement pstmtUpdatePhieuDatBan = null;
        PreparedStatement pstmtDeleteKhachHang = null;

        try {
            conn = XJdbc.getConnection();

            String updatePhieuDatBanQuery = "UPDATE PhieuDatBan SET MaKhachHang = ? WHERE MaKhachHang = ?";
            pstmtUpdatePhieuDatBan = conn.prepareStatement(updatePhieuDatBanQuery);
            pstmtUpdatePhieuDatBan.setInt(1, maKhCu);
            pstmtUpdatePhieuDatBan.setInt(2, maKhMoi);
            pstmtUpdatePhieuDatBan.executeUpdate();

            String deleteKhachHangQuery = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
            pstmtDeleteKhachHang = conn.prepareStatement(deleteKhachHangQuery);
            pstmtDeleteKhachHang.setInt(1, maKhMoi);
            pstmtDeleteKhachHang.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update customer information.", e);
        } finally {
        }
    }

    public void XoaKhachHangMoi(int maKhMoi) {
        Connection conn = null;
        PreparedStatement pstmtUpdatePhieuDatBan = null;
        PreparedStatement pstmtDeleteKhachHang = null;

        try {
            conn = XJdbc.getConnection();

            String updatePhieuDatBanQuery = "UPDATE PhieuDatBan SET MaKhachHang = null WHERE MaKhachHang = ?";
            pstmtUpdatePhieuDatBan = conn.prepareStatement(updatePhieuDatBanQuery);
            pstmtUpdatePhieuDatBan.setInt(1, maKhMoi);
            pstmtUpdatePhieuDatBan.executeUpdate();

            String deleteKhachHangQuery = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
            pstmtDeleteKhachHang = conn.prepareStatement(deleteKhachHangQuery);
            pstmtDeleteKhachHang.setInt(1, maKhMoi);
            pstmtDeleteKhachHang.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update customer information.", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close(); 
                }
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
        }
    }

    public int insertHoaDon(Date ngayLap, double tienMonAn, int maPhieuDatBan, int maNhanVien) {
        int maxMaHoaDon = -1;
        try {
            String queryInsert = "INSERT INTO HoaDon (NgayLap, TienMonAn, MaPhieuDatBan, MaNhanVien) VALUES (?, ?, ?, ?)";
            Connection connection = XJdbc.getConnection();
            PreparedStatement preparedStatementInsert = connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
            preparedStatementInsert.setDate(1, ngayLap);
            preparedStatementInsert.setDouble(2, tienMonAn);
            preparedStatementInsert.setInt(3, maPhieuDatBan);
            preparedStatementInsert.setInt(4, maNhanVien);
            preparedStatementInsert.executeUpdate();

            ResultSet generatedKeys = preparedStatementInsert.getGeneratedKeys();
            if (generatedKeys.next()) {
                maxMaHoaDon = generatedKeys.getInt(1);
            }

            generatedKeys.close();
            preparedStatementInsert.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxMaHoaDon;
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
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

}
