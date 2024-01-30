/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Ban;
import Utils.Auth;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.XJdbc;

/**
 *
 * @author Võ Thanh Tùng
 */
public class DatBanDao {

    public static final String Trong = "Đang hoạt động";
    public static final String DANG_PHUC_VU = "Đang phục vụ";
    public static final String DA_DAT = "Đã đặt";
    public static final String BAO_TRI = "Đang bảo trì";

    public static final String Update_TrangThai = "update ban set TrangThai =? where MaBan = ?";
    String SELECT_ALL_SQL = "select * from Ban";

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> selectTang() {
        String SQL = "select distinct SUBSTRING(vitri,1,CHARINDEX(',',vitri)-1) from Ban";
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(SQL);
            while (rs.next()) {
                String tang = rs.getString(1);
                list.add(tang);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTrangThai(String trangTrai, String maBan) {
        try {
            XJdbc.executeUpdate(Update_TrangThai, trangTrai, maBan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected List<Ban> selectBySQL(String sql, Object... args) {
        List<Ban> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                Ban entity = new Ban();
                entity.setMaBan(rs.getString(1));
                entity.setViTri(rs.getString(2));
                entity.setTrangThai(rs.getString(3));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ban> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<Object[]> loadData() {
        String SQL = "SELECT MaBan,TrangThai FROM Ban";
        String[] columns = {"MaBan", "TrangThai"};
        return getListOfArray(SQL, columns);
    }

}
