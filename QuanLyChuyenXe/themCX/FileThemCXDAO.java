package QuanLyChuyenXe.themCX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import QuanLyChuyenXe.CXNoiThanh;
import QuanLyChuyenXe.ChuyenXe;

public class FileThemCXDAO implements ThemCXDAO {
	private String fileName;

	public FileThemCXDAO(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void insert(ChuyenXe cx){
		HashMap<Integer, ChuyenXe> memoryDB = null;
		// try {
		memoryDB = getFromFile();

		if (memoryDB == null) {
			memoryDB = new HashMap<Integer, ChuyenXe>();
		}

		memoryDB.put(cx.getMaCX(), cx);
		writeToFile(memoryDB);

		
	}

	public void writeToFile(HashMap<Integer, ChuyenXe> memoryDB) {
		File file = new File(fileName);
		FileOutputStream fOut;
		ObjectOutputStream oOut = null;
		try {
			fOut = new FileOutputStream(file);
			oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(memoryDB);
			fOut.close();
			oOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkMaCX(int ma) {
		HashMap<Integer, ChuyenXe> memoryTemp = null;
		memoryTemp = getFromFile();
		if(memoryTemp != null) {
			if (memoryTemp.get(ma) != null) {
				return false;
			}
		}
		return true;
	}

	private HashMap<Integer, ChuyenXe> getFromFile() {
		HashMap<Integer, ChuyenXe> memoryDB = null;
		ObjectInputStream oIn = null;
		File file = new File(fileName);
		FileInputStream fileIn = null;

		try {
			fileIn = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			oIn = new ObjectInputStream(fileIn);
			memoryDB = (HashMap<Integer, ChuyenXe>) oIn.readObject();

			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fileIn.close();
			oIn.close();
		}catch (Exception e) {
			// TODO: handle exception
		}

		return memoryDB;
	}

	public static void main(String[] args) {
		ChuyenXe noi = new CXNoiThanh(1, "Lê văn tèo", "tèo 111", 100, 1, 1);

		FileThemCXDAO fileThemCXDAO = new FileThemCXDAO("cx.db");

		fileThemCXDAO.insert(noi);
	}

}
