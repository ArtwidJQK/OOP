package QuanLyChuyenXe.themCX;

import javax.swing.JOptionPane;

public class ThemCXResultDialogGUI{

	public void show(ResData resData) {
		String message = "";
		for (String msg : resData.listMessage) {
			message += "\n" + msg;
		}
		
		JOptionPane.showMessageDialog(null, message);
		
	}	

}
