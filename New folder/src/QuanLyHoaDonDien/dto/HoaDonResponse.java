package QuanLyHoaDonDien.dto;

public class HoaDonResponse {
    public boolean success;
    public String message;
    public Object data;

    public HoaDonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = null;
    }

    public HoaDonResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
