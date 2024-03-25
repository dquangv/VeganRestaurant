/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

/**
 *
 * @author hoang
 */
import Controller.HoaDonDAO;
import Model.HoaDon;
import Utils.MsgBox;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;

public class HoaDonJPanel extends javax.swing.JPanel {

    /**
     * Creates new form HoaDonJPanel
     */
    HoaDonDAO hdDAO = new HoaDonDAO();
//    ThanhToanJDialog ttJDialog = new ThanhToanJDialog(parent, true);
    int row = -1;

    public HoaDonJPanel() {
        initComponents();
        this.themVaoTableHD();
//        this.themVaoCbo();
    }

    //fill vào hóa đơn
    void themVaoTableHD() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            List<HoaDon> list = hdDAO.selectHD();
            for (HoaDon hd : list) {
                Object[] row = {
                    "HD" + hd.getMaHoaDon(),
                    hd.getNgayLap(),
                    hd.getTienMonAn(),
                    hd.getTienGiamDiemThuong(),
                    hd.getTienGiamKhuyenMai(),
                    hd.getTongTien(),
                    hd.getPhuongThuc() ? "Thanh Toán" : "Chưa Thanh Toán",
                    "PDB" + hd.getMaPhieuDatBan(),
                    "KM" + hd.getMaKhuyenMai(),
                    "NV" + hd.getMaNhanVien(),
                    "KH" + hd.getMaKhachHang()
                };

                model.addRow(row);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    void timTheoMaKH() {
//        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
//        model.setRowCount(0);
//        String id = txtTimID.getText();
//        List<HoaDon> list = hdDAO.selectByMaKH(Integer.valueOf(id.substring(2)));
//        for (HoaDon hd : list) {
//            model.addRow(new Object[]{
//                "HD" + hd.getMaHoaDon(),
//                hd.getNgayLap(),
//                hd.getTienMonAn(),
//                hd.getTienGiamDiemThuong(),
//                hd.getTienGiamKhuyenMai(),
//                hd.getTongTien(),
//                hd.getPhuongThuc() ? "Thanh Toán" : "Chưa Thanh Toán",
//                "PDB" + hd.getMaPhieuDatBan(),
//                "KM" + hd.getMaKhuyenMai(),
//                "NV" + hd.getMaNhanVien(),
//                "KH" + hd.getMaKhachHang()
//            });
//        }
//    }
//    void thietLapForm(HoaDon hd) {
//        txtMaHoaDon.setText("HD" + hd.getMaHoaDon());
//        txtMaKH.setText("KH" + hd.getMaKhachHang());
//        txtBan.setText("PDB" + String.valueOf(hd.getMaPhieuDatBan()));
//        txtNhanVien.setText("NV" + String.valueOf(hd.getMaNhanVien()));
//        txtNgayLap.setDate(hd.getNgayLap());
//        txtMaGiamGia.setText("KM" + String.valueOf(hd.getMaKhuyenMai()));
//        txtTienMon.setText(String.valueOf(Double.sum(hd.getTienMonAn(), 0)));
//        txtTienGiam.setText(String.valueOf(hd.getTienGiamKhuyenMai()));
//        txtDiemThuong.setText(hd.getTienGiamDiemThuong() + "");
//        cboPhuongThuc.setSelectedItem(hd.getPhuongThuc() ? "Thanh Toán" : "Chưa Thanh Toán" + "");
//        txtTongTien.setText(hd.getTongTien() + "");
//    }

//    void thietLapTableCT(Integer hd) {
//        DefaultTableModel model = (DefaultTableModel) tblChiTiet.getModel();
//        model.setRowCount(0);
//
//        List<Object[]> list = hdDAO.getChiTiet(hd);
//        for (Object[] row : list) {
//            model.addRow(new Object[]{
//                row[0], row[1], row[2], row[3]
//            });
//        }
//    }

//    HoaDon layForm() {
//        HoaDon hd = new HoaDon();
//
//        hd.setMaHoaDon(Integer.valueOf(txtMaHoaDon.getText().substring(2)));
//        hd.setMaPhieuDatBan(Integer.valueOf(txtBan.getText().substring(3)));
//        hd.setMaNhanVien(Integer.valueOf(txtNhanVien.getText().substring(2)));
//        hd.setNgayLap(txtNgayLap.getDate());
//        hd.setMaKhuyenMai(Integer.valueOf(txtMaGiamGia.getText().substring(2)));
//        hd.setTienMonAn(Double.parseDouble(txtTienMon.getText()));
//        hd.setTienGiamKhuyenMai(Double.parseDouble(txtTienGiam.getText()));
//        boolean pt;
//        pt = cboPhuongThuc.getSelectedItem().equals("Thanh Toán");
//        hd.setPhuongThuc(pt);
//        hd.setTongTien(Double.parseDouble(txtTongTien.getText()));
//
//        return hd;
//    }

//    void chinhSuaForm() {
//        String maHD = (String) tblHoaDon.getValueAt(this.row, 0);
//
//        System.out.println(maHD);
//
//        HoaDon hd = hdDAO.selectById(Integer.valueOf(maHD.substring(2)));
//
//        this.thietLapForm(hd);
//        this.thietLapTableCT(Integer.valueOf(maHD.substring(2)));
//        tpane.setSelectedIndex(0);
//    }

//    void themHD() {
//        HoaDon hd = layForm();
//        try {
//            hdDAO.update(hd);
//            this.themVaoTableHD();
//
//            MsgBox.alert(this, "Lưu Hóa Đơn Thành Công!");
//        } catch (Exception e) {
//            MsgBox.alert(this, "Lưu Hóa Đơn Thất Bại");
//        }
//    }

//    File luuFile() {
//        JFileChooser ch = new JFileChooser();
//        int luaChon = ch.showSaveDialog(this);
//        if (luaChon == JFileChooser.APPROVE_OPTION) {
//            return ch.getSelectedFile();
//        } else {
//            return null;
//        }
//    }
//    void themVaoCbo(){
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboPhuongThuc.getModel();
//        model.removeAllElements();
//        List<HoaDon> list = hdDAO.selectHD();
//        for (HoaDon pt : list) {
//            if(pt.getPhuongThuc() == true){
//                cboPhuongThuc.addItem("Thanh Toán");
//            }else{
//                cboPhuongThuc.addItem("Chưa Thanh Toán");
//            }
//        }
//    }
//    void themVaoCbo() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboPhuongThuc.getModel();
//        model.removeAllElements();
//        List<Boolean> list = hdDAO.selectPT();
//        for (Boolean pt : list) {
//            if (pt == true) {
//                cboPhuongThuc.addItem("Thanh Toán");
//            } else {
//                cboPhuongThuc.addItem("Chưa Thanh Toán");
//            }
//        }
//    }

    //in hoa đơn theo mẫu
    void inHoaDon() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=NhaHangChay_CohesiveStars;encrypt = false", "sa", "songlong")) {
                String reportPath = "src\\View\\HoaDonIn.jrxml";

                String maHD = (String) tblHoaDon.getValueAt(this.row, 0);

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("P_MaHD", maHD);

                JasperReport jcomp = JasperCompileManager.compileReport(reportPath);
                JasperPrint jprint = JasperFillManager.fillReport(jcomp, parameters, con);
                JasperViewer.viewReport(jprint, false);
                JasperPrintManager.printReport(jprint, true);
            }
        } catch (ClassNotFoundException | SQLException | JRException ex) {
        }
    }

    void xemHoaDon(int mahd) {
        ThanhToanJDialog jdialog = new ThanhToanJDialog(new javax.swing.JFrame(), true);
        jdialog.layMaHoaDon(mahd);
        jdialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        tpane = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        txtTimID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel2.setText("Quản lý hóa đơn");

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Ngày Lập", "Tiền Món Ăn", "Điểm Thưởng", "Khuyến Mãi", "Tổng tiền", "Phương Thức", "Phiếu Bàn", "Mã Khuyến Mãi", "Mã Nhân Viên", "Mã Khách Hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jLabel1.setText("Tìm kiếm:");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jButton1.setText("Xem Hóa Đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtTimID, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTim)
                .addGap(37, 37, 37)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnTim)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpane.addTab("Hóa Đơn", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpane)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(334, 334, 334)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.row = tblHoaDon.getSelectedRow();
        if (this.row >= 0) {

            String mahd = String.valueOf(tblHoaDon.getValueAt(row, 0));

            xemHoaDon(Integer.parseInt(mahd.substring(2)));

        }else{
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        //        this.timTheoMaKH();
    }//GEN-LAST:event_btnTimActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
//        this.row = tblHoaDon.getSelectedRow();
//        if (this.row >= 0) {
//
//            String mahd = String.valueOf(tblHoaDon.getValueAt(row, 0));
//
//            xemHoaDon(Integer.parseInt(mahd.substring(2)));
//
//        }
    }//GEN-LAST:event_tblHoaDonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTim;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTabbedPane tpane;
    private javax.swing.JTextField txtTimID;
    // End of variables declaration//GEN-END:variables
}
