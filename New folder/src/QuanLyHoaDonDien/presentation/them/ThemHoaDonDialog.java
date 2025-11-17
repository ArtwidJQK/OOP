package QuanLyHoaDonDien.presentation.them;

import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.*;
import QuanLyHoaDonDien.service.them.ThemHoaDonService;
import QuanLyHoaDonDien.dto.HoaDonRequest;
import QuanLyHoaDonDien.dto.HoaDonResponse;

public class ThemHoaDonDialog extends JFrame {
    private JTextField txtMaKH, txtHoTen, txtNgayRaHD, txtDonGia, txtSoKW, txtDinhMuc, txtQuocTich;
    private JComboBox<String> cboLoai;
    private JButton btnLuu, btnCancel;
    private ThemHoaDonService service;

    public ThemHoaDonDialog(ThemHoaDonService service) {
        this.service = service;
        initUI();
    }

    private void initUI() {
        setTitle("Thêm Hóa đơn");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 5, 5));

        add(new JLabel("Mã KH:"));
        txtMaKH = new JTextField();
        add(txtMaKH);

        add(new JLabel("Họ tên:"));
        txtHoTen = new JTextField();
        add(txtHoTen);

        add(new JLabel("Ngày (yyyy-MM-dd):"));
        txtNgayRaHD = new JTextField();
        add(txtNgayRaHD);

        add(new JLabel("Đơn giá:"));
        txtDonGia = new JTextField();
        add(txtDonGia);

        add(new JLabel("Loại KH:"));
        cboLoai = new JComboBox<>(new String[]{"Việt Nam - Sinh hoạt", "Việt Nam - Kinh doanh", "Việt Nam - Sản xuất", "Nước ngoài"});
        add(cboLoai);

        add(new JLabel("Số KW:"));
        txtSoKW = new JTextField();
        add(txtSoKW);

        add(new JLabel("Định mức:"));
        txtDinhMuc = new JTextField();
        add(txtDinhMuc);

        add(new JLabel("Quốc tịch:"));
        txtQuocTich = new JTextField();
        add(txtQuocTich);

        btnLuu = new JButton("Lưu");
        btnLuu.addActionListener(e -> luuHoaDon());
        add(btnLuu);

        btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);

        setVisible(true);
    }

    private void luuHoaDon() {
        try {
            HoaDonRequest request = new HoaDonRequest();
            request.maKH = Integer.parseInt(txtMaKH.getText());
            request.hoTen = txtHoTen.getText();
            request.ngayRaHD = LocalDate.parse(txtNgayRaHD.getText());
            request.donGia = Double.parseDouble(txtDonGia.getText());
            request.loaiKH = cboLoai.getSelectedItem().toString();
            request.soKWTieuThu = Double.parseDouble(txtSoKW.getText());
            request.dinhMuc = Double.parseDouble(txtDinhMuc.getText());
            request.quocTich = txtQuocTich.getText();

            HoaDonResponse response = service.them(request);
            if (response.success) {
                JOptionPane.showMessageDialog(this, response.message);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, response.message, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}