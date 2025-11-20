package QuanLyHoaDonDien.presentation.sua;

import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.*;
import QuanLyHoaDonDien.entity.HoaDon;
import QuanLyHoaDonDien.entity.HoaDonVietNam;
import QuanLyHoaDonDien.entity.HoaDonNuocNgoai;
import QuanLyHoaDonDien.service.sua.SuaHoaDonService;
import QuanLyHoaDonDien.dto.HoaDonRequest;
import QuanLyHoaDonDien.dto.HoaDonResponse;

public class SuaHoaDonDialog extends JFrame {
    private JTextField txtMaKH;
    private JTextField txtHoTen;
    private JTextField txtNgayRaHD;
    private JTextField txtDonGia;
    private JTextField txtSoKW;
    private JTextField txtDinhMuc;
    private JTextField txtQuocTich;
    private JButton btnTim;
    private JButton btnLuu;
    private JButton btnCancel;
    
    private SuaHoaDonService service;
    private HoaDon hdCurrent;

    public SuaHoaDonDialog(SuaHoaDonService service) {
        this.service = service;
        this.hdCurrent = null;
        initUI();
    }

    private void initUI() {
        setTitle("Sửa Hóa đơn");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(12, 2, 5, 5));

        add(new JLabel("Mã Khách hàng:"));
        txtMaKH = new JTextField(20);
        add(txtMaKH);

        add(new JLabel(""));
        btnTim = new JButton("Tìm");
        btnTim.addActionListener(e -> timHoaDon());
        add(btnTim);

        add(new JLabel("Họ tên:"));
        txtHoTen = new JTextField(20);
        txtHoTen.setEditable(false);
        add(txtHoTen);

        add(new JLabel("Ngày (yyyy-MM-dd):"));
        txtNgayRaHD = new JTextField(20);
        add(txtNgayRaHD);

        add(new JLabel("Đơn giá:"));
        txtDonGia = new JTextField(20);
        add(txtDonGia);

        add(new JLabel("Số KW:"));
        txtSoKW = new JTextField(20);
        add(txtSoKW);

        add(new JLabel("Định mức:"));
        txtDinhMuc = new JTextField(20);
        add(txtDinhMuc);

        add(new JLabel("Quốc tịch:"));
        txtQuocTich = new JTextField(20);
        add(txtQuocTich);

        add(new JLabel(""));
        add(new JLabel(""));

        btnLuu = new JButton("Lưu");
        btnLuu.addActionListener(e -> luuHoaDon());
        add(btnLuu);

        btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);

        setVisible(true);
    }

    private void timHoaDon() {
        try {
            int maKH = Integer.parseInt(txtMaKH.getText());
            HoaDonResponse response = service.layChiTiet(maKH);
            
            if (response.success) {
                hdCurrent = (HoaDon) response.data;
                hienThiDuLieuCu();
            } else {
                JOptionPane.showMessageDialog(this, response.message, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hienThiDuLieuCu() {
        txtHoTen.setText(hdCurrent.getHoTen());
        txtNgayRaHD.setText(hdCurrent.getNgayRaHD().toString());
        txtDonGia.setText(String.valueOf(hdCurrent.getDonGia()));
        
        if (hdCurrent instanceof HoaDonVietNam) {
            HoaDonVietNam hd = (HoaDonVietNam) hdCurrent;
            txtSoKW.setText(String.valueOf(hd.getSoKWTieuThu()));
            txtDinhMuc.setText(String.valueOf(hd.getDinhMuc()));
            txtQuocTich.setText("");
        } else {
            HoaDonNuocNgoai hd = (HoaDonNuocNgoai) hdCurrent;
            txtSoKW.setText(String.valueOf(hd.getSoKWTieuThu()));
            txtDinhMuc.setText("");
            txtQuocTich.setText(hd.getQuocTich());
        }
    }

    private void luuHoaDon() {
        if (hdCurrent == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng tìm hóa đơn trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            HoaDonRequest request = new HoaDonRequest();
            request.maKH = Integer.parseInt(txtMaKH.getText());
            request.hoTen = txtHoTen.getText();
            request.ngayRaHD = LocalDate.parse(txtNgayRaHD.getText());
            request.donGia = Double.parseDouble(txtDonGia.getText());
            request.soKWTieuThu = Double.parseDouble(txtSoKW.getText());
            
            if (hdCurrent instanceof HoaDonVietNam) {
                HoaDonVietNam hd = (HoaDonVietNam) hdCurrent;
                request.loaiKH = hd.getLoaiKH();
                request.dinhMuc = Double.parseDouble(txtDinhMuc.getText());
            } else {
                request.loaiKH = "Nước ngoài";
                request.quocTich = txtQuocTich.getText();
            }

            HoaDonResponse response = service.sua(request);
            if (response.success) {
                JOptionPane.showMessageDialog(this, response.message);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, response.message, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}