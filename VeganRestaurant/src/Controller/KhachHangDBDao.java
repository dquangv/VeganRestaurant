/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.KhachHang;
import Utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author Võ Thanh Tùng
 */
public class KhachHangDBDao extends NhaHangChayDAO<KhachHang, String> {

    String Insert = "insert into KhachHang (TenKhachHang, SDT) values (?,?) ";
    String SELECT_ALL_SQL = "SELECT * FROM KhachHang";
    String SelectMaKhachHangMax = "select max(makhachhang) max from khachhang";
    
    
    
     public void setMaxKh(int maKhachHang){
        String sql = "{call SP_ReSetMaKhachHang(?)}";
         try {
             XJdbc.executeUpdate(sql,maKhachHang);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    public int SelectMaxkH(){
        int maxKh = 0;
        try {
            ResultSet rs = XJdbc.executeQuery(SelectMaKhachHangMax);
            if (rs.next()) {
                maxKh = rs.getInt("max");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxKh;
    }
    @Override
    public void insert(KhachHang entity) {
        XJdbc.executeUpdate(Insert,
                entity.getTenKhachHang(),
                entity.getSDT()
        );

    }

    @Override
    public void update(KhachHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public KhachHang selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
