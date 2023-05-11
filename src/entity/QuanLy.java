package entity;

import java.sql.Date;

public class QuanLy extends NhanSu {
	private String maQL, hoQL, tenQL;

	public QuanLy(String maQL, String hoQL, String tenQL, Boolean gioiTinh, String diaChi, Date ngaySinh, String sdt,
			int tuoi, String tenTaiKhoan) {
		super();
		this.maQL = maQL;
		this.hoQL = hoQL;
		this.tenQL = tenQL;
		GioiTinh = gioiTinh;
		DiaChi = diaChi;
		NgaySinh = ngaySinh;
		SDT = sdt;
		this.tuoi = tuoi;
		this.tenTaiKhoan = tenTaiKhoan;

	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMaQL() {
		return maQL;
	}

	public void setMaQL(String maQL) {
		this.maQL = maQL;
	}

	public String getHoQL() {
		return hoQL;
	}

	public void setHoQL(String hoQL) {
		this.hoQL = hoQL;
	}

	public String getTenQL() {
		return tenQL;
	}

	public void setTenQL(String tenQL) {
		this.tenQL = tenQL;
	}

	public void setGioiTinh(boolean gt) {
		GioiTinh = gt;
	}

	public Boolean getGioiTinh() {
		return GioiTinh;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public Date getNgaySinh() {
		return (Date) NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

}
