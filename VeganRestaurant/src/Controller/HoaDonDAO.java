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
public class HoaDonDAO extends NhaHangChayDAO<HoaDon, String> {

    String INSERT_SQl = "INSERT INTO HoaDon(NgayLap,TienMonAn,TienGiamDiemThuong,TienGiamKhuyenMai,TongTien,PhuongThucThanhToan,MaPhieuDatBan,MaKhuyenMai,MaNhanVien) VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HoaDon SET NgayLap=? ,TienMonAn=? ,TienGiamDiemThuong=? ,TienGiamKhuyenMai=? ,TongTien=? ,PhuongThucThanhToan=? ,MaPhieuDatBan=? ,MaKhuyenMai=? ,MaNhanVien=? WHERE MaHoaDon =?";
    String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
    String SELECT_ALL_SQL = """
                            select MaHoaDon,NgayLap,TienMonAn,TienGiamDiemThuong,TienGiamKhuyenMai,TongTien,PhuongThucThanhToan,pdb.MaPhieuDatBan,MaKhuyenMai,MaNhanVien,kh.MaKhachHang from HoaDon hd
                            join PhieuDatBan pdb on hd.MaPhieuDatBan = pdb.MaPhieuDatBan
                            join KhachHang kh on pdb.MaKhachHang= kh.MaKhachHang""";

    String SELECT_BY_IDKhach_SQL = """
                                   select MaHoaDon,NgayLap,TienMonAn,TienGiamDiemThuong,TienGiamKhuyenMai,TongTien,PhuongThucThanhToan,pdb.MaPhieuDatBan,MaKhuyenMai,MaNhanVien,kh.MaKhachHang from HoaDon hd
                                   join PhieuDatBan pdb on hd.MaPhieuDatBan = pdb.MaPhieuDatBan
                                   join KhachHang kh on pdb.MaKhachHang= kh.MaKhachHang
                                   where MaHoaDon = ? """;

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
                entity.getMaPhieuDatBan(),
                entity.getMaKhuyenMai(),
                entity.getMaNhanVien(),
                entity.getMaHoaDon());
    }

    //xóa
    @Override
    public void delete(String maHD) {
        XJdbc.executeUpdate(DELETE_SQL, maHD);
    }

    @Override
    public HoaDon selectById(String id) {
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
                entity.setMaPhieuDatBan(rs.getInt("MaPhieuDatBan"));
                entity.setMaKhuyenMai(rs.getInt("MaKhuyenMai"));
                entity.setMaNhanVien(rs.getInt("MaNhanVien"));
                entity.setMaKhachHang(rs.getInt("MaKhachHang"));

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

    public List<HoaDon> selectByMaKH(Integer maKhachHang) {
    String sql = """
            select MaHoaDon,NgayLap,TienMonAn,TienGiamDiemThuong,TienGiamKhuyenMai,TongTien,PhuongThucThanhToan,pdb.MaPhieuDatBan,MaKhuyenMai,MaNhanVien,kh.MaKhachHang from HoaDon hd
                                 join PhieuDatBan pdb on hd.MaPhieuDatBan = pdb.MaPhieuDatBan
                                 join KhachHang kh on pdb.MaKhachHang= kh.MaKhachHang
                                 where MaHoaDon = ?""";

    List<HoaDon> list = new ArrayList<>();
    try (Connection conn = XJdbc.getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) { 

        ps.setInt(1, maKhachHang);

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
        e.printStackTrace();
        return null;
    }
    return list;
}

    public List<HoaDon> selectHD() {
        String sql = """
                     select MaHoaDon,NgayLap,TienMonAn,TienGiamDiemThuong,TienGiamKhuyenMai,TongTien,PhuongThucThanhToan,pdb.MaPhieuDatBan,MaKhuyenMai,MaNhanVien,kh.MaKhachHang from HoaDon hd
                     join PhieuDatBan pdb on hd.MaPhieuDatBan = pdb.MaPhieuDatBan
                     join KhachHang kh on pdb.MaKhachHang= kh.MaKhachHang""";
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

}
