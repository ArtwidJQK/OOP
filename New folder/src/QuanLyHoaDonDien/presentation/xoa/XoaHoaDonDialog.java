package QuanLyHoaDonDien.presentation.xoa;

import java.awt.GridLayout;
import javax.swing.*;
import QuanLyHoaDonDien.service.xoa.XoaHoaDonService;
import QuanLyHoaDonDien.dto.HoaDonResponse;

public class XoaHoaDonDialog extends JFrame {
    private JTextField txtMaKH;
    private JButton btnXoa, btnCancel;
    private XoaHoaDonService service;

    public XoaHoaDonDialog(XoaHoaDonService service) {
        this.service = service;
        initUI();
    }

    private void initUI() {
        setTitle("Xóa Hóa đơn");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Mã KH:"));
        txtMaKH = new JTextField();
        add(txtMaKH);

        add(new JLabel(""));
        add(new JLabel(""));

        btnXoa = new JButton("Xóa");
        btnXoa.addActionListener(e -> xoaHoaDon());
        add(btnXoa);

        btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);

        setVisible(true);
    }

    private void xoaHoaDon() {
        try {
            int maKH = Integer.parseInt(txtMaKH.getText());
            HoaDonResponse response = service.xoa(maKH);
            
            if (response.success) {
                JOptionPane.showMessageDialog(this, response.message);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, response.message, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}