package View;

import Controller.ChuyenManHinh;
import Controller.DanhMuc;
import Controller.TaiKhoanDAO;
import Model.TaiKhoan;
import Utils.Auth;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import net.sf.jasperreports.engine.json.parser.JsonQueryParserTokenTypes;

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
    TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    boolean TrangThaiThietLap = false;
    boolean TrangThaiThongKe = false;
    boolean TrangThaiQuanLy = false;
    static JFrame fr = new JFrame();
// cac tad o tren
    JPanel pnMenu = new JPanel();
    static JPanel pnView = new JPanel();
    JPanel pnMenuCon1 = new JPanel();
    JPanel pnMenuConTrai = new JPanel();
    JPanel pnMenuConPhai = new JPanel();
    JPanel pnMenuCon2 = new JPanel();
    JPanel pnHeThong = new JPanel();
    JPanel pnQuanLy = new JPanel();
    JPanel pnThongKe = new JPanel();

    JPanel pnDoiMatKhau = new JPanel();
    JPanel pnDangXuat = new JPanel();

    JPanel pnTKDoanhThu = new JPanel();
    JPanel pnTKMonAn = new JPanel();
    JPanel pnTKDanhGia = new JPanel();

    JLabel lblHeThong = new JLabel("Hệ thống");
    JLabel lblQuanLy = new JLabel("Quản lý");
    JLabel lblThongKe = new JLabel("Thống kê");

    JLabel lblThongKeDoanhThu = new JLabel("Doanh thu");
    JLabel lblThongKeMonAn = new JLabel("Món ăn");
    JLabel lblThongDanhGia = new JLabel("Đánh giá");

    JLabel lbVaiTro = new JLabel("Quản lý: Võ Thanh Tùng");

    JLabel lblDoiMatKhau = new JLabel("Đổi mật  khẩu");
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
        item.add(new DanhMuc("ThongKeMonAn", pnTKMonAn, lblThongKeMonAn));
        item.add(new DanhMuc("ThongKeDoanhThu", pnTKDoanhThu, lblThongKeDoanhThu));
        item.add(new DanhMuc("NhanVien", pnNhanVien, lblNhanVien));
        item.add(new DanhMuc("HoaDon", pnThanhToan, lblThanhToan));
        item.add(new DanhMuc("TKDanhGia", pnTKDanhGia, lblThongDanhGia));
        item.add(new DanhMuc("NguyenVatLieu", pnNguyenLieu, lblNguyenLieu));
        item.add(new DanhMuc("DoiMatKhau", pnDoiMatKhau, lblDoiMatKhau));

        item.add(new DanhMuc("KhachHang", pnKhachHang, lblKhachHang));
        control.setEvent(item);

    }

    public static void main(String[] args) {
        Main m = new Main();
        pnView.removeAll();
        m.GiaoDien();
        m.themSuKienChoTatCaPanel();
        m.CaiDat();
        m.ListPanelMenu();
        m.ListAllPanel();
        m.ListPanelHeThong();
        m.ThongKe();
        m.QuanLy();
        m.VaiTro();
        m.setIconPanel();
        m.setIconLabel();
        m.logout();
        m.capNhatVaiTro();
    }

    public static void callMain() {
        Main m = new Main();
        pnView.removeAll();
        m.GiaoDien();
        m.CaiDat();
        m.ThongKe();
        m.ListPanelHeThong();
        m.ListAllPanel();
        m.QuanLy();
        m.ListPanelMenu();
        m.themSuKienChoTatCaPanel();
        m.VaiTro();
        m.setIconLabel();
        m.setIconPanel();
        m.logout();
        m.capNhatVaiTro();
    }

    void setIconPanel() {
        ImageIcon icon = new ImageIcon("Logos/TrangChu.jpg");
        JLabel lbIcon = new JLabel();
        pnView.setLayout(null);
        pnView.add(lbIcon);

        // Thay đổi kích thước của biểu tượng
        Image scaledImage = icon.getImage().getScaledInstance(1550, 650, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Đặt biểu tượng đã thay đổi kích thước cho nhãn
        lbIcon.setIcon(scaledIcon);

        // Đặt vị trí và kích thước cho nhãn
        lbIcon.setBounds(0, 0, pnView.getWidth(), pnView.getHeight());
    }

    public static void setIcon(JLabel label, String[] paths, int index) {
        if (index >= 0 && index < paths.length) {
            ImageIcon icon = new ImageIcon(paths[index]);
            label.setIcon(icon);
        }
    }

    void setIconLabel() {
        String path[] = {"Logos/datban.png", "Logos/thanhtoan.png", "Logos/khachhang.png", "Logos/NguyenVatLieu.png", "Logos/thucdon.png",
            "Logos/MonAn.png", "Logos/hethong.png", "Logos/doimatkhau.png", "Logos/dangxuat.png",
            "Logos/quanly.png", "Logos/nhanvien.png", "Logos/danhgia.png", "Logos/thongke.png", "Logos/doanhthu.png", "Logos/tkmonan.png"};
        int[] iconIndexes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        for (int i = 0; i < iconIndexes.length; i++) {
            setIcon(getLabelByIndex(i), path, iconIndexes[i]);
        }
    }

    void ChamDutPanelVaLabel() {

        pnView.removeAll();

    }

    void logout() {
        pnDangXuat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                fr.dispose();

                new LoginJFrame().setVisible(true);
                ChamDutPanelVaLabel();
            }
        });
    }

    void capNhatVaiTro() {
        class vaiTro extends Thread {

            @Override
            public void run() {
                while (true) {
                    init();
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        vaiTro th = new vaiTro();
        th.start();
    }

    void init() {
        TaiKhoan tk = tkDAO.selectById(Auth.user.getTenTaiKhoan());
        Auth.user = tk;
        lbVaiTro.setText((Auth.isManager() ? "Quản lý" : "Nhân viên") + ": " + Auth.user.getTenTaiKhoan());
    }

    JLabel getLabelByIndex(int index) {
        switch (index) {
            case 0:
                return lblDatBan;
            case 1:
                return lblThanhToan;
            case 2:
                return lblKhachHang;
            case 3:
                return lblNguyenLieu;
            case 4:
                return lblThucDon;
            case 5:
                return lblMonAn;
            case 6:
                return lblHeThong;
            case 7:
                return lblDoiMatKhau;
            case 8:
                return lblDangXuat;
            case 9:
                return lblQuanLy;
            case 10:
                return lblNhanVien;
            case 11:
                return lblThongDanhGia;
            case 12:
                return lblThongKe;
            case 13:
                return lblThongKeDoanhThu;
            case 14:
                return lblThongKeMonAn;
            default:
                return null;
        }
    }

    static public void ChuyenThanhToan(int daBam) {
        if (daBam == 1) {
            HoaDonJPanel hd = new HoaDonJPanel();
            pnView.removeAll();
            pnView.add(hd);
            pnView.revalidate();
            pnView.repaint();
            daBam = 0;
        }
    }

    void VaiTro() {
        ImageIcon icon = new ImageIcon("Logos/user.png");
        lbVaiTro.setIcon(icon);
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
        pnHeThong.add(lblHeThong);
        pnQuanLy.add(lblQuanLy);
        pnThongKe.add(lblThongKe);

        // set font cho chu label
        lblHeThong.setFont(new Font("Arial", Font.BOLD, 30));
        lblQuanLy.setFont(new Font("Arial", Font.BOLD, 30));
        lblThongKe.setFont(new Font("Arial", Font.BOLD, 30));

        // chinh khoang cach  chu label
        lblHeThong.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        lblQuanLy.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        lblThongKe.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

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
                    // Đóng các panel khác và mở panel HeThong
                    if (!TrangThaiThietLap) {
                        moCacPanel();
                        dongCacPanelQuanLy();
                        dongCacPanelThongKe();
                        // Cập nhật trạng thái
                        TrangThaiThietLap = true;
                        TrangThaiQuanLy = false;
                        TrangThaiThongKe = false;
                    } else {
                        dongCacPanel();
                        TrangThaiThietLap = false;
                    }
                } else {
                    if (!TrangThaiThietLap) {
                        moCacPanel();
                        dongCacPanelQuanLy();
                        dongCacPanelThongKe();
                        // Cập nhật trạng thái
                        TrangThaiThietLap = true;
                        TrangThaiQuanLy = false;
                        TrangThaiThongKe = false;
                    } else {
                        dongCacPanel();
                        TrangThaiThietLap = false;
                    }
                }
            }
        });
    }

    void QuanLy() {
        pnQuanLy.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    // Đóng các panel khác và mở panel QuanLy
                    if (!TrangThaiQuanLy) {
                        moCacPanelQuanLy();
                        dongCacPanel();
                        dongCacPanelThongKe();
                        // Cập nhật trạng thái
                        TrangThaiThietLap = false;
                        TrangThaiQuanLy = true;
                        TrangThaiThongKe = false;
                    } else {
                        dongCacPanelQuanLy();
                        TrangThaiQuanLy = false;
                    }
                } else {
                    // Đóng các panel khác và mở panel QuanLy
                    if (!TrangThaiQuanLy) {
                        moCacPanelQuanLy();
                        dongCacPanel();
                        dongCacPanelThongKe();
                        // Cập nhật trạng thái
                        TrangThaiThietLap = false;
                        TrangThaiQuanLy = true;
                        TrangThaiThongKe = false;
                    } else {
                        dongCacPanelQuanLy();
                        TrangThaiQuanLy = false;
                    }
                }
            }
        });
    }

    void ThongKe() {
        pnThongKe.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    // Đóng các panel khác và mở panel ThongKe
                    if (!TrangThaiThongKe) {
                        moCacPanelThongKe();
                        dongCacPanel();
                        dongCacPanelQuanLy();
                        // Cập nhật trạng thái
                        TrangThaiThietLap = false;
                        TrangThaiQuanLy = false;
                        TrangThaiThongKe = true;
                    } else {
                        dongCacPanelThongKe();
                        TrangThaiThongKe = false;
                    }
                } else {
                    // Đóng các panel khác và mở panel ThongKe
                    if (!TrangThaiThongKe) {
                        moCacPanelThongKe();
                        dongCacPanel();
                        dongCacPanelQuanLy();
                        // Cập nhật trạng thái
                        TrangThaiThietLap = false;
                        TrangThaiQuanLy = false;
                        TrangThaiThongKe = true;
                    } else {
                        dongCacPanelThongKe();
                        TrangThaiThongKe = false;
                    }
                }
            }
        });
    }

    void dongTatCaPanel() {
        // Đóng tất cả các panel 
        dongCacPanel();
        dongCacPanelQuanLy();
        dongCacPanelThongKe();
    }

    void themSuKienChoTatCaPanel() {
        // Danh sách các JPanel cần thêm sự kiện
        List<JPanel> panels = new ArrayList<>();
        panels.add(pnMenu);
        panels.add(pnView);
        panels.add(pnMenuCon1);
        panels.add(pnMenuCon2);
        panels.add(pnMenuConTrai);
        panels.add(pnMenuConPhai);
        panels.add(pnDoiMatKhau);
        panels.add(pnDangXuat);
        panels.add(pnTKDoanhThu);
        panels.add(pnTKMonAn);
        panels.add(pnDatBan);
        panels.add(pnThanhToan);
        panels.add(pnKhachHang);
        panels.add(pnThucDon);
        panels.add(pnNguyenLieu);
        panels.add(pnMonAn);
        panels.add(pnNhanVien);
        panels.add(pnDanhGia);

        // Lặp qua danh sách các JPanel và thêm sự kiện mouseClicked cho mỗi JPanel
        for (JPanel panel : panels) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        if (!TrangThaiThietLap || !TrangThaiQuanLy || !TrangThaiThongKe) {
                            dongTatCaPanel();
                        }
                    }
                }
            });
        }
    }

