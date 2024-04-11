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

    String Insert = "insert into PhieuDatBan (ThoiGianDat, MaKhachHang) values (?,?)";
//    String Insert_null = "insert into PhieuDatBan values (?,?)";
    String SELECT_ALL_SQL = "select * from PhieuDatBan";
    String SELECT_MA_PDB = "SELECT ChiTietDatBan.MaPhieuDatBan, Ban.MaBan "
            + "FROM ChiTietDatBan "
            + "JOIN PhieuDatBan ON ChiTietDatBan.MaPhieuDatBan = PhieuDatBan.MaPhieuDatBan "
            + "JOIN Ban ON ChiTietDatBan.MaBan = Ban.MaBan "
            + "WHERE (Ban.TrangThai = N'Đang phục vụ' OR Ban.TrangThai = N'Đã đặt') "
            + "AND (ChiTietDatBan.MaPhieuDatBan NOT IN (SELECT MaPhieuDatBan FROM HoaDon where TrangThai = 1)) "
            + "AND Ban.MaBan = ?";

    String SelectMaPhieuDatBanMax = "select max(MaPhieuDatBan) max from PhieuDatBan";

    public void setMaxPDB(int maPhieuDatBan) {
        String sql = "{call SP_ReSetMaPhieuDatBan(?)}";
        try {
            XJdbc.executeUpdate(sql, maPhieuDatBan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int SelectMaPDB(int maBan) {
        int maPhieuDB = 0;
        try {
            ResultSet rs;
            rs = XJdbc.executeQuery(SELECT_MA_PDB, maBan);
            if (rs.next()) {
                maPhieuDB = rs.getInt("MaPhieuDatBan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maPhieuDB;
    }

    public int SelectMaxPDB() {
        int maxPDB = 0;
        try {
            ResultSet rs = XJdbc.executeQuery(SelectMaPhieuDatBanMax);
            if (rs.next()) {
                maxPDB = rs.getInt("max");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxPDB;
    }

    @Override
    public void insert(PhieuDatBan entity) {
        XJdbc.executeUpdate(Insert,
                entity.getThoiGianDat(),
                entity.getMaKhachHang()
        );
    }

    public void insert_null(PhieuDatBan entity) {
        try {
            XJdbc.executeUpdate(Insert,
                    entity.getThoiGianDat(),
                    entity.getMaKhachHang()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                entity.setThoiGianDat(rs.getTimestamp("ThoiGianDat"));
                entity.setMaKhachHang(rs.getInt("MaKhachHang"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public PhieuDatBan selectByPDB(int maPhieuDatBan) {
        return this.selectBySQL("select * from phieudatban where maphieudatban = ?", maPhieuDatBan).get(0);
    }

    public PhieuDatBan getPhieuDatBanTheoThoiGian(int maBan) {
        String sql = "select top 1 pdb.* from phieudatban pdb\n"
                + "  join chitietdatban ctdb on pdb.maphieudatban = ctdb.maphieudatban \n"
                + "  join ban b on b.maban = ctdb.maban \n"
                + "  where ctdb.maban = ? and b.trangthai = N'Đã đặt' and ctdb.maphieudatban not in (select maphieudatban from hoadon)\n"
                + "  order by thoigiandat";
        PhieuDatBan pdb = new PhieuDatBan();

        try {
            ResultSet rs = XJdbc.executeQuery(sql, maBan);
            if (rs.next()) {
                pdb.setMaPhieuDatBan(rs.getInt("MaPhieuDatBan"));
                pdb.setThoiGianDat(rs.getTimestamp("ThoiGianDat"));
                pdb.setMaKhachHang(rs.getInt("MaKhachHang"));
                return pdb;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return pdb;
    }
}
