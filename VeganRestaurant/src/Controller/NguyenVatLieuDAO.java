package Controller;

import Utils.XJdbc;
import Model.NguyenVatLieu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NguyenVatLieuDAO {

    private static final String INSERT_SQL = "INSERT INTO NguyenVatLieu(MaNguyenVatLieu, TenNguyenVatLieu, SoLuong, DonViTinh, DonGia) VALUES(?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE NguyenVatLieu SET TenNguyenVatLieu=?, SoLuong=?, DonViTinh=?, DonGia=? WHERE MaNguyenVatLieu=?";
    private static final String DELETE_SQL = "DELETE FROM NguyenVatLieu WHERE MaNguyenVatLieu=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM NguyenVatLieu";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM NguyenVatLieu WHERE MaNguyenVatLieu=?";

  public void insert(NguyenVatLieu nvl) {
      XJdbc.executeUpdate(INSERT_SQL,
              nvl.getMaNguyenVatLieu(),
              nvl.getTenNguyenVatLieu(),
              nvl.getSoLuong(),
              nvl.getDonViTinh(),
              nvl.getDonGia());
    }

    public void update(NguyenVatLieu nvl) {
        XJdbc.executeUpdate(UPDATE_SQL,
                nvl.getTenNguyenVatLieu(),
                nvl.getSoLuong(),
                nvl.getDonViTinh(),
                nvl.getDonGia(),
                nvl.getMaNguyenVatLieu());
    }

    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    public NguyenVatLieu selectById(String id) {
        List<NguyenVatLieu> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<NguyenVatLieu> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    protected List<NguyenVatLieu> selectBySQL(String sql, Object... args) {
        List<NguyenVatLieu> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                NguyenVatLieu nvl = new NguyenVatLieu();
                nvl.setMaNgyuenVatLieu(rs.getString("MaNguyenVatLieu"));
                nvl.setTenNguyenVatLieu(rs.getString("TenNguyenVatLieu"));
                nvl.setSoLuong(rs.getInt("SoLuong"));
                nvl.setDonViTinh(rs.getString("DonViTinh"));
                nvl.setDonGia(rs.getBigDecimal("DonGia")); // Lấy giá trị kiểu BigDecimal
                list.add(nvl);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<NguyenVatLieu> getAllNguyenVatLieu() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

 
    public int getSoLuongByMaNVL(String maNVL) throws SQLException {
        String sql = "SELECT soLuong FROM NguyenVatLieu WHERE MaNguyenVatLieu = ?";
        try (java.sql.Connection connection = XJdbc.getConnection(); // Sử dụng XJdbc để mở kết nối
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,maNVL );
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("soLuong");
                } else {
                    throw new SQLException("Không tìm thấy nguyên vật liệu với mã " + maNVL);
                }
            }
        }
    }

}
