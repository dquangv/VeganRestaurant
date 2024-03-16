/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.NguyenVatLieuDAO;
import Utils.Auth;
import Utils.MsgBox;
import static Utils.XJdbc.dburl;
import static Utils.XJdbc.password;
import static Utils.XJdbc.username;
import com.sun.jdi.connect.spi.Connection;
import java.beans.Statement;
import java.io.File;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Quang
 */
public class NguyenVatLieu extends javax.swing.JPanel {

    NguyenVatLieuDAO dao = new NguyenVatLieuDAO();
    ArrayList<Model.NguyenVatLieu> list = new ArrayList<Model.NguyenVatLieu>();
    int current = 0;
    DefaultTableModel tblModel;

    public NguyenVatLieu() {
        initComponents();
        initTable();
    }

    public void initTable() {
        tblModel = (DefaultTableModel) tblNVL.getModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã NVL", "Tên NVL", "Số lượng", "Đơn vị tính", "Đơn giá"});
    }

    public void LoadDataToArray() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try ( java.sql.Connection con = DriverManager.getConnection(dburl, username, password);  java.sql.Statement st = con.createStatement();  ResultSet rs = st.executeQuery("SELECT * FROM NguyenVatLieu")) {

                list.clear();
                while (rs.next()) {
                    String manvl = rs.getString(1);
                    String tennvl = rs.getString(2);
                    int soluong = rs.getInt(3);
                    String donvitinh = rs.getString(4);
                    BigDecimal dongia = rs.getBigDecimal(5);

                    Model.NguyenVatLieu nvl = new Model.NguyenVatLieu(manvl, tennvl, soluong, donvitinh, dongia);
                    list.add(nvl);
                }
                fillTable();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("LoadDataToArray Error: " + ex.getMessage());
        }
    }

