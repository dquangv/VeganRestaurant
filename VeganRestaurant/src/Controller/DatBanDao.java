/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Ban;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.XJdbc;
import java.util.stream.Collectors;

/**
 *
 * @author Võ Thanh Tùng
 */
public class DatBanDao {

    public static final String Trong = "Trống";
    public static final String DANG_PHUC_VU = "Đang phục vụ";
    public static final String DA_DAT = "Đã đặt";
    public static final String BAO_TRI = "Đang bảo trì";

    public static final String Update_TrangThai = "update ban set TrangThai =? where MaBan = ?";
    String SELECT_ALL_SQL = "select * from Ban";
    static String Select_Thongtin = "SELECT db.MaPhieuDatBan, db.MaBan, TenKhachHang, SDT, ThoiGianDat, TrangThai "
            + "FROM ChiTietDatBan db "
            + "INNER JOIN PhieuDatBan pdb ON pdb.MaPhieuDatBan = db.MaPhieuDatBan "
            + "left JOIN KhachHang kh ON kh.MaKhachHang = pdb.MaKhachHang "
            + "INNER JOIN Ban b ON b.MaBan = db.MaBan "
            + "WHERE ((TenKhachHang LIKE ? OR SDT LIKE ?) "
            + "OR (TenKhachHang IS NULL OR SDT IS NULL)) "
            + "AND (TrangThai = N'Đã đặt' OR TrangThai = N'Đang phục vụ') "
            + "AND (db.MaPhieuDatBan Not IN (SELECT MaPhieuDatBan FROM HoaDon Where TrangThai = 1)) "
            + "ORDER BY ThoiGianDat;";
    static String Select_ThongTin_TimKiem = "SELECT db.MaPhieuDatBan, db.MaBan, TenKhachHang, SDT, ThoiGianDat, TrangThai \n"
            + "FROM ChiTietDatBan db \n"
            + "INNER JOIN PhieuDatBan pdb ON pdb.MaPhieuDatBan = db.MaPhieuDatBan \n"
            + "LEFT JOIN KhachHang kh ON kh.MaKhachHang = pdb.MaKhachHang \n"
            + "INNER JOIN Ban b ON b.MaBan = db.MaBan \n"
            + "WHERE ((TenKhachHang LIKE ? OR SDT LIKE ?) \n"
            + "OR (TenKhachHang IS NULL OR SDT IS NULL)) \n"
            + "AND (TrangThai = N'Đã đặt' OR TrangThai = N'Đang phục vụ') \n"
            + "AND (db.MaPhieuDatBan NOT IN (SELECT MaPhieuDatBan FROM HoaDon WHERE TrangThai = 1)) \n"
            + "AND (TenKhachHang IS NOT NULL AND SDT IS NOT NULL)  -- Loại bỏ dòng có dữ liệu null\n"
            + "ORDER BY ThoiGianDat";
    static String ThayDoiBan = " UPDATE ChiTietDatBan SET MaBan = ? WHERE MaBan = ? AND MaPhieuDatBan = ?;";
    static String checkTrung = "select TrangThai from Ban where  MaBan = ?";

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

    public List<String> selectTang() {
        String SQL = "select distinct SUBSTRING(vitri,1,CHARINDEX(',',vitri)-1) from Ban";
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(SQL);
            while (rs.next()) {
                String tang = rs.getString(1);
                list.add(tang);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void huyDatBan(int maPhieuDatBan) {
        String SQLXoa1 = " Delete From ChiTietDatBan where MaPhieuDatBan = ?";
        String SQL = " Delete From PhieuDatBan where MaPhieuDatBan = ?";
        try {
            XJdbc.executeUpdate(SQLXoa1, maPhieuDatBan);
            System.out.println("Xoa CTDB");
            XJdbc.executeUpdate(SQL, maPhieuDatBan);
            System.out.println("Xoa PDB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTrangThai(String trangTrai, String maBan) {
        try {
            XJdbc.executeUpdate(Update_TrangThai, trangTrai, maBan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<Ban> selectBySQL(String sql, Object... args) {
        List<Ban> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                Ban entity = new Ban();
                entity.setMaBan(rs.getInt(1));
                entity.setViTri(rs.getString(2));
                entity.setTrangThai(rs.getString(3));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ban> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<Object[]> loadData() {
        String SQL = "SELECT MaBan,TrangThai FROM Ban";
        String[] columns = {"MaBan", "TrangThai"};
        return getListOfArray(SQL, columns);
    }

    public List<Object[]> LoadThongTin(String keyword) {
        String keyTimKiem = "%" + keyword + "%";
        String cols[] = {"MaPhieuDatBan", "MaBan", "TenKhachHang", "SDT", "ThoiGianDat", "TrangThai"};
        return this.getListOfArray(Select_Thongtin, cols, keyTimKiem, keyTimKiem);
    }
    public List<Object[]> LoadThongTinTimKiem(String keyword) {
        String keyTimKiem = "%" + keyword + "%";
        String cols[] = {"MaPhieuDatBan", "MaBan", "TenKhachHang", "SDT", "ThoiGianDat", "TrangThai"};
        return this.getListOfArray(Select_ThongTin_TimKiem, cols, keyTimKiem, keyTimKiem);
    }

    public void chuyenBan(int maBanMoi, int maBanCu, int maPhieuDatBan) {
        try {
            XJdbc.executeUpdate(ThayDoiBan, maBanMoi, maBanCu, maPhieuDatBan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String checkTonTai(int maBan) {
        String TrangThai = "";
        try {
            ResultSet rs = XJdbc.executeQuery(checkTrung, maBan);
            if (rs.next()) {
                TrangThai = rs.getString("TrangThai");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TrangThai;
    }
}
