package BillManagement.entity;

import java.util.Date;

public class HoaDonNuocNgoai extends HoaDon {
    private static final long serialVersionUID = 1L;
    
    private String quocTich;

    public HoaDonNuocNgoai(String maKH, String hoTen, Date ngayRaHD, double soLuong, double donGia, String quocTich) {
        super(maKH, hoTen, ngayRaHD, soLuong, donGia);
        this.quocTich = quocTich;
    }

    @Override
    public double tinhThanhTien() {
        return soLuong * donGia;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }
}
