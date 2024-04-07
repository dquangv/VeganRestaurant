/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.DatBanDao;
import Controller.PhieuDatBanDao;
import static View.JPanelTang1.ctThongTIn;
import static View.JPanelTang1.listBT;
import static View.JPanelTang1.listSo;
import static View.JPanelTang1.listSoBan;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author Võ Thanh Tùng
 */
public class JPanelTang2 extends javax.swing.JPanel {

    static DatBanDao dBDao = new DatBanDao();

    /**
     * Creates new form JPanelTang1
     */
    public JPanelTang2() {
        initComponents();
        TrangThaiBan();
//        System.out.println("tang 2");

    }

    public static void thayDoiMauButton(JButton btn[]) {
        for (int i = 0; i < btn.length; i++) {
            if (listBT.contains(btn[i])) {
                btn[i].setBackground(Color.yellow);
            } else {
                btn[i].setBackground(Color.PINK);
            }
        }
    }

    public static void setButton(int maBan) {
        JButton button = timButtonByMaBan(maBan);
        if (button != null) {
            boolean found = false;
            for (JButton btn : listBT) {
                if (btn.equals(button)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                listBT.removeIf(i -> i.equals(button));
                System.out.println("da xoa nut " + maBan);
            } else {
                listBT.add(button);
                System.out.println("da them nut  " + maBan);
            }
            thayDoiMauButton(new JButton[]{button});
            System.out.println(listBT.size());
        }
    }

    

    public int getMaPDB(String maBan) {
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
        return MaPDB;
    }
    
    static void timMaBanByButton(Integer maBan) {
        boolean found = false;
        
        for (Integer indexMaBan : listSo) {
            if (indexMaBan.equals(maBan)) {
                found = true;
                break;
            }
        }
        
        if (found) {
            listSo.removeIf(i -> i.equals(maBan));
            System.out.println("da xoa so " + maBan);
        } else {
            listSo.add(maBan);
            System.out.println("da them so " + maBan);
        }
        
        for (Integer so : listSo) {
            System.out.print(so + " ");
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

        btnban10 = new javax.swing.JButton();
        btnban4 = new javax.swing.JButton();
        btnban11 = new javax.swing.JButton();
        lbT1B12 = new javax.swing.JLabel();
        btnban12 = new javax.swing.JButton();
        btnban5 = new javax.swing.JButton();
        lbT1B01 = new javax.swing.JLabel();
        lbT1B07 = new javax.swing.JLabel();
        lbT1B02 = new javax.swing.JLabel();
        btnban1 = new javax.swing.JButton();
        lbT1B03 = new javax.swing.JLabel();
        lbT1B08 = new javax.swing.JLabel();
        lbT1B04 = new javax.swing.JLabel();
        lbT1B09 = new javax.swing.JLabel();
        btnban2 = new javax.swing.JButton();
        lbT1B10 = new javax.swing.JLabel();
        btnban7 = new javax.swing.JButton();
        lbT1B05 = new javax.swing.JLabel();
        lbT1B11 = new javax.swing.JLabel();
        btnban8 = new javax.swing.JButton();
        btnban6 = new javax.swing.JButton();
        btnban3 = new javax.swing.JButton();
        btnban9 = new javax.swing.JButton();
        lbT1B06 = new javax.swing.JLabel();

        btnban10.setBackground(new java.awt.Color(51, 51, 51));
        btnban10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban10ActionPerformed(evt);
            }
        });

