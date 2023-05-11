package entity;

import java.util.Date;

public class ThongTinDat {
	private String maKH, maPh;
	private Date thoiGianDat;

	public ThongTinDat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThongTinDat(String maKH, String maPh, Date thoiGianDat) {
		super();
		this.maKH = maKH;
		this.maPh = maPh;
		this.thoiGianDat = thoiGianDat;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getMaPh() {
		return maPh;
	}

	public void setMaPh(String maPh) {
		this.maPh = maPh;
	}

	public Date getThoiGianDat() {
		return thoiGianDat;
	}

	public void setThoiGianDat(Date thoiGianDat) {
		this.thoiGianDat = thoiGianDat;
	}

}
