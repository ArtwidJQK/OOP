package BillManagement.entity;

import java.util.Date;

public class HoaDonVietNam extends HoaDon {
    private static final long serialVersionUID = 1L;
    
    private String doiTuongKH;
    private double dinhMuc;

    public HoaDonVietNam(String maKH, String hoTen, Date ngayRaHD, double soLuong, double donGia, String doiTuongKH, double dinhMuc) {
        super(maKH, hoTen, ngayRaHD, soLuong, donGia);
        this.doiTuongKH = doiTuongKH;
        this.dinhMuc = dinhMuc;
    }

    @Override
    public double tinhThanhTien() {
        if (soLuong <= dinhMuc) {
            return soLuong * donGia;
        } else {
            return (dinhMuc * donGia) + ((soLuong - dinhMuc) * donGia * 2.5);
        }
    }

    public String getDoiTuongKH() {
        return doiTuongKH;
    }

    public void setDoiTuongKH(String doiTuongKH) {
        this.doiTuongKH = doiTuongKH;
    }

    public double getDinhMuc() {
        return dinhMuc;
    }

    public void setDinhMuc(double dinhMuc) {
        this.dinhMuc = dinhMuc;
    }
}
