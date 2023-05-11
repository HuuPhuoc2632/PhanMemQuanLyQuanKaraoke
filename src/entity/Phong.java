package entity;

public class Phong {
	private String maPhong;
	private boolean loaiPhong, trangThai, trangThaiSuDung;

	public boolean isTrangThaiSuDung() {
		return trangThaiSuDung;
	}

	public void setTrangThaiSuDung(boolean trangThaiSuDung) {
		this.trangThaiSuDung = trangThaiSuDung;
	}

	private double donGia;
	private int sucChua;

	public int getSucChua() {
		return sucChua;
	}

	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}

	public Phong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public boolean isLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(boolean loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public Phong(String maPhong, boolean loaiPhong, boolean trangThai, double donGia, int sucChua,
			boolean trangThaiSuDung) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.trangThai = trangThai;
		this.trangThaiSuDung = trangThaiSuDung;
		this.donGia = donGia;
		this.sucChua = sucChua;
	}

}
