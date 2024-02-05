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
        String sql = "insert into monan values (?, ?, ?, ?, ?, ?);";

        try ( PreparedStatement pstmt = xJdbc.preparedStatement(sql)) {
            pstmt.setString(1, monAn.getMaMonAn());
            pstmt.setString(2, monAn.getTenMonAn());
            pstmt.setDouble(3, monAn.getDonGia());
            pstmt.setString(4, monAn.getLoaiMonAn());
            pstmt.setString(5, monAn.getHinhAnh());
            pstmt.setString(6, monAn.getTrangThai());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Sửa món ăn (bao gồm trạng thái)
    public void chinhSuaMonAn(MonAn monAn) {
        String sql = "update monan set tenmonan = ?, dongia = ?, loaimonan = ?, hinhanh = ?, trangthai = ? where mamonan = ?";

        try ( PreparedStatement pstmt = xJdbc.preparedStatement(sql)) {
            pstmt.setString(1, monAn.getTenMonAn());
            pstmt.setDouble(2, monAn.getDonGia());
            pstmt.setString(3, monAn.getLoaiMonAn());
            pstmt.setString(4, monAn.getHinhAnh());
            pstmt.setString(5, monAn.getTrangThai());
            pstmt.setString(6, monAn.getMaMonAn());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private MonAn extractMonAnFromResultSet(ResultSet rs) throws SQLException {
        MonAn monAn = new MonAn();
        monAn.setMaMonAn(rs.getString("mamonan"));
        monAn.setTenMonAn(rs.getString("tenmonan"));
        monAn.setDonGia(rs.getDouble("dongia"));
        monAn.setLoaiMonAn(rs.getString("loaimonan"));
        monAn.setHinhAnh(rs.getString("hinhanh"));
        monAn.setTrangThai(rs.getString("trangthai"));

        return monAn;
    }

    //Lấy ds tất cả món ăn
    public List<MonAn> selectAll() {
        String sql = "select * from monan;";
        List<MonAn> monAnList = new ArrayList<>();
        try ( ResultSet rs = xJdbc.executeQuery(sql)) {
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
        String sql = "select distinct  loaiMonAn from monan;";
        List<String> monAnList = new ArrayList<>();
        try ( ResultSet rs = xJdbc.executeQuery(sql)) {
            while (rs.next()) {

                monAnList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monAnList;
    }

    public List<String> selectCBTrangThai() {
        String sql = "select distinct TrangThai from monan;";
        List<String> monAnList = new ArrayList<>();
        try ( ResultSet rs = xJdbc.executeQuery(sql)) {
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

        try ( PreparedStatement pstmt = xJdbc.preparedStatement(SQL, args);  ResultSet rs = pstmt.executeQuery()) {

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
    public MonAn getMonAnByID(String maMonAn) {
        String sql = "select * from monan where mamonan = ?";
        MonAn monAn = null;

        try ( PreparedStatement pstmt = xJdbc.preparedStatement(sql, maMonAn);  ResultSet rs = pstmt.executeQuery()) {

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
