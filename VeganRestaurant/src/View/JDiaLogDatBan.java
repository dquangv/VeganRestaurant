/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Controller.ChiTietDatBan_DAO;
import Controller.DatBanDao;
import static Controller.DatBanDao.BAO_TRI;
import static Controller.DatBanDao.DANG_PHUC_VU;
import Controller.KhachHangDAO;
import Controller.PhieuDatBanDao;
import Controller.KhachHangDBDao;
import Model.ChiTietDatBan;
import Model.PhieuDatBan;
import Model.KhachHang;
import Utils.MsgBox;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

/**
 *
 * @author Võ Thanh Tùng
 */
public class JDiaLogDatBan extends javax.swing.JDialog {

    DatBanDao dbDAO = new DatBanDao();
    KhachHangDBDao khDBDAO = new KhachHangDBDao();
    KhachHangDAO khDAO = new KhachHangDAO();
    PhieuDatBanDao pdbDAO = new PhieuDatBanDao();
    ChiTietDatBan_DAO ctdbdao = new ChiTietDatBan_DAO();
    int maBan;
    private List<Integer> listSo;

    /**
     * Creates new form JDiaLogDatBan
     */
    public JDiaLogDatBan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Trạng thái bàn");

    }

    public void setBan(int maBan) {
        lbMaBan.setText("Bàn: " + maBan);
        this.maBan = maBan;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTrangThai = new javax.swing.JLabel();
        lbBDPV = new javax.swing.JLabel();
        lbDatBanTruoc = new javax.swing.JLabel();
        lbBaoTri = new javax.swing.JLabel();
        lbMaBan = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTrangThai.setForeground(new java.awt.Color(255, 153, 153));
        lbTrangThai.setText("Trang thái:Trống");

        lbBDPV.setBackground(new java.awt.Color(51, 255, 0));
        lbBDPV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbBDPV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Dang phuc vu.png"))); // NOI18N
        lbBDPV.setText("Bất đầu phục vụ");
        lbBDPV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbBDPV.setFocusTraversalPolicyProvider(true);
        lbBDPV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBDPVMouseClicked(evt);
            }
        });

        lbDatBanTruoc.setBackground(new java.awt.Color(51, 255, 0));
        lbDatBanTruoc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbDatBanTruoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/da dat.png"))); // NOI18N
        lbDatBanTruoc.setText("Đặt bàn trước");
        lbDatBanTruoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbDatBanTruoc.setFocusTraversalPolicyProvider(true);
        lbDatBanTruoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDatBanTruocMouseClicked(evt);
            }
        });

        lbBaoTri.setBackground(new java.awt.Color(51, 255, 0));
        lbBaoTri.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbBaoTri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bao tri.png"))); // NOI18N
        lbBaoTri.setText("Bảo trì");
        lbBaoTri.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbBaoTri.setFocusTraversalPolicyProvider(true);
        lbBaoTri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBaoTriMouseClicked(evt);
            }
        });

        lbMaBan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbMaBan.setText("Bàn 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDatBanTruoc)
                    .addComponent(lbBaoTri)
                    .addComponent(lbBDPV))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDatBanTruoc)
                .addGap(18, 18, 18)
                .addComponent(lbBaoTri)
                .addGap(18, 18, 18)
                .addComponent(lbBDPV)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbDatBanTruocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDatBanTruocMouseClicked
        String maBan = lbMaBan.getText().substring(5);
        JDiaLogNhapThongTin dlnt = new JDiaLogNhapThongTin(new javax.swing.JFrame(), true);
        dlnt.setBan(JPanelTang1.listSo);
        dlnt.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lbDatBanTruocMouseClicked

    private void lbBaoTriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBaoTriMouseClicked
        String maBan = lbMaBan.getText().substring(5);
        MsgBox.alert(this, "Đã chuyên sang bảo trì");
        thayDoiTrangThai(maBan);
        this.setVisible(false);
        JPanelDatBan.fillToTable();
        JPanelTang1.TrangThaiBan();
        JPanelTang2.TrangThaiBan();
        JPanelTang3.TrangThaiBan();
    }//GEN-LAST:event_lbBaoTriMouseClicked

    private void lbBDPVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBDPVMouseClicked
//        String maBan = lbMaBan.getText().substring(5);
//
//        MsgBox.alert(this, "Bất đầu phục vụ");
//        thayDoiTrangThaiBatDauPhucVu(listSo);
//        insert(this.maBan);
//        this.setVisible(false);
//
//        JPanelDatBan.fillToTable();
//        JPanelTang1.TrangThaiBan();
//        JPanelTang2.TrangThaiBan();
//        JPanelTang3.TrangThaiBan();
    }//GEN-LAST:event_lbBDPVMouseClicked

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
            java.util.logging.Logger.getLogger(JDiaLogDatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDiaLogDatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDiaLogDatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDiaLogDatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDiaLogDatBan dialog = new JDiaLogDatBan(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbBDPV;
    private javax.swing.JLabel lbBaoTri;
    private javax.swing.JLabel lbDatBanTruoc;
    private javax.swing.JLabel lbMaBan;
    private javax.swing.JLabel lbTrangThai;
    // End of variables declaration//GEN-END:variables
     public void insert(int maMaxKH, int maBan) {
        PhieuDatBan pdb = new PhieuDatBan();
        ChiTietDatBan ctdb = new ChiTietDatBan();
        int maMaxPbd = pdbDAO.SelectMaxPDB();
        pdbDAO.setMaxPDB(maMaxPbd);
        pdb.setThoiGianDat(new Timestamp(System.currentTimeMillis()));
        pdb.setMaKhachHang(maMaxKH + 1);
        pdbDAO.insert_null(pdb);
        ctdb.setMaBan(maBan);
        ctdb.setMaPhieuDat(maMaxPbd + 1);
        ctdbdao.insert(ctdb);
    }

    public int insertKHnull() {
        KhachHang kh = new KhachHang();
        int maMaxKH = khDBDAO.SelectMaxkH();
        khDBDAO.setMaxKh(maMaxKH);
        khDAO.insertNull(kh);
        return maMaxKH;
    }

    public void thayDoiTrangThai(String maBan) {
        dbDAO.updateTrangThai(BAO_TRI, maBan);
    }

    public void thayDoiTrangThaiBatDauPhucVu(List<Integer> listSo) {

        for (Integer maBan : listSo) {
            dbDAO.updateTrangThai(DANG_PHUC_VU, maBan + "");
        }
    }
}
