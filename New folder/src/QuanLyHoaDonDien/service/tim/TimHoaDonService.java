package QuanLyHoaDonDien.service.tim;

import QuanLyHoaDonDien.dto.HoaDonResponse;
import QuanLyHoaDonDien.entity.HoaDon;
import QuanLyHoaDonDien.persistence.IHoaDonRepository;

public class TimHoaDonService {
    private IHoaDonRepository repository;

    public TimHoaDonService(IHoaDonRepository repository) {
        this.repository = repository;
    }

    public HoaDonResponse timTheoMaKH(int maKH) {
        HoaDon hoaDon = repository.findById(maKH);
        if (hoaDon == null) {
            return new HoaDonResponse(false, "Không tìm thấy hóa đơn với mã khách hàng: " + maKH);
        }
        // Trả về response thành công kèm theo đối tượng hóa đơn tìm được
        return new HoaDonResponse(true, "Tìm thấy hóa đơn", hoaDon);
    }
}