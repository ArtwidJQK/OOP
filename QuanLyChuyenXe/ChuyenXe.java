package QuanLyChuyenXe;

import java.io.Serializable;

public abstract class ChuyenXe implements Serializable{
	protected int maCX;
	protected String hoTenTaiXe;
	protected String soXe;
	protected double gia;

	protected ChuyenXe() {

	}

	protected ChuyenXe(int _maCX, String _hoTenTaiXe, String _soXe, double _gia) {
		maCX = _maCX;
		hoTenTaiXe = _hoTenTaiXe;
		soXe = _soXe;
		gia = _gia;
	}

	public abstract double tinhTien();

	public int getMaCX() {
		return maCX;
	}

	protected void setMaCX(int maCX) {
		this.maCX = maCX;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public static boolean check(double gia) {
		if (gia >= 100_000) {
			return true;
		}

		return false;
	}
	
	public String getHoTenTaiXe() {
		return hoTenTaiXe;
	}
	
	public String getSoXe() {
		return soXe;
	}
	
	public double getGia() {
		return gia;
	}

}
