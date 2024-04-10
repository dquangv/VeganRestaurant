
package Controller;

import Model.HoaDon;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Model.KhuyenMai;
import Utils.XJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static junit.runner.Version.id;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public abstract class KhuyenMaiDAO extends NhaHangChayDAO<KhuyenMai, String> {

    String INSERT_SQl = "INSERT INTO KhuyenMai (MaKhuyenMai,TenKhuyenMai,PhanTram,SoLuong,Loai,NgayBatDau,NgayKetThuc) VALUES(?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhuyenMai SET TenKhuyenMai=? ,PhanTram=? ,SoLuong=? ,LoaiMa=? ,NgayBatDau=? ,NgayKetThuc=?  WHERE MaKhuyenMai = ? ";
    String DELETE_SQL = "DELETE FROM KhuyenMai WHERE MaKhuyenMai = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KhuyenMai where NgayKetThuc >= GETDATE() ";
    String SELECT_ALL_SQL_MAKM = """
                                 select * from KhuyenMai
                                 where MaKhuyenMai = ?""";

    private XJdbc xJdbc;

    @Override
    public void insert(KhuyenMai entity) {
        XJdbc.executeUpdate(INSERT_SQl,
                entity.getMaKhuyenMai(),
                entity.getTenKhuyenMai(),
                entity.getPhanTram(),
                entity.getSoLuong(),
                entity.getLoaiMa(),
                entity.getNgayBatDau(),
                entity.getNgayKetThuc());
    }

    //update
    @Override
    public void update(KhuyenMai entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMaKhuyenMai(),
                entity.getTenKhuyenMai(),
                entity.getPhanTram(),
                entity.getSoLuong(),
                entity.getLoaiMa(),
                entity.getNgayBatDau(),
                entity.getNgayKetThuc());
    }

    //xóa
    @Override
    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public KhuyenMai selectById(String id) {
        List<KhuyenMai> list = this.selectBySQL(SELECT_ALL_SQL_MAKM, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhuyenMai> selectAll() {
        return this.select(SELECT_ALL_SQL);
    }

    //Thêm món ăn
    public void themKhuyenMai(KhuyenMai km) {
        String sql = "insert into KhuyenMai values (?, ?, ?, ?, ?, ?, ?);";

        try ( PreparedStatement pstmt = xJdbc.preparedStatement(sql)) {
            pstmt.setString(1, km.getMaKhuyenMai());
            pstmt.setString(2, km.getTenKhuyenMai());
            pstmt.setInt(3, km.getPhanTram());
            pstmt.setInt(4, km.getSoLuong());
            pstmt.setString(5,  km.getLoaiMa());
            pstmt.setDate(6, (Date) km.getNgayBatDau());
            pstmt.setDate(7, (Date) km.getNgayKetThuc());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Câu lệnh tự xóa voucher hết hạn
    public void deleteVoucherExpiry() throws SQLException {
        String deleteVoucherExpiry = "select * from KhuyenMai where NgayKetThuc>= GETDATE();";

        try ( PreparedStatement pstmt = xJdbc.preparedStatement(deleteVoucherExpiry)) {
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected List<KhuyenMai> selectBySQL(String sql, Object... args) {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhuyenMai entity = new KhuyenMai();

                entity.setMaKhuyenMai(rs.getString("MaKhuyenMai"));
                entity.setTenKhuyenMai(rs.getString("TenKhuyenMai"));
                entity.setPhanTram(rs.getInt("PhanTram"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setLoaiMa(rs.getString("Loai"));
                entity.setNgayBatDau(rs.getDate("NgayBatDau"));
                entity.setNgayThucKet(rs.getDate("NgayKetThuc"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private List<KhuyenMai> select(String sql, Object... args) {
        List<KhuyenMai> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.executeQuery(sql, args);
                while (rs.next()) {
                    KhuyenMai model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
        } catch (SQLException ex) {
            // throw new RuntimeException(ex);
        }

        return list;
    }

    private KhuyenMai readFromResultSet(ResultSet rs) throws SQLException {
        KhuyenMai model = new KhuyenMai();
        model.setMaKhuyenMai(rs.getString("MaKhuyenMai"));
        model.setTenKhuyenMai(rs.getString("TenKhuyenMai"));
        model.setPhanTram(rs.getInt("PhamTram"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setLoaiMa(rs.getString("Loai"));
        model.setNgayBatDau(rs.getDate("NgayBatDau"));
        model.setNgayThucKet(rs.getDate("NgayKetThuc"));
        return model;
    }

    public List<KhuyenMai> selectByMaKM(String maKhachHang) {
        String sql = "select * from KhuyenMai where MaKhuyenMai = %" + maKhachHang + "%";

        List<KhuyenMai> list = new ArrayList<>();
        try ( Connection conn = XJdbc.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maKhachHang);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhuyenMai entity = new KhuyenMai();

                entity.setMaKhuyenMai(rs.getString("MaKhuyenMai"));
                entity.setTenKhuyenMai(rs.getString("TenKhuyenMai"));
                entity.setPhanTram(rs.getInt("PhanTram"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setLoaiMa(rs.getString("Loai"));
                entity.setNgayBatDau(rs.getDate("NgayBatDau"));
                entity.setNgayThucKet(rs.getDate("NgayKetThuc"));

                list.add(entity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<KhuyenMai> selectByKeyword(String keyword) {
        String SQL = "SELECT * FROM KhuyenMai "
                + "WHERE (MaKhuyenMai LIKE ? OR TenKhuyenMai LIKE ? OR PhanTram LIKE ?) and NgayKetThuc>= GETDATE();";
        String searchKeyword = "%" + keyword + "%";
        return this.selectBySQL(SQL, searchKeyword, searchKeyword, searchKeyword);
    }

    // Câu lệnh set số lượng voucher
    public void soLuongVoucher(String maKM){
        String soLuongVoucher = "UPDATE KhuyenMai " + "SET SoLuong = SoLuong - 1 WHERE MaKhuyenMai =?;";

        try ( PreparedStatement pstmt = xJdbc.preparedStatement(soLuongVoucher,maKM)) {
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
//        public void conLai(String maKM) {
//        String soLuongVoucher = "UPDATE KhuyenMai SET SoLuong = SoLuong - 1 WHERE MaKhuyenMai = ?";
//        String conLai = "SELECT SoLuong FROM KhuyenMai WHERE MaKhuyenMai = ?";
//
//    try ( PreparedStatement pstmt = xJdbc.preparedStatement(soLuongVoucher,maKM)) {
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    
//    }
}

