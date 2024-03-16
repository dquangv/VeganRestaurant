/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Model.Ban;
import Model.NhanVIen;
import Model.TaiKhoan;

/**
 *
 * @author balis
 */
public class Auth {
    public static Ban maBan = null;
    //public static NhanVIen user = null;
    
    /**
     *
     */
    public static TaiKhoan user = new TaiKhoan();
    
    
   public static void setMaBan(Ban ban) {
        Auth.maBan = ban;
    }

    public static Ban getMaBan() {
        return Auth.maBan;
    }
    public static void clear() {
        Auth.user = null;
        Auth.maBan = null;
    }
    
    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static boolean isManager() {
        return Auth.isLogin() && user.isVaiTro();
    }


    
}
