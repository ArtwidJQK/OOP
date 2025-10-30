package BillManagement.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class HoaDon implements Serializable {

    private static final long serialVersionUID = 1L; 

    protected String maKH;
    protected String hoTen;
    protected Date ngayRaHD;
    protected double soLuong;
    protected double donGia;

    public HoaDon(String maKH, String hoTen, Date ngayRaHD, double soLuong, double donGia) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.ngayRaHD = ngayRaHD;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public abstract double tinhThanhTien();

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgayRaHD() {
        return ngayRaHD;
    }

    public void setNgayRaHD(Date ngayRaHD) {
        this.ngayRaHD = ngayRaHD;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
