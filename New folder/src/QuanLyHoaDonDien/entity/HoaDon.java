package QuanLyHoaDonDien.entity;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class HoaDon implements Serializable {
    protected int maKH;
    protected String hoTen;
    protected LocalDate ngayRaHD;
    protected double donGia;

    protected HoaDon() {}

    protected HoaDon(int maKH, String hoTen, LocalDate ngayRaHD, double donGia) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.ngayRaHD = ngayRaHD;
        this.donGia = donGia;
    }

    public abstract double tinhThanhTien();

    public int getMaKH() { return maKH; }
    public String getHoTen() { return hoTen; }
    public LocalDate getNgayRaHD() { return ngayRaHD; }
    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
}