package QuanLyChuyenXe.themCX;

import QuanLyChuyenXe.CXNoiThanh;

public class ThemCXControlNoiThanh extends ThemCXControl {

	public ThemCXControlNoiThanh(ThemCXResultDialogGUI resultDialogGUI, ThemCXDAO themCXDAO) {
		super(resultDialogGUI, themCXDAO);
	}

	@Override
	protected void execute(ReqData rqData) {
		if (!CXNoiThanh.checkGia(rqData.gia)) {
			String errGia = "Giá không hợp lệ (>=5.000)!!";
			resData.listMessage.add(errGia);
		}

		if (!CXNoiThanh.checkSoKm(rqData.soKm)) {
			String errSoKm = "Số km không hợp lệ (>=0.5km)!!";
			resData.listMessage.add(errSoKm);
		}

		cx = new CXNoiThanh(rqData.maCX, rqData.hoTenTX, rqData.soXe, rqData.gia, rqData.soTuyen, rqData.soKm);
	}

}
