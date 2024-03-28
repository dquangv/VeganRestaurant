package View;

import Controller.ChuyenManHinh;
import Controller.DanhMuc;
import Utils.Auth;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import org.jfree.layout.FormatLayout;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Võ Thanh Tùng
 */
public class Main {
    int maPDB;

    public int getMaPDB() {
        return maPDB;
    }

    public void setMaPDB(int maPDB) {
        this.maPDB = maPDB;
    }

    boolean TrangThaiThietLap = false;
    boolean TrangThaiThongKe = false;
    boolean TrangThaiQuanLy = false;
    JFrame fr = new JFrame();
// cac tad o tren
    JPanel pnMenu = new JPanel();
    JPanel pnView = new JPanel();
    JPanel pnMenuCon1 = new JPanel();
    JPanel pnMenuConTrai = new JPanel();
    JPanel pnMenuConPhai = new JPanel();
    JPanel pnMenuCon2 = new JPanel();
    JPanel pnHeThong = new JPanel();
    JPanel pnQuanLy = new JPanel();
    JPanel pnThongKe = new JPanel();

    JPanel pnDoiMatKhau = new JPanel();
    JPanel pnDangXuat = new JPanel();
    JPanel pnThoat = new JPanel();

    JPanel pnTKDoanhThu = new JPanel();
    JPanel pnTKMonAn = new JPanel();

    JLabel lbHeThong = new JLabel("Hệ thống");
    JLabel lbQuanLy = new JLabel("Quản lý");
    JLabel lbThongKe = new JLabel("Thống kê");

    JLabel lbThongKeDoanhThu = new JLabel("Doanh thu");
    JLabel lbThongKeMonAn = new JLabel("Món ăn");

    JLabel lbVaiTro = new JLabel("Quản lý: Võ Thanh Tùng");

    JLabel lblDoiMatKhau = new JLabel("Đổi mật khẩu");
    JLabel lblDangXuat = new JLabel("Đăng xuất", JLabel.CENTER);
    JLabel lblThoat = new JLabel("Thoát", JLabel.CENTER);

    // cac tad ben duoi
    JPanel pnDatBan = new JPanel();
    JPanel pnThanhToan = new JPanel();
    JPanel pnKhachHang = new JPanel();
    JPanel pnThucDon = new JPanel();
    JPanel pnNguyenLieu = new JPanel();
    JPanel pnMonAn = new JPanel();

    JLabel lblDatBan = new JLabel("Đặt bàn");
    JLabel lblThanhToan = new JLabel("Thanh toán");
    JLabel lblKhachHang = new JLabel("Khách hàng");
    JLabel lblThucDon = new JLabel("Thực đơn");
    JLabel lblNguyenLieu = new JLabel("Nguyên liệu");
    JLabel lblMonAn = new JLabel("Món ăn");

    // pn quan ly 
    JPanel pnNhanVien = new JPanel();
    JPanel pnDanhGia = new JPanel();

    JLabel lblNhanVien = new JLabel("Nhân viên");
    JLabel lblDanhGia = new JLabel("Đánh giá");

    public Main() {
        ChuyenManHinh control = new ChuyenManHinh(pnView);
        //control.setView(jpn,);
        List<DanhMuc> item = new ArrayList<>();
        item.add(new DanhMuc("MonAn", pnMonAn, lblMonAn));
        item.add(new DanhMuc("DatBan", pnDatBan, lblDatBan));
        item.add(new DanhMuc("KhachHang", pnKhachHang, lblKhachHang));
        item.add(new DanhMuc("ThucDon", pnThucDon, lblThucDon));
        item.add(new DanhMuc("ThongKeMonAn", pnTKMonAn, lbThongKeMonAn));
        item.add(new DanhMuc("ThongKeDoanhThu", pnTKDoanhThu, lbThongKeDoanhThu));
        item.add(new DanhMuc("NhanVien", pnNhanVien, lblNhanVien));
        item.add(new DanhMuc("HoaDon", pnThanhToan, lblThanhToan));
        item.add(new DanhMuc("DanhGia", pnDanhGia, lblDanhGia));
        item.add(new DanhMuc("NguyenVatLieu", pnNguyenLieu, lblNguyenLieu));
        item.add(new DanhMuc("DoiMatKhau", pnDoiMatKhau, lblDoiMatKhau));

        item.add(new DanhMuc("KhachHang", pnKhachHang, lblKhachHang));
        control.setEvent(item);
//        capNhatVaiTro();
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.GiaoDien();
        m.CaiDat();
        m.ThongKe();
        m.QuanLy();
        m.VaiTro();
    }

