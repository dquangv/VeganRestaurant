/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

/**
 *
 * @author hoang
 */
import Controller.ChiTietHD_DAO;
import Controller.HoaDonDAO;
import Model.HoaDon;
import Utils.MsgBox;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;

public class HoaDonJPanel extends javax.swing.JPanel {

    /**
     * Creates new form HoaDonJPanel
     */
    HoaDonDAO hdDAO = new HoaDonDAO();
    ChiTietHD_DAO cthdDAO = new ChiTietHD_DAO();
    int row = -1;

    public HoaDonJPanel() {
        initComponents();
        this.themVaoTableHD();
        this.themVaoCbo();
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

    void timTheoMaKH() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        String id = txtTimID.getText();
        List<HoaDon> list = hdDAO.selectByMaKH(Integer.valueOf(id.substring(2)));
        for (HoaDon hd : list) {
            model.addRow(new Object[]{
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
            });
        }
    }

    void thietLapForm(HoaDon hd) {
        txtMaHoaDon.setText("HD" + hd.getMaHoaDon());
        txtMaKH.setText("KH" + hd.getMaKhachHang());
        txtBan.setText("PDB" + String.valueOf(hd.getMaPhieuDatBan()));
        txtNhanVien.setText("NV" + String.valueOf(hd.getMaNhanVien()));
        txtNgayLap.setDate(hd.getNgayLap());
        txtMaGiamGia.setText("KM" + String.valueOf(hd.getMaKhuyenMai()));
        txtTienMon.setText(String.valueOf(Double.sum(hd.getTienMonAn(), 0)));
        txtTienGiam.setText(String.valueOf(hd.getTienGiamKhuyenMai()));
        txtDiemThuong.setText(hd.getTienGiamDiemThuong() + "");
        cboPhuongThuc.setSelectedItem(hd.getPhuongThuc() ? "Thanh Toán" : "Chưa Thanh Toán" + "");
        txtTongTien.setText(hd.getTongTien() + "");
    }

    void thietLapTableCT(Integer hd) {
        DefaultTableModel model = (DefaultTableModel) tblChiTiet.getModel();
        model.setRowCount(0);

        List<Object[]> list = hdDAO.getChiTiet(hd);
        for (Object[] row : list) {
            model.addRow(new Object[]{
                row[0], row[1], row[2], row[3]
            });
        }
    }

    HoaDon layForm() {
        HoaDon hd = new HoaDon();

        hd.setMaHoaDon(Integer.valueOf(txtMaHoaDon.getText().substring(2)));
        hd.setMaPhieuDatBan(Integer.valueOf(txtBan.getText().substring(3)));
        hd.setMaNhanVien(Integer.valueOf(txtNhanVien.getText().substring(2)));
        hd.setNgayLap(txtNgayLap.getDate());
        hd.setMaKhuyenMai(Integer.valueOf(txtMaGiamGia.getText().substring(2)));
        hd.setTienMonAn(Double.parseDouble(txtTienMon.getText()));
        hd.setTienGiamKhuyenMai(Double.parseDouble(txtTienGiam.getText()));
        boolean pt;
        pt = cboPhuongThuc.getSelectedItem().equals("Thanh Toán");
        hd.setPhuongThuc(pt);
        hd.setTongTien(Double.parseDouble(txtTongTien.getText()));

        return hd;
    }

    void chinhSuaForm() {
        String maHD = (String) tblHoaDon.getValueAt(this.row, 0);
        System.out.println();
        HoaDon hd = hdDAO.selectById(maHD.substring(2));

        this.thietLapForm(hd);
        this.thietLapTableCT(Integer.valueOf(maHD.substring(2)));
        tpane.setSelectedIndex(0);

    }