// Phương thức mở các panel
    void moCacPanelThongKe() {
        if (!Auth.user.isVaiTro()) {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào Thống kê.");
            return;
        }
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);
        Border bottomBorderDanhGia = BorderFactory.createMatteBorder(0, 2, 1, 1, Color.BLACK);
        Border bottomBordertkdg = BorderFactory.createMatteBorder(0, 2, 2, 1, Color.BLACK);

        pnTKDoanhThu.setBackground(new Color(196, 185, 185));
        pnTKDoanhThu.setOpaque(true);
        pnTKDoanhThu.setBorder(bottomBorder);

        pnTKMonAn.setBackground(new Color(196, 185, 185));
        pnTKMonAn.setOpaque(true);
        pnTKMonAn.setBorder(bottomBorderDanhGia);

        pnTKDanhGia.setBackground(new Color(196, 185, 185));
        pnTKDanhGia.setOpaque(true);
        pnTKDanhGia.setBorder(bottomBordertkdg);

        pnMenuCon2.setLayout(null);
        pnView.setLayout(null);
        pnMenuCon2.add(pnTKDoanhThu);

        pnView.add(pnTKMonAn);
        pnView.add(pnTKDanhGia);

        pnTKDoanhThu.setBounds(pnThongKe.getWidth() * 2, 0, pnThongKe.getWidth(), pnKhachHang.getHeight());
        pnTKMonAn.setBounds(pnThongKe.getWidth() * 2, 0, pnThongKe.getWidth() + 1, pnThongKe.getHeight());
        pnTKDanhGia.setBounds(pnThongKe.getWidth() * 2, pnHeThong.getHeight() * 2 + 1, pnThongKe.getWidth(), pnThongKe.getHeight());

        pnTKDoanhThu.add(lblThongKeDoanhThu);
        pnTKMonAn.add(lblThongKeMonAn);
        pnTKDanhGia.add(lblThongDanhGia);

        lblThongKeDoanhThu.setFont(new Font("Arial", Font.BOLD, 30));
        lblThongKeDoanhThu.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblThongKeMonAn.setFont(new Font("Arial", Font.BOLD, 30));
        lblThongKeMonAn.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblThongDanhGia.setFont(new Font("Arial", Font.BOLD, 30));
        lblThongDanhGia.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        // Sử dụng Thread để tạo hiệu ứng di chuyển từ trên xuống dưới
        new Thread(() -> {
            for (int y = -pnTKDoanhThu.getHeight() + 8; y <= 0; y += 10) {
                try {
                    Thread.sleep(10); // Đợi 10 miliseconds trước khi di chuyển
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                pnMenuCon2.setComponentZOrder(pnTKDoanhThu, 0);
                pnView.setComponentZOrder(pnTKMonAn, 0);
                pnView.setComponentZOrder(pnTKDanhGia, 0); // Thay đổi chỉ số z-order của panel Thống kê Đánh giá
                pnTKDoanhThu.setLocation(pnThongKe.getWidth() * 2, y); // Di chuyển panel Thống kê Doanh thu
                pnTKMonAn.setLocation(pnThongKe.getWidth() * 2 - 1, y); // Di chuyển panel Thống kê Món Ăn
                pnTKDanhGia.setLocation(pnThongKe.getWidth() * 2 - 1, pnTKMonAn.getY() + pnTKMonAn.getHeight()); // Di chuyển panel Thống kê Đánh giá
            }
        }).start();
        fr.revalidate();
        fr.repaint();
    }

