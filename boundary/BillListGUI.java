package BillManagement.boundary;

import BillManagement.control.BillManagerControl;
import BillManagement.entity.HoaDon;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;

public class BillListGUI extends JPanel {
    
    private BillManagerControl control;
    private JTable table;
    private DefaultTableModel tableModel;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public BillListGUI(BillManagerControl control) {
        this.control = control;
        setLayout(new BorderLayout());

        String[] columns = {"Mã KH", "Họ Tên", "Ngày HĐ", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        tableModel = new DefaultTableModel(columns, 0) {
             @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton editButton = new JButton("Sửa");
        JButton deleteButton = new JButton("Xóa");
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        refreshTable();

        deleteButton.addActionListener(e -> deleteSelectedBill());
        editButton.addActionListener(e -> editSelectedBill());
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        for (HoaDon hd : control.getBillList()) {
            tableModel.addRow(new Object[]{
                hd.getMaKH(),
                hd.getHoTen(),
                dateFormat.format(hd.getNgayRaHD()),
                hd.getSoLuong(),
                hd.getDonGia(),
                hd.tinhThanhTien()
            });
        }
    }
    
    private void deleteSelectedBill() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String maKH = (String) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa hóa đơn " + maKH + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                control.deleteBill(maKH);
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editSelectedBill() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String maKH = (String) tableModel.getValueAt(selectedRow, 0);
        HoaDon hd = control.getBillById(maKH);

        if (hd == null) {
             JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
             return;
        }
        
        BillFormGUI editForm = new BillFormGUI(control, hd, this);
        editForm.setVisible(true);
    }
}
