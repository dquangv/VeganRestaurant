/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.NhanVienDAO;
import Model.NhanVIen;
import Utils.MsgBox;
import static Utils.XJdbc.dburl;
import static Utils.XJdbc.password;
import static Utils.XJdbc.username;
import java.awt.Image;
import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VIP
 */
public class NhanVien_1 extends javax.swing.JPanel {

    NhanVienDAO dao = new NhanVienDAO();
    private static final String P_EMAIL = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$";
    ArrayList<NhanVIen> list = new ArrayList<NhanVIen>();
    int current = 0;
    String picture = null;
    DefaultTableModel tblModel;

    public NhanVien_1() {
        initComponents();
        this.LoadDataToArray();
        this.fillTable();

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

// Hàm fillTable
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblnhanVien.getModel();
        model.setRowCount(0);

        try {
            // Sử dụng danh sách đã được cập nhật từ LoadDataToArray() thay vì truy vấn cơ sở dữ liệu
            List<NhanVIen> list = getListFromDataArray();
            for (NhanVIen nv : list) {
                String fileName = new File(nv.getHinhAnh()).getName();
                Object[] rows = {nv.getMaNhanVien(),
                    nv.getTenNhanVIen(),
                    nv.isChucVu() ? "Quản lý" : "Nhân viên",
                    nv.isTrangThai() ? "Nghỉ" : "Hoạt động",
                    nv.isGioiTinh() ? "Nữ" : "Nam",
                    nv.getsDT(),
                    nv.getEmail(),
                    nv.getLuong(),
                    nv.getTenTaikhoan(),
                    fileName, // Tên tệp hình ảnh
                    nv.getHinhAnh()}; // Đường dẫn đầy đủ
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
        rdodangHoatDong.setSelected(!nv.isTrangThai());
        rdoNghi.setSelected(nv.isTrangThai());

        // Giới tính
        if (!nv.isGioiTinh()) {
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

    private NhanVIen getForm() {
        NhanVIen nv = new NhanVIen();
        nv.setMaNhanVien(txtmaNhanVien.getText());
        nv.setTenNhanVIen(txttenNhanVien.getText());
        nv.setChucVu(rdoquanLy.isSelected());
        nv.setTrangThai(rdodangHoatDong.isSelected());
        nv.setGioiTinh(rdoNam.isSelected());

        nv.setsDT(txtSDT.getText());
        nv.setEmail(txtEmail.getText());

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

    public void reset() {
        txtmaNhanVien.setText("");
        txttenNhanVien.setText("");
        txtEmail.setText("");

        rdoNam.isSelected();
        rdoNu.isSelected();
        rdodangHoatDong.isSelected();
        rdoNghi.isSelected();
        rdoquanLy.isSelected();
        rdonhanVien.isSelected();
        lblhinhAnh.setText("");
        txtLuong.setText("");
        txtSDT.setText("");
        lbltenTK.setText("");
    }

    void insert() {
        if (validateForm()) {
            return;
        }
        NhanVIen nv = getForm();
        try {
            dao.insert(nv);
            LoadDataToArray();
            fillTable();
            this.reset();
            // Thêm thông báo thành công
            JOptionPane.showMessageDialog(this, "Insert successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            // Hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Insert unsuccessfully! Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void update() {
        if (validateForm()) {
            return;
        }
        NhanVIen nv = getForm();
        try {
            NhanVienDAO nvDAO = new NhanVienDAO();
            nvDAO.update(nv);
            LoadDataToArray();
            fillTable();
            this.reset();
            // Thêm thông báo thành công
            JOptionPane.showMessageDialog(this, "Update successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            // Hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Update unsuccessfully! Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    public boolean CheckTrung(String maNV) {
        NhanVIen nv = getForm();
        java.sql.Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dburl, username, password);

            String sql = "SELECT MaNhanVien FROM NhanVien WHERE MaNhanVien = ?";
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
            showDetail(current);
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
            showDetail(current);
            setForm(current);
        }
    }

    void last() {
        if (list.size() != 0) {
            current = list.size() - 1;
            CT4();
            showDetail(current);
            setForm(current);
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

        rdoquanLy = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
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
        btnNext = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblhinhAnh = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnhanVien = new javax.swing.JTable();
        btnPrev = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btncapNhat = new javax.swing.JButton();
        txtLuong = new javax.swing.JTextField();
        lbltenTK = new javax.swing.JLabel();
        txtmaNhanVien = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        txttenNhanVien = new javax.swing.JTextField();
        rdoquanLy1 = new javax.swing.JRadioButton();

        rdoquanLy.setText("Quản lý");
        rdoquanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoquanLyActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdonhanVien);
        rdonhanVien.setText("Nhân viên");

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
        rdoNghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNghiActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên nhân viên:");

        jLabel3.setText("Chức vụ:");

        jLabel4.setText("Giới tính:");

        jLabel5.setText("Số điện thoại:");

        jLabel6.setText("Email:");

        jLabel7.setText("Trạng thái:");

        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jLabel10.setText("Mã nhân viên:");

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

        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        jLabel9.setText("Lương");

        btncapNhat.setText("Cập nhật");
        btncapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapNhatActionPerformed(evt);
            }
        });

        lbltenTK.setText("Tên TK");

        txtmaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaNhanVienActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoquanLy1);
        rdoquanLy1.setText("Quản lý");
        rdoquanLy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoquanLy1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(btnFirst)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrev)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLast)
                .addGap(107, 107, 107)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncapNhat)
                .addGap(113, 231, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNu))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtmaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txttenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdoquanLy1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdonhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdoNghi)
                                            .addComponent(rdodangHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(lblhinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbltenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbltenTK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblhinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtmaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoquanLy1)
                                    .addComponent(rdonhanVien)
                                    .addComponent(jLabel7)
                                    .addComponent(rdodangHoatDong))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(rdoNam)
                                            .addComponent(rdoNu)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(rdoNghi))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnFirst)
                        .addComponent(btnNext)
                        .addComponent(btnPrev)
                        .addComponent(btnLast))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btncapNhat)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void rdoNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNghiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNghiActionPerformed

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

    private void lblhinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhinhAnhMouseClicked

        try {
            JFileChooser fc = new JFileChooser("D:\\FPT\\tai lieu\\nhap mon ki thuat phan mem\\Ron\\DuLieu\\");
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

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btncapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapNhatActionPerformed

        update();
        fillTable();
        LoadDataToArray();
        //        reset();
    }//GEN-LAST:event_btncapNhatActionPerformed

    private void txtmaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaNhanVienActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insert();
        LoadDataToArray();

        fillTable();
        reset();
    }//GEN-LAST:event_btnAddActionPerformed

    private void rdoquanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoquanLyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoquanLyActionPerformed

    private void rdoquanLy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoquanLy1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoquanLy1ActionPerformed


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
    private javax.swing.JRadioButton rdoquanLy1;
    private javax.swing.JTable tblnhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtmaNhanVien;
    private javax.swing.JTextField txttenNhanVien;
    // End of variables declaration//GEN-END:variables
     public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý Nhân viên");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NhanVien_1 nv = new NhanVien_1();
        frame.getContentPane().add(nv);
        frame.pack();
        frame.setVisible(true);

        nv.LoadDataToArray();
        nv.fillTable();
    }
}