// Phương thức mở các panel
    void moCacPanel() {
        Border bottomBorder = BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK);
        Border bottomBorderDangXuat = BorderFactory.createMatteBorder(0, 1, 2, 0, Color.BLACK);
        fr.setLayout(null);
        pnDoiMatKhau.setBackground(new Color(196, 185, 185));
        pnDoiMatKhau.setOpaque(true);
        pnDoiMatKhau.setBorder(bottomBorder);

        pnDangXuat.setBackground(new Color(196, 185, 185));
        pnDangXuat.setOpaque(true);
        pnDangXuat.setBorder(bottomBorderDangXuat);

        pnMenuCon2.setLayout(null);
        pnView.setLayout(null);
        pnMenuCon2.add(pnDoiMatKhau);

        pnView.add(pnDangXuat);

        pnDoiMatKhau.setBounds(0, 0, pnDatBan.getWidth(), pnDatBan.getHeight());
        pnDangXuat.setBounds(0, 0, pnHeThong.getWidth(), pnHeThong.getHeight());

        pnDoiMatKhau.add(lblDoiMatKhau);
        pnDangXuat.add(lblDangXuat);

        lblDoiMatKhau.setFont(new Font("Arial", Font.BOLD, 30));
        lblDoiMatKhau.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblDangXuat.setFont(new Font("Arial", Font.BOLD, 30));
        lblDangXuat.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblThoat.setFont(new Font("Arial", Font.BOLD, 30));
        lblThoat.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        // Sử dụng Thread để tạo hiệu ứng di chuyển từ trên xuống dưới
        new Thread(() -> {
            for (int y = -pnDoiMatKhau.getHeight() + 8; y <= 0; y += 10) {
                try {
                    Thread.sleep(10); // Đợi 10 miliseconds trước khi di chuyển
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                pnMenuCon2.setComponentZOrder(pnDoiMatKhau, 0);
                pnView.setComponentZOrder(pnDangXuat, 0);
                pnDoiMatKhau.setLocation(0, y); // Di chuyển panel DoiMatKhau
                pnDangXuat.setLocation(0, y); // Di chuyển panel DangXuat
            }
        }).start();
        fr.revalidate();
        fr.repaint();
    }

    void moCacPanelQuanLy() {
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);
        Border bottomBorderDanhGia = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK);

        pnNhanVien.setBackground(new Color(196, 185, 185));
        pnNhanVien.setOpaque(true);
        pnNhanVien.setBorder(bottomBorder);
