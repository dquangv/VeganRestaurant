/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.ThucDonDAO;
import Model.MonAn;
import Utils.Auth;
import Utils.XImage;
import Utils.XJdbc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

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
        loadThucDonToComboBox();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);

    }

    public void setBan(String maBan) {
        lbmaBan.setText("Bàn: " + maBan);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblTongTien = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblNhanVien = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTongMon = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
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
                .addGap(281, 281, 281)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnlThanhToan.setBackground(new java.awt.Color(153, 153, 153));

        lbmaBan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbmaBan.setText("Bàn 01");

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Món ăn", "Số lượng", "Đơn giá", "Thành tiền", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
        }

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(0, 0, 0));

        jButton1.setBackground(new java.awt.Color(255, 0, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Lưu");

        jButton2.setBackground(new java.awt.Color(255, 0, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Thanh toán");

        lblNhanVien.setText("Nhân viên:");

        jLabel3.setText("Khách hàng");

        jLabel4.setText("Thời gian:");

        lblTongMon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongMon.setForeground(new java.awt.Color(0, 0, 0));

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThanhToanLayout = new javax.swing.GroupLayout(pnlThanhToan);
        pnlThanhToan.setLayout(pnlThanhToanLayout);
        pnlThanhToanLayout.setHorizontalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThanhToanLayout.createSequentialGroup()
                                .addComponent(lbmaBan)
                                .addGap(232, 232, 232))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThanhToanLayout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNhanVien)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTongMon, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        pnlThanhToanLayout.setVerticalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbmaBan)
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblNhanVien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTongMon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThanhToanLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(137, 137, 137))))
        );

        pnlThanhToanLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

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
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        hienThiDanhSachMonAn(selectedLoaiMon);
    }//GEN-LAST:event_cbbLoaiMonItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int idx = jTable1.getSelectedRow();
        model.removeRow(idx);
        tinhTongTien();
    }//GEN-LAST:event_jButton3ActionPerformed
    private void hienThiDanhSachMonAn(String loaiMon) {
        List<MonAn> danhSachMonTheoLoai = thucDonDAO.layDanhSachMonTheoLoai(loaiMon);
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

                    int scaledWidth = 150;
                    int scaledHeight = (int) (((double) scaledWidth / originalImage.getWidth()) * originalImage.getHeight());
                    Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);

                    JLabel lblMonAn = new JLabel(scaledIcon);

                    JLabel lblTenMonAn = new JLabel(monAn.getTenMonAn());
                    lblTenMonAn.setFont(new java.awt.Font("Segoe UI", 1, 14));
                    JLabel lblGiaMonAn = new JLabel(monAn.getFormattedDonGia() + " VNĐ");
                    lblGiaMonAn.setFont(new java.awt.Font("Segoe UI", 0, 18));
                    monAnPanel.add(lblMonAn);
                    monAnPanel.add(lblTenMonAn);
                    monAnPanel.add(lblGiaMonAn);
                    monAnPanel.setBounds((i % 3) * 250, (i / 3) * 250, 240, 250);
                    pnlMonAn.add(monAnPanel);
                    
                } catch (IOException ex) {
                }
            } else {
                System.err.println("Không thể tìm thấy hình ảnh: " + imagePath);
            }
            monAnPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    hienThiThongTinMonAn(monAn);
                }
            });

        }

        pnlMonAn.revalidate();
        pnlMonAn.repaint();
    }

    private void hienThiThongTinMonAn(MonAn monAn) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        boolean found = false;
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(monAn.getTenMonAn())) {
                int soLuong = Integer.parseInt((String) model.getValueAt(i, 1)) + 1;
                double donGia = Double.parseDouble(((String) model.getValueAt(i, 2)).replace(",", "").replace(" VNĐ", ""));
                double thanhTien = soLuong * donGia;
                model.setValueAt(String.valueOf(soLuong), i, 1);
                model.setValueAt(decimalFormat.format(thanhTien), i, 3);
                found = true;
                break;
            }
        }

        if (!found) {
            double donGia = Double.parseDouble(monAn.getFormattedDonGia().replace(",", "").replace(" VNĐ", ""));
            double thanhTien = donGia;
            model.addRow(new Object[]{monAn.getTenMonAn(), "1", monAn.getFormattedDonGia(), decimalFormat.format(thanhTien)});
            tinhTongTien();
        }

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    if (column == 1) {
                        String soLuongStr = (String) model.getValueAt(row, column);
                        int soLuong = Integer.parseInt(soLuongStr);
                        double donGia = Double.parseDouble(((String) model.getValueAt(row, 2)).replace(",", "").replace(" VNĐ", ""));
                        double thanhTien = soLuong * donGia;
                        model.setValueAt(decimalFormat.format(thanhTien), row, 3);
                        tinhTongTien();
                        if (soLuong == 0) {
                            model.removeRow(row);
                        }
                    }
                }
            }
        }
        );

    }

    private void tinhTongTien() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        double tongTien = 0.0;
        int tongMon = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            String thanhTienString = (String) model.getValueAt(i, 3);
            double thanhTien = Double.parseDouble(thanhTienString.replace(",", "").replace(" VNĐ", ""));
            tongTien += thanhTien;
            String soLuong = (String) model.getValueAt(i, 1);
            int tongSL = Integer.parseInt(soLuong);
            tongMon += tongSL;
        }
        lblTongMon.setText("Tổng số món: "+tongMon+".");
        lblTongTien.setText("Tổng tiền: " + decimalFormat.format(tongTien) + " VNĐ");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbLoaiMon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblTongMon;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lbmaBan;
    private javax.swing.JPanel pnlMonAn;
    private javax.swing.JPanel pnlThanhToan;
    // End of variables declaration//GEN-END:variables
}
