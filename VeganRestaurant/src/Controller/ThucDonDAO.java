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
import com.utils.XJdbc;

public class ThucDonDAO {

    private XJdbc xJdbc;

    public ThucDonDAO(XJdbc xJdbc) {
        this.xJdbc = xJdbc;
    }

    public ThucDonDAO() {
    }

    public List<MonAn> layDanhSachMonAn() {
        String sql = "SELECT * FROM MonAn WHERE TrangThai = N'Hoạt Động' ";
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
        monAn.setMaMonAn(rs.getString("MaMonAn"));
        monAn.setTenMonAn(rs.getString("TenMonAn"));
        monAn.setDonGia(rs.getDouble("DonGia"));
        monAn.setLoaiMonAn(rs.getString("LoaiMonAn"));
        monAn.setHinhAnh(rs.getString("HinhAnh"));
        monAn.setTrangThai(rs.getString("TrangThai"));
        return monAn;
    }

    public List<MonAn> layDanhSachMonTheoLoai(String loaiMon) {
        String sql = "SELECT * FROM MonAn WHERE LoaiMonAn = ?";
        List<MonAn> danhSachMonAn = new ArrayList<>();

        try (ResultSet resultSet = xJdbc.executeQuery(sql, loaiMon)) {
            while (resultSet.next()) {
                MonAn monAn = extractMonAnFromResultSet(resultSet);
                danhSachMonAn.add(monAn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachMonAn;
    }

}
