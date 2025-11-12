package QuanLyChuyenXe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import QuanLyChuyenXe.inDanhSachCX.FileInDSCXDAO;
import QuanLyChuyenXe.inDanhSachCX.InDSCXControl;
import QuanLyChuyenXe.inDanhSachCX.InDSCXDAO;
import QuanLyChuyenXe.inDanhSachCX.MemoryInDSCXDAO;
import QuanLyChuyenXe.inDanhSachCX.TableDSCXGUI;
import QuanLyChuyenXe.themCX.FormThemCXGUI;

public class QLCXMenuGUI extends JFrame{
	private JMenuBar menuBar;
	public QLCXMenuGUI() {
		//tiêu đề
		setTitle("Quản lý chuyến xe");
		//kích thước cửa sổ
		setSize(800, 700);
		//tắt chương trình
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//hiển thị
		//cửa đặt giữa màn hình
		setLocationRelativeTo(null);
		
		//thiết lập bố cục cho cửa sổ
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		//gọi method
		khoiTaoThanhPhanWindow();
		setVisible(true);
	}
	
	private void khoiTaoThanhPhanWindow() {
		menuBar = new JMenuBar();
		//Menu chức năng
		JMenu chucNang = new JMenu("Chức năng");
		//menu item - thêm chuyến xe
		JMenuItem themCX = new JMenuItem("Thêm CX");
		//tham chiếu ThemCXActionListener
		ThemCXListener themCXAL = new ThemCXListener();
		themCX.addActionListener(themCXAL);
		//đưa vào menu chức năng
		chucNang.add(themCX);
		
		//In danh sách cx
		JMenuItem inDSCX = new JMenuItem("In DS các CX");
		
		ActionListener inCXListener = null;
		inCXListener = new InDSCXListener();
		inDSCX.addActionListener(inCXListener);
		chucNang.add(inDSCX);
		
		//Menu tùy chọn
		JMenu tuyChon = new JMenu("Tùy chọn");
		JMenu hoTro = new JMenu("Hỗ trợ");

		//them chức năng vào menu bar
		menuBar.add(chucNang);
		menuBar.add(tuyChon);
		menuBar.add(hoTro);
		//thêm menu bar vào cửa sổ
		setJMenuBar(menuBar);
		
		//"Được phát triển bởi GDUers
		JLabel lbFooter = 
				new JLabel("Được phát triển bởi GDUers"
						, SwingConstants.CENTER);
		//đặt label vào cửa sổ
		add(lbFooter, BorderLayout.SOUTH);
		
	}

	private class ThemCXListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//JOptionPane.showMessageDialog(null,
					//"Hello baby!!!");
			//FormThemCXGUI formThemCX;
			//Hiển thị form
			new FormThemCXGUI();
		}
		
	}
	
	private class InDSCXListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			InDSCXDAO dscxdao = new MemoryInDSCXDAO();
			//InDSCXDAO dscxdao = new FileInDSCXDAO("cx.db"); 
			TableDSCXGUI tableDSCXGUI = new TableDSCXGUI();
			InDSCXControl inDscxControl = new InDSCXControl(dscxdao, tableDSCXGUI);
			inDscxControl.execute();
			
		}
		
	}
}