        btnban4.setBackground(new java.awt.Color(51, 51, 51));
        btnban4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban4ActionPerformed(evt);
            }
        });

        btnban11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban11ActionPerformed(evt);
            }
        });

        lbT1B12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B12.setText("T1B12");

        btnban12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban12ActionPerformed(evt);
            }
        });

        btnban5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban5ActionPerformed(evt);
            }
        });

        lbT1B01.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B01.setText("T2B01");

        lbT1B07.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B07.setText("T2B07");

        lbT1B02.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B02.setText("T2B02");

        btnban1.setBackground(new java.awt.Color(51, 255, 0));
        btnban1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban1ActionPerformed(evt);
            }
        });

        lbT1B03.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B03.setText("T2B03");

        lbT1B08.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B08.setText("T2B08");

        lbT1B04.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B04.setText("T2B04");

        lbT1B09.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B09.setText("T2B09");

        btnban2.setBackground(new java.awt.Color(255, 0, 51));
        btnban2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban2ActionPerformed(evt);
            }
        });

        lbT1B10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B10.setText("T2B10");

        btnban7.setBackground(new java.awt.Color(51, 255, 0));
        btnban7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban7ActionPerformed(evt);
            }
        });

        lbT1B05.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B05.setText("T2B05");

        lbT1B11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B11.setText("T2B11");

        btnban8.setBackground(new java.awt.Color(255, 0, 51));
        btnban8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban8ActionPerformed(evt);
            }
        });

        btnban6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban6ActionPerformed(evt);
            }
        });

        btnban3.setBackground(new java.awt.Color(255, 204, 204));
        btnban3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban3ActionPerformed(evt);
            }
        });

        btnban9.setBackground(new java.awt.Color(255, 204, 204));
        btnban9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ban.png"))); // NOI18N
        btnban9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnban9ActionPerformed(evt);
            }
        });

        lbT1B06.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbT1B06.setText("T2B06");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnban1)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnban2)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnban3)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnban4)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnban5)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnban6))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnban7)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnban8)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnban9)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnban10)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnban11)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnban12)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(lbT1B01, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(lbT1B02, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(lbT1B03, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(lbT1B04, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(lbT1B05, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(lbT1B06, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lbT1B07, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(lbT1B08, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(lbT1B09, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(lbT1B10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(lbT1B11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(lbT1B12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnban6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnban5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnban4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnban3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnban2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnban1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbT1B01)
                    .addComponent(lbT1B02)
                    .addComponent(lbT1B03)
                    .addComponent(lbT1B04)
                    .addComponent(lbT1B05)
                    .addComponent(lbT1B06))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnban12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnban11, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnban10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnban9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnban8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnban7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbT1B07)
                    .addComponent(lbT1B08)
                    .addComponent(lbT1B09)
                    .addComponent(lbT1B10)
                    .addComponent(lbT1B11)
                    .addComponent(lbT1B12))
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnban10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban10ActionPerformed

        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 22);
        dialog.layMaBan(22);
        int PDB = getMaPDB("22");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(22);

    }//GEN-LAST:event_btnban10ActionPerformed

    private void btnban4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban4ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 16);
        dialog.layMaBan(16);
        int PDB = getMaPDB("16");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(16);

    }//GEN-LAST:event_btnban4ActionPerformed

    private void btnban11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban11ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 23);
        dialog.layMaBan(23);
        int PDB = getMaPDB("23");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(23);

    }//GEN-LAST:event_btnban11ActionPerformed

    private void btnban12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban12ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 24);
        dialog.layMaBan(24);
        int PDB = getMaPDB("24");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(24);

    }//GEN-LAST:event_btnban12ActionPerformed

    private void btnban5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban5ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 17);
        dialog.layMaBan(17);
        int PDB = getMaPDB("17");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(17);

    }//GEN-LAST:event_btnban5ActionPerformed

    private void btnban1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban1ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 13);
        dialog.layMaBan(13);
        int PDB = getMaPDB("13");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(13);

    }//GEN-LAST:event_btnban1ActionPerformed

    private void btnban2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban2ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 14);
        dialog.layMaBan(14);
        int PDB = getMaPDB("14");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(14);

    }//GEN-LAST:event_btnban2ActionPerformed

    private void btnban7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban7ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 19);
        dialog.layMaBan(19);
        int PDB = getMaPDB("19");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(19);

    }//GEN-LAST:event_btnban7ActionPerformed

    private void btnban8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban8ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 20);
        dialog.layMaBan(20);
        int PDB = getMaPDB("20");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(20);

    }//GEN-LAST:event_btnban8ActionPerformed

    private void btnban6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban6ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 18);
        dialog.layMaBan(18);
        int PDB = getMaPDB("18");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(18);

    }//GEN-LAST:event_btnban6ActionPerformed

    private void btnban3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban3ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 15);
        dialog.layMaBan(15);
        int PDB = getMaPDB("15");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(15);

    }//GEN-LAST:event_btnban3ActionPerformed

    private void btnban9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnban9ActionPerformed
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, 21);
        dialog.layMaBan(21);
        int PDB = getMaPDB("21");
        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
        kiemTraTrangThaiBan(21);

    }//GEN-LAST:event_btnban9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnban1;
    public static javax.swing.JButton btnban10;
    public static javax.swing.JButton btnban11;
    public static javax.swing.JButton btnban12;
    public static javax.swing.JButton btnban2;
    public static javax.swing.JButton btnban3;
    public static javax.swing.JButton btnban4;
    public static javax.swing.JButton btnban5;
    public static javax.swing.JButton btnban6;
    public static javax.swing.JButton btnban7;
    public static javax.swing.JButton btnban8;
    public static javax.swing.JButton btnban9;
    private javax.swing.JLabel lbT1B01;
    private javax.swing.JLabel lbT1B02;
    private javax.swing.JLabel lbT1B03;
    private javax.swing.JLabel lbT1B04;
    private javax.swing.JLabel lbT1B05;
    private javax.swing.JLabel lbT1B06;
    private javax.swing.JLabel lbT1B07;
    private javax.swing.JLabel lbT1B08;
    private javax.swing.JLabel lbT1B09;
    private javax.swing.JLabel lbT1B10;
    private javax.swing.JLabel lbT1B11;
    private javax.swing.JLabel lbT1B12;
    // End of variables declaration//GEN-END:variables

    static public JButton timButtonByMaBan(int maBan) {
        switch (maBan) {
            case 13:
                return btnban1;
            case 14:
                return btnban2;
            case 15:
                return btnban3;
            case 16:
                return btnban4;
            case 17:
                return btnban5;
            case 18:
                return btnban6;
            case 19:
                return btnban7;
            case 20:
                return btnban8;
            case 21:
                return btnban9;
            case 22:
                return btnban10;
            case 23:
                return btnban11;
            case 24:
                return btnban12;
            default:
                return null;
        }
    }

    static void TrangThaiBan() {
        List<Object[]> banList = dBDao.loadData();
        for (Object[] ban : banList) {
            int maBan = (int) ban[0];
            String trangThai = (String) ban[1];
            JButton button = timButtonByMaBan(maBan);
            if (button != null) {
                switch (trangThai) {
                    case DatBanDao.Trong:
                        button.setBackground(Color.PINK); // Đặt màu nền trắng cho bàn đang hoạt động
                        break;
                    case DatBanDao.DANG_PHUC_VU:
                        button.setBackground(Color.GREEN); // Đặt màu nền xanh cho bàn đang phục vụ
                        break;
                    case DatBanDao.DA_DAT:
                        button.setBackground(Color.PINK); // Đặt màu nền hồng cho bàn trống
                        break;
                    case DatBanDao.BAO_TRI:
                        button.setBackground(Color.BLACK); // Đặt màu nền đen cho bàn đang bảo trì
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void showDiaLogTrangThaiDaDat(List<Integer> maBan) {
        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, maBan.get(0));
        dialog.setBan(maBan);
        dialog.setVisible(true);
    }

    void ShowDialogDatBan(int maBan) {
        JDiaLogDatBan dialog = new JDiaLogDatBan(new javax.swing.JFrame(), true);
        dialog.setBan(maBan);
        dialog.setVisible(true);

    }

    void showDiaLogBaoTri(int maBan) {
        JDialogBaoTriXong dialog = new JDialogBaoTriXong(new javax.swing.JFrame(), true);
        dialog.setBan(maBan);
        dialog.setVisible(true);

    }

    void showDiaLogDangPhucVu(int maBan) {
        JDiaLogDangPhucVu dialog = new JDiaLogDangPhucVu(new javax.swing.JFrame(), true);
        dialog.setBan(maBan);
        dialog.setVisible(true);
    }

    void kiemTraTrangThaiBan(int maBan) {
//        List<Object[]> banList = dBDao.loadData();
//        for (Object[] ban : banList) {
//            int maBanDB = (int) ban[0];
//            String trangThai = (String) ban[1];
//            if ((maBan == maBanDB)) {
//                switch (trangThai) {
//                    case DatBanDao.Trong:
//                        setButton(maBan);
//                        timMaBanByButton(maBan);
//                        return;
//                    case DatBanDao.DANG_PHUC_VU:
//                        showDiaLogDangPhucVu(maBan);
//                        break;
//                    case DatBanDao.DA_DAT:
//                        showDiaLogTrangThaiDaDat(listSoBan);
//                        return;
//                    case DatBanDao.BAO_TRI:
//                        showDiaLogBaoTri(maBan);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }

        List<Object[]> banList = dBDao.loadData();
        for (Object[] ban : banList) {
            int maBanDB = (int) ban[0];
            String trangThai = (String) ban[1];
            if ((maBan == maBanDB)) {
                switch (trangThai) {
                    case DatBanDao.Trong:
                        setButton(maBan);
                        timMaBanByButton(maBan);
                        return;
                    case DatBanDao.DANG_PHUC_VU:
                        showDiaLogDangPhucVu(maBan);
                        break;
                    case DatBanDao.DA_DAT:
//                        showDiaLogTrangThaiDaDat(listSoBan);
                        setButton(maBan);
                        timMaBanByButton(maBan);
//                        JDialogTrangThaiDatBan dialog = new JDialogTrangThaiDatBan(new javax.swing.JFrame(), true, maBan);
//                        dialog.layMaBan(maBan);
//                        int PDB = getMaPDB(String.valueOf(maBan));
//                        listSoBan = ctThongTIn.dsBanTheoPDB(PDB);
//                        kiemTraTrangThaiBan(maBan);
                        return;
                    case DatBanDao.BAO_TRI:
                        showDiaLogBaoTri(maBan);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    

}
