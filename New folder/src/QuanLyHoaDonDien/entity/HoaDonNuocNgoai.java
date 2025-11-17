package QuanLyHoaDonDien.entity;

import java.time.LocalDate;

public class HoaDonNuocNgoai extends HoaDon {
    private String quocTich;
    private double soKWTieuThu;

    public HoaDonNuocNgoai() {}

    public HoaDonNuocNgoai(int maKH, String hoTen, LocalDate ngayRaHD, double donGia,
            String quocTich, double soKWTieuThu) {
        super(maKH, hoTen, ngayRaHD, donGia);
        this.quocTich = quocTich;
        this.soKWTieuThu = soKWTieuThu;
    }

    @Override
    public double tinhThanhTien() {
        return soKWTieuThu * donGia;
    }

    public String getQuocTich() { return quocTich; }
    public double getSoKWTieuThu() { return soKWTieuThu; }
}
