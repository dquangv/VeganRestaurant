/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DanhGia;
import Utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author hoang
 */
public class DanhGia_DAO extends NhaHangChayDAO<DanhGia, Object> {

    String SELECT_ALL = """
                        select MaHoaDon,NgayLap,TenMonAn,HinhAnh,dg.MaDanhGia,DanhGia,TenKhachHang from DanhGia dg
                        right join ChiTietGM ctgm on ctgm.MaDanhGia = dg.MaDanhGia
                        join PhieuDatBan pdb on pdb.MaPhieuDatBan = ctgm.MaPhieuDatBan
                        join HoaDon hd on hd.MaPhieuDatBan=pdb.MaPhieuDatBan
                        join MonAn ma on ma.MaMonAn = ctgm.MaMonAn
                        join KhachHang kh on pdb.MaKhachHang = kh.MaKhachHang""";

    @Override
    public void insert(DanhGia entity) {
    }

    @Override
    public void update(DanhGia entity) {
    }

    @Override
    public void delete(Object id) {
    }

    @Override
    public DanhGia selectById(Object id) {
        return null;
    }

    @Override
    public List<DanhGia> selectAll() {
        return this.selectBySQL(SELECT_ALL);
    }

    @Override
    protected List<DanhGia> selectBySQL(String sql, Object... args) {
        List<DanhGia> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DanhGia entity = new DanhGia();
                
                entity.setMaDanhGia(rs.getInt("MaDanhGia"));
                entity.setMaHoaDon(rs.getInt("MaHoaDon"));
                entity.setTenMonAn(rs.getNString("TenMonAn"));
                entity.setTenKhachHang(rs.getNString("TenKhachHang"));
                entity.setDanhGia(rs.getNString("DanhGia"));
                entity.setHinhAnh(rs.getNString("HinhAnh"));
                entity.setNgayLap(rs.getDate("NgayLap"));
                
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
    
    public List<Object[]> loadDanhGia(int mahd) {
        String SQL = """
                     select TenMonAn,HinhAnh,dg.MaDanhGia,MaHoaDon from DanhGia dg
                     right join ChiTietGM ctgm on ctgm.MaDanhGia=dg.MaDanhGia
                     join MonAn ma on ma.MaMonAn=ctgm.MaMonAn
                     join PhieuDatBan pbd on pbd.MaPhieuDatBan=ctgm.MaPhieuDatBan
                     join HoaDon hd on hd.MaPhieuDatBan=pbd.MaPhieuDatBan
                     where MaHoaDon = ?
                     """;
        String[] columns = {"TenMonAn","HinhAnh","MaDanhGia","MaHoaDon"};
        return getListOfArray(SQL, columns,mahd);
    }
    
}
