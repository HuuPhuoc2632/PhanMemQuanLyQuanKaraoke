package entity;

public class SanPhamExportHoaDon {
	private String tenSp, soLuong, thanhTien;

	public String getTenSp() {
		return tenSp;
	}

	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}

	public String getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}

	public String getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(String thanhTien) {
		this.thanhTien = thanhTien;
	}

	public SanPhamExportHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPhamExportHoaDon(String tenSp, String soLuong, String thanhTien) {
		super();
		this.tenSp = tenSp;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
	}

}
