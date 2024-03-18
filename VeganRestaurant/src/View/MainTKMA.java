/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javax.swing.JFrame;

/**
 *
 * @author Võ Thanh Tùng
 */
public class MainTKMA {
     public static void main(String[] args) {
        JFrame frame = new JFrame("Thống kê món ăn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(new JpanelThongKeMonAn());
//        frame.getContentPane().add(new JPanelThongKeDoanhThu());
        frame.getContentPane().add(new JPanelDatBan());
//        frame.getContentPane().add(new JPanelTang1());

        frame.pack();
        frame.setVisible(true);
    }
}
