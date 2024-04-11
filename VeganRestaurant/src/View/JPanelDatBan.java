/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.ChiTietDatBan_DAO;
import Controller.DatBanDao;
import Controller.KhachHangDAO;
import Controller.KhachHangDBDao;
import Controller.PhieuDatBanDao;
import Model.ChiTietDatBan;
import Model.PhieuDatBan;
import Utils.MsgBox;
import static View.JPanelTang1.colorBan;
import static View.JPanelTang1.listSoBan;
import static View.JPanelTang1.timButtonByMaBan;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.bridj.objc.NSNumber;
import java.sql.*;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Võ Thanh Tùng
 */
public class JPanelDatBan extends javax.swing.JPanel {

    static DatBanDao dBDao = new DatBanDao();
    static KhachHangDBDao khDBDAO = new KhachHangDBDao();
    static KhachHangDAO khDAO = new KhachHangDAO();
    static PhieuDatBanDao pdbDAO = new PhieuDatBanDao();
    static ChiTietDatBan_DAO ctdbdao = new ChiTietDatBan_DAO();

//    public static List<JButton> list = new ArrayList<>();
//   
//    public static void thayDoiMauButton(JButton btn[]) {
//        for (int i = 0; i < 36; i++) {
//            if (list.contains(btn[i])) {
//                btn[i].setBackground(Color.yellow);
//            } else {
//                btn[i].setBackground(UIManager.getColor("Button.backgrund"));
//            }
//        }
//    }
//    public static void setButton(int maBan){
//         JButton button = timButtonByMaBan(maBan);
//          if (button != null) { 
//        if (list.contains(button)) {
//            list.remove(button);
//            System.out.println("Đã xóa bàn " + maBan);
//        } else {
//            list.add(button);
//            System.out.println("Đã thêm bàn " + maBan);
//        }
//        thayDoiMauButton(new JButton[]{button}); // Truyền vào mảng chứa button
//    } else {
//        System.out.println("Không tìm thấy button với mã bàn " + maBan);
//    }
//        for (JButton jButton : list) {
//            jButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JButton clickedButton = (JButton) e.getSource();
//                if (!list.contains(clickedButton)) {
//                    list.add(clickedButton);
//                    System.out.println("Da them " + clickedButton.getText());
//                } else {
//                    list.remove(clickedButton);
//                    System.out.println("Da xoa " + clickedButton.getText());
//                }
//                updateButtonBackgrounds(new JButton[]{jButton});
//                System.out.println(list);
//            }
//        });
//        }
//    }
    /**
     * Creates new form NewJPanel
     */
    public JPanelDatBan() {
        initComponents();

        jPanel3.removeAll();
        jPanel3.add(new JPanelTang2());

        jPanel3.add(new JPanelTang3());

        jPanel3.removeAll();
        jPanel3.add(new JPanelTang1());
        jPanel3.updateUI();
        jPanel3.setLayout(new FlowLayout());

        fillToTable();

        fillComboBoxTang();
    }

    public void updateRedColorForButton1stFloor() {
        jPanel3.removeAll();
        jPanel3.add(new JPanelTang1());
//                    JPanelTang1.thayDoiMauButton(JPanelTang1.listBT);
        jPanel3.updateUI();
        jPanel3.setLayout(new FlowLayout());

//                    DoiTrangThaiBanDaDatTheoThoiGian();
        for (int i = 1; i <= 12; i++) {
            JButton btn = JPanelTang1.timButtonByMaBan(i);
            PhieuDatBan pdb = pdbDAO.getPhieuDatBanTheoThoiGian(i);

            if (btn.getBackground() != Color.GREEN) {
                if (pdb != null) {
                    if (pdb.getThoiGianDat().getTime() - new Timestamp(System.currentTimeMillis()).getTime() <= 3600000) {
                        btn.setBackground(Color.red);
//                                    System.out.println(pdb.getThoiGianDat());
//                JPanelTang1.showDiaLogTrangThaiDaDat(listSoBan);
                    }
                }
            }
        }
    }

    public void updateRedColorForButton2ndFloor() {
        jPanel3.removeAll();
        jPanel3.add(new JPanelTang2());
//                    JPanelTang1.thayDoiMauButton(JPanelTang1.listBT);
        jPanel3.updateUI();
        jPanel3.setLayout(new FlowLayout());

//                    DoiTrangThaiBanDaDatTheoThoiGian();
        for (int i = 13; i <= 24; i++) {
            JButton btn = JPanelTang2.timButtonByMaBan(i);
            PhieuDatBan pdb = pdbDAO.getPhieuDatBanTheoThoiGian(i);

            if (btn.getBackground() != Color.GREEN) {
                if (pdb != null) {
                    if (pdb.getThoiGianDat().getTime() - new Timestamp(System.currentTimeMillis()).getTime() <= 3600000) {
                        btn.setBackground(Color.red);
//                    JPanelTang2.showDiaLogTrangThaiDaDat(listSoBan);
                    }
                }
            }
        }
    }

