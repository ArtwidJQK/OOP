package QuanLyChuyenXe.themCX;

import QuanLyChuyenXe.ChuyenXe;

public interface ThemCXDAO {
	
	void insert (ChuyenXe cx);
	
	boolean checkMaCX(int ma);
		

}
