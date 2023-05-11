package entity;

import java.util.Date;

public class SanPhamDV {
	private String maDV, tenDV, loai;
	private int soLuongTon;
	private double donGia;
	private Date hanSD;
	private boolean trangThai;

	public SanPhamDV() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPhamDV(String maDV, String tenDV, int soLuongTon, double donGia, Date hanSD, String loai,
			boolean trangThai) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.loai = loai;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
		this.hanSD = hanSD;
		this.trangThai = trangThai;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public Date getHanSD() {
		return hanSD;
	}

	public void setHanSD(Date hanSD) {
		this.hanSD = hanSD;
	}
}