    public void updateRedColorForButton3rdFloor() {
        jPanel3.removeAll();
        jPanel3.add(new JPanelTang3());
        jPanel3.updateUI();
        jPanel3.setLayout(new FlowLayout());

//                    DoiTrangThaiBanDaDatTheoThoiGian();
        for (int i = 25; i <= 36; i++) {
            JButton btn = JPanelTang3.timButtonByMaBan(i);
//            System.out.println(btn.getBackground());
            PhieuDatBan pdb = pdbDAO.getPhieuDatBanTheoThoiGian(i);
            if (btn.getBackground() != Color.GREEN) {
                if (pdb != null) {
                    if (pdb.getThoiGianDat().getTime() - new Timestamp(System.currentTimeMillis()).getTime() <= 3600000) {
                        btn.setBackground(Color.red);
//                JPanelTang3.showDiaLogTrangThaiDaDat(listSoBan);
                    }
                }
            }
        }
    }

//    public void capNhatTable() {
//        class TrangThaitable extends Thread {
//
//            @Override
//            public void run() {
//                while (true) {
//                    fillToTable();
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        }
//
//        TrangThaitable thread = new TrangThaitable();
//        thread.start();
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbTang = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatBan = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtTimKIem = new javax.swing.JTextField();
        btnDatBan = new javax.swing.JButton();
        btnPhucVu = new javax.swing.JButton();
        btnBaoTri = new javax.swing.JButton();
        btnGoiMon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        cbTang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbTang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tầng 1" }));
        cbTang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTangActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/phieu.png"))); // NOI18N

        jLabel2.setBackground(new java.awt.Color(51, 255, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/da dat.png"))); // NOI18N
        jLabel2.setText("Đã đặt");
        jLabel2.setFocusTraversalPolicyProvider(true);

        jLabel4.setBackground(new java.awt.Color(51, 255, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Dang phuc vu.png"))); // NOI18N
        jLabel4.setText("Đang phục vụ");
        jLabel4.setFocusTraversalPolicyProvider(true);

        jLabel3.setBackground(new java.awt.Color(51, 255, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/trong.png"))); // NOI18N
        jLabel3.setText("Trống");
        jLabel3.setFocusTraversalPolicyProvider(true);

        jLabel5.setBackground(new java.awt.Color(51, 255, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bao tri.png"))); // NOI18N
        jLabel5.setText("Bảo trì");
        jLabel5.setFocusTraversalPolicyProvider(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        tblDatBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiêu đặt bàn", "Mã bàn", "Tên khách hàng", "Số điện thoai", "Thời gian", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDatBan);
        if (tblDatBan.getColumnModel().getColumnCount() > 0) {
            tblDatBan.getColumnModel().getColumn(0).setResizable(false);
            tblDatBan.getColumnModel().getColumn(1).setMinWidth(50);
        }

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Danh sách đặt bàn");

        txtTimKIem.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        txtTimKIem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKIemActionPerformed(evt);
            }
        });
        txtTimKIem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKIemKeyReleased(evt);
            }
        });

        btnDatBan.setText("Đặt bàn ");
        btnDatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatBanActionPerformed(evt);
            }
        });

        btnPhucVu.setText("Phục vụ");
        btnPhucVu.setPreferredSize(new java.awt.Dimension(94, 23));
        btnPhucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhucVuActionPerformed(evt);
            }
        });

        btnBaoTri.setText("Bảo trì");
        btnBaoTri.setPreferredSize(new java.awt.Dimension(94, 23));
        btnBaoTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaoTriActionPerformed(evt);
            }
        });

        btnGoiMon.setText("Gộp bàn");
        btnGoiMon.setPreferredSize(new java.awt.Dimension(94, 23));
        btnGoiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoiMonActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Chuyển bàn");

        jButton1.setText("Huỷ bàn");
        jButton1.setPreferredSize(new java.awt.Dimension(94, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPhucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBaoTri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGoiMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                            .addComponent(txtTimKIem))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbTang, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDatBan)
                            .addComponent(btnPhucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGoiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBaoTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThanhToan)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTimKIem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbTangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTangActionPerformed
        JComboBox<String> comboBox = (JComboBox<String>) evt.getSource();
        if (comboBox == cbTang && comboBox.getSelectedItem() != null) {

            String selectedTang = (String) comboBox.getSelectedItem();
            switch (selectedTang) {
                case "Tầng 1":
                    updateRedColorForButton1stFloor();
                    break;
                case "Tầng 2":
                    updateRedColorForButton2ndFloor();
                    break;
                case "Tầng 3":
                    updateRedColorForButton3rdFloor();
                    break;
                // Thêm các case khác tương ứng với các tầng khác
                default:
                    break;
            }

        }

