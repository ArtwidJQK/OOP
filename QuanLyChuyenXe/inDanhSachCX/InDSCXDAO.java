package QuanLyChuyenXe.inDanhSachCX;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import QuanLyChuyenXe.ChuyenXe;

public interface InDSCXDAO {
	
	ArrayList<ChuyenXe> getAllCX() throws FileNotFoundException;

}