//
//        pnDanhGia.setBackground(new Color(196, 185, 185));
//        pnDanhGia.setOpaque(true);
//        pnDanhGia.setBorder(bottomBorderDanhGia);

        pnMenuCon2.setLayout(null);
        pnView.setLayout(null);
        pnMenuCon2.add(pnNhanVien);

//        pnView.add(pnDanhGia);
        pnNhanVien.setBounds(pnQuanLy.getWidth(), 0, pnQuanLy.getWidth(), pnKhachHang.getHeight());
//        pnDanhGia.setBounds(pnQuanLy.getWidth(), 0, pnNhanVien.getWidth()+1, pnKhachHang.getHeight());

        pnNhanVien.add(lblNhanVien);
//        pnDanhGia.add(lblDanhGia);

        lblNhanVien.setFont(new Font("Arial", Font.BOLD, 30));
        lblNhanVien.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        lblDanhGia.setFont(new Font("Arial", Font.BOLD, 30));
        lblDanhGia.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        // Sử dụng Thread để tạo hiệu ứng di chuyển từ trên xuống dưới
        new Thread(() -> {
            for (int y = -pnNhanVien.getHeight() + 8; y <= 0; y += 10) {
                try {
                    Thread.sleep(10); // Đợi 10 miliseconds trước khi di chuyển
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                pnMenuCon2.setComponentZOrder(pnNhanVien, 0);
//                pnView.setComponentZOrder(pnDanhGia, 0);
                pnNhanVien.setLocation(pnQuanLy.getWidth(), y); // Di chuyển panel DoiMatKhau
//                pnDanhGia.setLocation(pnNhanVien.getWidth()-1, y); // Di chuyển panel DangXuat
            }
        }).start();
        fr.revalidate();
        fr.repaint();
    }

