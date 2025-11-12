package QuanLyChuyenXe.inDanhSachCX;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import QuanLyChuyenXe.xemChiTietCX.ChiTietCXGUI;
import QuanLyChuyenXe.xemChiTietCX.MemoryXemChiTietCXDAO;
import QuanLyChuyenXe.xemChiTietCX.ReqDataXemCT;
import QuanLyChuyenXe.xemChiTietCX.XemChiTietCXControl;
import QuanLyChuyenXe.xemChiTietCX.XemChiTietCXDAO;

public class TableDSCXGUI extends JFrame {
	private JTable table = null;
	private String[] columns = { "Mã", "Loại", "Tài xế", "Số xe", "Giá", "Thành tiền" };
	private DefaultTableModel model = null;
	public TableDSCXGUI() {
		setTitle("Danh sách các chuyến xe");
		setLayout(new BorderLayout());
		setSize(800, 400);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		// initTable();
		// pack();
		// setVisible sau khi đã add xong các component
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void show(ResData resData) {

		if (resData.resList != null) {
			Object[][] data = toTableData(resData.resList);

			model = new DefaultTableModel(data, columns);
			table = new JTable(model);

			// Thay vì add(table, ...), phải add JScrollPane chứa table để header hiện ra:
			JScrollPane scroll = new JScrollPane(table);

			createPopup();

			add(scroll, BorderLayout.CENTER);
		} else {
			JOptionPane.showMessageDialog(this, resData.message);
		}

	}

	public void createPopup() {

		TableDSCXMouseAdapter adapter = new TableDSCXMouseAdapter();

		table.addMouseListener(adapter);
	}

	private class TableDSCXMouseAdapter extends MouseAdapter {

		JPopupMenu popup = new JPopupMenu();
		JMenuItem miView = new JMenuItem("Xem chi tiết");
		JMenuItem miEdit = new JMenuItem("Sửa");
		JMenuItem miDelete = new JMenuItem("Xóa");

		public TableDSCXMouseAdapter() {
			popup.add(miView);
			popup.add(miEdit);
			popup.add(miDelete);
			
			viewCXDetail();

		}

		private void viewCXDetail() {
			miView.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int viewRow = table.getSelectedRow();
					if (viewRow == -1)
						return;
					int modelRow = table.convertRowIndexToModel(viewRow);
		            int ma = Integer.parseInt(model.getValueAt(modelRow, 0).toString());
		            
		            ReqDataXemCT reqData = new ReqDataXemCT();
		            reqData.maCX = ma;
		            XemChiTietCXDAO chiTietCXDAO = new MemoryXemChiTietCXDAO();
		            ChiTietCXGUI chiTietCXGUI = new ChiTietCXGUI();
		            XemChiTietCXControl chiTietCXControl =
		            		new XemChiTietCXControl(chiTietCXDAO, chiTietCXGUI);
		            chiTietCXControl.execute(reqData);
		            
		            
					
				}
			});
		}

		private void selectRowIfNeeded(MouseEvent e) {
			Point p = e.getPoint();
			int row = table.rowAtPoint(p);
			if (row >= 0 && row < table.getRowCount()) {
				table.setRowSelectionInterval(row, row);
			} else {
				table.clearSelection();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.isPopupTrigger()) {
				selectRowIfNeeded(e);
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) {
				selectRowIfNeeded(e);
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	private Object[][] toTableData(ArrayList<ResItem> ds) {
		Object[][] data = new Object[ds.size()][columns.length];
		for (int i = 0; i < ds.size(); i++) {
			ResItem item = ds.get(i);
			data[i][0] = item.maCX;
			data[i][1] = item.loaiXe;
			data[i][2] = item.hoTenTX;
			data[i][3] = item.soXe;
			data[i][4] = item.gia;
			data[i][5] = item.tien;
		}
		return data;
	}

	public static void main(String[] args) {
		new TableDSCXGUI();
	}
}
