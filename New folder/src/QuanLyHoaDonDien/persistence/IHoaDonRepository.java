package QuanLyHoaDonDien.persistence;

import QuanLyHoaDonDien.entity.HoaDon;
import java.util.ArrayList;

public interface IHoaDonRepository {
    void save(HoaDon hoaDon);
    HoaDon findById(int maKH);
    ArrayList<HoaDon> findAll();
    boolean delete(int maKH);
    boolean update(HoaDon hoaDon);
    boolean exists(int maKH);
}