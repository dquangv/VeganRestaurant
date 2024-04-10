/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.DanhGia1JPanel;
import View.DanhGia2JPanel;
import View.DoiMatKhauPanel;
import View.HoaDonJPanel;
import View.JPanelDatBan;
import View.JPanelMonAn;
import View.JPanelTang2;
import View.JPanelThongKeDanhGIa;
import View.JPanelThongKeDoanhThu;
import View.JpanelThongKeMonAn;
import View.KhachHang;
import View.NguyenVatLieu;
import View.NhanVien_1;
import View.ThucDon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
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
    private JPanel currentNode;

    public ChuyenManHinh(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    private void animatePanel(final JPanel panel) {
        new Thread(() -> {
            for (int y = -panel.getHeight() + 20; y <= 0; y += 40) {
                try {
                    Thread.sleep(10); // Wait for 10 milliseconds before moving
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                panel.setLocation(panel.getX(), y + 20);
            }
        }).start();
    }

    //Hàm show trang đầu tiên xuất hiện khi mới truy cập vào ứng dụng
    public void setView(JPanel jpnItem, JLabel jlbItem) {
        
//        jpnItem.setBackground(new Color(23, 70, 162));
//        jlbItem.setBackground(new Color(23, 70, 162));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new JPanelMonAn());
        root.validate();
        root.repaint();
    }

    //----------------------------Hàm tạo event để xử lý sự kiện MouseListener ( thao tác chuyển đổi menu)
    //--Dùng cho Giao diện quản lý
    public void setEvent(List<DanhMuc> listItem) {
        this.listItem = listItem;
        for (DanhMuc item : listItem) {
            item.getJpn().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;
        private String currentKind = ""; // Biến lưu trữ loại tab hiện tại

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        List<String> list = new ArrayList<>();

        @Override
        public void mouseClicked(MouseEvent e) {
            // Thêm kind vào cuối danh sách
            if (e.getClickCount() == 1) {
                switch (kind) {
                    case "NhanVien":
                        node = new NhanVien_1();
                        break;
                    case "MonAn":
                        node = new JPanelMonAn();
                        break;
                    case "DatBan":
                        node = new JPanelDatBan();
                        break;
                    case "KhachHang":
                        node = new KhachHang();
                        break;
                    case "NguyenVatLieu":
                        node = new NguyenVatLieu();
                        break;
                    case "ThucDon":
                        node = new ThucDon();
                        break;
                    case "ThongKeMonAn":
                        node = new JpanelThongKeMonAn();
                        break;
                    case "ThongKeDoanhThu":
                        node = new JPanelThongKeDoanhThu();
                        break;
                    case "TKDanhGia":
                        node = new JPanelThongKeDanhGIa();
                        break;
                    case "HoaDon":
                        node = new HoaDonJPanel();
                        break;
                    case "DoiMatKhau":
                        node = new DoiMatKhauPanel();
                        break;
                    default:
                        node = new JPanelDatBan();
                }
            } else {
                switch (kind) {
                    case "NhanVien":
                        node = new NhanVien_1();
                        break;
                    case "MonAn":
                        node = new JPanelMonAn();
                        break;
                    case "DatBan":
                        node = new JPanelDatBan();
                        break;
                    case "KhachHang":
                        node = new KhachHang();
                        break;
                    case "NguyenVatLieu":
                        node = new NguyenVatLieu();
                        break;
                    case "ThucDon":
                        node = new ThucDon();
                        break;
                    case "ThongKeMonAn":
                        node = new JpanelThongKeMonAn();
                        break;
                    case "ThongKeDoanhThu":
                        node = new JPanelThongKeDoanhThu();
                        break;
                    case "DanhGia":
                        node = new DanhGia2JPanel();
                        break;
                    case "HoaDon":
                        node = new HoaDonJPanel();
                        break;
                    case "DoiMatKhau":
                        node = new DoiMatKhauPanel();
                        break;
                    default:
                        node = new JPanelDatBan();
                }
            }

            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
//            changeBackGroundColor(kind);
           
        }

        @Override
        public void mousePressed(MouseEvent e) {
//            kindSelected = kind;
//            jpnItem.setBackground(new Color(0, 0, 0));
//            jlbItem.setBackground(new Color(0, 0, 0));
//            jlbItem.setForeground(new Color(232, 249, 253));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//            jpnItem.setBackground(new Color(0, 0, 0));
//            jlbItem.setBackground(new Color(0, 0, 0));
//            jlbItem.setForeground(new Color(232, 249, 253));
        }

        @Override
        public void mouseExited(MouseEvent e) {
//            if (!kindSelected.equalsIgnoreCase(kind)) {
//                jpnItem.setBackground(new Color(255, 30, 0));
//                jlbItem.setBackground(new Color(255, 30, 0));
//                jlbItem.setForeground(new Color(232, 249, 253));
//            }
        }

    }

//    private void changeBackGroundColor(String kind) {
//        for (DanhMuc item : listItem) {
//            if (item.getKind().equalsIgnoreCase(kind)) {
//                item.getJpn().setBackground(new Color(0, 0, 0));
//                item.getJlb().setBackground(new Color(0, 0, 0));
//                item.getJlb().setForeground(new Color(232, 249, 253));
//            } else {
//                item.getJlb().setBackground(new Color(255, 30, 0));
//                item.getJpn().setBackground(new Color(255, 30, 0));
//                item.getJlb().setForeground(new Color(232, 249, 253));
//            }
//        }
//    }
//
//    private void changeBackGroundColorQL(String kind) {
//        for (DanhMuc item : listItem) {
//            if (item.getKind().equalsIgnoreCase(kind)) {
//                item.getJpn().setBackground(new Color(255, 30, 0));
//                item.getJlb().setBackground(new Color(255, 30, 0));
//
//            } else {
//                item.getJlb().setBackground(new Color(0, 0, 0));
//                item.getJpn().setBackground(new Color(0, 0, 0));
//
//            }
//        }
//    }
}
