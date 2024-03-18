package Controller;

import Utils.XJdbc;
import Model.NhanVIen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    private static final String INSERT_SQL = "INSERT INTO NhanVien(MaNhanVien, TenNhanVien, ChucVu, TrangThai, GioiTinh, SDT, Email, Luong, TenTaiKhoan, Hinh ) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE NhanVien SET TenNhanVien=?, ChucVu=?, TrangThai=?, GioiTinh=?, SDT=?, Email=?, Luong=?, TenTaiKhoan=?, Hinh=? WHERE MaNhanVien=?";
    private static final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNhanVien=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNhanVien=?";
    private static final String UPDATE_SQL_WITHOUT_IMAGE = "UPDATE NhanVien SET TenNhanVien=?, ChucVu=?, TrangThai=?, GioiTinh=?, SDT=?, Email=?, Luong=?, TenTaiKhoan=? WHERE MaNhanVien=?";

    public void insert(NhanVIen nv) {
        XJdbc.executeUpdate(INSERT_SQL,
                nv.getMaNhanVien(),
                nv.getTenNhanVIen(),
                nv.isChucVu(),
                nv.isTrangThai(),
                nv.isGioiTinh(),
                nv.getsDT(),
                nv.getEmail(),
                nv.getLuong(),
                nv.getTenTaikhoan(),
                nv.getHinhAnh());
    }

    public void update(NhanVIen nv) {
        String updateSQL = (nv.getHinhAnh() == null || nv.getHinhAnh().isEmpty()) ? UPDATE_SQL_WITHOUT_IMAGE : UPDATE_SQL;

        XJdbc.executeUpdate(updateSQL,
                nv.getTenNhanVIen(),
                nv.isChucVu(),
                nv.isTrangThai(),
                nv.isGioiTinh(),
                nv.getsDT(),
                nv.getEmail(),
                nv.getLuong(),
                nv.getTenTaikhoan(),
                nv.getHinhAnh(),
                nv.getMaNhanVien());
    }

    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    public NhanVIen selectById(String id) {
        List<NhanVIen> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<NhanVIen> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    private List<NhanVIen> selectBySQL(String sql, Object... args) {
        List<NhanVIen> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                NhanVIen nv = new NhanVIen();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTenNhanVIen(rs.getString("TenNhanVien"));
                nv.setChucVu(rs.getBoolean("ChucVu"));
                nv.setTrangThai(rs.getBoolean("TrangThai"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setsDT(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setLuong(rs.getDouble("Luong"));
                nv.setTenTaikhoan(rs.getString("TenTaiKhoan"));
                nv.setHinhAnh(rs.getString("HinhAnh"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
