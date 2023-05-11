package entity;

public class HoaDon_SanPhamDV {
	private String maHD, maDV;
	private int soLuong;

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public HoaDon_SanPhamDV(String maHD, String maDV, int soLuong) {
		super();
		this.maHD = maHD;
		this.maDV = maDV;
		this.soLuong = soLuong;
	}

	public HoaDon_SanPhamDV() {
		super();
		// TODO Auto-generated constructor stub
	}

}