// Phương thức đóng các panel
    void dongCacPanel() {
        new Thread(() -> {
            for (int y = 0; y >= -pnDoiMatKhau.getHeight(); y -= 10) {
                try {
                    Thread.sleep(10); // Đợi 10 miliseconds trước khi di chuyển
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                pnDoiMatKhau.setLocation(0, y); // Di chuyển panel DoiMatKhau
                pnDangXuat.setLocation(0, y); // Di chuyển panel DangXuat
            }

            // Sau khi di chuyển hoàn tất, loại bỏ các panel khỏi pnMenuCon2 và pnView
            pnMenuCon2.remove(pnDoiMatKhau);
            pnView.remove(pnDangXuat);
        }).start();
    }

    void dongCacPanelQuanLy() {
        new Thread(() -> {
            for (int y = 0; y >= -pnNhanVien.getHeight(); y -= 10) {
                try {
                    Thread.sleep(10); // Đợi 10 miliseconds trước khi di chuyển
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                pnNhanVien.setLocation(pnQuanLy.getWidth(), y); // Di chuyển panel DoiMatKhau
                pnDanhGia.setLocation(pnQuanLy.getWidth(), y); // Di chuyển panel DangXuat
            }

            // Sau khi di chuyển hoàn tất, loại bỏ các panel khỏi pnMenuCon2 và pnView
            pnMenuCon2.remove(pnNhanVien);
//            pnView.remove(pnDanhGia);
        }).start();
        fr.revalidate();
        fr.repaint();
    }

// Phương thức đóng các panel
    void dongCacPanelThongKe() {
        new Thread(() -> {
            for (int y = 0; y >= -pnTKDoanhThu.getHeight(); y -= 10) {
                try {
                    Thread.sleep(10); // Đợi 10 miliseconds trước khi di chuyển
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                pnTKDoanhThu.setLocation(pnThongKe.getWidth() * 2, y); // Di chuyển panel DoiMatKhau
                pnTKMonAn.setLocation(pnThongKe.getWidth() * 2, y); // Di chuyển panel DangXuat
                pnTKDanhGia.setLocation(pnThongKe.getWidth() * 2, y); // Di chuyển panel DangXuat
            }

            // Sau khi di chuyển hoàn tất, loại bỏ các panel khỏi pnMenuCon2 và pnView
            pnMenuCon2.remove(pnTKDoanhThu);
            pnView.remove(pnTKMonAn);
            pnView.remove(pnTKDanhGia);
        }).start();
        fr.revalidate();
        fr.repaint();
    }

    void ListPanelMenu() {
        List<JPanel> panels = new ArrayList<>();
        panels.add(pnTKMonAn);
        panels.add(pnDatBan);
        panels.add(pnThanhToan);
        panels.add(pnKhachHang);
        panels.add(pnThucDon);
        panels.add(pnNguyenLieu);
        panels.add(pnMonAn);
        suKienDaBamVao(panels);
        suKienThayDoiMauPanel(panels);
    }

    void ListPanelHeThong() {
        List<JPanel> panels = new ArrayList<>();
        panels.add(pnHeThong);
        panels.add(pnDoiMatKhau);
        panels.add(pnDangXuat);
        panels.add(pnQuanLy);
        panels.add(pnNhanVien);
        panels.add(pnThongKe);
        panels.add(pnTKDoanhThu);
        panels.add(pnTKMonAn);
        panels.add(pnTKDanhGia);
        suKienDaBamVaoHeThong(panels);
        suKienThayMauHeThong(panels);
    }

    void ListAllPanel() {
        List<JPanel> panels = new ArrayList<>();
        panels.add(pnTKMonAn);
        panels.add(pnDatBan);
        panels.add(pnThanhToan);
        panels.add(pnKhachHang);
        panels.add(pnThucDon);
        panels.add(pnNguyenLieu);
        panels.add(pnMonAn);
        panels.add(pnHeThong);
        panels.add(pnDoiMatKhau);
        panels.add(pnDangXuat);
        panels.add(pnQuanLy);
        panels.add(pnNhanVien);
        panels.add(pnThongKe);
        panels.add(pnTKDoanhThu);
        panels.add(pnTKMonAn);
        panels.add(pnTKDanhGia);
        suKienRoiChuotThanhConTro(panels);
    }

    void suKienThayMauHeThong(List<JPanel> panelList) {
        for (JPanel panel : panelList) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Đổi màu của panel khi chuột rơi vào
                    panel.setBackground(Color.GRAY); // Thay đổi màu thành màu đỏ (hoặc bất kỳ màu nào bạn muốn)
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Trở lại màu gốc của panel khi chuột rời khỏi
                    panel.setBackground(new Color(196, 185, 185)); // Màu gốc của panel (hoặc bất kỳ màu nào bạn đã thiết lập trước đó)
                }
            });
        }
    }

    void suKienThayDoiMauPanel(List<JPanel> panelList) {
        for (JPanel panel : panelList) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Đổi màu của panel khi chuột rơi vào
                    panel.setBackground(Color.gray); // Thay đổi màu thành màu đỏ (hoặc bất kỳ màu nào bạn muốn)
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Trở lại màu gốc của panel khi chuột rời khỏi
                    panel.setBackground(new Color(240, 187, 187)); // Màu gốc của panel (hoặc bất kỳ màu nào bạn đã thiết lập trước đó)
                }
            });
        }
    }

    void suKienDaBamVaoHeThong(List<JPanel> panelList) {
        for (JPanel panel : panelList) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    // Thay đổi màu của panel khi bấm vào
                    panel.setBackground(Color.RED); // Thay đổi màu thành màu đen (hoặc bất kỳ màu nào bạn muốn)

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // Trở lại màu gốc của panel khi thả chuột ra
                    panel.setBackground(new Color(196, 185, 185)); // Màu gốc của panel (hoặc bất kỳ màu nào bạn đã thiết lập trước đó)
                }
            });
        }
    }

    void suKienDaBamVao(List<JPanel> panelList) {
        for (JPanel panel : panelList) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    // Thay đổi màu của panel khi bấm vào
                    panel.setBackground(Color.red); // Thay đổi màu thành màu đen (hoặc bất kỳ màu nào bạn muốn)

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // Trở lại màu gốc của panel khi thả chuột ra
                    panel.setBackground(new Color(240, 187, 187)); // Màu gốc của panel (hoặc bất kỳ màu nào bạn đã thiết lập trước đó)
                }
            });
        }
    }

    public static void suKienRoiChuotThanhConTro(List<JPanel> panelList) {
        for (JPanel panel : panelList) {
            // Con trỏ chuột tùy chỉnh
            Cursor customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Đổi con trỏ chuột thành con trỏ tùy chỉnh khi rơi chuột vào
                    panel.setCursor(customCursor);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Trở lại con trỏ chuột mặc định khi chuột rời khỏi panel
                    panel.setCursor(Cursor.getDefaultCursor());
                }
            });
        }
    }
}
