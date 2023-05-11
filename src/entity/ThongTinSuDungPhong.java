package entity;

public class ThongTinSuDungPhong {
	private String maPh, gioVao, maKhachHang, maHD;

	public String getMaPh() {
		return maPh;
	}

	public void setMaPh(String maPh) {
		this.maPh = maPh;
	}

	public String getGioVao() {
		return gioVao;
	}

	public void setGioVao(String gioVao) {
		this.gioVao = gioVao;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public ThongTinSuDungPhong(String maPh, String gioVao, String maKhachHang, String maHD) {
		super();
		this.maPh = maPh;
		this.gioVao = gioVao;
		this.maKhachHang = maKhachHang;
		this.maHD = maHD;
	}

	public ThongTinSuDungPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

}
