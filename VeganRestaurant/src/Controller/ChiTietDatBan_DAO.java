/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ChiTietDatBan;
import Utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Võ Thanh Tùng
 */
public class ChiTietDatBan_DAO extends NhaHangChayDAO<ChiTietDatBan, String> {

    String Insert = "insert into ChiTietDatBan (MaBan, MaPhieuDatBan) values (?,?)";

    public List<Integer> getListMaPhieuDatBan(int maBan) {
        List<Integer> list = new ArrayList<>();
        String sql = "select * from chitietdatban where maban = ? and maphieudatban not in (select maphieudatban from hoadon)";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, maBan);
            while (rs.next()) {
                list.add(rs.getInt("maphieudatban"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public void insert(String MaBan, int maPDB) {
        XJdbc.executeUpdate(Insert,
                MaBan,
                maPDB
        );
    }

    @Override
    public void update(ChiTietDatBan entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietDatBan selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietDatBan> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<ChiTietDatBan> selectBySQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(ChiTietDatBan entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
