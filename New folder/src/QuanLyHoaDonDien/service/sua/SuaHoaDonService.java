package QuanLyHoaDonDien.service.sua;

import QuanLyHoaDonDien.entity.*;
import QuanLyHoaDonDien.dto.HoaDonRequest;
import QuanLyHoaDonDien.dto.HoaDonResponse;
import QuanLyHoaDonDien.persistence.IHoaDonRepository;

public class SuaHoaDonService {
    private IHoaDonRepository repository;

    public SuaHoaDonService(IHoaDonRepository repository) {
        this.repository = repository;
    }

    public HoaDonResponse layChiTiet(int maKH) {
        HoaDon hoaDon = repository.findById(maKH);
        if (hoaDon == null) {
            return new HoaDonResponse(false, "Hóa đơn không tồn tại");
        }
        return new HoaDonResponse(true, "Tìm thấy", hoaDon);
    }

    public HoaDonResponse sua(HoaDonRequest request) {
        if (!repository.exists(request.maKH)) {
            return new HoaDonResponse(false, "Hóa đơn không tồn tại");
        }

        if (!validateDuLieu(request)) {
            return new HoaDonResponse(false, "Dữ liệu không hợp lệ");
        }

        HoaDon hoaDon = taoHoaDon(request);
        if (repository.update(hoaDon)) {
            return new HoaDonResponse(true, "Sửa hóa đơn thành công");
        }
        return new HoaDonResponse(false, "Sửa hóa đơn thất bại");
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