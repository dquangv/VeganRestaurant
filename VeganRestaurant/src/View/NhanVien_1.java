/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.NhanVienDAO;
import Controller.TaiKhoanDAO;
import Model.NhanVIen;
import Model.TaiKhoan;
import Utils.MsgBox;
import Utils.XImage;
import static Utils.XJdbc.dburl;
import static Utils.XJdbc.password;
import static Utils.XJdbc.username;
import java.awt.Image;
import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VIP
 */
public class NhanVien_1 extends javax.swing.JPanel {

    String pathImage = null, nameImage;
    NhanVienDAO dao = new NhanVienDAO();
    TaiKhoanDAO daoTK = new TaiKhoanDAO();
    private static final String P_EMAIL = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$";
    ArrayList<NhanVIen> list = new ArrayList<NhanVIen>();
    int current = 0;
    String picture = null;
    DefaultTableModel tblModel;

    public NhanVien_1() {
        initComponents();
        this.fillTable();
        rdoNam.setSelected(true);
        rdodangHoatDong.setSelected(true);
        rdonhanVien.setSelected(true);
    }
    
// Hàm fillTable
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblnhanVien.getModel();
        model.setRowCount(0);

        try {
            // Sử dụng danh sách đã được cập nhật từ LoadDataToArray() thay vì truy vấn cơ sở dữ liệu
            list = (ArrayList<NhanVIen>) dao.selectAll();

            for (NhanVIen nv : list) {
                TaiKhoan tk = daoTK.selectByIdMaNV(nv.getMaNhanVien());
                
                String filename = (nv.getHinhAnh() != null) ? new File(nv.getHinhAnh()).getName() : "";
               String tenTaiKhoan = (tk != null) ? tk.getTenTaiKhoan() : "N/A";
                Object[] rows = {
                    nv.getMaNhanVien(),
                    nv.getTenNhanVIen(),
                    nv.isChucVu() ? "Quản lý" : "Nhân viên",
                    nv.getTrangThai(),
                    nv.isGioiTinh() ? "Nữ" : "Nam",
                    nv.getsDT(),
                    nv.getEmail(),
                    tenTaiKhoan,
                    filename
                };

                model.addRow(rows);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Data Query Error!");
        }
    }

// Thêm phương thức để lấy danh sách từ LoadDataToArray()
    public void setForm(NhanVIen nv) {

        txtmaNhanVien.setText(nv.getMaNhanVien());
        txttenNhanVien.setText(nv.getTenNhanVIen());

        // Chức vụ
        if (nv.isChucVu()) {
            rdoquanLy1.setSelected(true);
        } else {
            rdonhanVien.setSelected(true);
        }

        if (nv.getTrangThai().equals("Hoạt động")) {
            rdodangHoatDong.setSelected(true);
        }
        if (nv.getTrangThai().equals("Nghỉ")) {
            rdoNghi.setSelected(true);
        }
        // Giới tính
        if (!nv.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        txtSDT.setText(nv.getsDT());
        txtEmail.setText(nv.getEmail());
        nameImage = nv.getHinhAnh();

//        if (nv.getTenTaikhoan() != null) {
//            lbltenTK.setText(nv.getTenTaikhoan());
//        } else {
//            lbltenTK.setText("");
//        }
        if (nv.getHinhAnh() != null) {
            lblhinhAnh.setToolTipText(nv.getHinhAnh());
            lblhinhAnh.setIcon(XImage.read(nv.getHinhAnh()));

        } //        if (nv.getHinhAnh() != null) {
        //            String picPath = "D:\\FPT\\tai lieu\\DuAn1\\Fpoly-VeganRestaurant\\VeganRestaurant\\Logos\\" + nv.getHinhAnh();
        //
        //            ImageIcon originalIcon = new ImageIcon(picPath);
        //            Image originalImage = originalIcon.getImage();
        //
        //            // Lấy kích thước của JLabel
        //            int labelWidth = lblhinhAnh.getWidth();
        //            int labelHeight = lblhinhAnh.getHeight();
        //
        //            // Scale hình ảnh theo kích thước của JLabel
        //            Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
        //
        //            // Tạo ImageIcon mới từ hình ảnh đã được scale
        //            ImageIcon scaledIcon = new ImageIcon(scaledImage);
        //
        //            lblhinhAnh.setIcon(scaledIcon);
        //            lblhinhAnh.setText(""); // Đảm bảo văn bản trên JLabel được xóa nếu có
        //        }
        else {

            lblhinhAnh.setIcon(null); // Xóa hình ảnh nếu không có
        }
    }

    private NhanVIen getForm() {
        NhanVIen nv = new NhanVIen();
        nv.setMaNhanVien(txtmaNhanVien.getText());
        nv.setTenNhanVIen(txttenNhanVien.getText());
        nv.setChucVu(rdoquanLy1.isSelected());
        System.out.println(nv.isChucVu());
        nv.setTrangThai(rdodangHoatDong.isSelected() ? "Hoạt động" : "Nghỉ");
        nv.setGioiTinh(!rdoNam.isSelected());

        nv.setsDT(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
//        nv.setTenTaikhoan(lbltenTK.getText());
        nv.setHinhAnh(nameImage);
//        System.out.println("AAA"+lblhinhAnh.getToolTipText());
        return nv;
    }
    private TaiKhoan getFormTK() {
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTaiKhoan(lbltenTK.getText());
        tk.setMatKhau("123");
        tk.setVaiTro(rdoquanLy1.isSelected());
        tk.setMaNhanVien(txtmaNhanVien.getText());
        return tk;
    }

    public void reset() {
        txtmaNhanVien.setText("");
        txttenNhanVien.setText("");
        txtEmail.setText("");
        rdoquanLy.setSelected(true);
        rdodangHoatDong.isSelected();
        rdoNam.setSelected(true);
        lblhinhAnh.setIcon(null);
        txtSDT.setText("");
        lbltenTK.setText("");
        nameImage = "";
        txtmaNhanVien.setEditable(true);
        
    }

    void insert() {
        if (!validateForm()) {
            return;
        }
        NhanVIen nv = getForm();
        TaiKhoan tk = getFormTK();
        try {
            dao.insert(nv);
            daoTK.insert(tk);
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

    void cat() {
        String[] ten = txttenNhanVien.getText().split(" ");
        String tenTK = ten[ten.length - 1];
        for (int i = 0; i < ten.length - 1; i++) {
            tenTK += ten[i].split("")[0];
        }

        lbltenTK.setText(chuyenKhongDau(tenTK));
        System.out.println(chuyenKhongDau(tenTK));
    }

    public static String chuyenKhongDau(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'Đ') {
                str = str.replace(ch, 'D');
            }
            if (ch == 'đ') {
                str = str.replace(ch, 'd');
            }
        }
        str = str.replaceAll("\\p{M}", "");

        // vo thanh tung tungvt
        return str;
    }

    void update() {
        if (!validateForm()) {
            return;
        }
        NhanVIen nv = getForm();
        TaiKhoan tk = getFormTK();
        try {
            NhanVienDAO nvDAO = new NhanVienDAO();
            nvDAO.update(nv);
            daoTK.update(tk);
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

//    private void showDetail(int idx) {
//        if (idx >= 0 && idx < list.size()) {
//            NhanVIen nv = list.get(idx);
//            txtmaNhanVien.setText(nv.getMaNhanVien());
//            txttenNhanVien.setText(nv.getTenNhanVIen());
//            rdoquanLy.setSelected(nv.isChucVu());
//            rdodangHoatDong.setSelected(nv.getTrangThai().equals("Hoạt động"));
//            rdoNghi.setSelected(nv.getTrangThai().equals("Nghỉ"));
//            rdoNam.setSelected(nv.isGioiTinh());
//            txtSDT.setText(nv.getsDT());
//            txtEmail.setText(nv.getEmail());
////            lbltenTK.setText(nv.getTenTaikhoan());
//            String pic = nv.getHinhAnh();
//            picture = pic;
//            if (pic != null && !pic.equalsIgnoreCase("none")) {
//                String picPath = "D:\\SOF102_NHAPMONKITHUATPM\\DuLieu\\" + nv.getHinhAnh();
//                Image img = new ImageIcon(picPath).getImage();
//                int width = 180;
//                int height = 200;
//                lblhinhAnh.setText("");
//                lblhinhAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
//            } else {
//                lblhinhAnh.setText("Ảnh 3x4");
//            }
//        }
//    }
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

    void edit() {
        String macd = (String) tblnhanVien.getValueAt(this.current, 0);
        NhanVIen nv = dao.selectById(macd);
        this.setForm(nv);
        txtmaNhanVien.setEditable(false);
        this.CT4();
    }

    void first() {
        this.current = 0;
        this.edit();
    }

    void prev() {
        if (current > 0) {
            this.current--;
            this.edit();
        }
    }

    void next() {
        if (current < tblnhanVien.getRowCount() - 1) {
            this.current++;
            this.edit();
        }
    }

    void last() {
        this.current = tblnhanVien.getRowCount() - 1;
        this.edit();
    }

    void CT4() {
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
        btncapNhat = new javax.swing.JButton();
        lbltenTK = new javax.swing.JLabel();
        txtmaNhanVien = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        txttenNhanVien = new javax.swing.JTextField();
        rdoquanLy1 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

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
        lblhinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblhinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhinhAnhMouseClicked(evt);
            }
        });

        tblnhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Chức vụ", "Trạng thái", "Giới tính", "SDT", "Email", "Tên TK", "Hình ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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

        btncapNhat.setText("Cập nhật");
        btncapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapNhatActionPerformed(evt);
            }
        });

        lbltenTK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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

        txttenNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txttenNhanVienMouseReleased(evt);
            }
        });
        txttenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenNhanVienActionPerformed(evt);
            }
        });
        txttenNhanVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttenNhanVienKeyReleased(evt);
            }
        });

        buttonGroup1.add(rdoquanLy1);
        rdoquanLy1.setText("Quản lý");
        rdoquanLy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoquanLy1ActionPerformed(evt);
            }
        });

        jButton1.setText("Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtmaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txttenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoquanLy1)
                                    .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoNu)
                                    .addComponent(rdonhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoNghi)
                                    .addComponent(rdodangHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbltenTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblhinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addGap(42, 42, 42))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addContainerGap(36, Short.MAX_VALUE)
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
                        .addGap(57, 57, 57)))
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
                        .addComponent(btncapNhat)
                        .addComponent(jButton1)))
                .addContainerGap(51, Short.MAX_VALUE))
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
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("D:\\FPT\\tai lieu\\DuAn1\\Fpoly-VeganRestaurant\\VeganRestaurant\\Logos"));
            FileNameExtensionFilter extPhoto = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
            fc.setFileFilter(extPhoto);
            fc.showOpenDialog(null);
            File file = fc.getSelectedFile();
            if (file != null) {
                pathImage = file.getAbsolutePath();
                nameImage = file.getName();
                ImageIcon img = XImage.read(nameImage);
                picture = file.getName();
                lblhinhAnh.setText("");
//                int width = lblhinhAnh.getWidth();
//                int height = lblhinhAnh.getHeight();
                lblhinhAnh.setIcon(img);
            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblhinhAnhMouseClicked


    private void tblnhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhanVienMouseClicked

        if (evt.getClickCount() == 2) {
            this.current = tblnhanVien.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tblnhanVienMouseClicked

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btncapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapNhatActionPerformed

        update();
        fillTable();

        //        reset();
    }//GEN-LAST:event_btncapNhatActionPerformed

    private void txtmaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaNhanVienActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insert();
        fillTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void rdoquanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoquanLyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoquanLyActionPerformed

    private void rdoquanLy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoquanLy1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoquanLy1ActionPerformed

    private void txttenNhanVienMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttenNhanVienMouseReleased
//        cat();
//        cat();
    }//GEN-LAST:event_txttenNhanVienMouseReleased

    private void txttenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenNhanVienActionPerformed

    private void txttenNhanVienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttenNhanVienKeyReleased
        cat();
    }//GEN-LAST:event_txttenNhanVienKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reset();
    }//GEN-LAST:event_jButton1ActionPerformed


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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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

        nv.fillTable();
    }
}
