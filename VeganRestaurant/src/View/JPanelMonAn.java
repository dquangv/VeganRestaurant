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
import java.sql.ResultSet;

/**
 *
 * @author Quang
 */
public class JPanelMonAn extends javax.swing.JPanel {

    private int row = -1;
    MonAnDAO madao = new MonAnDAO();

    /**
     * Creates new form MonAnPanel
     */
    public JPanelMonAn() {
        initComponents();
        fillCboLoai();
        fillCboTrangThai();
        displayMonAn();
        
          ImageIcon iconuser = new ImageIcon("Logos/plus.png");
        btnThemMon.setIcon(iconuser);
        ImageIcon iconuser1 = new ImageIcon("Logos/edit.png");
        btnCapNhat.setIcon(iconuser1);
        ImageIcon iconuser2 = new ImageIcon("Logos/reset.png");
        btnLamMoi.setIcon(iconuser2);
        ImageIcon iconuser3 = new ImageIcon("Logos/search.png");
        btnTimKiem.setIcon(iconuser3);
    }

    public void fillCboLoai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiMonAn.getModel();

        model.removeAllElements();

//        MonAnDAO dao = new MonAnDAO(new XJdbc());
//        try {
//            List<String> list = dao.selectCBMonAn();
//
//            for (String monAn : list) {
//                model.addElement(monAn);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        model.addElement("Bún");
        model.addElement("Cơm");
        model.addElement("Khai vị");
        model.addElement("Khác");
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
            Object row[] = {monAn.getMaMonAn(), monAn.getTenMonAn(), monAn.getDonGia(), monAn.getSoLuong(), madao.getTenLoaiMon(monAn.getMaLoaiMon()), monAn.getHinhAnh(), monAn.getTrangThai()};

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
        int maMonAn = Integer.parseInt(txtMaMonAn.getText());
        String tenMonAn = txtTenMonAn.getText();
        Double donGia = Double.parseDouble(txtDonGia.getText());
//        String loaiMon = (String) cboLoaiMonAn.getSelectedItem();
        String trangThai = (String) cboTrangThai.getSelectedItem();

        MonAn monAn = new MonAn();
        monAn.setMaMonAn(maMonAn);
        monAn.setTenMonAn(tenMonAn);
        monAn.setDonGia(donGia);
        monAn.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        monAn.setMaLoaiMon(madao.getMaLoaiMon((String) cboLoaiMonAn.getSelectedItem()));
        monAn.setTrangThai(trangThai);
        monAn.setHinhAnh(lblHinhAnh.getToolTipText());

        MonAnDAO dao = new MonAnDAO();

        dao.themMonAn(monAn);
        JOptionPane.showMessageDialog(this, "Thêm món ăn thành công");
        displayMonAn();
    }

    public void capNhatMon() {
        int maMonAn = Integer.parseInt(txtMaMonAn.getText());
        String tenMonAn = txtTenMonAn.getText();
        Double donGia = Double.parseDouble(txtDonGia.getText());
        String loaiMon = (String) cboLoaiMonAn.getSelectedItem();
        String trangThai = (String) cboTrangThai.getSelectedItem();
        String hinh = lblHinhAnh.getToolTipText();

        MonAn monAn = new MonAn();
        monAn.setMaMonAn(maMonAn);
        monAn.setTenMonAn(tenMonAn);
        monAn.setDonGia(donGia);
        monAn.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
//        monAn.setLoaiMonAn(loaiMon);
        monAn.setMaLoaiMon(madao.getMaLoaiMon((String) cboLoaiMonAn.getSelectedItem()));
        monAn.setTrangThai(trangThai);
        monAn.setHinhAnh(hinh);

        MonAnDAO dao = new MonAnDAO();
        dao.chinhSuaMonAn(monAn);

        JOptionPane.showMessageDialog(this, "Cập nhật món ăn thành công");

        displayMonAn();
    }

    public void resetForm() {
        String sql = "select top 1 mamonan from monan order by mamonan desc;";

        try (ResultSet rs = XJdbc.executeQuery(sql)) {
            while (rs.next()) {
                txtMaMonAn.setText(String.valueOf(rs.getInt(1) + 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        txtMaMonAn.setText("");
        txtTenMonAn.setText("");
        txtDonGia.setText("");
        txtSoLuong.setText("");
        cboLoaiMonAn.setSelectedIndex(0);
        cboTrangThai.setSelectedIndex(0);

//        txtThemLoaiMA.setText("");
//        txtThemTrangThai.setText("");
        lblHinhAnh.setIcon(null);
        lblHinhAnh.setText("Hình Ảnh");
        lblHinhAnh.setToolTipText(null);
    }

    void ChoosePicture() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("\\Fpoly-VeganRestaurant\\VeganRestaurant\\Logos"));
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
        txtMaMonAn.setText(String.valueOf(ma.getMaMonAn()));
        txtTenMonAn.setText(ma.getTenMonAn());
        txtDonGia.setText(ma.getDonGia() + "");
        txtSoLuong.setText(String.valueOf(ma.getSoLuong()));
        cboLoaiMonAn.setSelectedItem(madao.getTenLoaiMon(ma.getMaLoaiMon()));
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
                ma.getSoLuong(),
                madao.getTenLoaiMon(ma.getMaLoaiMon()),
                ma.getHinhAnh(),
                ma.getTrangThai()});
        }
    }

    void edit() {
        row = tblMonAn.getSelectedRow();
        MonAnDAO dao = new MonAnDAO();
        int ma = (int) tblMonAn.getValueAt(this.row, 0);
        MonAn cd = dao.getMonAnByID(ma);
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
        btnTimKiem = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        txtMaMonAn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenMonAn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        btnThemMon = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();

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
                "Mã món ăn", "Tên món ăn", "Đơn giá", "Số lượng", "Loại món", "Hình ảnh", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });

        jLabel6.setText("Trạng thái:");

        jLabel1.setText("Mã món ăn:");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMaMonAn.setEditable(false);

        jLabel2.setText("Tên Món ăn:");

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

        jLabel8.setText("Số lượng:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(64, 64, 64)
                                .addComponent(txtSoLuong))
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(btnLamMoi))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(btnTimKiem))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cboLoaiMonAn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(102, 102, 102)
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtMaMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cboLoaiMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTimKiem)
                            .addComponent(btnLamMoi)
                            .addComponent(btnCapNhat)
                            .addComponent(btnThemMon)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
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
        displayMonAn();
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
            resetForm();
        }
    }//GEN-LAST:event_btnThemMonActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        ChoosePicture();
    }//GEN-LAST:event_lblHinhAnhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThemMon;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cboLoaiMonAn;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JTable tblMonAn;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaMonAn;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenMonAn;
    // End of variables declaration//GEN-END:variables
}
