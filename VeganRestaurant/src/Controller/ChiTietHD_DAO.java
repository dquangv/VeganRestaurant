/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ChiTietHD;
import com.utils.XJdbc;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class ChiTietHD_DAO extends NhaHangChayDAO<ChiTietHD, String> {

    String INSERT_SQl = "INSERT INTO ChiTietHD(MaHoaDon,MaMonAn,SoLuong,ThanhTien,DanhGia) VALUES(?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE ChiTietHD SET Soluong= ?, ThanhTien= ?, DanhGia= ? WHERE MaHoaDon = 'HD07' OR MaMonAn = 'MA01' ";
    String DELETE_SQL = "DELETE FROM ChiTietHD WHERE MaHoaDon = ?";
    String SELECT_ALL_SQL = "SELECT * FROM ChiTietHD";
    String SELECT_BY_ID = "SELECT * FROM ChiTietHD WHERE MaHoaDon= ?";

    //thêm
    @Override
    public void insert(ChiTietHD entity) {
        XJdbc.executeUpdate(INSERT_SQl,
                entity.getMaHoaDon(),
                entity.getMaMonAn(),
                entity.getSoLuong(),
                entity.getThanhTien(),
                entity.getDanhGia()
        );
    }

    //sửa
    @Override
    public void update(ChiTietHD entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getSoLuong(),
                entity.getThanhTien(),
                entity.getDanhGia(),
                entity.getMaHoaDon(),
                entity.getMaMonAn()
        );
    }

    //xóa
    @Override
    public void delete(String maHD) {
        XJdbc.executeUpdate(DELETE_SQL, maHD);
    }

    @Override
    public ChiTietHD selectById(String id) {
        List<ChiTietHD> list = this.selectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    //
    @Override
    public List<ChiTietHD> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChiTietHD> selectBySQL(String sql, Object... args) {
        List<ChiTietHD> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                ChiTietHD entity = new ChiTietHD();

                entity.setMaHoaDon(rs.getString("MaHoaDon"));
                entity.setMaMonAn(rs.getString("MaMonAn"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setThanhTien(rs.getDouble("ThanhTien"));
                entity.setDanhGia(rs.getInt("DanhGia"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
    
    public List<Object[]> getChiTiet(String hd) {
        String sql = "{CALL sp_HoaDon(?)}";
        String[] cols = {"TenMonAn", "SoLuong", "ThanhTien"};
        return this.getListOfArray(sql, cols, hd);
    }
//    public List<ChiTietHD> selectByChiTiet(String keyword) {
//        String sql = """
//                     select cthd.MaMonAn,SoLuong,ThanhTien,DanhGia from ChiTietHD cthd
//                     join MonAn ma on cthd.MaMonAn = ma.MaMonAn
//                     join HoaDon hd on hd.MaHoaDon = cthd.MaHoaDon
//                     where hd.MaHoaDon = ? """;
//        return this.selectBySQL(sql, "%" + keyword + "%");
//    }
}
