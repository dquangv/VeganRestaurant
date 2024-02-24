/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.NhanVienDAO;
import Model.NhanVIen;
import Utils.Auth;
import Utils.MsgBox;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Utils.XJdbc;
import static Utils.XJdbc.dburl;
import static Utils.XJdbc.password;
import static Utils.XJdbc.username;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author VIP
 */
public class NhanVien extends javax.swing.JFrame {

    NhanVienDAO dao = new NhanVienDAO();
    private static final String P_EMAIL = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$";
    ArrayList<NhanVIen> list = new ArrayList<NhanVIen>();
    int current = 0;
    String picture = null;
    DefaultTableModel tblModel;

    public NhanVien() {
        initComponents();
        this.LoadDataToArray();
        this.fillTable();
        setTitle("Quản lý nhân viên");
        setLocationRelativeTo(null);
    }

    public void LoadDataToArray() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try ( java.sql.Connection con = DriverManager.getConnection(dburl, username, password);  java.sql.Statement st = con.createStatement();  ResultSet rs = st.executeQuery("SELECT * FROM NhanVien")) {

                list.clear();
                while (rs.next()) {
                    String manv = rs.getString(1);
                    String hoten = rs.getString(2);
                    boolean ChucVu = rs.getBoolean(3);
                    boolean trangthai = rs.getBoolean(4);
                    boolean gt = rs.getBoolean(5);
                    String sodt = rs.getString(6);
                    String email = rs.getString(7);
                    double luong = rs.getDouble(8);
                    String tentk = rs.getString(9);
                    String hinhanh = rs.getString(10);

                    NhanVIen sv = new NhanVIen(manv, hoten, ChucVu, trangthai, gt, sodt, email, luong, tentk, hinhanh);
                    list.add(sv);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("LoadDataToArray Error: " + ex.getMessage());
        }
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblnhanVien.getModel();
        model.setRowCount(0);

        try {
            // Sử dụng danh sách đã được cập nhật từ LoadDataToArray() thay vì truy vấn cơ sở dữ liệu
            List<NhanVIen> list = getListFromDataArray();
            for (NhanVIen nv : list) {
                String fileName = "";
                String hinhAnh = nv.getHinhAnh();
                if (hinhAnh != null && !hinhAnh.isEmpty()) {
                    fileName = new File(hinhAnh).getName();
                }
                Object[] rows = {nv.getMaNhanVien(),
                    nv.getTenNhanVIen(),
                    nv.isChucVu() ? "Quản lý" : "Nhân viên",
                    nv.isTrangThai() ? "Nghỉ" : "Hoạt động",
                    nv.isGioiTinh() ? "Nữ" : " Nam",
                    nv.getsDT(),
                    nv.getEmail(),
                    nv.getLuong(),
                    nv.getTenTaikhoan(),
                    fileName, // Tên tệp hình ảnh
                    hinhAnh}; // Đường dẫn đầy đủ
                model.addRow(rows);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Data Query Error!");
        }
    }

// Thêm phương thức để lấy danh sách từ LoadDataToArray()
    private List<NhanVIen> getListFromDataArray() {
        return new ArrayList<>(list);
    }

    public void setForm(int i) {
    NhanVIen nv = list.get(i);
    txtmaNhanVien.setText(nv.getMaNhanVien());
    txttenNhanVien.setText(nv.getTenNhanVIen());

    // Chức vụ
    if (nv.isChucVu()) {
        rdoquanLy.setSelected(true);
    } else {
        rdonhanVien.setSelected(true);
    }

    // Trạng thái
    if (nv.isTrangThai()) {
        rdoNghi.setSelected(true);
    } else {
        rdodangHoatDong.setSelected(true);
    }

    // Giới tính
    if (nv.isGioiTinh()) {
        rdoNu.setSelected(true);
    } else {
        rdoNam.setSelected(true);
    }

    txtSDT.setText(nv.getsDT());
    txtEmail.setText(nv.getEmail());
    txtLuong.setText(String.valueOf(nv.getLuong()));

    if (nv.getTenTaikhoan() != null) {
        lbltenTK.setText(nv.getTenTaikhoan());
    } else {
        lbltenTK.setText("");
    }
    lblhinhAnh.setText("");
}

public void reset() {
    txtmaNhanVien.setText("");
    txttenNhanVien.setText("");
    txtEmail.setText("");
    rdoNam.setSelected(true); // Giới tính mặc định
    rdodangHoatDong.setSelected(true); // Trạng thái mặc định
   
    rdoquanLy.setSelected(false); // Chức vụ mặc định
    lblhinhAnh.setText("");
    txtLuong.setText("");
    txtSDT.setText("");
    lbltenTK.setText("");
}


    private NhanVIen getForm() {
        NhanVIen nv = new NhanVIen();
        nv.setMaNhanVien(txtmaNhanVien.getText());
        nv.setTenNhanVIen(txttenNhanVien.getText());
        nv.setChucVu(rdoquanLy.isSelected());
        nv.setChucVu(!rdonhanVien.isSelected());
        nv.setTrangThai(rdoNghi.isSelected());
        nv.setTrangThai(!rdodangHoatDong.isSelected());
        nv.setTrangThai(rdoNam.isSelected());
        nv.setTrangThai(!rdoNu.isSelected());
        nv.setsDT(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
        // Kiểm tra trước khi chuyển đổi
        String luongText = txtLuong.getText();
        if (!luongText.isEmpty()) {
            try {
                double luong = Double.parseDouble(luongText);
                nv.setLuong(luong);
            } catch (NumberFormatException e) {
                System.err.println("Lỗi chuyển đổi số: " + e.getMessage());
            }
        }

        nv.setTenTaikhoan(lbltenTK.getText());
        nv.setHinhAnh(lblhinhAnh.getText());
        return nv;
    }

    void insert() {
        if (validateForm()) {
            return;
        }

        NhanVIen nv = getForm();

        if (txtmaNhanVien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập Mã SV");
            txtmaNhanVien.requestFocus();
            return;
        }

        if (!Auth.isManager() && rdoquanLy.isSelected()) {
            MsgBox.alert(this, "Employee can't add new Manager!");
            return;
        }

        try {
            dao.insert(nv);
            this.LoadDataToArray();
            this.fillTable();
            MsgBox.alert(this, "Insert successfully");
            // Kiểm tra và cập nhật dữ liệu trong bảng tại đây
        } catch (Exception e) {
            MsgBox.alert(this, "Insert unsuccessfully! Error: " + e.getMessage());
            e.printStackTrace(); // Log lỗi để kiểm tra trong quá trình phát triển
        }
    }

    void update() {
        NhanVIen nv = getForm();
        String mk2 = new String(btncapNhat.getText());
        if (txtmaNhanVien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập Mã SV");
            txtmaNhanVien.requestFocus();
            return;
        } else {
            try {
                dao.update(nv);

                this.LoadDataToArray();

                MsgBox.alert(this, "Update successfully");
            } catch (Exception e) {
                MsgBox.alert(this, "Update unsucessfully!");
            }
        }
    }

    // validate khi cập nhật
    public boolean validateForm() {
        if (txtmaNhanVien.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chua nhap ma nhan vien");
            return false;
        }
        if (txttenNhanVien.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chua nhap ho ten!!!");
            return false;
        }
        if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chua nhap SDT!!!");
            return false;
        }
        try {
            Integer.parseInt(txtSDT.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "SDT phai la so!!!");
            return false;
        }

        if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chua nhap Email!!!");
            return false;
        }
        Matcher matcher = Pattern.compile(P_EMAIL).matcher(txtEmail.getText());
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Email sai dinh dang!!!");
            return false;
        }
        return true;
        // neu nhu k va vao tren thi da nhap dung
    }

    public boolean CheckTrung(String MaSV) {
        NhanVIen nv = getForm();
        java.sql.Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dburl, username, password);

            String sql = "SELECT MaSV FROM STUDENTS WHERE MaSV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, txtmaNhanVien.getText());

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
            showDetail(current);
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
            showDetail(current);
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
            showDetail(current);
        }
    }

    void last() {
        if (list.size() != 0) {
            current = list.size() - 1;
            CT4();
            showDetail(current);
        }
    }

    private void CT4() {
        tblnhanVien.setRowSelectionInterval(current, current);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        btnNext = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        txtmaNhanVien = new javax.swing.JTextField();
        txttenNhanVien = new javax.swing.JTextField();
        rdoquanLy = new javax.swing.JRadioButton();
        rdonhanVien = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        rdodangHoatDong = new javax.swing.JRadioButton();
        rdoNghi = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblhinhAnh = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnhanVien = new javax.swing.JTable();
        btncapNhat = new javax.swing.JButton();
        lbltenTK = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        txtmaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaNhanVienActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoquanLy);
        rdoquanLy.setText("Quản lý");
        rdoquanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoquanLyActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdonhanVien);
        rdonhanVien.setText("Nhân viên");
        rdonhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdonhanVienActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup2.add(rdoNu);
        rdoNu.setText("Nữ");
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdodangHoatDong);
        rdodangHoatDong.setText("Đang hoạt động");

        buttonGroup3.add(rdoNghi);
        rdoNghi.setText("Nghỉ");

        jLabel1.setText("Tên nhân viên:");

        jLabel3.setText("Chức vụ:");

        jLabel4.setText("Giới tính:");

        jLabel5.setText("Số điện thoại:");

        jLabel6.setText("Email:");

        jLabel7.setText("Trạng thái:");

        jLabel10.setText("Mã nhân viên:");

        lblhinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblhinhAnh.setText("Hình ảnh");
        lblhinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblhinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhinhAnhMouseClicked(evt);
            }
        });

        tblnhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Chức vụ", "Trạng thái", "Giới tính", "SDT", "Email", "Lương", "Tên TK", "Hình ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblnhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnhanVien);

        btncapNhat.setText("Cập nhật");
        btncapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapNhatActionPerformed(evt);
            }
        });

        lbltenTK.setText("Tên TK");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(44, 44, 44)
                                .addComponent(rdoNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu)
                                .addGap(198, 198, 198))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel10)
                                        .addGap(31, 31, 31)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtmaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(txttenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdoquanLy)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rdonhanVien)
                                        .addGap(61, 61, 61)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6))
                                        .addGap(35, 35, 35))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(txtEmail)
                            .addComponent(rdoNghi, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdodangHoatDong, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLuong, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(lblhinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbltenTK)
                                .addGap(93, 93, 93))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnFirst)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPrev)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLast)
                                .addGap(125, 125, 125)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(358, 358, 358)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(479, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(43, Short.MAX_VALUE)
                        .addComponent(lbltenTK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblhinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdodangHoatDong)
                                .addGap(7, 7, 7)
                                .addComponent(rdoNghi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtmaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdonhanVien)
                                    .addComponent(rdoquanLy)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoNu)
                                    .addComponent(rdoNam)
                                    .addComponent(jLabel4))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnPrev, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLast)
                                    .addComponent(btnNext))
                                .addComponent(btnFirst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnAdd)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btncapNhat)))
                .addGap(35, 35, 35))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(103, 103, 103)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void txtmaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaNhanVienActionPerformed

    private void rdoquanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoquanLyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoquanLyActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void showDetail(int idx) {
        if (idx >= 0 && idx < list.size()) {
            NhanVIen nv = list.get(idx);
            txtmaNhanVien.setText(nv.getMaNhanVien());
            txttenNhanVien.setText(nv.getTenNhanVIen());
            rdoquanLy.setSelected(nv.isChucVu());
            rdodangHoatDong.setSelected(nv.isTrangThai());
            rdoNam.setSelected(nv.isGioiTinh());
            txtSDT.setText(nv.getsDT());
            txtEmail.setText(nv.getEmail());
            txtLuong.setText(String.valueOf(nv.getLuong()));
            lbltenTK.setText(nv.getTenTaikhoan());
            String pic = nv.getHinhAnh();
            picture = pic;
            if (!pic.equalsIgnoreCase("none")) {
                String picPath = "D:\\SOF102_NHAPMONKITHUATPM\\DuLieu\\" + nv.getHinhAnh();
                Image img = new ImageIcon(picPath).getImage();
                int width = 180;
                int height = 200;
                lblhinhAnh.setText("");
                lblhinhAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
            } else {
                lblhinhAnh.setText("Ảnh 3x4");
            }
        }
    }

    private void tblnhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhanVienMouseClicked
        if (evt.getClickCount() == 1) {
            
        int row = tblnhanVien.getSelectedRow();

        if (row >= 0) {
            String tenHinhAnh = (String) tblnhanVien.getValueAt(row, 9);
            String duongDanHinhAnh = "D:\\SOF102_NHAPMONKITHUATPM\\DuLieu" + tenHinhAnh;

            setForm(row);

            LoadDataToArray();

            setForm(row);
        }
        }
    }//GEN-LAST:event_tblnhanVienMouseClicked

    private void btncapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapNhatActionPerformed

        update();
        LoadDataToArray();

        fillTable();
        reset();

    }//GEN-LAST:event_btncapNhatActionPerformed

    private void lblhinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhinhAnhMouseClicked

        try {
            JFileChooser fc = new JFileChooser("D:\\SOF102_NHAPMONKITHUATPM\\DuLieu\\");
            fc.showOpenDialog(null);
            File file = fc.getSelectedFile();
            Image img = ImageIO.read(file);
            picture = file.getName();
            lblhinhAnh.setText("");
            int width = lblhinhAnh.getWidth();
            int height = lblhinhAnh.getHeight();
            lblhinhAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblhinhAnhMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insert();
        LoadDataToArray();

        fillTable();
        reset();
    }//GEN-LAST:event_btnAddActionPerformed

    private void rdonhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdonhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdonhanVienActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btncapNhat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblhinhAnh;
    private javax.swing.JLabel lbltenTK;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghi;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdodangHoatDong;
    private javax.swing.JRadioButton rdonhanVien;
    private javax.swing.JRadioButton rdoquanLy;
    private javax.swing.JTable tblnhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtmaNhanVien;
    private javax.swing.JTextField txttenNhanVien;
    // End of variables declaration//GEN-END:variables
}
