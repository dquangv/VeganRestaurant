/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Võ Thanh Tùng
 */
public class ThongKeDAO {

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
    public List<Object[]> getMonAnBanChay(){
        String sql = "{CALL Sp_ThongKeMonAn}";
        String cols[] = {"TenMonAn","Soluongmonan"};
        
        return getListOfArray(sql, cols);
    }
    public List<Object[]> getDSDanhGia(){
        String sql = "{CALL Sp_soluongdanhgia}";
        String cols[] = {"soluongdanhgia"};
        
        return getListOfArray(sql, cols);
    }
    
     public List<Object[]> getDoanhThuThang(Integer thang) {
        String sql = "{CALL SP_DoanhThuThang(?)}";
        String[] cols = {"Thang", "TongThanhTien"};
        return this.getListOfArray(sql, cols, thang);
    }
     public List<Object[]> getDoanhThuNam(Integer nam) {
        String sql = "{CALL SP_DoanhThuNam(?)}";
        String[] cols = {"Thang", "TongThanhTien"};
        return this.getListOfArray(sql, cols, nam);
    }
      public List<Object[]> sp_laySao() {
        String sql = "{CALL sp_laySao}";
        String[] cols = {"maDanhGia", "soluongdanhgia"};
        return this.getListOfArray(sql, cols);
    }
       public List<Object[]> getCTDanhGia(int maDanhGia) {
        String sql = "{CALL sp_chitietDanhGia(?)}";
        String[] cols = {"TenMonAn", "soluongdanhgia"};
        return this.getListOfArray(sql, cols, maDanhGia);
    }
      public List<Integer> selectMonth() {
        String SQL = "SELECT DISTINCT month(NgayLap) month FROM hoadon ORDER BY month";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(SQL);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
      public List<Integer> selectYest() {
        String SQL = "SELECT DISTINCT Year(NgayLap) Year FROM hoadon ORDER BY Year";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(SQL);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
       public boolean checkMaMonAn(String maMonAn){
          try {
              ResultSet rs = XJdbc.executeQuery("select MaMonAn from MonAn where MaMonAn = ?", maMonAn);
              if (rs.next()) {
                  return false;
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return true;
      }
       
}

