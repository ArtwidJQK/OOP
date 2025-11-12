package QuanLyChuyenXe.inDanhSachCX;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import QuanLyChuyenXe.CXNoiThanh;
import QuanLyChuyenXe.ChuyenXe;

public class InDSCXControl {
	private InDSCXDAO inDSCXDAO = null;
	private TableDSCXGUI tableDSCXGUI = null;
	
	
	
	public InDSCXControl(InDSCXDAO inDSCXDAO, TableDSCXGUI tableDSCXGUI) {
		this.inDSCXDAO = inDSCXDAO;
		this.tableDSCXGUI = tableDSCXGUI;
	}



	public void execute() {
		
		ArrayList<ChuyenXe> ds = null;
		try {
			ds =  inDSCXDAO.getAllCX();
		}catch(FileNotFoundException fNFE) {
			ResData resError = new ResData();
			resError.message = "Không tìm thấy file Database!!\n"+
			fNFE.getMessage();
			tableDSCXGUI.show(resError);
			return;
		}
		
		ArrayList<ResItem> resList  = null;
		ResData resData = new ResData();
		
		
		if(ds != null) {
			resList = convertToRes(ds);
			resData.resList = resList;
		}else {
			resData.message = "Danh sách rỗng!!!";
		}
			
		tableDSCXGUI.show(resData);
		
	}
	
	private ArrayList<ResItem> convertToRes(ArrayList<ChuyenXe> dsCX){
		ArrayList<ResItem> resList = new ArrayList<ResItem>();
		for(ChuyenXe cx: dsCX) {
			ResItem item = new ResItem();
			item.maCX = String.valueOf(cx.getMaCX());
			item.hoTenTX = cx.getHoTenTaiXe();
			item.soXe = cx.getSoXe();
			item.gia = String.valueOf(cx.getGia());
			item.tien = String.valueOf(cx.tinhTien());
			if(cx instanceof CXNoiThanh) {
				item.loaiXe = "Nội Thành";
			}
			
			resList.add(item);
			
		}
		
		return resList;
	}

}
