package BillManagement.persistence;

import BillManagement.control.DAO;
import BillManagement.entity.HoaDon;

import java.io.*;
import java.util.ArrayList;

public class FileDAO implements DAO {
    private String filePath = "hoadon.db";

    @Override
    public ArrayList<HoaDon> loadData() {
        ArrayList<HoaDon> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            list = (ArrayList<HoaDon>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found. Creating new list.");
            return list;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void saveData(ArrayList<HoaDon> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(list);
            System.out.println("Data saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
