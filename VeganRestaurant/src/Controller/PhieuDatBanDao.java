/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.PhieuDatBan;
import Utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Date;
        /**
         *
         * @author Võ Thanh Tùng
         */

public class PhieuDatBanDao extends NhaHangChayDAO<PhieuDatBan, String> {

    String Insert = "insert into PhieuDatBan (ThoiGianDat,MaKhachHang) values (?,?)";
    String SELECT_ALL_SQL = "select * from PhieuDatBan";

    @Override
    public void insert(PhieuDatBan entity) {
        XJdbc.executeUpdate(Insert,
                entity.getThoiGianDat(),
                entity.getMaKhachHang()
        );
    }

    @Override
    public void update(PhieuDatBan entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PhieuDatBan selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PhieuDatBan> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    protected List<PhieuDatBan> selectBySQL(String sql, Object... args) {
        List<PhieuDatBan> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                PhieuDatBan entity = new PhieuDatBan();
                entity.setMaPhieuDatBan(rs.getInt("MaPhieuDatBan"));
                entity.setThoiGianDat(rs.getDate("ThoiGianDat"));
                entity.setMaKhachHang(rs.getInt("MaKhachHang"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