    public static void callMain() {
        Main m = new Main();
        m.GiaoDien();
        m.CaiDat();
        m.ThongKe();
        m.QuanLy();
        m.VaiTro();
    }

    void VaiTro() {
        lbVaiTro.setText((Auth.user.isVaiTro() ? "Quản lý: " : "Nhân viên: ") + Auth.user.getTenTaiKhoan());
    }

    void GiaoDien() {

        fr.setExtendedState(fr.MAXIMIZED_BOTH);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setVisible(true);

        // add cac pn vao fr chinh 
        //add menu vao fr
        pnMenu.setPreferredSize(new Dimension(fr.getWidth(), 200));
        pnMenu.setLayout(new GridLayout(2, 1));

        //add view vao fr
        pnView.setPreferredSize(new Dimension(fr.getWidth(), fr.getHeight()));

        fr.add(pnMenu, BorderLayout.NORTH);
        fr.add(pnView, BorderLayout.CENTER);

        // Cài đặt layout cho pnView thành null
        pnView.setLayout(null);

        // add pnMenuCon1 vao pnMenu
        pnMenu.add(pnMenuCon1, BorderLayout.NORTH);
        pnMenuCon1.setLayout(new GridLayout(1, 2));
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
        pnMenuCon1.setBorder(bottomBorder);
        pnMenuCon1.setPreferredSize(new Dimension(pnMenu.getWidth(), pnMenu.getHeight() / 2));

        // add pnMenuCon2 vao pnMenu 
        pnMenu.add(pnMenuCon2);
        pnMenuCon2.setPreferredSize(new Dimension(pnMenu.getWidth(), pnMenu.getHeight() / 2));
        pnMenuCon2.setBorder(bottomBorder);

        // Thêm pnMenuConTrai vào phần trái của pnMenuCon1
        pnMenuCon1.add(pnMenuConTrai, BorderLayout.WEST);

        // Thêm pnMenuConPhai vào phần phải của pnMenuCon1
        pnMenuCon1.add(pnMenuConPhai, BorderLayout.EAST);

        // Thiết lập kích thước và màu nền cho pnMenuConPhai
        pnMenuConTrai.setLayout(new GridLayout(1, 3));
        Border RightBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);
        pnMenuConTrai.add(pnHeThong);
        pnHeThong.setBorder(RightBorder);
        pnMenuConTrai.add(pnQuanLy);
        pnQuanLy.setBorder(RightBorder);
        pnMenuConTrai.add(pnThongKe);
        pnThongKe.setBorder(RightBorder);

        //add Jlabel vao he thong, quan ly, thong ke
        pnHeThong.add(lbHeThong);
        pnQuanLy.add(lbQuanLy);
        pnThongKe.add(lbThongKe);

        // set font cho chu label
        lbHeThong.setFont(new Font("Arial", Font.BOLD, 30));
        lbQuanLy.setFont(new Font("Arial", Font.BOLD, 30));
        lbThongKe.setFont(new Font("Arial", Font.BOLD, 30));

        // chinh khoang cach  chu label
        lbHeThong.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        lbQuanLy.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        lbThongKe.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        // Thêm lbVaiTro vào pnMenuConPhai
        pnMenuConPhai.add(lbVaiTro);
        lbVaiTro.setFont(new Font("Arial", Font.BOLD, 30));
        lbVaiTro.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        // set mau nen cho 3 pn he thong, quan ly, thong ke.
        pnHeThong.setBackground(new Color(196, 185, 185));
        pnQuanLy.setBackground(new Color(196, 185, 185));
        pnThongKe.setBackground(new Color(196, 185, 185));

        //set layout cho menu con 2 1 hang 6 cot
        pnMenuCon2.setLayout(new GridLayout(1, 6));