    void themHD() {
        HoaDon hd = layForm();
        try {
            hdDAO.update(hd);
            this.themVaoTableHD();

            MsgBox.alert(this, "Lưu Hóa Đơn Thành Công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Lưu Hóa Đơn Thất Bại");
        }
    }

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
    void themVaoCbo() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboPhuongThuc.getModel();
        model.removeAllElements();
        List<Boolean> list = hdDAO.selectPT();
        for (Boolean pt : list) {
            if (pt == true) {
                cboPhuongThuc.addItem("Thanh Toán");
            } else {
                cboPhuongThuc.addItem("Chưa Thanh Toán");
            }
        }
    }

    //in hoa đơn theo mẫu
    void inHoaDon() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=NhaHangChay_CohesiveStars;encrypt = false", "sa", "songlong")) {
                String reportPath = "src\\View\\HoaDonIn.jrxml";

                String mahd = txtMaHoaDon.getText();

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("P_MaHD", mahd);

                JasperReport jcomp = JasperCompileManager.compileReport(reportPath);
                JasperPrint jprint = JasperFillManager.fillReport(jcomp, parameters, con);
                JasperViewer.viewReport(jprint, false);
                JasperPrintManager.printReport(jprint, true);
            }
        } catch (ClassNotFoundException | SQLException | JRException ex) {
        }
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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtNhanVien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtBan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNgayLap = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaGiamGia = new javax.swing.JTextField();
        lblDiemThuong = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        chkTichDiem = new javax.swing.JCheckBox();
        txtDiemThuong = new javax.swing.JTextField();
        cboPhuongThuc = new javax.swing.JComboBox<>();
        txtTienMon = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTienGiam = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTiet = new javax.swing.JTable();
        btnThanhToan = new javax.swing.JButton();
        btnInHD = new javax.swing.JButton();
        btnXemDG = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        txtTimID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel2.setText("Quản lý hóa đơn");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Mã Hóa Đơn");

        jLabel5.setText("Mã Khách Hàng");

        jLabel4.setText("Ngày Lập");

        jLabel10.setText("Phiếu Bàn");

        jLabel8.setText("Nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNhanVien)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Mã giảm giá");

        jLabel9.setText("Phương Thức");

        lblDiemThuong.setText("Điểm thưởng:");

        jLabel6.setText("Tổng tiền");

        chkTichDiem.setText("Tích điểm");

        jLabel11.setText("Tiền Món ăn");

        jLabel12.setText("Tiền Giảm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(47, 47, 47))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cboPhuongThuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtTongTien)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienMon, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiemThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtDiemThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkTichDiem)))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblDiemThuong)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkTichDiem)
                    .addComponent(txtDiemThuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhuongThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tblChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Món Ăn", "Đơn Giá", "Số lượng", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblChiTiet);

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnInHD.setText("In Hóa Đơn");
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });

        btnXemDG.setText("Xem Đánh Giá");
        btnXemDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemDGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXemDG)
                        .addGap(18, 18, 18)
                        .addComponent(btnInHD)
                        .addGap(18, 18, 18)
                        .addComponent(btnThanhToan))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnInHD)
                    .addComponent(btnXemDG))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpane.addTab("Thanh Toán", jPanel2);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtTimID, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTim)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnTim))
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

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        this.themHD();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed
        this.inHoaDon();
    }//GEN-LAST:event_btnInHDActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblHoaDon.getSelectedRow();
            if (this.row >= 0) {
                this.chinhSuaForm();
                tpane.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnXemDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemDGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXemDGActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        this.timTheoMaKH();
    }//GEN-LAST:event_btnTimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXemDG;
    private javax.swing.JComboBox<String> cboPhuongThuc;
    private javax.swing.JCheckBox chkTichDiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDiemThuong;
    private javax.swing.JTable tblChiTiet;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTabbedPane tpane;
    private javax.swing.JTextField txtBan;
    private javax.swing.JTextField txtDiemThuong;
    private javax.swing.JTextField txtMaGiamGia;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaKH;
    private com.toedter.calendar.JDateChooser txtNgayLap;
    private javax.swing.JTextField txtNhanVien;
    private javax.swing.JTextField txtTienGiam;
    private javax.swing.JTextField txtTienMon;
    private javax.swing.JTextField txtTimID;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
