package QuanLyHoaDonDien.presentation.timkiem;

import QuanLyHoaDonDien.dto.HoaDonResponse;
import QuanLyHoaDonDien.entity.HoaDon;
import QuanLyHoaDonDien.entity.HoaDonNuocNgoai;
import QuanLyHoaDonDien.entity.HoaDonVietNam;
import QuanLyHoaDonDien.service.tim.TimHoaDonService;

import javax.swing.*;
import java.awt.*;

public class TimHoaDonDialog extends JFrame {
    private JTextField txtMaKH;
    private JButton btnTim;
    private JTextArea txtKetQua;
    private TimHoaDonService service;

    public TimHoaDonDialog(TimHoaDonService service) {
        this.service = service;
        initUI();
    }

    private void initUI() {
        setTitle("Tìm kiếm Hóa đơn");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel nhập liệu
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Nhập Mã Khách hàng:"));
        txtMaKH = new JTextField(15);
        topPanel.add(txtMaKH);
        btnTim = new JButton("Tìm");
        btnTim.addActionListener(e -> timHoaDon());
        topPanel.add(btnTim);

        add(topPanel, BorderLayout.NORTH);

        // Panel hiển thị kết quả
        txtKetQua = new JTextArea();
        txtKetQua.setEditable(false);
        txtKetQua.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(txtKetQua);
        add(scrollPane, BorderLayout.CENTER);

        // Panel nút bấm
        JPanel bottomPanel = new JPanel();
        JButton btnDong = new JButton("Đóng");
        btnDong.addActionListener(e -> dispose());
        bottomPanel.add(btnDong);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void timHoaDon() {
        String maKHStr = txtMaKH.getText().trim();
        if (maKHStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int maKH = Integer.parseInt(maKHStr);
            HoaDonResponse response = service.timTheoMaKH(maKH);

            if (response.success) {
                HoaDon hd = (HoaDon) response.data;
                hienThiThongTin(hd);
            } else {
                txtKetQua.setText(""); // Xóa kết quả cũ
                JOptionPane.showMessageDialog(this, response.message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng phải là một con số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hienThiThongTin(HoaDon hd) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- THÔNG TIN HÓA ĐƠN ---\n\n");
        sb.append(String.format("%-20s: %s\n", "Mã khách hàng", hd.getMaKH()));
        sb.append(String.format("%-20s: %s\n", "Họ tên", hd.getHoTen()));
        sb.append(String.format("%-20s: %s\n", "Ngày ra hóa đơn", hd.getNgayRaHD()));
        sb.append(String.format("%-20s: %,.0f VND\n", "Đơn giá", hd.getDonGia()));

        if (hd instanceof HoaDonVietNam) {
            HoaDonVietNam hdv = (HoaDonVietNam) hd;
            sb.append(String.format("%-20s: %s\n", "Loại khách hàng", hdv.getLoaiKH()));
            sb.append(String.format("%-20s: %,.2f kWh\n", "Số KW tiêu thụ", hdv.getSoKWTieuThu()));
            sb.append(String.format("%-20s: %,.2f\n", "Định mức", hdv.getDinhMuc()));
        } else if (hd instanceof HoaDonNuocNgoai) {
            HoaDonNuocNgoai hdn = (HoaDonNuocNgoai) hd;
            sb.append(String.format("%-20s: %s\n", "Quốc tịch", hdn.getQuocTich()));
            sb.append(String.format("%-20s: %,.2f kWh\n", "Số KW tiêu thụ", hdn.getSoKWTieuThu()));
        }
        sb.append(String.format("\n%-20s: %,.2f VND\n", "THÀNH TIỀN", hd.tinhThanhTien()));
        txtKetQua.setText(sb.toString());
    }
}