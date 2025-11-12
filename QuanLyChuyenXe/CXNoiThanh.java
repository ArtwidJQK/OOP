package QuanLyChuyenXe;


public class CXNoiThanh extends ChuyenXe{
	
	private int soTuyen;
	private double soKm;
	
	public static boolean checkGia(double gia) {
		if(gia > 5_000) {
			return true;
		}
		return false;
	}
	
	public static boolean checkSoKm(double km) {
		if(km > 0.5) {
			return true;
		}
		
		return false;
	}
	
	
	
	public CXNoiThanh() {
		
	}
	
	public CXNoiThanh(int _maCX, String _hoTenTaiXe,
			 String _soXe, double _gia, 
			int _soTuyen, double _soKm)
	{
		//gọi đến constructor 4 tham số
		//của cha để cha tự khởi tạo
		super(_maCX, _hoTenTaiXe, _soXe, _gia);
		//con tự khởi tạo
		soTuyen = _soTuyen;
		soKm = _soKm;
	}
	
	@Override
	public double tinhTien() {
		double tien = 0.0;
		tien = gia * soKm;
		return tien;
	}
	

	
	//getters /setters
	
	public int getSoTuyen() {
		return soTuyen;
	}
	
	public double getSoKm() {
		return soKm;
	}

}
