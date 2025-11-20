package QuanLyHoaDonDien.presentation;

import java.awt.BorderLayout;
import javax.swing.*;
import QuanLyHoaDonDien.persistence.FileHoaDonRepository;
import QuanLyHoaDonDien.service.them.ThemHoaDonService;
import QuanLyHoaDonDien.service.xoa.XoaHoaDonService;
import QuanLyHoaDonDien.service.sua.SuaHoaDonService;
import QuanLyHoaDonDien.service.indanhsach.InDanhSachService;
import QuanLyHoaDonDien.service.tim.TimHoaDonService;
import QuanLyHoaDonDien.service.thongke.ThongKeService;
import QuanLyHoaDonDien.presentation.them.ThemHoaDonDialog;
import QuanLyHoaDonDien.presentation.xoa.XoaHoaDonDialog;
import QuanLyHoaDonDien.presentation.sua.SuaHoaDonDialog;
import QuanLyHoaDonDien.presentation.indanhsach.InDanhSachDialog;
import QuanLyHoaDonDien.presentation.thongke.ThongKeDialog;
import QuanLyHoaDonDien.presentation.timkiem.TimHoaDonDialog;

public class QLHoaDonMenuGUI extends JFrame {
    private FileHoaDonRepository repository;
    private ThemHoaDonService themService;
    private XoaHoaDonService xoaService;
    private SuaHoaDonService suaService;
    private InDanhSachService inDSService;
    private ThongKeService thongKeService;
    private TimHoaDonService timService;

    public QLHoaDonMenuGUI() {
        repository = new FileHoaDonRepository();
        themService = new ThemHoaDonService(repository);
        xoaService = new XoaHoaDonService(repository);
        suaService = new SuaHoaDonService(repository);
        inDSService = new InDanhSachService(repository);
        thongKeService = new ThongKeService(repository);
        timService = new TimHoaDonService(repository);
        initUI();
    }

    private void initUI() {
        setTitle("Quản lý Hóa đơn Tiền Điện");
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu chucNang = new JMenu("Chức năng");

        JMenuItem themHD = new JMenuItem("1. Thêm Hóa đơn");
        themHD.addActionListener(e -> new ThemHoaDonDialog(themService));
        chucNang.add(themHD);

        JMenuItem suaHD = new JMenuItem("2. Sửa Hóa đơn");
        suaHD.addActionListener(e -> new SuaHoaDonDialog(suaService));
        chucNang.add(suaHD);

        JMenuItem xoaHD = new JMenuItem("3. Xóa Hóa đơn");
        xoaHD.addActionListener(e -> new XoaHoaDonDialog(xoaService));
        chucNang.add(xoaHD);

        chucNang.addSeparator();

        JMenuItem timHD = new JMenuItem("4. Tìm kiếm Hóa đơn");
        timHD.addActionListener(e -> new TimHoaDonDialog(timService));
        chucNang.add(timHD);

        JMenuItem inDSHD = new JMenuItem("5. In danh sách hóa đơn theo tháng");
        inDSHD.addActionListener(e -> new InDanhSachDialog(inDSService));
        chucNang.add(inDSHD);

        JMenuItem thongKe = new JMenuItem("6. Thống kê Theo Loại kèm Tính Tổng Tiền");
        thongKe.addActionListener(e -> new ThongKeDialog(thongKeService));
        chucNang.add(thongKe);

        menuBar.add(chucNang);
        setJMenuBar(menuBar);

        JLabel lbFooter = new JLabel("Được phát triển bởi DoiBunJQK", SwingConstants.CENTER);
        add(lbFooter, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new QLHoaDonMenuGUI();
    }
}