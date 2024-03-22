/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author buimi
 */
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainThucDon {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Quản lý thực đơn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ThucDon thucDonPanel = new ThucDon();
        frame.getContentPane().add(thucDonPanel);
//        KhachHang khachHangPanel = new KhachHang();
//        frame.getContentPane().add(khachHangPanel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
