package QuanLyHoaDonDien.presentation.thongke;

import java.util.HashMap;
import javax.swing.*;
import QuanLyHoaDonDien.service.thongke.ThongKeService;
import QuanLyHoaDonDien.dto.HoaDonResponse;

public class ThongKeDialog extends JFrame {
    private ThongKeService service;

    public ThongKeDialog(ThongKeService service) {
        this.service = service;
        initUI();
    }

    private void initUI() {
        setTitle("Thống kê Theo Loại Khách hàng");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea txtKetQua = new JTextArea();
        txtKetQua.setEditable(false);
        txtKetQua.setLineWrap(true);
        txtKetQua.setWrapStyleWord(true);
        txtKetQua.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));

        HoaDonResponse response = service.thongKeTheoLoai();
        
        if (response.success) {
            HashMap<String, Object> data = (HashMap<String, Object>) response.data;
            
            int countVN = (int) data.get("countVN");
            int countNN = (int) data.get("countNN");
            double tongKWVN = (double) data.get("tongKWVN");
            double tongKWNN = (double) data.get("tongKWNN");
            double tongThanhTienVN = (double) data.get("tongThanhTienVN");
            double tongThanhTienNN = (double) data.get("tongThanhTienNN");
            double trungBinhVN = (double) data.get("trungBinhVN");
            double trungBinhNN = (double) data.get("trungBinhNN");
            
            StringBuilder sb = new StringBuilder();
            sb.append("╔════════════════════════════════════════════════════╗\n");
            sb.append("║    TỔNG KÊ THEO LOẠI KHÁCH HÀNG                   ║\n");
            sb.append("╚════════════════════════════════════════════════════╝\n\n");

            sb.append("┌─ KHÁCH HÀNG VIỆT NAM ─────────────────────┐\n");
            sb.append(String.format("│ Số lượng hóa đơn: %d%n", countVN));
            sb.append(String.format("│ Tổng KW tiêu thụ: %.2f KW%n", tongKWVN));
            sb.append(String.format("│ Tổng thành tiền:  %.2f VNĐ%n", tongThanhTienVN));
            sb.append(String.format("│ Trung bình/HĐ:   %.2f VNĐ%n", trungBinhVN));
            sb.append("└───────────────────────────────────────────┘\n\n");

            sb.append("┌─ KHÁCH HÀNG NƯỚC NGOÀI ─────────────────┐\n");
            sb.append(String.format("│ Số lượng hóa đơn: %d%n", countNN));
            sb.append(String.format("│ Tổng KW tiêu thụ: %.2f KW%n", tongKWNN));
            sb.append(String.format("│ Tổng thành tiền:  %.2f VNĐ%n", tongThanhTienNN));
            sb.append(String.format("│ Trung bình/HĐ:   %.2f VNĐ%n", trungBinhNN));
            sb.append("└─────────────────────────────────────────┘\n\n");

            sb.append("┌─ TỔNG CỘng ─────────────────────────────┐\n");
            sb.append(String.format("│ Tổng số hóa đơn:  %d%n", countVN + countNN));
            sb.append(String.format("│ Tổng KW:          %.2f KW%n", tongKWVN + tongKWNN));
            sb.append(String.format("│ Tổng thành tiền:  %.2f VNĐ%n", tongThanhTienVN + tongThanhTienNN));
            sb.append("└─────────────────────────────────────────┘\n");
            
            txtKetQua.setText(sb.toString());
        } else {
            txtKetQua.setText(response.message);
        }

        add(new JScrollPane(txtKetQua));
        setVisible(true);
    }
}

