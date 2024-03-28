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

    public ChuyenManHinh(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    //Hàm show trang đầu tiên xuất hiện khi mới truy cập vào ứng dụng
    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "DatBan";
//        jpnItem.setBackground(new Color(23, 70, 162));
//        jlbItem.setBackground(new Color(23, 70, 162));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(jpnItem);
        root.validate();
        root.repaint();
        System.out.println(root);
//        System.out.println(jpnItem.getToolTipText());

//        System.out.println(root.getComponent(0));
//        System.out.println(jpnItem);
//        System.out.println("a");
    }

    //----------------------------Hàm tạo event để xử lý sự kiện MouseListener ( thao tác chuyển đổi menu)
    //--Dùng cho Giao diện quản lý
    public void setEvent(List<DanhMuc> listItem) {
        this.listItem = listItem;
        for (DanhMuc item : listItem) {
            //getJpn để gắn sự kiện cho panel, getLbl để gắn sự kiện cho Label
            item.getJpn().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    // tạo sự kiện
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
            switch (kind) {
                case "NhanVien":
                    node = new NhanVien_1();
                    break;
                case "MonAn":
                    node = new JPanelMonAn();
                    break;
                case "DatBan":
                    node = new JPanelDatBan();
//                    System.out.println(node);
//                    System.out.println(new JPanelDatBan());
//                    System.out.println("b");
//                    System.out.println(root);
//                    System.out.println("c");
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
//            System.out.println(node);
//
//            System.out.println(root);
//            System.out.println("d");
            root.removeAll();
//            System.out.println(node);
//
//            System.out.println(root);
//            System.out.println("e");
            root.setLayout(new BorderLayout());
//            System.out.println(node);
//
//            System.out.println(root);
//            System.out.println("f");

            root.add(node);
//            System.out.println(node);
//
//            System.out.println(root);
//            System.out.println("g");

            root.validate();
//            System.out.println(node);
//
//            System.out.println(root);
//            System.out.println("h");
            root.repaint();
//            System.out.println(node);
//
//            System.out.println(root);
//            System.out.println("i");
//            changeBackGroundColor(kind);

//            System.out.println(root.getComponent(0));
//            System.out.println(new JPanelDatBan());
//            System.out.println(root.getComponent(0));
//            System.out.println(node);
//            System.out.println("b");
//            System.out.println(node);
//
//            System.out.println(root);
            System.out.println(root);

//            System.out.println("j");
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
