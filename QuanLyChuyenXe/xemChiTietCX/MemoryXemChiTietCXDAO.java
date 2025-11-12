package QuanLyChuyenXe.xemChiTietCX;

import QuanLyChuyenXe.ChuyenXe;
import QuanLyChuyenXe.MemoryCXDatabase;

public class MemoryXemChiTietCXDAO implements XemChiTietCXDAO{

	@Override
	public ChuyenXe getCXById(int maCX) {
		// TODO Auto-generated method stub
		return MemoryCXDatabase.getCXById(maCX);
	}

}
