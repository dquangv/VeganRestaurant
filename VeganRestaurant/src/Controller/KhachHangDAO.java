/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.KhachHang;
import com.utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author hoang
 */
public class KhachHangDAO extends NhaHangChayDAO<KhachHang, Object> {

    String INSERT_SQl = "INSERT INTO KhachHang(MaKhachHang,TenKhachHang,SDT,NgayDkThanhVien,DiemThuong) VALUES(?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhachHang SET TenKhachHang=? ,SDT=? ,NgayDkThanhVien=? ,DiemThuong=? WHERE MaKhachHang = ? ";
    String DELETE_SQL = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KhachHang";

    @Override
    public void insert(KhachHang entity) {
        XJdbc.executeUpdate(INSERT_SQl,
                entity.getMaKhachHang(),
                entity.getTenKhachHang(),
                entity.getSDT(),
                entity.getNgayDkThanhVien(),
                entity.getDiemThuong());
    }

    @Override
    public void update(KhachHang entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getTenKhachHang(),
                entity.getSDT(),
                entity.getNgayDkThanhVien(),
                entity.getDiemThuong(),
                entity.getMaKhachHang());
    }

    @Override
    public void delete(Object id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
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

                entity.setMaKhachHang(rs.getString("MaKhachHang"));
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

}
