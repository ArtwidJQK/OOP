package QuanLyChuyenXe.xemChiTietCX;

import QuanLyChuyenXe.CXNoiThanh;
import QuanLyChuyenXe.ChuyenXe;

public class XemChiTietCXControl {
	private XemChiTietCXDAO chiTietCXDAO = null;
	private ChiTietCXGUI chiTietGUI = null;
	
	public XemChiTietCXControl(XemChiTietCXDAO chiTietCXDAO, ChiTietCXGUI chiTietGUI) {
		this.chiTietCXDAO = chiTietCXDAO;
		this.chiTietGUI = chiTietGUI;
	}



	public void execute(ReqDataXemCT rqData) {
		ChuyenXe cx = chiTietCXDAO.getCXById(rqData.maCX);
		
		if(cx instanceof CXNoiThanh) {
			//ép kiểu - chuyển từ kiểu ChuyenXe => CXNoiThanh
			CXNoiThanh noiThanh = (CXNoiThanh)cx;
			ResDataChiTietCX resDataNoiT = new ResDataChiTietCX();
			resDataNoiT.maCX = String.valueOf(noiThanh.getMaCX());
			resDataNoiT.hoTenTX = noiThanh.getHoTenTaiXe();
			resDataNoiT.gia =  String.valueOf(noiThanh.getGia());
			resDataNoiT.soTuyen = String.valueOf(noiThanh.getSoTuyen());
			resDataNoiT.soKm = String.valueOf(noiThanh.getSoKm());
			resDataNoiT.loaiCX = "Nội Thành";
			resDataNoiT.soTien = String.valueOf(noiThanh.tinhTien());
			
			chiTietGUI.show(resDataNoiT);
		}
		
		
	}

}
