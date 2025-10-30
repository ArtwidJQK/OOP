package BillManagement.boundary;

import BillManagement.control.BillManagerControl;
import BillManagement.entity.HoaDon;
import BillManagement.entity.HoaDonNuocNgoai;
import BillManagement.entity.HoaDonVietNam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillFormGUI extends JDialog {

    private BillManagerControl control;
    private BillListGUI billListGUI;
    private HoaDon currentBill;
    private boolean isEditMode = false;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private JTextField txtMaKH, txtHoTen, txtNgayRaHD, txtSoLuong, txtDonGia;
    private JTextField txtDinhMuc, txtDoiTuongKH, txtQuocTich;
    private JComboBox<String> cmbLoaiKH;
    private JPanel panelVietNam, panelNuocNgoai;

    public BillFormGUI(BillManagerControl control, HoaDon hoaDon, BillListGUI billListGUI) {
        this.control = control;
        this.currentBill = hoaDon;
        this.billListGUI = billListGUI;
        this.isEditMode = (hoaDon != null);

        setTitle(isEditMode ? "Sửa Hóa Đơn" : "Thêm Hóa Đơn Mới");
        setSize(450, 450);
        setModal(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents();

        if (isEditMode) {
            loadBillData();
        } else {
            toggleCustomerFields(false);
            panelVietNam.setVisible(false);
            panelNuocNgoai.setVisible(false);
        }
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createFieldPanel("Mã KH:", txtMaKH = new JTextField(20)));
        mainPanel.add(createFieldPanel("Họ Tên:", txtHoTen = new JTextField(20)));
        mainPanel.add(createFieldPanel("Ngày HĐ (yyyy-MM-dd):", txtNgayRaHD = new JTextField(20)));
        mainPanel.add(createFieldPanel("Số Lượng (KW):", txtSoLuong = new JTextField(20)));
        mainPanel.add(createFieldPanel("Đơn Giá:", txtDonGia = new JTextField(20)));

        cmbLoaiKH = new JComboBox<>(new String[]{"Chọn loại KH", "Việt Nam", "Nước Ngoài"});
        mainPanel.add(createFieldPanel("Loại KH:", cmbLoaiKH));

        panelVietNam = new JPanel();
        panelVietNam.setLayout(new BoxLayout(panelVietNam, BoxLayout.Y_AXIS));
        panelVietNam.add(createFieldPanel("Đối Tượng KH:", txtDoiTuongKH = new JTextField(20)));
        panelVietNam.add(createFieldPanel("Định Mức:", txtDinhMuc = new JTextField(20)));
        mainPanel.add(panelVietNam);

        panelNuocNgoai = new JPanel();
        panelNuocNgoai.setLayout(new BoxLayout(panelNuocNgoai, BoxLayout.Y_AXIS));
        panelNuocNgoai.add(createFieldPanel("Quốc Tịch:", txtQuocTich = new JTextField(20)));
        mainPanel.add(panelNuocNgoai);

        cmbLoaiKH.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selected = (String) cmbLoaiKH.getSelectedItem();
                if (selected.equals("Việt Nam")) {
                    toggleCustomerFields(true);
                } else if (selected.equals("Nước Ngoài")) {
                    toggleCustomerFields(false);
                } else {
                    panelVietNam.setVisible(false);
                    panelNuocNgoai.setVisible(false);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Lưu");
        JButton btnCancel = new JButton("Hủy");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> saveBill());

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createFieldPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(String.format("%-20s:", labelText)));
        component.setPreferredSize(new Dimension(200, 25));
        panel.add(component);
        return panel;
    }

    private void toggleCustomerFields(boolean isVietNam) {
        panelVietNam.setVisible(isVietNam);
        panelNuocNgoai.setVisible(!isVietNam);
        if (isVietNam) {
            cmbLoaiKH.setSelectedItem("Việt Nam");
        } else {
            cmbLoaiKH.setSelectedItem("Nước Ngoài");
        }
    }

    private void loadBillData() {
        txtMaKH.setText(currentBill.getMaKH());
        txtMaKH.setEditable(false);
        txtHoTen.setText(currentBill.getHoTen());
        txtNgayRaHD.setText(dateFormat.format(currentBill.getNgayRaHD()));
        txtSoLuong.setText(String.valueOf(currentBill.getSoLuong()));
        txtDonGia.setText(String.valueOf(currentBill.getDonGia()));

        if (currentBill instanceof HoaDonVietNam) {
            HoaDonVietNam hdvn = (HoaDonVietNam) currentBill;
            txtDoiTuongKH.setText(hdvn.getDoiTuongKH());
            txtDinhMuc.setText(String.valueOf(hdvn.getDinhMuc()));
            toggleCustomerFields(true);
        } else if (currentBill instanceof HoaDonNuocNgoai) {
            HoaDonNuocNgoai hdnn = (HoaDonNuocNgoai) currentBill;
            txtQuocTich.setText(hdnn.getQuocTich());
            toggleCustomerFields(false);
        }
    }

    private void saveBill() {
        try {
            String maKH = txtMaKH.getText();
            String hoTen = txtHoTen.getText();
            Date ngayHD = dateFormat.parse(txtNgayRaHD.getText());
            double soLuong = Double.parseDouble(txtSoLuong.getText());
            double donGia = Double.parseDouble(txtDonGia.getText());
            String loaiKH = (String) cmbLoaiKH.getSelectedItem();

            if (maKH.isEmpty() || hoTen.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã KH và Họ Tên không được rỗng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isEditMode == false && control.getBillById(maKH) != null) {
                 JOptionPane.showMessageDialog(this, "Mã KH đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            HoaDon hoaDon = null;

            if (loaiKH.equals("Việt Nam")) {
                String doiTuong = txtDoiTuongKH.getText();
                double dinhMuc = Double.parseDouble(txtDinhMuc.getText());
                hoaDon = new HoaDonVietNam(maKH, hoTen, ngayHD, soLuong, donGia, doiTuong, dinhMuc);

            } else if (loaiKH.equals("Nước Ngoài")) {
                String quocTich = txtQuocTich.getText();
                hoaDon = new HoaDonNuocNgoai(maKH, hoTen, ngayHD, soLuong, donGia, quocTich);
            
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn loại khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isEditMode) {
                control.updateBill(hoaDon);
            } else {
                control.addBill(hoaDon);
            }

            billListGUI.refreshTable();
            dispose();

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ (yyyy-MM-dd).", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số Lượng, Đơn Giá, Định Mức phải là số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
