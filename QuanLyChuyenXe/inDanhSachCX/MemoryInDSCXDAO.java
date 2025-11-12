package QuanLyChuyenXe.inDanhSachCX;

import java.util.ArrayList;

import QuanLyChuyenXe.ChuyenXe;
import QuanLyChuyenXe.MemoryCXDatabase;

public class MemoryInDSCXDAO implements InDSCXDAO {
	
	public ArrayList<ChuyenXe> getAllCX(){
		return MemoryCXDatabase.getAll();
	}

}
