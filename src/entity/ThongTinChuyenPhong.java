package entity;

import java.util.Date;

public class ThongTinChuyenPhong {
	private String maHD, maPhongCu, maPhongMoi;
	private Date gioVaoCu, gioVaoMoi;
	private Double tienGioPhongCu;

	public ThongTinChuyenPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThongTinChuyenPhong(String maHD, String maPhongCu, String maPhongMoi, Date gioVaoCu, Date gioVaoMoi,
			Double tienGioPhongCu) {
		super();
		this.maHD = maHD;
		this.maPhongCu = maPhongCu;
		this.maPhongMoi = maPhongMoi;
		this.gioVaoCu = gioVaoCu;
		this.gioVaoMoi = gioVaoMoi;
		this.tienGioPhongCu = tienGioPhongCu;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaPhongCu() {
		return maPhongCu;
	}

	public void setMaPhongCu(String maPhongCu) {
		this.maPhongCu = maPhongCu;
	}

	public String getMaPhongMoi() {
		return maPhongMoi;
	}

	public void setMaPhongMoi(String maPhongMoi) {
		this.maPhongMoi = maPhongMoi;
	}

	public Date getGioVaoCu() {
		return gioVaoCu;
	}

	public void setGioVaoCu(Date gioVaoCu) {
		this.gioVaoCu = gioVaoCu;
	}

	public Date getGioVaoMoi() {
		return gioVaoMoi;
	}

	public void setGioVaoMoi(Date gioVaoMoi) {
		this.gioVaoMoi = gioVaoMoi;
	}

	public Double getTienGioPhongCu() {
		return tienGioPhongCu;
	}

	public void setTienGioPhongCu(Double tienGioPhongCu) {
		this.tienGioPhongCu = tienGioPhongCu;
	}
}
