package QuanLyHoaDonDien.service.indanhsach;

import QuanLyHoaDonDien.entity.HoaDon;
import QuanLyHoaDonDien.dto.HoaDonResponse;
import QuanLyHoaDonDien.persistence.IHoaDonRepository;
import java.util.ArrayList;

public class InDanhSachService {
    private IHoaDonRepository repository;

    public InDanhSachService(IHoaDonRepository repository) {
        this.repository = repository;
    }

    public HoaDonResponse layDanhSach() {
        ArrayList<HoaDon> list = repository.findAll();
        if (list.isEmpty()) {
            return new HoaDonResponse(false, "Danh sách trống");
        }
        return new HoaDonResponse(true, "Có " + list.size() + " hóa đơn", list);
    }

    public HoaDonResponse layTheoThang(int thang) {
        ArrayList<HoaDon> result = new ArrayList<>();
        for (HoaDon hd : repository.findAll()) {
            if (hd.getNgayRaHD().getMonthValue() == thang) {
                result.add(hd);
            }
        }
        if (result.isEmpty()) {
            return new HoaDonResponse(false, "Không có hóa đơn tháng " + thang);
        }
        return new HoaDonResponse(true, "Có " + result.size() + " hóa đơn", result);
    }
}