        //add cac phan tu vao menucon2 
        pnMenuCon2.add(pnDatBan);
        pnMenuCon2.add(pnThanhToan);
        pnMenuCon2.add(pnKhachHang);
        pnMenuCon2.add(pnThucDon);
        pnMenuCon2.add(pnNguyenLieu);
        pnMenuCon2.add(pnMonAn);
        // them dua ke ben phai
        pnDatBan.setBorder(RightBorder);
        pnThanhToan.setBorder(RightBorder);
        pnKhachHang.setBorder(RightBorder);
        pnThucDon.setBorder(RightBorder);
        pnNguyenLieu.setBorder(RightBorder);
        pnMonAn.setBorder(RightBorder);
        // them mau nen
        pnDatBan.setBackground(new Color(240, 187, 187));
        pnThanhToan.setBackground(new Color(240, 187, 187));
        pnKhachHang.setBackground(new Color(240, 187, 187));
        pnThucDon.setBackground(new Color(240, 187, 187));
        pnNguyenLieu.setBackground(new Color(240, 187, 187));
        pnMonAn.setBackground(new Color(240, 187, 187));
        // them Jlabel vao cac panel
        pnDatBan.add(lblDatBan);
        pnThanhToan.add(lblThanhToan);
        pnKhachHang.add(lblKhachHang);
        pnThucDon.add(lblThucDon);
        pnNguyenLieu.add(lblNguyenLieu);
        pnMonAn.add(lblMonAn);
        // them font chu va margin 30 px
        lblDatBan.setFont(new Font("Arial", Font.BOLD, 30));
        lblDatBan.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblThanhToan.setFont(new Font("Arial", Font.BOLD, 30));
        lblThanhToan.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblKhachHang.setFont(new Font("Arial", Font.BOLD, 30));
        lblKhachHang.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblThucDon.setFont(new Font("Arial", Font.BOLD, 30));
        lblThucDon.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblNguyenLieu.setFont(new Font("Arial", Font.BOLD, 30));
        lblNguyenLieu.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblMonAn.setFont(new Font("Arial", Font.BOLD, 30));
        lblMonAn.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
    }

    void CaiDat() {
        pnHeThong.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    // Nếu TrangThaiThietLap là false, tức là panel đang đóng
                    if (!TrangThaiThietLap) {
                        // Mở các panel và cập nhật trạng thái
                        moCacPanel();

                        TrangThaiThietLap = true;
                    } else {
                        // Đóng các panel và cập nhật trạng thái
                        dongCacPanel();
                        TrangThaiThietLap = false;
                    }
                }
            }
        });
    }

    void ThongKe() {
        pnThongKe.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    // Nếu TrangThaiThietLap là false, tức là panel đang đóng
                    if (!TrangThaiThongKe) {
                        // Mở các panel và cập nhật trạng thái
                        moCacPanelThongKe();
                        TrangThaiThongKe = true;
                    } else {
                        // Đóng các panel và cập nhật trạng thái
                        dongCacPanelThongKe();
                        TrangThaiThongKe = false;
                    }
                }
            }
        });
    }

    void QuanLy() {
        pnQuanLy.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    // Nếu TrangThaiThietLap là false, tức là panel đang đóng
                    if (!TrangThaiQuanLy) {
                        // Mở các panel và cập nhật trạng thái
                        moCacPanelQuanLy();
                        TrangThaiQuanLy = true;
                    } else {
                        // Đóng các panel và cập nhật trạng thái
                        dongCacPanelQuanLy();
                        TrangThaiQuanLy = false;
                    }
                }
            }
        });
    }

// Phương thức mở các panel
    void moCacPanelThongKe() {
        Border bottomBorder = BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK);
        Border bottomBorderDanhGia = BorderFactory.createMatteBorder(0, 1, 2, 1, Color.BLACK);

        pnTKDoanhThu.setBackground(new Color(196, 185, 185));
        pnTKDoanhThu.setOpaque(true);
        pnTKDoanhThu.setBorder(bottomBorder);

        pnTKMonAn.setBackground(new Color(196, 185, 185));
        pnTKMonAn.setOpaque(true);
        pnTKMonAn.setBorder(bottomBorderDanhGia);

        pnTKDoanhThu.setBounds(pnThongKe.getWidth() * 2, 0, pnThongKe.getWidth(), pnThongKe.getHeight());

        pnMenuCon2.setLayout(null);
        pnView.setLayout(null);
        pnMenuCon2.add(pnTKDoanhThu);

        pnView.add(pnTKMonAn);

        pnTKMonAn.setBounds(pnThongKe.getWidth() * 2, 0, pnThongKe.getWidth(), pnThongKe.getHeight());

        pnTKDoanhThu.add(lbThongKeDoanhThu);
        pnTKMonAn.add(lbThongKeMonAn);

        lbThongKeDoanhThu.setFont(new Font("Arial", Font.BOLD, 30));
        lbThongKeDoanhThu.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lbThongKeMonAn.setFont(new Font("Arial", Font.BOLD, 30));
        lbThongKeMonAn.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        pnMenuCon2.setComponentZOrder(pnTKDoanhThu, 0);
        pnView.setComponentZOrder(pnTKMonAn, 0);

        fr.revalidate();
        fr.repaint();
    }

