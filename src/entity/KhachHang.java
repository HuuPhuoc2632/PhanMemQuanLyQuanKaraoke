package entity;

import java.util.Date;

public class KhachHang {
	private String maKH, hoKH, tenKH, sđt, bacThanhVien;
	private boolean gioiTinh, trangThai;
	private Date ngaySinh;

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHoKH() {
		return hoKH;
	}

	public void setHoKH(String hoKH) {
		this.hoKH = hoKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getSđt() {
		return sđt;
	}

	public void setSđt(String sđt) {
		this.sđt = sđt;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getBacThanhVien() {
		return bacThanhVien;
	}

	public void setBacThanhVien(String bacThanhVien) {
		this.bacThanhVien = bacThanhVien;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public KhachHang(String maKH, String hoKH, String tenKH, String sđt, boolean gioiTinh, Date ngaySinh,
			String bacThanhVien, boolean trangThai) {
		super();
		this.maKH = maKH;
		this.hoKH = hoKH;
		this.tenKH = tenKH;
		this.sđt = sđt;
		this.bacThanhVien = bacThanhVien;
		this.gioiTinh = gioiTinh;
		this.trangThai = trangThai;
		this.ngaySinh = ngaySinh;
	}

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

}
