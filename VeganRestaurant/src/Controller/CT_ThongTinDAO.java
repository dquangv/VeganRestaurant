/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.CT_ThongTin;
import Utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author Võ Thanh Tùng
 */
public class CT_ThongTinDAO extends NhaHangChayDAO<CT_ThongTin, String>{

   
    @Override
    public void insert(CT_ThongTin entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(CT_ThongTin entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CT_ThongTin selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CT_ThongTin> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }   
    public List<CT_ThongTin> selectAllKH(int maBan) {
    String sql = "select MaBan,TenKhachHang,SDT,ThoiGianDat from ChiTietDatBan db " +
                 "inner join PhieuDatBan pdb on pdb.MaPhieuDatBan = db.MaPhieuDatBan " +
                 "inner join KhachHang kh on kh.MaKhachHang = pdb.MaKhachHang " +
                 "where MaBan = ? and ThoiGianDat > GETDATE();";
    return this.selectBySQL(sql, maBan);
}
    @Override
    protected List<CT_ThongTin> selectBySQL(String sql, Object... args) {
          List<CT_ThongTin> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                CT_ThongTin entity = new CT_ThongTin();
                entity.setMaban(rs.getInt(1));
                entity.setTenKhachHang(rs.getString(2));
                entity.setSDT(rs.getInt(3));
                entity.setThoiGianDate(rs.getTimestamp(4));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
}
