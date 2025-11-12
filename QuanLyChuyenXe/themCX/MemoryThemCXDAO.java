package QuanLyChuyenXe.themCX;

import QuanLyChuyenXe.ChuyenXe;
import QuanLyChuyenXe.MemoryCXDatabase;

public class MemoryThemCXDAO implements ThemCXDAO {
	public void insert(ChuyenXe cx) {

		MemoryCXDatabase.put(cx);

	}

	public boolean checkMaCX(int ma) {
		return MemoryCXDatabase.checkMaCX(ma);
	}
}
