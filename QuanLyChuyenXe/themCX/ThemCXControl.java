package QuanLyChuyenXe.themCX;
import QuanLyChuyenXe.ChuyenXe;

public abstract class ThemCXControl {
	protected ThemCXResultDialogGUI resultDialogGUI = null;
	protected ThemCXDAO themCXDAO = null;
	protected ChuyenXe cx = null;
	protected ResData resData = null;
	

	
	protected ThemCXControl(ThemCXResultDialogGUI resultDialogGUI, ThemCXDAO themCXDAO) {
		this.resultDialogGUI = resultDialogGUI;
		this.themCXDAO = themCXDAO;
	}
	
	
	public void control(ReqData reqData) {
		resData = new ResData();
		isMaCXDuplicate(reqData.maCX);
		execute(reqData);
		
		if(resData.listMessage.size() > 0) {
			resultDialogGUI.show(resData);
			return;
		}
		
		insertCX();
		
	}
	
	
	protected abstract void execute(ReqData rqData);

	private void insertCX() {
		themCXDAO.insert(cx);
		resData.listMessage.add("Đã thêm CX thành công!");
		resultDialogGUI.show(resData);
	}
	
	private void isMaCXDuplicate(int maCX) {
		boolean result = themCXDAO.checkMaCX(maCX);
		if(!result) {
			resData.listMessage.add("Mã " + maCX + "bị trùng !");
		}
	}
	
}
