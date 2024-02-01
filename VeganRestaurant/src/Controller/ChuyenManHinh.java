/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.MonAnPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Quang
 */
public class ChuyenManHinh {
    private JPanel root;
    private String kindSelected = "";
    private List<DanhMuc> listItem = null;

    public ChuyenManHinh(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    //Hàm show trang đầu tiên xuất hiện khi mới truy cập vào ứng dụng
    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChu";
//        jpnItem.setBackground(new Color(23, 70, 162));
//        jlbItem.setBackground(new Color(23, 70, 162));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new MonAnPanel());
        root.validate();
        root.repaint();
    }

    //----------------------------Hàm tạo event để xử lý sự kiện MouseListener ( thao tác chuyển đổi menu)
    //--Dùng cho Giao diện quản lý
    public void setEvent(List<DanhMuc> listItem) {
        this.listItem = listItem;
        for (DanhMuc item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
//            switch (kind) {
//                case "TrangChu":
//                    node = new TrangChuPanel();
//                    break;
//                case "QuanLy":
//                    node = new QuanLyPanel();
//                    break;
//                case "BanHang":
//                    node = new BanVePanel();
//                    break;
//                case "ThongKe":
//                    node = new ThongKePanel();
//                    break;
//                case "KhachHang":
//                    node = new QLKhachHangPanel();
//                    break;
//                default:
//                    node = new TrangChuPanel();
//            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            changeBackGroundColor(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(0, 0, 0));
            jlbItem.setBackground(new Color(0,0,0));
            jlbItem.setForeground(new Color(232, 249, 253));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(0, 0, 0));
            jlbItem.setBackground(new Color(0,0,0));
            jlbItem.setForeground(new Color(232, 249, 253));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(255, 30, 0));
                jlbItem.setBackground(new Color(255, 30, 0));
                jlbItem.setForeground(new Color(232, 249, 253));
            }
        }

    }

    //---Dùng cho giao diện Nhân viên
    public void setEventQL(List<DanhMuc> listItem) {
        this.listItem = listItem;
        for (DanhMuc item : listItem) {
            item.getJlb().addMouseListener(new LabelEventNV(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEventNV implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEventNV(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
//            switch (kind) {
//                case "Phim":
//                    node = new QLPhimPanel();
//                    break;
//                case "SuatChieu":
//                {
//                    try {
//                        node = new QLSuatChieuPanel();
//                    } catch (ParseException ex) {
//                        Logger.getLogger(Chuyenmanhinh.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                    break;
//
//                case "NhanVien":
//                    node = new QLNhanVienPanel();
//                    break;
//                case "KhachHang":
//                    node = new QLKhachHangPanel();
//                    break;
//                case "Ve":
//                    node = new QLVePanel();
//                    break;
//                default:
//                    node = new QLPhimPanel();
//            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            changeBackGroundColorQL(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(255, 30, 0));
            jlbItem.setBackground(new Color(255, 30, 0));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(255, 30, 0));
            jlbItem.setBackground(new Color(255, 30, 0));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(0,0,0));
                jlbItem.setBackground(new Color(0,0,0));
            }
        }

    }

    private void changeBackGroundColor(String kind) {
        for (DanhMuc item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(0, 0, 0));
                item.getJlb().setBackground(new Color(0, 0, 0));
                item.getJlb().setForeground(new Color(232, 249, 253));
            } else {
                item.getJlb().setBackground(new Color(255, 30, 0));
                item.getJpn().setBackground(new Color(255, 30, 0));
                item.getJlb().setForeground(new Color(232, 249, 253));
            }
        }
    }

    private void changeBackGroundColorQL(String kind) {
        for (DanhMuc item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(255, 30, 0));
                item.getJlb().setBackground(new Color(255, 30, 0));

            } else {
                item.getJlb().setBackground(new Color(0,0,0));
                item.getJpn().setBackground(new Color(0,0,0));

            }
        }
    }
}
