/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author buimi
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.MonAn;
import Utils.XJdbc;

public class ThucDonDAO {

    private XJdbc xJdbc;

    public ThucDonDAO(XJdbc xJdbc) {
        this.xJdbc = xJdbc;
    }

    public ThucDonDAO() {
    }

    public List<MonAn> layDanhSachMonAn() {
        String sql = "SELECT MonAn.MaMonAn, MonAn.TenMonAn, MonAn.DonGia, LoaiMon.TenLoaiMon,MonAn.HinhAnh, MonAn.TrangThai FROM MonAn Join LoaiMon "
                + "On MonAn.MaLoaiMon = LoaiMon.MaLoaiMon WHERE MonAn.TrangThai = N'Hoạt Động' ";
        List<MonAn> danhSachMonAn = new ArrayList<>();
        try (ResultSet resultSet = xJdbc.executeQuery(sql)) {
            while (resultSet.next()) {
                MonAn monAn = extractMonAnFromResultSet(resultSet);
                danhSachMonAn.add(monAn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachMonAn;
    }

    private MonAn extractMonAnFromResultSet(ResultSet rs) throws SQLException {
        MonAn monAn = new MonAn();
        monAn.setMaMonAn(rs.getInt("MaMonAn"));
        monAn.setTenMonAn(rs.getString("TenMonAn"));
        monAn.setDonGia(rs.getDouble("DonGia"));
        monAn.setTenMonAn(rs.getString("TenLoaiMon"));
        monAn.setHinhAnh(rs.getString("HinhAnh"));
        monAn.setTrangThai(rs.getString("TrangThai"));
        return monAn;
    }

    private MonAn extractMonAnFromResultSetThucDon(ResultSet rs) throws SQLException {
        MonAn monAn = new MonAn(null, null, null, null);
        monAn.setTenMonAn(rs.getString("TenMonAn"));
        monAn.setLoaiMonAn(rs.getString("TenLoaiMon"));
        monAn.setHinhAnh(rs.getString("HinhAnh"));
        monAn.setNgayPhucVu(rs.getString("MaThucDon"));
        return monAn;
    }

    public List<MonAn> layDanhSachMonTheoLoai(String loaiMon) {
        String sql = "SELECT MonAn.MaMonAn, MonAn.TenMonAn, MonAn.DonGia, LoaiMon.TenLoaiMon, MonAn.HinhAnh, MonAn.TrangThai FROM MonAn Join LoaiMon "
                + "On MonAn.MaLoaiMon = LoaiMon.MaLoaiMon WHERE LoaiMon.TenLoaiMon = ?";
        List<MonAn> danhSachMonAn = new ArrayList<>();

        try (ResultSet resultSet = xJdbc.executeQuery(sql, loaiMon)) {
            while (resultSet.next()) {
                MonAn  monAn = extractMonAnFromResultSet(resultSet);
                danhSachMonAn.add(monAn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachMonAn;
    }

    public List<MonAn> layDanhSachThucDon() {
        String sql = "select ChiTietTD.MaThucDon, LoaiMon.TenLoaiMon, MonAn.TenMonAn, MonAn.HinhAnh\n"
                + "from ChiTietTD \n"
                + "join MonAn on ChiTietTD.MaMonAn = MonAn.MaMonAn\n"
                + "join LoaiMon on MonAn.MaLoaiMon = LoaiMon.MaLoaiMon";

        List<MonAn> danhSachMonAn = new ArrayList<>();

        try (ResultSet resultSet = xJdbc.executeQuery(sql)) {
            while (resultSet.next()) {
                MonAn monAn = extractMonAnFromResultSetThucDon(resultSet);
                danhSachMonAn.add(monAn);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In thông báo lỗi để kiểm tra
        }
        return danhSachMonAn;
    }

    public List<MonAn> layDanhSachTheoMaThucDon(String maThucDon) {
        String sql = "SELECT "
                + "    MonAn.TenMonAn, "
                + "    MonAn.LoaiMonAn, "
                + "    MonAn.HinhAnh, "
                + "    CASE "
                + "        WHEN COUNT(DISTINCT ChiTietTD.MaThucDon) = 2 THEN 'CaTuan' "
                + "        WHEN MAX(ChiTietTD.MaThucDon) = 'TD246' THEN 'TD246' "
                + "        WHEN MAX(ChiTietTD.MaThucDon) = 'TD357' THEN 'TD357' "
                + "    END AS MaThucDon "
                + "FROM "
                + "    MonAn "
                + "JOIN "
                + "    ChiTietTD ON MonAn.MaMonAn = ChiTietTD.MaMonAn "
                + "WHERE "
                + "    ChiTietTD.MaThucDon = ? OR ? = 'CaTuan' "
                + "GROUP BY "
                + "    MonAn.TenMonAn, MonAn.LoaiMonAn, MonAn.HinhAnh;";

        List<MonAn> danhSachMonAn = new ArrayList<>();

        try (ResultSet resultSet = xJdbc.executeQuery(sql, maThucDon, maThucDon)) {
            while (resultSet.next()) {
                MonAn monAn = extractMonAnFromResultSetThucDon(resultSet);
                danhSachMonAn.add(monAn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachMonAn;
    }

//    public List<MonAn> layDanhSachTheoThucDonChuNhat() {
//        String sql = " SELECT MonAn.TenMonAn, MonAn.LoaiMonAn, MonAn.HinhAnh, 'CaTuan' as MaThucDon\n"
//                + "FROM MonAn \n"
//                + "JOIN ChiTietTD ON MonAn.MaMonAn = ChiTietTD.MaMonAn \n"
//                + "GROUP BY MonAn.TenMonAn, MonAn.LoaiMonAn, MonAn.HinhAnh\n"
//                + "HAVING COUNT(DISTINCT ChiTietTD.MaThucDon) >= 2;";
//
//        List<MonAn> danhSachMonAn = new ArrayList<>();
//
//        try (ResultSet resultSet = xJdbc.executeQuery(sql)) {
//            while (resultSet.next()) {
//                MonAn monAn = extractMonAnFromResultSetThucDon(resultSet);
//                danhSachMonAn.add(monAn);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // In thông báo lỗi để kiểm tra
//        }
//        return danhSachMonAn;
//    }

    public List<MonAn> layDanhSachTheoLoai(String loaiMon) {
        String sql = "SELECT "
                + "    MonAn.TenMonAn, "
                + "    MonAn.LoaiMonAn, "
                + "    MonAn.HinhAnh, "
                + "    CASE "
                + "        WHEN COUNT(DISTINCT ChiTietTD.MaThucDon) = 2 THEN 'CaTuan' "
                + "        WHEN MAX(ChiTietTD.MaThucDon) = 'TD246' THEN 'TD246' "
                + "        WHEN MAX(ChiTietTD.MaThucDon) = 'TD357' THEN 'TD357' "
                + "    END AS MaThucDon "
                + "FROM "
                + "    MonAn "
                + "JOIN "
                + "    ChiTietTD ON MonAn.MaMonAn = ChiTietTD.MaMonAn "
                + "WHERE "
                + "    MonAn.LoaiMonAn = ?"
                + "GROUP BY "
                + "    MonAn.TenMonAn, MonAn.LoaiMonAn, MonAn.HinhAnh;";

        List<MonAn> danhSachMonAn = new ArrayList<>();

        try (ResultSet resultSet = xJdbc.executeQuery(sql, loaiMon)) {
            while (resultSet.next()) {
                MonAn monAn = extractMonAnFromResultSetThucDon(resultSet);
                danhSachMonAn.add(monAn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachMonAn;
    }

    public List<String> layDanhSachTenThucDon() {
        List<String> danhSachTenThucDon = new ArrayList<>();
        String sql = "SELECT DISTINCT MaThucDon FROM ChiTietTD";
        try (ResultSet resultSet = xJdbc.executeQuery(sql)) {
            while (resultSet.next()) {
                String tenThucDon;
                if (resultSet.getString("MaThucDon").equals("2")) {
                    tenThucDon = "Thứ 3,5,7";
                } else {
                    tenThucDon = "Thứ 2,4,6,CN";
                }
                danhSachTenThucDon.add(tenThucDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachTenThucDon;
    }

    public List<String> layDanhSachLoaiMon() {
        List<String> danhSachLoaiMon = new ArrayList<>();
        String sql = "SELECT DISTINCT TenLoaiMon FROM LoaiMon";
        try (ResultSet resultSet = xJdbc.executeQuery(sql)) {
            while (resultSet.next()) {
                String loaiMon = resultSet.getString("TenLoaiMon");
                danhSachLoaiMon.add(loaiMon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachLoaiMon;
    }

    public void themMonAnVaoThucDon(String maThucDon, String tenMonAn) {
        String sql = "INSERT INTO ChiTietTD (MaThucDon, MaMonAn) VALUES (?, (SELECT MaMonAn FROM MonAn WHERE TenMonAn = ?))";
        XJdbc.executeUpdate(sql, maThucDon, tenMonAn);
    }

    public void xoaMonAnKhoiThucDon(String maThucDon, String tenMonAn) {
        String sql = "DELETE FROM ChiTietTD WHERE MaThucDon = ? AND MaMonAn = (SELECT MaMonAn FROM MonAn WHERE TenMonAn = ?)";
        XJdbc.executeUpdate(sql, maThucDon, tenMonAn);
    }

    public boolean kiemTraMonTonTaiTrongThucDon(String maThucDon, String tenMon) {
        String sql = "SELECT COUNT(*) FROM ChiTietTD "
                + "WHERE MaThucDon = ? AND MaMonAn = (SELECT MaMonAn FROM MonAn WHERE TenMonAn = ?)";
        try (ResultSet resultSet = xJdbc.executeQuery(sql, maThucDon, tenMon)) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
