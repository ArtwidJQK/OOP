package QuanLyChuyenXe.themCX;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import QuanLyChuyenXe.CXNoiThanh;
import QuanLyChuyenXe.ChuyenXe;


public class FormThemCXGUI extends JFrame{
	private JLabel lblMaCX, lblHoTenTX, lblSoxe,
	lblGia, lblSokm, lblNgayDi, lblNoiDen, lblSoTuyen,
	lblLoaiCX, lblEmpty; 
	private JTextField txtMaCX, txtHoTenTX, txtSoxe,
	txtGia, txtSokm, txtNgayDi, txtNoiDen, txtSoTuyen;
	private JComboBox<String> cboLoaiCX;
	private JButton btnLuu;
	
	public FormThemCXGUI() {
		setTitle("Thêm mới chuyến xe");
		setSize(400, 400);
		setLocationRelativeTo(null);
		GridLayout gridLayout = new 
				GridLayout(10, 2, 5, 5);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(gridLayout);
		initComponents();
		//bó sát
		pack();

		setVisible(true);
		
		
	}
	
	private void initComponents() {
		lblMaCX = new JLabel("Mã CX");
		txtMaCX = new JTextField(20);
		add(lblMaCX);
		add(txtMaCX);
		lblHoTenTX = new JLabel("Họ tên TX");
		txtHoTenTX = new JTextField(20);
		add(lblHoTenTX);
		add(txtHoTenTX);
		lblSoxe = new JLabel("Số xe");
		txtSoxe = new JTextField(20);
		add(lblSoxe);
		add(txtSoxe);
		lblGia = new JLabel("Giá");
		txtGia = new JTextField(20);
		add(lblGia);
		add(txtGia);
		
		lblLoaiCX = new JLabel("Loại CX");
		cboLoaiCX = new JComboBox<String>(
				new String[] {"Nội Thành", "Ngoại Thành"}
				);
		add(lblLoaiCX);
		add(cboLoaiCX);
				
		
		//nội thành
		lblSokm = new JLabel("Số km đi được (nội thành)");
		txtSokm = new JTextField(20);
		add(lblSokm);
		add(txtSokm);
		
		lblSoTuyen = new JLabel("Số tuyến (nội thành)");
		txtSoTuyen = new JTextField(20);
		add(lblSoTuyen);
		add(txtSoTuyen);
		
		//ngoại thành
		lblNgayDi = new JLabel("Ngày đi được (ngoại thành)");
		txtNgayDi = new JTextField(20);
		add(lblNgayDi);
		add(txtNgayDi);
		
		lblNoiDen = new JLabel("Nơi đến (ngoại thành)");
		txtNoiDen = new JTextField(20);
		add(lblNoiDen);
		add(txtNoiDen);
		
		//nút
		lblEmpty = new JLabel("");
		btnLuu = new JButton("Lưu");
		
		ActionListener luuCXListener = null;
		
		luuCXListener = new LuuCXListener();
		
		btnLuu.addActionListener(luuCXListener);
		add(lblEmpty);
		add(btnLuu);
		
		
	}
	
	private class LuuCXListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//gửi thông điệp đến đối tượng Control
			//bài tập vè nhà
			//sihh viên code tiếp vào đây
			ThemCXControl themCXControl = null;
			ThemCXDAO themCXDAO = null;
			themCXDAO = new MemoryThemCXDAO();
			//themCXDAO = new FileThemCXDAO("cx.db");
			ThemCXResultDialogGUI resultDialogGUI = new ThemCXResultDialogGUI();
			
			ReqData request = new ReqData();
			request.maCX = Integer.parseInt(txtMaCX.getText());
			request.hoTenTX = txtHoTenTX.getText();
			request.soXe = txtSoxe.getText();
			request.gia = Double.parseDouble(txtGia.getText());
			
			String loaiCX = cboLoaiCX.getSelectedItem().toString();
			request.loaiCX = loaiCX;
			
			if(loaiCX.equalsIgnoreCase("Nội Thành")) {
				request.soTuyen = Integer.parseInt(txtSoTuyen.getText());
				request.soKm = Double.parseDouble(txtSokm.getText());
				themCXControl = new ThemCXControlNoiThanh(resultDialogGUI, themCXDAO);
				
			}
			
			if(loaiCX.equalsIgnoreCase("Ngoại Thành")) {
				request.noiDen = txtNoiDen.getText();
				request.ngayDi = Integer.parseInt(txtNgayDi.getText());
			}
			
			themCXControl.control(request);
			
		}
		
	}
	
	public static void main(String[] args) {
		new FormThemCXGUI();
	}

}
