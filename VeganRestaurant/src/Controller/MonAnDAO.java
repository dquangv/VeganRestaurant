/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.MonAn;
import Utils.XJdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Quang
 */
public class MonAnDAO {

    private XJdbc xJdbc;

    public MonAnDAO(XJdbc xJdbc) {
        this.xJdbc = xJdbc;
    }

    public MonAnDAO() {
    }

    //Thêm món ăn
    public void themMonAn(MonAn monAn) {
        String sql = "insert into monan (TenMonAn, DonGia, SoLuong, HinhAnh, TrangThai, MaLoaiMon) values (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement pstmt = xJdbc.preparedStatement(sql)) {
//            pstmt.setString(1, monAn.getMaMonAn());
            pstmt.setString(1, monAn.getTenMonAn());
            pstmt.setDouble(2, monAn.getDonGia());
//            pstmt.setString(4, monAn.getLoaiMonAn());
            pstmt.setInt(3, monAn.getSoLuong());
            pstmt.setString(4, monAn.getHinhAnh());
            pstmt.setString(5, monAn.getTrangThai());
            pstmt.setInt(6, monAn.getMaLoaiMon());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Sửa món ăn (bao gồm trạng thái)
    public void chinhSuaMonAn(MonAn monAn) {
        String sql = "update monan set tenmonan = ?, dongia = ?, soluong = ?, hinhanh = ?, trangthai = ?, maloaimon = ? where mamonan = ?";

        try (PreparedStatement pstmt = xJdbc.preparedStatement(sql)) {
            pstmt.setString(1, monAn.getTenMonAn());
            pstmt.setDouble(2, monAn.getDonGia());
//            pstmt.setString(3, monAn.getLoaiMonAn());
            pstmt.setInt(3, monAn.getSoLuong());
            pstmt.setString(4, monAn.getHinhAnh());
            pstmt.setString(5, monAn.getTrangThai());
//            pstmt.setString(6, monAn.getMaMonAn());
            pstmt.setInt(6, monAn.getMaLoaiMon());
            pstmt.setInt(7, monAn.getMaMonAn());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private MonAn extractMonAnFromResultSet(ResultSet rs) throws SQLException {
        MonAn monAn = new MonAn();
        monAn.setMaMonAn(rs.getInt("mamonan"));
        monAn.setTenMonAn(rs.getString("tenmonan"));
        monAn.setDonGia(rs.getDouble("dongia"));
        monAn.setSoLuong(rs.getInt("soluong"));
//        monAn.setLoaiMonAn(rs.getString("loaimonan"));
        monAn.setHinhAnh(rs.getString("hinhanh"));
        monAn.setTrangThai(rs.getString("trangthai"));
        monAn.setMaLoaiMon(rs.getInt("maloaimon"));

        return monAn;
    }

    //Lấy ds tất cả món ăn
    public List<MonAn> selectAll() {
        String sql = "select * from monan;";
        List<MonAn> monAnList = new ArrayList<>();
        try (ResultSet rs = xJdbc.executeQuery(sql)) {
            while (rs.next()) {
                MonAn monAn = extractMonAnFromResultSet(rs);
                monAnList.add(monAn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monAnList;
    }

    public List<String> selectCBMonAn() {
        String sql = "select distinct ma.maloaimon, tenloaimon from monan ma right join loaimon lm on ma.maloaimon = lm.maloaimon;";
        List<String> monAnList = new ArrayList<>();
        try (ResultSet rs = xJdbc.executeQuery(sql)) {
            while (rs.next()) {
                monAnList.add(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monAnList;
    }

    public String getTenLoaiMon(int maLoaiMon) {
        String sql = "select * from loaimon where maloaimon = ?";
        String tenLoaiMon = null;
        try (ResultSet rs = XJdbc.executeQuery(sql, maLoaiMon)) {
            while (rs.next()) {
                tenLoaiMon = rs.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tenLoaiMon;
    }

    public int getMaLoaiMon(String tenLoaiMon) {
        String sql = "select * from loaimon where tenloaimon = ?";
        int maLoaiMon = 0;

        try (ResultSet rs = XJdbc.executeQuery(sql, tenLoaiMon)) {
            while (rs.next()) {
                maLoaiMon = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maLoaiMon;
    }

    public List<String> selectCBTrangThai() {
        String sql = "select distinct TrangThai from monan;";
        List<String> monAnList = new ArrayList<>();
        try (ResultSet rs = xJdbc.executeQuery(sql)) {
            while (rs.next()) {

                monAnList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monAnList;
    }

    //Lấy ds có đk
    public List<MonAn> selectBySQL(String SQL, Object... args) {
        List<MonAn> monAnList = new ArrayList<>();

        try (PreparedStatement pstmt = xJdbc.preparedStatement(SQL, args); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MonAn monAn = extractMonAnFromResultSet(rs);
                monAnList.add(monAn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monAnList;
    }

    //Lấy món ăn theo mã
    public MonAn getMonAnByID(int maMonAn) {
        String sql = "select * from monan where mamonan = ?";
        MonAn monAn = null;

        try (PreparedStatement pstmt = xJdbc.preparedStatement(sql, maMonAn); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                monAn = extractMonAnFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monAn;
    }

    public List<MonAn> getTenMonAn(String tenMonAn) {
        String SQL = "select  * from MonAn where TenMonAn  like ?";
        String searchKeyword = "%" + tenMonAn + "%";
        return this.selectBySQL(SQL, searchKeyword);
    }

    public boolean checkMaMonAn(String maMonAn) {
        try {
            ResultSet rs = XJdbc.executeQuery("select MaMonAn from MonAn where MaMonAn = ?", maMonAn);
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