// Phương thức mở các panel
    void moCacPanel() {
        Border bottomBorder = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK);
        fr.setLayout(null);
        pnDoiMatKhau.setBackground(new Color(196, 185, 185));
        pnDoiMatKhau.setOpaque(true);
        pnDoiMatKhau.setBorder(bottomBorder);

        pnDangXuat.setBackground(new Color(196, 185, 185));
        pnDangXuat.setOpaque(true);
        pnDangXuat.setBorder(bottomBorder);

        pnDoiMatKhau.setPreferredSize(new Dimension(256, 100));
        pnDangXuat.setPreferredSize(new Dimension(256, 100));

        pnDoiMatKhau.setBounds(0, 0, pnHeThong.getWidth(), pnHeThong.getHeight());

        pnMenuCon2.setLayout(null);
        pnView.setLayout(null);
        pnMenuCon2.add(pnDoiMatKhau);

        pnView.add(pnDangXuat);

        int panelWidth = 256;
        int panelHeight = 100;

        pnDangXuat.setBounds(0, 0, pnHeThong.getWidth(), pnHeThong.getHeight());

        pnDoiMatKhau.add(lblDoiMatKhau);
        pnDangXuat.add(lblDangXuat);

        lblDoiMatKhau.setFont(new Font("Arial", Font.BOLD, 30));
        lblDoiMatKhau.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblDangXuat.setFont(new Font("Arial", Font.BOLD, 30));
        lblDangXuat.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblThoat.setFont(new Font("Arial", Font.BOLD, 30));
        lblThoat.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        pnMenuCon2.setComponentZOrder(pnDoiMatKhau, 0);
        pnView.setComponentZOrder(pnDangXuat, 0);

        fr.revalidate();
        fr.repaint();
    }

    void moCacPanelQuanLy() {
        Border bottomBorder = BorderFactory.createMatteBorder(0, 1, 2, 0, Color.BLACK);
        Border bottomBorderDanhGia = BorderFactory.createMatteBorder(0, 1, 2, 1, Color.BLACK);

        pnNhanVien.setBackground(new Color(196, 185, 185));
        pnNhanVien.setOpaque(true);
        pnNhanVien.setBorder(bottomBorder);

        pnDanhGia.setBackground(new Color(196, 185, 185));
        pnDanhGia.setOpaque(true);
        pnDanhGia.setBorder(bottomBorderDanhGia);

        Point location = pnQuanLy.getLocation();
        int x = (int) location.getX();
        int y = (int) location.getY();

        pnNhanVien.setBounds(pnQuanLy.getWidth(), 0, pnQuanLy.getWidth(), 100);

        pnMenuCon2.setLayout(null);
        pnView.setLayout(null);
        pnMenuCon2.add(pnNhanVien);

        pnView.add(pnDanhGia);

        int panelWidth = 257;
        int panelHeight = 100;

        pnDanhGia.setBounds(pnQuanLy.getWidth(), 0, pnQuanLy.getWidth(), panelHeight);

        pnNhanVien.add(lblNhanVien);
        pnDanhGia.add(lblDanhGia);

        lblNhanVien.setFont(new Font("Arial", Font.BOLD, 30));
        lblNhanVien.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblDanhGia.setFont(new Font("Arial", Font.BOLD, 30));
        lblDanhGia.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        pnMenuCon2.setComponentZOrder(pnNhanVien, 0);
        pnView.setComponentZOrder(pnDanhGia, 0);

        fr.revalidate();
        fr.repaint();
    }

// Phương thức đóng các panel
    void dongCacPanel() {
        // Xóa các panel khỏi pnMenuCon2 và pnView
        pnMenuCon2.remove(pnDoiMatKhau);
        pnView.remove(pnDangXuat);

        fr.revalidate();
        fr.repaint();
    }

    void dongCacPanelQuanLy() {
        // Xóa các panel khỏi pnMenuCon2 và pnView
        pnMenuCon2.remove(pnNhanVien);
        pnView.remove(pnDanhGia);

        fr.revalidate();
        fr.repaint();
    }

// Phương thức đóng các panel
    void dongCacPanelThongKe() {
        // Xóa các panel khỏi pnMenuCon2 và pnView
        pnMenuCon2.remove(pnTKDoanhThu);
        pnView.remove(pnTKMonAn);

        fr.revalidate();
        fr.repaint();
    }
}
