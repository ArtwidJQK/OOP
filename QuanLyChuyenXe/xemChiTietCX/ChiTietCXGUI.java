package QuanLyChuyenXe.xemChiTietCX;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ChiTietCXGUI extends JFrame{
	private JLabel lblMaCX, lblHoTenTX, lblSoxe,
	lblGia, lblSokm, lblNgayDi, lblNoiDen, lblSoTuyen,
	lblLoaiCX, lblEmpty, lblSoTien; 
	private JTextField txtMaCX, txtHoTenTX, txtSoxe,
	txtGia, txtSokm, txtNgayDi, txtNoiDen, txtSoTuyen, txtSoTien;
	private JTextField txtLoaiCX;
	private JButton btnExit;
	
	public ChiTietCXGUI() {
		setTitle("Chi tiết chuyến xe");
		setSize(400, 400);
		setLocationRelativeTo(null);
		GridLayout gridLayout = new 
				GridLayout(11, 2, 5, 5);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(gridLayout);
		initComponents();
		//bó sát
		pack();

		setVisible(true);
	}
	
	private void initComponents() {
		lblMaCX = new JLabel("Mã CX");
		txtMaCX = new JTextField(20);
		txtMaCX.setEditable(false);
		add(lblMaCX);
		add(txtMaCX);
		lblHoTenTX = new JLabel("Họ tên TX");
		txtHoTenTX = new JTextField(20);
		txtHoTenTX.setEditable(false);

		add(lblHoTenTX);
		add(txtHoTenTX);
		lblSoxe = new JLabel("Số xe");
		txtSoxe = new JTextField(20);
		txtSoxe.setEditable(false);

		add(lblSoxe);
		add(txtSoxe);
		lblGia = new JLabel("Giá");
		txtGia = new JTextField(20);
		txtGia.setEditable(false);

		add(lblGia);
		add(txtGia);
		
		lblLoaiCX = new JLabel("Loại CX");
		txtLoaiCX = new JTextField(20);
		txtLoaiCX.setEditable(false);
		add(lblLoaiCX);
		add(txtLoaiCX);
		
		
				
		
		//nội thành
		lblSokm = new JLabel("Số km đi được (nội thành)");
		txtSokm = new JTextField(20);
		txtSokm.setEditable(false);

		add(lblSokm);
		add(txtSokm);
		
		lblSoTuyen = new JLabel("Số tuyến (nội thành)");
		txtSoTuyen = new JTextField(20);
		txtSoTuyen.setEditable(false);

		add(lblSoTuyen);
		add(txtSoTuyen);
		
		//ngoại thành
		lblNgayDi = new JLabel("Ngày đi được (ngoại thành)");
		txtNgayDi = new JTextField(20);
		txtNgayDi.setEditable(false);

		add(lblNgayDi);
		add(txtNgayDi);
		
		lblNoiDen = new JLabel("Nơi đến (ngoại thành)");
		txtNoiDen = new JTextField(20);
		txtNoiDen.setEditable(false);

		add(lblNoiDen);
		add(txtNoiDen);
		
		lblSoTien  = new JLabel("Số tiền");
		txtSoTien = new JTextField(20);
		txtSoTien.setEditable(false);
		
		add(lblSoTien);
		add(txtSoTien);
		
		//nút
		lblEmpty = new JLabel("");
		btnExit = new JButton("Thoát");
		
		ActionListener exitListener = null;
		
		exitListener = new ExitListener();
	
		btnExit.addActionListener(exitListener);
		add(lblEmpty);
		add(btnExit);
		
		
	}
	
	private class ExitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
	
	public void show(ResDataChiTietCX resData) {
		if(resData != null) {
			txtMaCX.setText(resData.maCX);
			txtHoTenTX.setText(resData.hoTenTX);
			txtSoxe.setText(resData.soXe);
			txtGia.setText(resData.gia);
			txtLoaiCX.setText(resData.loaiCX);
			
			if(resData.loaiCX.equalsIgnoreCase("Nội Thành")){
				txtSokm.setText(resData.soKm);
				txtSoTuyen.setText(resData.soTuyen);
			}
			
			if(resData.loaiCX.equalsIgnoreCase("Ngoại Thành")){
				txtNgayDi.setText(resData.ngayDi);
				txtNoiDen.setText(resData.noiDen);
			}
			
			txtSoTien.setText(resData.soTien);
			
		}
	}
	
	public static void main(String[] args) {
		new ChiTietCXGUI();
	}
	

}
