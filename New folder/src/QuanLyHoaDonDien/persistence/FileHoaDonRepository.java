package QuanLyHoaDonDien.persistence;

import QuanLyHoaDonDien.entity.HoaDon;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileHoaDonRepository implements IHoaDonRepository {
    private static final String FILE_NAME = "hoaDon.dat";
    private HashMap<Integer, HoaDon> db;

    public FileHoaDonRepository() {
        this.db = new HashMap<>();
        loadFromFile();
    }

    @Override
    public void save(HoaDon hoaDon) {
        if (hoaDon != null) {
            db.put(hoaDon.getMaKH(), hoaDon);
            saveToFile();
        }
    }

    @Override
    public HoaDon findById(int maKH) {
        return db.get(maKH);
    }

    @Override
    public ArrayList<HoaDon> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean delete(int maKH) {
        if (db.containsKey(maKH)) {
            db.remove(maKH);
            saveToFile();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(HoaDon hoaDon) {
        if (hoaDon != null && db.containsKey(hoaDon.getMaKH())) {
            db.put(hoaDon.getMaKH(), hoaDon);
            saveToFile();
            return true;
        }
        return false;
    }

    @Override
    public boolean exists(int maKH) {
        return db.containsKey(maKH);
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            db = (HashMap<Integer, HoaDon>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            db = new HashMap<>();
        }
    }

    private void saveToFile() {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(db);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}