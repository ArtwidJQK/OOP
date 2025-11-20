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
// kế thừa override phương thức tính thành tiền
    @Override
    public double tinhThanhTien() {
        return soKWTieuThu * donGia;
    }
// getters
    public String getQuocTich() { return quocTich; }
    public double getSoKWTieuThu() { return soKWTieuThu; }
}
