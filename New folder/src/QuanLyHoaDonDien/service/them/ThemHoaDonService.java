package QuanLyHoaDonDien.service.them;

import QuanLyHoaDonDien.entity.*;
import QuanLyHoaDonDien.dto.HoaDonRequest;
import QuanLyHoaDonDien.dto.HoaDonResponse;
import QuanLyHoaDonDien.persistence.IHoaDonRepository;

public class ThemHoaDonService {
    private IHoaDonRepository repository;

    public ThemHoaDonService(IHoaDonRepository repository) {
        this.repository = repository;
    }

    public HoaDonResponse them(HoaDonRequest request) {
        if (!validateDuLieu(request)) {
            return new HoaDonResponse(false, "Dữ liệu không hợp lệ");
        }

        if (repository.exists(request.maKH)) {
            return new HoaDonResponse(false, "Mã khách hàng đã tồn tại");
        }

        HoaDon hoaDon = taoHoaDon(request);
        repository.save(hoaDon);
        return new HoaDonResponse(true, "Thêm hóa đơn thành công");
    }

    private boolean validateDuLieu(HoaDonRequest request) {
        if (request.donGia < 1000) return false;
        if (request.soKWTieuThu <= 0) return false;
        
        if (request.loaiKH.startsWith("Việt Nam")) {
            if (request.dinhMuc <= 0) return false;
        }
        
        return true;
    }

    private HoaDon taoHoaDon(HoaDonRequest request) {
        if (request.loaiKH.startsWith("Việt Nam")) {
            return new HoaDonVietNam(request.maKH, request.hoTen, request.ngayRaHD,
                    request.donGia, request.loaiKH, request.soKWTieuThu, request.dinhMuc);
        } else {
            return new HoaDonNuocNgoai(request.maKH, request.hoTen, request.ngayRaHD,
                    request.donGia, request.quocTich, request.soKWTieuThu);
        }
    }
}