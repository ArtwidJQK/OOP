package QuanLyHoaDonDien.service.thongke;

import QuanLyHoaDonDien.entity.HoaDon;
import QuanLyHoaDonDien.entity.HoaDonVietNam;
import QuanLyHoaDonDien.entity.HoaDonNuocNgoai;
import QuanLyHoaDonDien.dto.HoaDonResponse;
import QuanLyHoaDonDien.persistence.IHoaDonRepository;
import java.util.HashMap;
import java.util.ArrayList;

public class ThongKeService {
    private IHoaDonRepository repository;

    public ThongKeService(IHoaDonRepository repository) {
        this.repository = repository;
    }

    public HoaDonResponse thongKeTheoLoai() {
        HashMap<String, Object> result = new HashMap<>();
        int countVN = 0;
        int countNN = 0;
        double tongKWVN = 0;
        double tongKWNN = 0;
        double tongThanhTienVN = 0;
        double tongThanhTienNN = 0;

        for (HoaDon hd : repository.findAll()) {
            if (hd instanceof HoaDonVietNam) {
                HoaDonVietNam hdVN = (HoaDonVietNam) hd;
                countVN++;
                tongKWVN += hdVN.getSoKWTieuThu();
                tongThanhTienVN += hdVN.tinhThanhTien();
            } else {
                HoaDonNuocNgoai hdNN = (HoaDonNuocNgoai) hd;
                countNN++;
                tongKWNN += hdNN.getSoKWTieuThu();
                tongThanhTienNN += hdNN.tinhThanhTien();
            }
        }

        result.put("countVN", countVN);
        result.put("countNN", countNN);
        result.put("tongKWVN", tongKWVN);
        result.put("tongKWNN", tongKWNN);
        result.put("tongThanhTienVN", tongThanhTienVN);
        result.put("tongThanhTienNN", tongThanhTienNN);
        result.put("trungBinhVN", countVN > 0 ? tongThanhTienVN / countVN : 0);
        result.put("trungBinhNN", countNN > 0 ? tongThanhTienNN / countNN : 0);

        return new HoaDonResponse(true, "Thống kê thành công", result);
    }
}