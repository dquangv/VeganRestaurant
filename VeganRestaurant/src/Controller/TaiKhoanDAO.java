/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.NhanVIen;
import Model.TaiKhoan;
import Utils.XJdbc;
import Utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Quang
 */
public class TaiKhoanDAO extends VeganDAO<TaiKhoan, String> {
  

    String INSERT_SQL = "INSERT INTO TaiKhoan(TenTaiKhoan, MatKhau, VaiTro, TrangThai) VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE TaiKhoan SET MatKhau=?, VaiTro=?, TrangThai=? WHERE TenTaiKhoan=?";
    String DELETE_SQL = "DELETE FROM TaiKhoan WHERE TenTaiKhoan=?";
    String SELECT_ALL_SQL = "SELECT * FROM TaiKhoan";
    String SELECT_BY_ID_SQL = "SELECT * FROM TaiKhoan WHERE TenTaiKhoan=?";

    @Override
    public void insert(TaiKhoan entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getTenTaiKhoan(),
                entity.getMatKhau(),
                entity.isVaiTro(),
                entity.getTrangThai());
    }

    @Override
    public void update(TaiKhoan entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMatKhau(),
                entity.isVaiTro(),                
                entity.getTrangThai(),
                entity.getTenTaiKhoan());
    }

    @Override
    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public TaiKhoan selectById(String id) {
        List<TaiKhoan> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TaiKhoan> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    protected List<TaiKhoan> selectBySQL(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                TaiKhoan entity = new TaiKhoan();
                entity.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setTrangThai(rs.getString("TrangThai"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(NhanVIen user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
