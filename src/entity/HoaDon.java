package entity;

public class HoaDon {
	private String maHD, maQL, maNV, maKH, thoiDiemSD, thoiDiemTT;
	private double tongThanhToan;

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaQL() {
		return maQL;
	}

	public void setMaQL(String maQL) {
		this.maQL = maQL;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getThoiDiemSD() {
		return thoiDiemSD;
	}

	public void setThoiDiemSD(String thoiDiemSD) {
		this.thoiDiemSD = thoiDiemSD;
	}

	public String getThoiDiemTT() {
		return thoiDiemTT;
	}

	public void setThoiDiemTT(String thoiDiemTT) {
		this.thoiDiemTT = thoiDiemTT;
	}

	public double getTongThanhToan() {
		return tongThanhToan;
	}

	public void setTongThanhToan(double tongThanhToan) {
		this.tongThanhToan = tongThanhToan;
	}

	public HoaDon(String maHD, String maQL, String maNV, String maKH, String thoiDiemSD, String thoiDiemTT,
			double tongThanhToan) {
		super();
		this.maHD = maHD;
		this.maQL = maQL;
		this.maNV = maNV;
		this.maKH = maKH;
		this.thoiDiemSD = thoiDiemSD;
		this.thoiDiemTT = thoiDiemTT;
		this.tongThanhToan = tongThanhToan;
	}

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

}
