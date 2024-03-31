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
    
     public List<Object[]> getDoanhThu(Integer nam) {
        String sql = "{CALL SP_DoanhThuThang(?)}";
        String[] cols = {"Thang", "TongThanhTien"};
        return this.getListOfArray(sql, cols, nam);
    }
      public List<Integer> selectYears() {
        String SQL = "SELECT DISTINCT year(NgayLap) Year FROM hoadon ORDER BY Year DESC";
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

