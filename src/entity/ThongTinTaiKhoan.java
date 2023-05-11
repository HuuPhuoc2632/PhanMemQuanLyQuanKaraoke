package entity;

import java.util.Date;

public class ThongTinTaiKhoan {
	private String Ma, Ho, Ten;
	private String DiaChi, SDT, tenTaiKhoan;
	private Boolean GioiTinh;
	private Date NgaySinh;
	private int tuoi;

	public String getMa() {
		return Ma;
	}

	public void setMa(String ma) {
		Ma = ma;
	}

	public String getHo() {
		return Ho;
	}

	public void setHo(String ho) {
		Ho = ho;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
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

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public Boolean getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return NgaySinh;
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

	public ThongTinTaiKhoan(String maNV, String hoNV, String tenNV, Boolean gioiTinh, Date ngaySinh, int Tuoi,
			String sDT, String diaChi, String tenTaiKhoan) {
		super();
		Ma = maNV;
		Ho = hoNV;
		Ten = tenNV;
		DiaChi = diaChi;
		SDT = sDT;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
		tuoi = Tuoi;
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public ThongTinTaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

}
