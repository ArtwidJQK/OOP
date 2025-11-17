package QuanLyHoaDonDien.entity;

import java.time.LocalDate;

public class HoaDonVietNam extends HoaDon {
    private String loaiKH;
    private double soKWTieuThu;
    private double dinhMuc;

    public HoaDonVietNam() {}

    public HoaDonVietNam(int maKH, String hoTen, LocalDate ngayRaHD, double donGia,
            String loaiKH, double soKWTieuThu, double dinhMuc) {
        super(maKH, hoTen, ngayRaHD, donGia);
        this.loaiKH = loaiKH;
        this.soKWTieuThu = soKWTieuThu;
        this.dinhMuc = dinhMuc;
    }

    @Override
    public double tinhThanhTien() {
        if (soKWTieuThu <= dinhMuc) {
            return soKWTieuThu * donGia;
        } else {
            return (dinhMuc * donGia) + ((soKWTieuThu - dinhMuc) * donGia * 2.5);
        }
    }

    public String getLoaiKH() { return loaiKH; }
    public double getSoKWTieuThu() { return soKWTieuThu; }
    public double getDinhMuc() { return dinhMuc; }
}