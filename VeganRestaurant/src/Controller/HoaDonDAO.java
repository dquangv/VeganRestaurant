/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HoaDon;
import Utils.XJdbc;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class HoaDonDAO extends NhaHangChayDAO<HoaDon, Integer> {

    String INSERT_SQl = "INSERT INTO HoaDon(NgayLap,TienMonAn,TienGiamDiemThuong,TienGiamKhuyenMai,TongTien,PhuongThucThanhToan,TrangThai,MaPhieuDatBan,MaKhuyenMai,MaNhanVien) VALUES (?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HoaDon SET NgayLap=? ,TienMonAn=? ,TienGiamDiemThuong=? ,TienGiamKhuyenMai=? ,TongTien=? ,PhuongThucThanhToan=? ,TrangThai = ? ,MaPhieuDatBan=? ,MaKhuyenMai=? WHERE MaHoaDon =?";
    String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
    String SELECT_ALL_SQL = """
                            select MaHoaDon,NgayLap,TienMonAn,TienGiamDiemThuong,TienGiamKhuyenMai,TongTien,PhuongThucThanhToan,pdb.MaPhieuDatBan,MaKhuyenMai,MaNhanVien,kh.MaKhachHang from HoaDon hd
                            join PhieuDatBan pdb on hd.MaPhieuDatBan = pdb.MaPhieuDatBan
                            left join KhachHang kh on pdb.MaKhachHang= kh.MaKhachHang""";

    String SELECT_BY_IDKhach_SQL = """
                                   SELECT 
                                       hd.MaHoaDon,
                                       hd.NgayLap,
                                       hd.TienMonAn,
                                       hd.TienGiamDiemThuong,
                                       hd.TienGiamKhuyenMai,
                                       hd.TongTien,
                                       hd.PhuongThucThanhToan,
                                       hd.TrangThai,
                                       pdb.MaPhieuDatBan,
                                       hd.MaKhuyenMai,
                                       hd.MaNhanVien,
                                       kh.MaKhachHang,
                                       STRING_AGG(ctdb.MaBan, ', ') AS MaBan
                                   FROM 
                                       HoaDon hd
                                   JOIN 
                                       PhieuDatBan pdb ON hd.MaPhieuDatBan = pdb.MaPhieuDatBan
                                   LEFT JOIN 
                                       KhachHang kh ON pdb.MaKhachHang = kh.MaKhachHang
                                   JOIN 
                                       ChiTietDatBan ctdb ON ctdb.MaPhieuDatBan = pdb.MaPhieuDatBan
                                   WHERE 
                                       MaHoaDon = ?
                                   GROUP BY 
                                       hd.MaHoaDon,
                                       hd.NgayLap,
                                       hd.TienMonAn,
                                       hd.TienGiamDiemThuong,
                                       hd.TienGiamKhuyenMai,
                                       hd.TongTien,
                                       hd.PhuongThucThanhToan,
                                       hd.TrangThai,
                                       pdb.MaPhieuDatBan,
                                       hd.MaKhuyenMai,
                                       hd.MaNhanVien,
                                       kh.MaKhachHang; """;

    //thêm
    @Override
    public void insert(HoaDon entity) {
        XJdbc.executeUpdate(INSERT_SQl,
                entity.getMaHoaDon(),
                entity.getNgayLap(),
                entity.getTienMonAn(),
                entity.getTienGiamDiemThuong(),
                entity.getTienGiamKhuyenMai(),
                entity.getTongTien(),
                entity.getPhuongThuc(),
                entity.getTrangThai(),
                entity.getMaPhieuDatBan(),
                entity.getMaKhuyenMai(),
                entity.getMaNhanVien());
    }

    //sửa
    @Override
    public void update(HoaDon entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getNgayLap(),
                entity.getTienMonAn(),
                entity.getTienGiamDiemThuong(),
                entity.getTienGiamKhuyenMai(),
                entity.getTongTien(),
                entity.getPhuongThuc(),
                entity.getTrangThai(),
                entity.getMaPhieuDatBan(),
                entity.getMaKhuyenMai(),
                entity.getMaHoaDon());
    }

    //xóa
    @Override
    public void delete(Integer maHD) {
        XJdbc.executeUpdate(DELETE_SQL, maHD);
    }

    @Override
    public HoaDon selectById(Integer id) {
        List<HoaDon> list = this.selectBySQL(SELECT_BY_IDKhach_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    //
    @Override
    public List<HoaDon> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    //truyền dữ liệu vào model khách hàng
    @Override
    protected List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();

                entity.setMaHoaDon(rs.getInt("MaHoaDon"));
                entity.setNgayLap(rs.getDate("NgayLap"));
                entity.setTienMonAn(rs.getDouble("TienMonAn"));
                entity.setTienGiamDiemThuong(rs.getDouble("TienGiamDiemThuong"));
                entity.setTienGiamKhuyenMai(rs.getDouble("TienGiamKhuyenMai"));
                entity.setTongTien(rs.getDouble("TongTien"));
                entity.setPhuongThuc(rs.getBoolean("PhuongThucThanhToan"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                entity.setMaPhieuDatBan(rs.getInt("MaPhieuDatBan"));
                entity.setMaKhuyenMai(rs.getInt("MaKhuyenMai"));
                entity.setMaNhanVien(rs.getInt("MaNhanVien"));
                entity.setMaKhachHang(rs.getInt("MaKhachHang"));
                entity.setTenBan(rs.getString("MaBan"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Boolean> selectPT() {
        String sql = "select distinct PhuongThucThanhToan from HoaDon";
        List<Boolean> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            while (rs.next()) {
                boolean pt = rs.getBoolean(1);
                list.add(pt);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Boolean> selectTT() {
        String sql = "select distinct TrangThai from HoaDon";
        List<Boolean> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            while (rs.next()) {
                boolean tt = rs.getBoolean(1);
                list.add(tt);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

    public int PhanTram(int MaKhuyenMai) {
        String sql = "SELECT PhanTram FROM KhuyenMai WHERE MaKhuyenMai = ?";
        int phanTram = 0;
        try (Connection conn = XJdbc.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, MaKhuyenMai);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                phanTram = rs.getInt("PhanTram");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phanTram;
    }

    public List<HoaDon> selectByMuti(String keyword) {
        String sql = """
                 select MaHoaDon, NgayLap, TienMonAn, TienGiamDiemThuong, TienGiamKhuyenMai, TongTien, PhuongThucThanhToan, pdb.MaPhieuDatBan, MaKhuyenMai, MaNhanVien, kh.MaKhachHang 
                 from HoaDon hd
                        join PhieuDatBan pdb on hd.MaPhieuDatBan = pdb.MaPhieuDatBan
                        left join KhachHang kh on pdb.MaKhachHang = kh.MaKhachHang
                        """;

        if (keyword != null && !keyword.isEmpty()) {
            String condition = getCondition(keyword);
            if (!condition.isEmpty()) {
                sql += " where " + condition;
            }
        }

        List<HoaDon> list = new ArrayList<>();
        try (Connection conn = XJdbc.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (keyword != null && !keyword.isEmpty()) {
                setParameter(ps, keyword);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon entity = new HoaDon();

                entity.setMaHoaDon(rs.getInt("MaHoaDon"));
                entity.setNgayLap(rs.getDate("NgayLap"));
                entity.setTienMonAn(rs.getDouble("TienMonAn"));
                entity.setTienGiamDiemThuong(rs.getDouble("TienGiamDiemThuong"));
                entity.setTienGiamKhuyenMai(rs.getDouble("TienGiamKhuyenMai"));
                entity.setTongTien(rs.getDouble("TongTien"));
                entity.setPhuongThuc(rs.getBoolean("PhuongThucThanhToan"));
                entity.setMaPhieuDatBan(rs.getInt("MaPhieuDatBan"));
                entity.setMaKhuyenMai(rs.getInt("MaKhuyenMai"));
                entity.setMaNhanVien(rs.getInt("MaNhanVien"));
                entity.setMaKhachHang(rs.getInt("MaKhachHang"));

                list.add(entity);
            }

        } catch (SQLException e) {
//            e.printStackTrace();
            return null;
        }
        return list;
    }

    private String getCondition(String keyword) {
        String condition = "";
        if (keyword.startsWith("HD")) {
            condition = "MaHoaDon like ?";
        } else if (keyword.startsWith("PDB")) {
            condition = "pdb.MaPhieuDatBan like ?";
        } else if (keyword.startsWith("KM")) {
            condition = "MaKhuyenMai like ?";
        } else if (keyword.startsWith("NV")) {
            condition = "MaNhanVien like ?";
        } else if (keyword.startsWith("KH")) {
            condition = "kh.MaKhachHang like ?";
        }
        return condition;
    }

    private void setParameter(PreparedStatement ps, String keyword) throws SQLException {
        if (keyword.startsWith("HD") || keyword.startsWith("KM") || keyword.startsWith("NV") || keyword.startsWith("KH")) {
            ps.setString(1, "%" + keyword.substring(2) + "%");
        }else if(keyword.startsWith("PDB")){
            ps.setString(1, "%" + keyword.substring(3) + "%");
        }else {
            ps.setString(1, "%" + keyword + "%");
        }
    }

//    public List<HoaDon> selectByMaKH(Integer maHoaDon) {
//        String sql = """
//            select MaHoaDon,NgayLap,TienMonAn,TienGiamDiemThuong,TienGiamKhuyenMai,TongTien,PhuongThucThanhToan,pdb.MaPhieuDatBan,MaKhuyenMai,MaNhanVien,kh.MaKhachHang from HoaDon hd
//                                 join PhieuDatBan pdb on hd.MaPhieuDatBan = pdb.MaPhieuDatBan
//                                 left join KhachHang kh on pdb.MaKhachHang= kh.MaKhachHang
//                                 where MaHoaDon = ?""";
//
//        List<HoaDon> list = new ArrayList<>();
//        try (Connection conn = XJdbc.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, maHoaDon);
//
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                HoaDon entity = new HoaDon();
//
//                entity.setMaHoaDon(rs.getInt("MaHoaDon"));
//                entity.setNgayLap(rs.getDate("NgayLap"));
//                entity.setTienMonAn(rs.getDouble("TienMonAn"));
//                entity.setTienGiamDiemThuong(rs.getDouble("TienGiamDiemThuong"));
//                entity.setTienGiamKhuyenMai(rs.getDouble("TienGiamKhuyenMai"));
//                entity.setTongTien(rs.getDouble("TongTien"));
//                entity.setPhuongThuc(rs.getBoolean("PhuongThucThanhToan"));
//                entity.setMaPhieuDatBan(rs.getInt("MaPhieuDatBan"));
//                entity.setMaKhuyenMai(rs.getInt("MaKhuyenMai"));
//                entity.setMaNhanVien(rs.getInt("MaNhanVien"));
//                entity.setMaKhachHang(rs.getInt("MaKhachHang"));
//
//                list.add(entity);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return list;
//    }
    public List<HoaDon> selectHD() {
        String sql = """
                    SELECT 
                        hd.MaHoaDon,
                        hd.NgayLap,
                        hd.TienMonAn,
                        hd.TienGiamDiemThuong,
                        hd.TienGiamKhuyenMai,
                        hd.TongTien,
                        hd.PhuongThucThanhToan,
                        hd.TrangThai,
                        pdb.MaPhieuDatBan,
                        hd.MaNhanVien,
                        kh.MaKhachHang,
                        STRING_AGG(ctdb.MaBan, ', ') AS MaBan,
                        hd.MaKhuyenMai
                    FROM 
                        HoaDon hd
                    JOIN 
                        PhieuDatBan pdb ON hd.MaPhieuDatBan = pdb.MaPhieuDatBan
                    LEFT JOIN 
                        KhachHang kh ON pdb.MaKhachHang = kh.MaKhachHang
                    JOIN 
                        ChiTietDatBan ctdb ON ctdb.MaPhieuDatBan = pdb.MaPhieuDatBan
                    GROUP BY 
                        hd.MaHoaDon,
                        hd.NgayLap,
                        hd.TienMonAn,
                        hd.TienGiamDiemThuong,
                        hd.TienGiamKhuyenMai,
                        hd.TongTien,
                        hd.PhuongThucThanhToan,
                        hd.TrangThai,
                        pdb.MaPhieuDatBan,
                        hd.MaNhanVien,
                        kh.MaKhachHang,
                        hd.MaKhuyenMai
                    ORDER BY 
                        hd.MaHoaDon;""";
        return this.selectBySQL(sql);
    }

    public List<HoaDon> selectByChiTiet(String keyword) {
        String sql = """
                     select TenMonAn,DonGia,SoLuong,sum(DonGia*SoLuong) as ThanhTien from HoaDon hd
                     join PhieuDatBan pdb on hd.MaPhieuDatBan = pdb.MaPhieuDatBan
                     join ChiTietGM ctgm on pdb.MaPhieuDatBan = ctgm.MaPhieuDatBan
                     join MonAn ma on ma.MaMonAn = ctgm.MaMonAn
                     where hd.MaHoaDon = ?
                     group by TenMonAn,DonGia,SoLuong""";
        return this.selectBySQL(sql, "%" + keyword + "%");
    }

    public List<Object[]> getChiTiet(Integer hd) {
        String sql = "{CALL sp_ChiTietHD(?)}";
        String[] cols = {"TenMonAn", "DonGia", "SoLuong", "ThanhTien"};
        return this.getListOfArray(sql, cols, hd);
    }

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

    public Integer getMaHoaDonByMaPhieuDatBan(Integer maPhieuDatBan) {
        String sql = "SELECT MaHoaDon FROM HoaDon WHERE MaPhieuDatBan = ?";
        try (Connection conn = XJdbc.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhieuDatBan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("MaHoaDon");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
