package QuanLyChuyenXe.inDanhSachCX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import QuanLyChuyenXe.ChuyenXe;

public class FileInDSCXDAO implements InDSCXDAO {
	private String fileName;

	public FileInDSCXDAO(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public ArrayList<ChuyenXe> getAllCX() throws FileNotFoundException {
		ArrayList<ChuyenXe> listCX = null;
		HashMap<Integer, ChuyenXe> memoryDB = null;
		memoryDB =  getFromFile();
		if(memoryDB != null) {
			listCX = new ArrayList<ChuyenXe>(memoryDB.values());
		}
		
		return listCX;
	}

	private HashMap<Integer, ChuyenXe> getFromFile() throws FileNotFoundException {
		HashMap<Integer, ChuyenXe> memoryDB = null;

		File file = new File(fileName);
		FileInputStream fileIn;
		fileIn = new FileInputStream(file);
		ObjectInputStream oIn;
		try {
			oIn = new ObjectInputStream(fileIn);
			memoryDB = (HashMap<Integer, ChuyenXe>) oIn.readObject();

			fileIn.close();
			oIn.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return memoryDB;
	}

}
