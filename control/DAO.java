package BillManagement.control;

import BillManagement.entity.HoaDon;
import java.util.ArrayList;

public interface DAO {
    ArrayList<HoaDon> loadData();
    
    void saveData(ArrayList<HoaDon> list);
}