// Hàm fillTable
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNVL.getModel();
        model.setRowCount(0);

        try {
            List<Model.NguyenVatLieu> list = getListFromDataArray();
            for (Model.NguyenVatLieu nvl : list) {
                Object[] rows = {nvl.getMaNguyenVatLieu(),
                    nvl.getTenNguyenVatLieu(),
                    nvl.getSoLuong(),
                    nvl.getDonViTinh(),
                    nvl.getDonGia()};
                model.addRow(rows);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Data Query Error!");
        }
    }

    private List<Model.NguyenVatLieu> getListFromDataArray() {
        return new ArrayList<>(list);
    }

    public void setForm(int i) {
        Model.NguyenVatLieu nvl = list.get(i);
        txtmaNVL.setText((String) nvl.getMaNguyenVatLieu());
        txttenNVL.setText((String) nvl.getTenNguyenVatLieu());
        txtsoLuong.setText(String.valueOf(nvl.getSoLuong()));
        txtdonViTinh.setText((String) nvl.getDonViTinh());
        txtdonGia.setText(String.valueOf(nvl.getDonGia()));
    }

    public Model.NguyenVatLieu getForm() {
        String maNguyenVatLieu = txtmaNVL.getText().trim();
        String tenNguyenVatLieu = txttenNVL.getText().trim();

        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(txtsoLuong.getText().trim());
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Số lượng phải là số nguyên.");
            return null;
        }

        String donViTinh = txtdonViTinh.getText().trim();

        // Kiểm tra đơn giá nhập vào có đúng định dạng số thực không
        BigDecimal donGia = BigDecimal.ZERO;
        try {
            donGia = new BigDecimal(txtdonGia.getText().trim());
        } catch (NumberFormatException e) {
            // Xử lý khi đơn giá không đúng định dạng số thực
            MsgBox.alert(this, "Đơn giá phải là số thực.");
            return null;
        }

        return new Model.NguyenVatLieu(maNguyenVatLieu, tenNguyenVatLieu, soLuong, donViTinh, donGia);
    }

    public void reset() {
        txtmaNVL.setText("");
        txttenNVL.setText("");
        txtsoLuong.setText("");
        txtdonViTinh.setText("");
        txtdonGia.setText("");
    }

    void insert() {
        if (validateForm()) {
            return;
        }
        Model.NguyenVatLieu nvl = getForm();
        try {
            dao.insert(nvl);
            this.fillTable();
            this.reset();
            MsgBox.alert(this, "Insert successfully");
        } catch (Exception e) {
            MsgBox.alert(this, "Insert unsucessfully!");
        }
    }

    void update() {
        try {
            Model.NguyenVatLieu nvl = getForm();

            if (txtmaNVL.getText().trim().equals("")) {
                MsgBox.alert(this, "Nhập Mã NVL");
                txtmaNVL.requestFocus();
                return;
            }
            NguyenVatLieuDAO nvlDAO = new NguyenVatLieuDAO();
            nvlDAO.update(nvl);

            MsgBox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại! Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }

// validate khi cập nhật
    public boolean validateForm() {
        if (txtmaNVL.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã nguyên vật liệu");
            return false;
        }

        if (txttenNVL.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên nguyên vật liệu");
            return false;
        }

        if (txtsoLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập số lượng");
            return false;
        }

        try {
            // Kiểm tra số lượng có phải là số nguyên không
            Integer.parseInt(txtsoLuong.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên");
            return false;
        }

        if (txtdonViTinh.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập đơn vị tính");
            return false;
        }

        if (txtdonGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập đơn giá");
            return false;
        }

        try {
            // Kiểm tra đơn giá có phải là số thực không
            Double.parseDouble(txtdonGia.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số thực");
            return false;
        }

        return true;
        // nếu nhập đúng các trường trên thì trả về true
    }

    public boolean CheckTrung(String MaSV) {
        Model.NguyenVatLieu nv = getForm();
        java.sql.Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dburl, username, password);

            String sql = "SELECT MaSV FROM STUDENTS WHERE MaSV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, txtmaNVL.getText());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    void first() {
        if (list.size() != 0) {
            current = 0;
            CT4();
            setForm(current);
        }
    }

    void prev() {
        if (list.size() != 0) {
            if (current == 0) {
                last();
            } else {
                current--;
            }
            CT4();
            setForm(current);
        }
    }

    void next() {
        if (list.size() != 0) {
            if (current == list.size() - 1) {
                first();
            } else {
                current++;
            }

            CT4();
            setForm(current);
        }
    }

    void last() {
        if (list.size() != 0) {
            current = list.size() - 1;
            CT4();
            setForm(current);
        }
    }

    private void CT4() {
        tblNVL.setRowSelectionInterval(current, current);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtmaNVL = new javax.swing.JTextField();
        txtdonViTinh = new javax.swing.JTextField();
        txttenNVL = new javax.swing.JTextField();
        txtsoLuong = new javax.swing.JTextField();
        btntinhToan = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btcapNhat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNVL = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtdonGia = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Mã nguyên vật liệu: ");

        jLabel2.setText("Đơn vị tính:");

        jLabel3.setText("Tên nguyên liệu:");

        jLabel4.setText("Số lượng:");

        btntinhToan.setText("Tính toán");
        btntinhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntinhToanActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btcapNhat.setText("Cập nhật");
        btcapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcapNhatActionPerformed(evt);
            }
        });

        tblNVL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNVL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVLMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNVL);

        jLabel5.setText("Đơn giá:");

        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btcapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtmaNVL)
                            .addComponent(txtdonViTinh)
                            .addComponent(txtdonGia, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txttenNVL)
                                    .addComponent(txtsoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                            .addComponent(btntinhToan))
                        .addContainerGap(70, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txtmaNVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttenNVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txtsoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtdonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntinhToan))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btcapNhat))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnPrev, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLast)
                            .addComponent(btnNext))
                        .addComponent(btnFirst)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insert();
        fillTable();
        LoadDataToArray();
        reset();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btcapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcapNhatActionPerformed
        update();
        fillTable();
        LoadDataToArray();
        reset();
    }//GEN-LAST:event_btcapNhatActionPerformed

    private int tinhToanSoLuongConLai(Model.NguyenVatLieu nvl) {
        try {
            // 1. Tạo đối tượng NguyenVatLieuDAO
            NguyenVatLieuDAO nvlDAO = new NguyenVatLieuDAO();

            // 2. Lấy số lượng hiện tại từ cơ sở dữ liệu
            int soLuongHienTai = nvlDAO.getSoLuongByMaNVL(nvl.getMaNguyenVatLieu());

            // 3. Thực hiện tính toán số lượng còn lại
            int soLuongNhap = nvl.getSoLuong();
            int soLuongConLai = soLuongHienTai - soLuongNhap;

            // 4. Cập nhật số lượng mới vào cơ sở dữ liệu (nếu cần)
            // Bạn có thể thực hiện cập nhật số lượng mới ở đây nếu cần
            return soLuongConLai;
        } catch (SQLException e) {
            // Xử lý lỗi kết nối cơ sở dữ liệu
            e.printStackTrace();
            return 0; // Hoặc giá trị mặc định tùy vào yêu cầu của bạn
        }
    }


    private void btntinhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntinhToanActionPerformed
        try {
            // Validate trước khi tính toán
            if (!validateForm()) {
                return;
            }

            // Lấy thông tin từ các trường nhập liệu
            String maNVL = txtmaNVL.getText();
            String tenNVL = txttenNVL.getText();
            int soLuongNhap = Integer.parseInt(txtsoLuong.getText());
            String donViTinh = txtdonViTinh.getText();

            // Thay đổi kiểu dữ liệu từ double sang BigDecimal
            BigDecimal donGia = BigDecimal.ZERO;
            try {
                donGia = new BigDecimal(txtdonGia.getText().trim());
            } catch (NumberFormatException e) {
                // Xử lý khi đơn giá không đúng định dạng số thực
                MsgBox.alert(this, "Đơn giá phải là số thực.");
                return;
            }

            // Giả sử có một đối tượng NguyenVatLieu đã được khởi tạo trước đó
            // Cập nhật thông tin của đối tượng đó với thông tin mới nhập từ giao diện
            Model.NguyenVatLieu nvl = new Model.NguyenVatLieu(maNVL, tenNVL, soLuongNhap, donViTinh, donGia);

            // Thực hiện tính toán số lượng nguyên vật liệu còn lại
            int soLuongConLai = tinhToanSoLuongConLai(nvl);

            // Hiển thị kết quả
            JOptionPane.showMessageDialog(this, "Số lượng nguyên vật liệu còn lại là: " + soLuongConLai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập liệu! Vui lòng kiểm tra lại.");
        }

    }//GEN-LAST:event_btntinhToanActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void tblNVLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVLMouseClicked
        // TODO add your handling code here:      
        int row = tblNVL.getSelectedRow();
        if (row >= 0) {
            LoadDataToArray();
            setForm(row);
        }
    }//GEN-LAST:event_tblNVLMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcapNhat;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btntinhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblNVL;
    private javax.swing.JTextField txtdonGia;
    private javax.swing.JTextField txtdonViTinh;
    private javax.swing.JTextField txtmaNVL;
    private javax.swing.JTextField txtsoLuong;
    private javax.swing.JTextField txttenNVL;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý Nguyên vật liệu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NguyenVatLieu nvlPanel = new NguyenVatLieu();
        frame.getContentPane().add(nvlPanel);
        frame.pack();
        frame.setVisible(true);

        nvlPanel.LoadDataToArray();
        nvlPanel.fillTable();
    }

}
