package QuanLyHoaDonDien.presentation.indanhsach;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import QuanLyHoaDonDien.entity.HoaDon;
import QuanLyHoaDonDien.entity.HoaDonVietNam;
import QuanLyHoaDonDien.entity.HoaDonNuocNgoai;
import QuanLyHoaDonDien.service.indanhsach.InDanhSachService;
import QuanLyHoaDonDien.dto.HoaDonResponse;

public class InDanhSachDialog extends JFrame {
    private JComboBox<Integer> cboThang;
    private JTable table;
    private DefaultTableModel model;
    private String[] columns = { "Mã KH", "Loại", "Họ tên", "Ngày", "Đơn giá", "Số KW", "Thành tiền" };
    private InDanhSachService service;

    public InDanhSachDialog(InDanhSachService service) {
        this.service = service;
        initUI();
    }

    private void initUI() {
        setTitle("In Danh sách Hóa đơn");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelTop = new JPanel(new GridLayout(1, 3, 10, 5));
        panelTop.add(new JLabel("Chọn tháng:"));
        
        Integer[] thang = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        cboThang = new JComboBox<>(thang);
        cboThang.setSelectedItem(11);
        panelTop.add(cboThang);
        
        JButton btnXuat = new JButton("Xuất");
        btnXuat.addActionListener(e -> xuatThang());
        panelTop.add(btnXuat);
        
        add(panelTop, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[0][columns.length], columns);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }

    private void xuatThang() {
        int thang = (Integer) cboThang.getSelectedItem();
        HoaDonResponse response = service.layTheoThang(thang);
        
        model.setRowCount(0);
        
        if (response.success) {
            ArrayList<HoaDon> list = (ArrayList<HoaDon>) response.data;
            for (HoaDon hd : list) {
                Object[] row = new Object[columns.length];
                row[0] = hd.getMaKH();
                row[1] = hd instanceof HoaDonVietNam ? "Việt Nam" : "Nước ngoài";
                row[2] = hd.getHoTen();
                row[3] = hd.getNgayRaHD();
                row[4] = hd.getDonGia();
                row[5] = hd instanceof HoaDonVietNam ? 
                         ((HoaDonVietNam)hd).getSoKWTieuThu() : 
                         ((HoaDonNuocNgoai)hd).getSoKWTieuThu();
                row[6] = hd.tinhThanhTien();
                model.addRow(row);
            }
            setTitle("Danh sách Hóa đơn Tháng " + thang + " (" + list.size() + " hóa đơn)");
        } else {
            JOptionPane.showMessageDialog(this, response.message);
        }
    }
}
