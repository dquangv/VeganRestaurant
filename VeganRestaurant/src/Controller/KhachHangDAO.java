/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Model.KhachHang;
import Utils.XJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoang
 */
public class KhachHangDAO extends NhaHangChayDAO<KhachHang, Object> {

    String INSERT_KH = "INSERT INTO KhachHang(TenKhachHang,SDT,NgaySinh) VALUES(?,?,?)";
    String INSERT_KH_null = "INSERT INTO KhachHang(TenKhachHang,SDT,NgaySinh) VALUES(null, null, null)";
    String INSERT_TV = "INSERT INTO ThanhVien(NgayDangKy,DiemThuong,MaKhachHang) VALUES(?,?,?)";
    String UPDATE_SQL = "UPDATE KhachHang SET TenKhachHang=? ,SDT=? ,NgayDkThanhVien=? ,DiemThuong=? WHERE MaKhachHang = ? ";
    String DELETE_SQL = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
    String SELECT_ALL_SQL = "SELECT KhachHang.MaKhachHang, KhachHang.TenKhachHang, KhachHang.SDT, KhachHang.NgaySinh, ThanhVien.NgayDangKy,"
            + "ThanhVien.DiemThuong FROM KhachHang Left Join ThanhVien on KhachHang.MaKhachHang = ThanhVien.MaKhachHang";

    private XJdbc xJdbc;

    public void insertNull(KhachHang entity) {
        XJdbc.executeUpdate(INSERT_KH_null);
    }

    public KhachHangDAO(XJdbc xJdbc) {
        this.xJdbc = xJdbc;
    }

    public KhachHangDAO() {
    }

    public String addKhachHang(KhachHang khachHang) {
        try {
            String INSERT_KH = "INSERT INTO KhachHang(TenKhachHang, SDT, NgaySinh) VALUES(?, ?, ?)";
            PreparedStatement pstmt = XJdbc.getConnection().prepareStatement(INSERT_KH, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, khachHang.getTenKhachHang());
            pstmt.setString(2, khachHang.getSDT());
            if (khachHang.getNgaySinh() != null) {
                pstmt.setDate(3, new java.sql.Date(khachHang.getNgaySinh().getTime()));
            } else {
                pstmt.setNull(3, Types.DATE); // Đặt giá trị null cho trường ngày sinh
            }
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            String maKhachHang = null;
            if (rs.next()) {
                maKhachHang = rs.getString(1);
                return maKhachHang;
            }

            pstmt.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public KhachHang getCustomerByPhoneNumber(String phoneNumber) {
        KhachHang customer = null;
        String sql = "SELECT MaKhachHang, TenKhachHang FROM KhachHang WHERE SDT = ?";
        try {
            PreparedStatement ps = xJdbc.preparedStatement(sql);
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

    public void updateKhachHang(KhachHang khachHang) {
        String sql = "UPDATE KhachHang SET SDT = ?, NgaySinh = ? "
                + "WHERE MaKhachHang = ?";

        try (PreparedStatement pstmt = xJdbc.preparedStatement(sql)) {
            pstmt.setString(1, khachHang.getSDT());
            if (khachHang.getNgaySinh() != null) {
                pstmt.setDate(2, new java.sql.Date(khachHang.getNgaySinh().getTime()));
            } else {
                pstmt.setNull(2, Types.DATE);
            }
            pstmt.setInt(3, khachHang.getMaKhachHang());
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
        String sql = SELECT_ALL_SQL;
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
        khachHang.setMaKhachHang(rs.getInt("MaKhachHang"));
        khachHang.setTenKhachHang(rs.getString("TenKhachHang"));
        khachHang.setSDT(rs.getString("SDT"));
        khachHang.setNgaySinh(rs.getDate("NgaySinh"));
        khachHang.setNgayDkThanhVien(rs.getTimestamp("NgayDangKy"));
        khachHang.setDiemThuong(rs.getInt("DiemThuong"));
        return khachHang;
    }

    @Override
    public KhachHang selectById(Object id) {
        return null;
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    protected List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();

                entity.setMaKhachHang(rs.getInt("MaKhachHang"));
                entity.setTenKhachHang(rs.getString("TenKhachHang"));
                entity.setSDT(rs.getString("SDT"));
                entity.setNgayDkThanhVien(rs.getDate("NgayDkThanhVien"));
                entity.setDiemThuong(rs.getDouble("DiemThuong"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(KhachHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(KhachHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
