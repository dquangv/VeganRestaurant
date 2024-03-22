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
                        select hd.MaHoaDon,NgayLap,MaMonAn,dg.MaDanhGia,DanhGia from HoaDon hd
                        join PhieuDatBan pdb on hd.MaPhieuDatBan = pdb.MaPhieuDatBan
                        join ChiTietGM ctgm on pdb.MaPhieuDatBan = ctgm.MaPhieuDatBan
                        left join DanhGia dg on ctgm.MaDanhGia = dg.MaDanhGia""";

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
                
                
                
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
