package QuanLyHoaDonDien.service.xoa;

import QuanLyHoaDonDien.dto.HoaDonResponse;
import QuanLyHoaDonDien.persistence.IHoaDonRepository;

public class XoaHoaDonService {
    private IHoaDonRepository repository;

    public XoaHoaDonService(IHoaDonRepository repository) {
        this.repository = repository;
    }

    public HoaDonResponse xoa(int maKH) {
        if (!repository.exists(maKH)) {
            return new HoaDonResponse(false, "Hóa đơn không tồn tại");
        }

        if (repository.delete(maKH)) {
            return new HoaDonResponse(true, "Xóa hóa đơn thành công");
        }
        return new HoaDonResponse(false, "Xóa hóa đơn thất bại");
    }
}