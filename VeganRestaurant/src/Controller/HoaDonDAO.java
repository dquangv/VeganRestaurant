/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HoaDon;
import com.utils.XJdbc;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class HoaDonDAO extends NhaHangChayDAO<HoaDon, String> {

    String INSERT_SQl = "INSERT INTO HoaDon(MaHoaDon,NgayLap,TienMonAn,TienPhatSinh,TienGiamDiemThuong,TienGiamKhuyenMai,TongTien,TrangThai,MaKhachHang,MaBan,MaKhuyenMai,MaNhanVien) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HoaDon SET NgayLap=? ,TienMonAn=? ,TienPhatSinh=? ,TienGiamDiemThuong=? ,TienGiamKhuyenMai=? ,TongTien=? ,TrangThai=? ,MaKhachHang=? ,MaBan=? ,MaKhuyenMai=? ,MaNhanVien=? WHERE MaHoaDon =?";
    String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
    String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    String SELECT_BY_IDKhach_SQL = "SELECT * FROM HoaDon WHERE MaHoaDon =? ";

    //thêm
    @Override
    public void insert(HoaDon entity) {
        XJdbc.executeUpdate(INSERT_SQl,
                entity.getMaHoaDon(),
                entity.getNgayLap(),
                entity.getTienMonAn(),
                entity.getTienPhatSinh(),
                entity.getTienGiamDiemThuong(),
                entity.getTienGiamKhuyenMai(),
                entity.getTongTien(),
                entity.getTrangThai(),
                entity.getMaKhachHang(),
                entity.getMaBan(),
                entity.getMaKhuyenMai(),
                entity.getMaNhanVien());
    }

    //sửa
    @Override
    public void update(HoaDon entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getNgayLap(),
                entity.getTienMonAn(),
                entity.getTienPhatSinh(),
                entity.getTienGiamDiemThuong(),
                entity.getTienGiamKhuyenMai(),
                entity.getTongTien(),
                entity.getTrangThai(),
                entity.getMaKhachHang(),
                entity.getMaBan(),
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

                entity.setMaHoaDon(rs.getString("MaHoaDon"));
                entity.setNgayLap(rs.getDate("NgayLap"));
                entity.setTienMonAn(rs.getDouble("TienMonAn"));
                entity.setTienPhatSinh(rs.getDouble("TienPhatSinh"));
                entity.setTienGiamDiemThuong(rs.getDouble("TienGiamDiemThuong"));
                entity.setTienGiamKhuyenMai(rs.getDouble("TienGiamKhuyenMai"));
                entity.setTongTien(rs.getDouble("TongTien"));
                entity.setTrangThai(rs.getString("TrangThai"));
                entity.setMaKhachHang(rs.getString("MaKhachHang"));
                entity.setMaBan(rs.getString("MaBan"));
                entity.setMaKhuyenMai(rs.getString("MaKhuyenMai"));
                entity.setMaNhanVien(rs.getString("MaNhanVien"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<HoaDon> selectByMaKH(String keyword){
        String sql = "SELECT * FROM HOADON WHERE MaHoaDon like ?";
        return this.selectBySQL(sql,"%"+keyword+"%");
    }
    
}
