package QuanLyChuyenXe;

public class MainChuyenXe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CXNoiThanh cxNoi;
		
		cxNoi = new CXNoiThanh(111, "L V Tèo", "teo111", 500,
				2, 10);
		
		System.out.print("Số tiền: " + cxNoi.tinhTien());
		
	}

}
