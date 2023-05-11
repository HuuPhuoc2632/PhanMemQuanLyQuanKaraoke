package entity;

public class CaTruc {
	private String maCaTruc, ca, thoiGian;

	public CaTruc(String maCaTruc, String ca, String thoiGian) {
		super();
		this.maCaTruc = maCaTruc;
		this.ca = ca;
		this.thoiGian = thoiGian;
	}

	public String getMaCaTruc() {
		return maCaTruc;
	}

	public void setMaCaTruc(String maCaTruc) {
		this.maCaTruc = maCaTruc;
	}

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

}