//        DoiTrangThaiBanDaDatTheoThoiGian();
    }//GEN-LAST:event_cbTangActionPerformed

    private void txtTimKIemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKIemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKIemActionPerformed

    private void txtTimKIemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKIemKeyReleased
        fillToTable();
    }//GEN-LAST:event_txtTimKIemKeyReleased

    private void btnPhucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhucVuActionPerformed
        // TODO add your handling code here:
        if (JPanelTang1.listSo.isEmpty()) {
            MsgBox.alert(this, "Bạn chưa chọn bàn để phục vụ");
        } else {
            boolean checkColor = false;
            int checkRedButton = 0;
            int checkPinkButton = 0;
            int checkBlackGreenButton = 0;
            JFrame parentFrame = (JFrame) SwingUtilities.getRoot(this); // Tìm JFrame cha của JPanel

            for (Integer so : JPanelTang1.listSo) {
//                System.out.println("");
//                System.out.print(" " + so);

                if (checkBlackGreenButton == 0) {

                    for (Object[] color : colorBan) {
                        int maBAN = (int) color[0];
                        Color mauBan = (Color) color[1];
                        if (so == maBAN) {
                            if (mauBan == Color.BLACK) {
                                JOptionPane.showMessageDialog(this, "Bàn này đang được bảo trì.\nVui lòng chọn bàn khác.");
                                checkColor = true;
                                checkBlackGreenButton += 1;
                                break;
                            } else if (mauBan == Color.GREEN) {
                                JOptionPane.showMessageDialog(this, "Bàn này đang phục vụ.\nVui lòng chọn bàn khác.");
                                checkColor = true;
                                checkBlackGreenButton += 1;

                                break;
                            } else if (mauBan == Color.red) {
                                checkRedButton += 1;
//                            checkColor = true;
                            } else if (mauBan == Color.pink) {
                                checkPinkButton += 1;
                            }
//                        } else if (mauBan == Color.PINK || mauBan == Color.red) {
//                            checkColor = false;
//                        }
                        }
                    }
                }

//                if (!checkColor) {
//                    for (Object[] color : colorBan) {
//                        int maBAN = (int) color[0];
//                        Color mauBan = (Color) color[1];
//                        if (so == maBAN) {
//                            if (mauBan == Color.PINK) {
//                                JDiaLogDatBan dialog = new JDiaLogDatBan(parentFrame, true); // Tạo dialog với JFrame cha
//                                MsgBox.alert(this, "Bất đầu phục vụ");
//                                dialog.thayDoiTrangThaiBatDauPhucVu(JPanelTang1.listSo);
//
//                                int maKHMax = insertKHnull();
//                                int maPDBMax = insertPDB(maKHMax);
//
//                                for (Integer maBan : JPanelTang1.listSo) {
//                                    insert(maKHMax, maPDBMax, maBan);
//                                }
//
//                                JPanelDatBan.fillToTable();
////                            JPanelTang1.listSo.clear();
////                            JPanelTang1.listBT.clear();
//                            } else if (mauBan == Color.red) {
//                                System.out.println("b");
//                            }
//                        }
//                    }
//                }
//            JDiaLogDatBan dialog = new JDiaLogDatBan(parentFrame, true); // Tạo dialog với JFrame cha
//            MsgBox.alert(this, "Bất đầu phục vụ");
//            dialog.thayDoiTrangThaiBatDauPhucVu(JPanelTang1.listSo);
//            
//            int maKHMax = insertKHnull();
//            int maPDBMax = insertPDB(maKHMax);
//            
//            for (Integer maBan : JPanelTang1.listSo) {
//                insert(maKHMax, maPDBMax, maBan);
//            }
//            
//            JPanelDatBan.fillToTable();
//            JPanelTang1.TrangThaiBan();
//            JPanelTang2.TrangThaiBan();
//            JPanelTang3.TrangThaiBan();
//            JPanelTang1.listSo.clear();
//            JPanelTang1.listBT.clear();
            }
//            System.out.println(checkColor);

            if (!checkColor) {
                if (checkPinkButton == 0) {
                    for (Integer maBan : JPanelTang1.listSo) {
                        dBDao.updateTrangThai(DatBanDao.DANG_PHUC_VU, String.valueOf(maBan));
                        MsgBox.alert(this, "Bắt đầu phục vụ");
                        JButton btn = JPanelTang1.timButtonByMaBan(maBan);
                        btn.setBackground(Color.green);
                        fillToTable();
                    }
//                    JPanelTang1.TrangThaiBan();
//                    JPanelTang2.TrangThaiBan();
//                    JPanelTang3.TrangThaiBan();

                    JPanelTang1.listSo.clear();
                    JPanelTang1.listBT.clear();
                } else if (checkRedButton == 0) {
                    for (Object[] color : colorBan) {
                        int maBAN = (int) color[0];
                        Color mauBan = (Color) color[1];
                        if (mauBan == Color.PINK) {
                            JDiaLogDatBan dialog = new JDiaLogDatBan(parentFrame, true); // Tạo dialog với JFrame cha
                            MsgBox.alert(this, "Bắt đầu phục vụ");
                            dialog.thayDoiTrangThaiBatDauPhucVu(JPanelTang1.listSo);

                            int maKHMax = insertKHnull();
                            int maPDBMax = insertPDB(maKHMax);

                            for (Integer maBan : JPanelTang1.listSo) {
                                insert(maKHMax, maPDBMax, maBan);
                                JButton btn = JPanelTang1.timButtonByMaBan(maBan);
                                btn.setBackground(Color.green);
                            }

                            JPanelDatBan.fillToTable();
                            JPanelTang1.listSo.clear();
                            JPanelTang1.listBT.clear();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Bàn này sắp được phục vụ.\nVui lòng chọn bàn khác.");
                }
            }
        }
    }//GEN-LAST:event_btnPhucVuActionPerformed

    private void btnDatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatBanActionPerformed
        if (JPanelTang1.listSo.isEmpty()) {
            MsgBox.alert(this, "Bạn chưa chọn bàn để đặt");
        } else {

            JFrame parentFrame = (JFrame) SwingUtilities.getRoot(this); // Tìm JFrame cha của JPanel
//            for (Integer so : JPanelTang1.listSo) {
//                System.out.println("");
//                System.out.print(" " + so);
//            }

            JDiaLogNhapThongTin dialog = new JDiaLogNhapThongTin(parentFrame, true); // Tạo dialog với JFrame cha
            dialog.setBan(JPanelTang1.listSo);

            dialog.setVisible(true);
        }
    }//GEN-LAST:event_btnDatBanActionPerformed

    private void btnBaoTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaoTriActionPerformed
        JPanelTang1.listSo.clear();
        JPanelTang1.listBT.clear();
    }//GEN-LAST:event_btnBaoTriActionPerformed

    private void btnGoiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiMonActionPerformed
        if (JPanelTang1.listSo.isEmpty()) {
            MsgBox.alert(this, "Bạn chưa chọn bàn để gọi món");
        } else {
            if (JPanelTang1.listSo.size() == 1) {
//            String maBan = lbmaBan.getText().substring(5);
//            System.out.println(maBan);
//                this.setVisible(false);
                String maBan = String.valueOf(JPanelTang1.listSo.get(0));
                PhieuDatBanDao pdb = new PhieuDatBanDao();
                int MaPDB = pdb.SelectMaPDB(Integer.parseInt(maBan));
                boolean foundButton = false;
                JButton button;
                button = JPanelTang1.timButtonByMaBan(Integer.parseInt(maBan));
                if (button != null) {
                    button.setToolTipText(MaPDB + "");
                    MaPDB = Integer.parseInt(button.getToolTipText());
                    foundButton = true;
                }

                if (!foundButton) {
                    button = JPanelTang2.timButtonByMaBan(Integer.parseInt(maBan));
                    if (button != null) {
                        button.setToolTipText(MaPDB + "");
                        MaPDB = Integer.parseInt(button.getToolTipText());
                        foundButton = true;
                    }
                }

                if (!foundButton) {
                    button = JPanelTang3.timButtonByMaBan(Integer.parseInt(maBan));
                    if (button != null) {
                        button.setToolTipText(MaPDB + "");
                        MaPDB = Integer.parseInt(button.getToolTipText());
                    }
                }

                GoiMon dialogGoiMon = new GoiMon();
                JDialog dialog = new JDialog();
                dialog.getContentPane().add(dialogGoiMon);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
//        dialogGoiMon.filltableCoSan(MaPDB);
                dialogGoiMon.setKHvaTG(MaPDB);
                dialogGoiMon.filltableCoSan(MaPDB);
            } else {
                MsgBox.alert(this, "Vui lòng chỉ chọn 1 bàn để gọi món");
                JPanelTang1.listSo.clear();
                JPanelTang1.listBT.clear();
            }
        }
    }//GEN-LAST:event_btnGoiMonActionPerformed

    public static void insert(int maMaxKH, int maMaxPbd, int maBan) {
        ChiTietDatBan ctdb = new ChiTietDatBan();
        ctdb.setMaBan(maBan);
        ctdb.setMaPhieuDat(maMaxPbd + 1);
        ctdbdao.insert(ctdb.getMaBan() + "", ctdb.getMaPhieuDat());
    }

    public static int insertPDB(int maMaxKH) {
        PhieuDatBan pdb = new PhieuDatBan();
        int maMaxPbd = pdbDAO.SelectMaxPDB();
        pdbDAO.setMaxPDB(maMaxPbd);
//        pdb.setThoiGianDat(new Date());
        pdb.setThoiGianDat(new Timestamp(System.currentTimeMillis()));
        pdb.setMaKhachHang(maMaxKH + 1);
        pdbDAO.insert_null(pdb);
        return maMaxPbd;
    }

    public static int insertKHnull() {
        Model.KhachHang kh = new Model.KhachHang();
        int maMaxKH = khDBDAO.SelectMaxkH();
        khDBDAO.setMaxKh(maMaxKH);
        khDAO.insertNull(kh);
        return maMaxKH;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaoTri;
    private javax.swing.JButton btnDatBan;
    private javax.swing.JButton btnGoiMon;
    private javax.swing.JButton btnPhucVu;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cbTang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tblDatBan;
    public static javax.swing.JTextField txtTimKIem;
    // End of variables declaration//GEN-END:variables
   static public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblDatBan.getModel();
        model.setRowCount(0);
        try {
            String key = txtTimKIem.getText();
            List<Object[]> list = dBDao.LoadThongTin(key);
            for (Object[] obj : list) {
                model.addRow(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void fillComboBoxTang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbTang.getModel();
        model.removeAllElements();
        List<String> list = dBDao.selectTang();
        for (String tang : list) {
            model.addElement(tang);
        }
    }

    static public void KiemTraXacNhan(int kt) {
        if (kt == 1) {
            JPanelDatBan.fillToTable();
            kt = 0;
        }
    }

//    void DoiTrangThaiBanDaDatTheoThoiGian() {
//        for (int i = 1; i <= 12; i++) {
//            JButton btn = JPanelTang1.timButtonByMaBan(i);
//            PhieuDatBan pdb = pdbDAO.getPhieuDatBanTheoThoiGian(i);
//
//            if (btn.getBackground() != Color.GREEN) {
//                if (pdb != null) {
//                    if (pdb.getThoiGianDat().getTime() - new Timestamp(System.currentTimeMillis()).getTime() <= 3600000) {
//                        btn.setBackground(Color.red);
////                JPanelTang1.showDiaLogTrangThaiDaDat(listSoBan);
//                    }
//                }
//            }
//        }
//
//        for (int i = 13; i <= 24; i++) {
//            JButton btn = JPanelTang2.timButtonByMaBan(i);
//            PhieuDatBan pdb = pdbDAO.getPhieuDatBanTheoThoiGian(i);
//
//            if (btn.getBackground() != Color.GREEN) {
//                if (pdb != null) {
//                    if (pdb.getThoiGianDat().getTime() - new Timestamp(System.currentTimeMillis()).getTime() <= 3600000) {
//                        btn.setBackground(Color.red);
////                    JPanelTang2.showDiaLogTrangThaiDaDat(listSoBan);
//                    }
//                }
//            }
//        }
//
//        for (int i = 25; i <= 36; i++) {
//            JButton btn = JPanelTang3.timButtonByMaBan(i);
////            System.out.println(btn.getBackground());
//            PhieuDatBan pdb = pdbDAO.getPhieuDatBanTheoThoiGian(i);
//            if (btn.getBackground() != Color.GREEN) {
//                if (pdb != null) {
//                    if (pdb.getThoiGianDat().getTime() - new Timestamp(System.currentTimeMillis()).getTime() <= 3600000) {
//                        btn.setBackground(Color.red);
////                JPanelTang3.showDiaLogTrangThaiDaDat(listSoBan);
//                    }
//                }
//            }
//        }
//    }
}
