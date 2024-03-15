/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.MonAnDAO;
import Model.MonAn;
import Utils.MsgBox;
import Utils.XImage;
import Utils.XJdbc;
import java.io.File;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Quang
 */
public class JPanelMonAn extends javax.swing.JPanel {

    private int row = -1;
    JFileChooser jFileChooser = new JFileChooser();

    /**
     * Creates new form MonAnPanel
     */
    public JPanelMonAn() {
        initComponents();
        fillCboLoai();
        fillCboTrangThai();
        displayMonAn();
    }

    public void fillCboLoai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiMonAn.getModel();

        model.removeAllElements();

        MonAnDAO dao = new MonAnDAO(new XJdbc());

        try {
            List<String> list = dao.selectCBMonAn();

            for (String monAn : list) {
                model.addElement(monAn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void fillCboTrangThai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTrangThai.getModel();

        model.removeAllElements();

        MonAnDAO dao = new MonAnDAO(new XJdbc());

        try {
            List<String> list = dao.selectCBTrangThai();

            for (String monAn : list) {
                model.addElement(monAn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void fillTable(List<MonAn> list) {
        DefaultTableModel model = (DefaultTableModel) tblMonAn.getModel();

        for (MonAn monAn : list) {
            Object row[] = {monAn.getMaMonAn(), monAn.getTenMonAn(), monAn.getDonGia(), monAn.getLoaiMonAn(), monAn.getHinhAnh(), monAn.getTrangThai()};

            model.addRow(row);
        }
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) tblMonAn.getModel();
        model.setRowCount(0);
    }

    public void displayMonAn() {
        clearTable();

        MonAnDAO dao = new MonAnDAO(new XJdbc());
        List<MonAn> list = dao.selectAll();
        fillTable(list);
    }

    public void themMon() {
        String maMonAn = txtMaMonAn.getText();
        String tenMonAn = txtTenMonAn.getText();
        Double donGia = Double.parseDouble(txtDonGia.getText());
        String loaiMon = (String) cboLoaiMonAn.getSelectedItem();
        String trangThai = (String) cboTrangThai.getSelectedItem();

        MonAn monAn = new MonAn();
        monAn.setMaMonAn(maMonAn);
        monAn.setTenMonAn(tenMonAn);
        monAn.setDonGia(donGia);
        monAn.setLoaiMonAn(loaiMon);
        monAn.setTrangThai(trangThai);

        MonAnDAO dao = new MonAnDAO();

        dao.themMonAn(monAn);
        JOptionPane.showMessageDialog(this, "Thêm món ăn thành công");
        displayMonAn();
    }

    public void capNhatMon() {
        String maMonAn = txtMaMonAn.getText();
        String tenMonAn = txtTenMonAn.getText();
        Double donGia = Double.parseDouble(txtDonGia.getText());
        String loaiMon = (String) cboLoaiMonAn.getSelectedItem();
        String trangThai = (String) cboTrangThai.getSelectedItem();
        String hinh = lblHinhAnh.getToolTipText();

        MonAn monAn = new MonAn();
        monAn.setMaMonAn(maMonAn);
        monAn.setTenMonAn(tenMonAn);
        monAn.setDonGia(donGia);
        monAn.setLoaiMonAn(loaiMon);
        monAn.setTrangThai(trangThai);
        monAn.setHinhAnh(hinh);

        MonAnDAO dao = new MonAnDAO();
        dao.chinhSuaMonAn(monAn);

        JOptionPane.showMessageDialog(this, "Cập nhật món ăn thành công");

        displayMonAn();
    }

    public void resetForm() {
        txtMaMonAn.setText("");
        txtTenMonAn.setText("");
        txtDonGia.setText("");
        cboLoaiMonAn.setSelectedIndex(0);
        cboTrangThai.setSelectedIndex(0);

        txtThemLoaiMA.setText("");
        txtThemTrangThai.setText("");
        lblHinhAnh.setIcon(null);
        lblHinhAnh.setText("Hình Ảnh");
    }

    void ChoosePicture() {
            jFileChooser.setCurrentDirectory(new File ("D:\\FPT\\tai lieu\\DuAn1\\Fpoly-VeganRestaurant\\VeganRestaurant\\Logos"));
        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            jFileChooser.setDialogTitle("Choose Image");
            File file = jFileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblHinhAnh.setToolTipText(file.getName());
            lblHinhAnh.setIcon(icon);
        }
    }

    void setForm(MonAn ma) {
        txtMaMonAn.setText(ma.getMaMonAn());
        txtTenMonAn.setText(ma.getTenMonAn());
        txtDonGia.setText(ma.getDonGia() + "");
        cboLoaiMonAn.setSelectedItem(ma.getLoaiMonAn());
        cboTrangThai.setSelectedItem(ma.getTrangThai());
        if (ma.getHinhAnh() != null) {
            lblHinhAnh.setToolTipText(ma.getHinhAnh());
            lblHinhAnh.setIcon(XImage.read(ma.getHinhAnh()));
        }
    }

    void timMonAn() {
        MonAnDAO dao = new MonAnDAO();
        String tenMonAn = txtTenMonAn.getText();
        DefaultTableModel model = (DefaultTableModel) tblMonAn.getModel();
        model.setRowCount(0);
        List<MonAn> list = dao.getTenMonAn(tenMonAn);
        for (MonAn ma : list) {
            model.addRow(new Object[]{ma.getMaMonAn(),
                ma.getTenMonAn(),
                ma.getDonGia(),
                ma.getHinhAnh(),
                ma.getTrangThai()});
        }
    }

    void edit() {
    row = tblMonAn.getSelectedRow();
    MonAnDAO dao = new MonAnDAO();
    String ma = (String) tblMonAn.getValueAt(this.row, 0);
    MonAn cd = dao.getMonAnByID(ma);
        System.out.println(ma);
    this.setForm(cd);
}


    boolean checkVaLiDate() {
        String maMonAn = txtMaMonAn.getText().trim();
        if (maMonAn.isEmpty()) {
            MsgBox.alert(this, "Mã món ăn không được bỏ trống");
            return false;
        }

        String tenMonAn = txtTenMonAn.getText().trim();
        if (tenMonAn.isEmpty()) {
            MsgBox.alert(this, "Tên món ăn không được bỏ trống");
            return false;
        }

        String donGiaText = txtDonGia.getText().trim();
        if (donGiaText.isEmpty()) {
            MsgBox.alert(this, "Đơn giá không được bỏ trống");
            return false;
        }

        try {
            double donGia = Double.parseDouble(donGiaText);
            if (donGia <= 0) {
                MsgBox.alert(this, "Đơn giá phải lớn hơn 0");
                return false;
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Đơn giá không hợp lệ");
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCapNhat = new javax.swing.JButton();
        cboLoaiMonAn = new javax.swing.JComboBox<>();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMonAn = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        txtThemLoaiMA = new javax.swing.JTextField();
        btnThemLoaiMA = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        txtMaMonAn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtThemTrangThai = new javax.swing.JTextField();
        txtTenMonAn = new javax.swing.JTextField();
        btnThemTrangThai = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        btnThemMon = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        cboLoaiMonAn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        tblMonAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã món ăn", "Tên món ăn", "Đơn giá", "Loại món", "Hình ảnh", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMonAnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMonAn);

        jLabel5.setText("Thêm loại món ăn:");

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });

        btnThemLoaiMA.setText("Thêm");

        jLabel6.setText("Trạng thái:");

        jLabel1.setText("Mã món ăn:");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Thêm trạng thái:");

        jLabel2.setText("Tên Món ăn:");

        btnThemTrangThai.setText("Thêm");

        jLabel3.setText("Đơn giá:");

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setText("Hình ảnh");
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });

        btnThemMon.setText("Thêm món");
        btnThemMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMonActionPerformed(evt);
            }
        });

