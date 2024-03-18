/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.ThucDonDAO;
import Model.MonAn;
import Utils.XImage;
import Utils.XJdbc;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author buimi
 */
public class GoiMon extends javax.swing.JPanel {

    /**
     * Creates new form GoiMon
     */
    private ThucDonDAO thucDonDAO;

    public GoiMon() {
        initComponents();
        thucDonDAO = new ThucDonDAO(new XJdbc());
        // Gọi phương thức để hiển thị thông tin món ăn vào JComboBox và các components khác
        loadThucDonToComboBox();
    }
    public void setBan(String maBan){
        lbmaBan.setText("Bàn: "+maBan);
    }
    private void loadThucDonToComboBox() {
        List<MonAn> danhSachMonAn = thucDonDAO.layDanhSachMonAn();

        for (MonAn monAn : danhSachMonAn) {
            if (!kiemTraMonAnTonTai(monAn.getLoaiMonAn())) {
                cbbLoaiMon.addItem(monAn.getLoaiMonAn());
            }
        }
    }

    private boolean kiemTraMonAnTonTai(String loaiMonAn) {
        for (int i = 0; i < cbbLoaiMon.getItemCount(); i++) {
            if (loaiMonAn.equals(cbbLoaiMon.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gọi món");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GoiMon());
        frame.pack();
        frame.setLocationRelativeTo(null); // Đặt frame ở giữa màn hình
        frame.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlThanhToan = new javax.swing.JPanel();
        lbmaBan = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbbLoaiMon = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlMonAn = new javax.swing.JPanel();

        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Gọi món");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnlThanhToan.setBackground(new java.awt.Color(153, 153, 153));

        lbmaBan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbmaBan.setText("Bàn 01");

        javax.swing.GroupLayout pnlThanhToanLayout = new javax.swing.GroupLayout(pnlThanhToan);
        pnlThanhToan.setLayout(pnlThanhToanLayout);
        pnlThanhToanLayout.setHorizontalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(lbmaBan)
                .addContainerGap(184, Short.MAX_VALUE))
        );
        pnlThanhToanLayout.setVerticalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbmaBan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Thực đơn hôm nay");

        cbbLoaiMon.setBackground(new java.awt.Color(153, 153, 153));
        cbbLoaiMon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiMonItemStateChanged(evt);
            }
        });

        pnlMonAn.setPreferredSize(new java.awt.Dimension(682, 1000));

        javax.swing.GroupLayout pnlMonAnLayout = new javax.swing.GroupLayout(pnlMonAn);
        pnlMonAn.setLayout(pnlMonAnLayout);
        pnlMonAnLayout.setHorizontalGroup(
            pnlMonAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
        );
        pnlMonAnLayout.setVerticalGroup(
            pnlMonAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlMonAn);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cbbLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbLoaiMonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiMonItemStateChanged
        // TODO add your handling code here:
        String selectedLoaiMon = (String) cbbLoaiMon.getSelectedItem();

        // Hiển thị danh sách món ăn theo loại món
        hienThiDanhSachMonAn(selectedLoaiMon);
    }//GEN-LAST:event_cbbLoaiMonItemStateChanged
    private void hienThiDanhSachMonAn(String loaiMon) {
        // Gọi phương thức lấy danh sách món ăn theo loại từ ThucDonDAO
        List<MonAn> danhSachMonTheoLoai = thucDonDAO.layDanhSachMonTheoLoai(loaiMon);

        // Gọi phương thức hiển thị danh sách món ăn
        hienThiDanhSachMonAnUI(danhSachMonTheoLoai);
    }

    private void hienThiDanhSachMonAnUI(List<MonAn> danhSachMonAn) {
        pnlMonAn.removeAll();

        for (int i = 0; i < danhSachMonAn.size(); i++) {
            MonAn monAn = danhSachMonAn.get(i);
            JPanel monAnPanel = new JPanel();
            monAnPanel.setLayout(new BoxLayout(monAnPanel, BoxLayout.Y_AXIS));

            String imagePath = "/Image/menu/" + monAn.getHinhAnh();
            InputStream inputStream = getClass().getResourceAsStream(imagePath);

            if (inputStream != null) {
                try {
                    BufferedImage originalImage = ImageIO.read(inputStream);

                    // Scale hình ảnh
                    int scaledWidth = 150; // Đặt kích thước mong muốn
                    int scaledHeight = (int) (((double) scaledWidth / originalImage.getWidth()) * originalImage.getHeight());

                    Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);

                    JLabel lblMonAn = new JLabel(scaledIcon);

                    JLabel lblTenMonAn = new JLabel(monAn.getTenMonAn());
                    lblTenMonAn.setFont(new java.awt.Font("Segoe UI", 1, 14));
                    JLabel lblGiaMonAn = new JLabel(monAn.getFormattedDonGia()+ " VNĐ");
                    lblGiaMonAn.setFont(new java.awt.Font("Segoe UI", 0, 18));
                    monAnPanel.add(lblMonAn);
                    monAnPanel.add(lblTenMonAn);
                    monAnPanel.add(lblGiaMonAn);
                    monAnPanel.setBounds((i % 3) * 250, (i / 3) * 250, 250, 250);
                    pnlMonAn.add(monAnPanel);
                } catch (IOException ex) {
                }
            } else {
                System.err.println("Không thể tìm thấy hình ảnh: " + imagePath);
            }
        }

        pnlMonAn.revalidate();
        pnlMonAn.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbLoaiMon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbmaBan;
    private javax.swing.JPanel pnlMonAn;
    private javax.swing.JPanel pnlThanhToan;
    // End of variables declaration//GEN-END:variables
}