        jLabel4.setText("Loại món ăn:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThemMon)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaMonAn)
                    .addComponent(txtTenMonAn)
                    .addComponent(txtDonGia))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(btnLamMoi))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(btnTimKiem))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtThemLoaiMA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboLoaiMonAn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtThemTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemLoaiMA)
                            .addComponent(btnThemTrangThai))))
                .addGap(18, 18, 18)
                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cboLoaiMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtThemLoaiMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemLoaiMA))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtThemTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemTrangThai))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTimKiem)
                            .addComponent(btnLamMoi)
                            .addComponent(btnCapNhat)
                            .addComponent(btnThemMon)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (checkVaLiDate()) {
            capNhatMon();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        resetForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMonAnMouseClicked

        if (evt.getClickCount() == 1) {
            edit();
        }
    }//GEN-LAST:event_tblMonAnMouseClicked

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        timMonAn();
    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void btnThemMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMonActionPerformed
        MonAnDAO dao = new MonAnDAO();
        if (checkVaLiDate()) {
            if (!dao.checkMaMonAn(txtMaMonAn.getText())) {
                MsgBox.alert(this, "Mã món ăn đã tồn tại");
                return;
            }
            themMon();
        }
    }//GEN-LAST:event_btnThemMonActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        ChoosePicture();
    }//GEN-LAST:event_lblHinhAnhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThemLoaiMA;
    private javax.swing.JButton btnThemMon;
    private javax.swing.JButton btnThemTrangThai;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cboLoaiMonAn;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JTable tblMonAn;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaMonAn;
    private javax.swing.JTextField txtTenMonAn;
    private javax.swing.JTextField txtThemLoaiMA;
    private javax.swing.JTextField txtThemTrangThai;
    // End of variables declaration//GEN-END:variables
}
