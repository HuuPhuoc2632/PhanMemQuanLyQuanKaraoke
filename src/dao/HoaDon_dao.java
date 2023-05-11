//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.HoaDon;

public class HoaDon_dao {
	private Connection con;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private HoaDon hd;

	public HoaDon_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public int getTongSoHoaDon() throws SQLException {
		preparedStatement = con.prepareStatement("SELECT * FROM HoaDon");
		resultSet = preparedStatement.executeQuery();
		int n = 0;
		while (resultSet.next()) {
			n += 1;
		}
		return n;
	}

	public boolean insertHoaDon(HoaDon hd) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"insert into HoaDon(MaHoaDon, MaQuanLy, MaNhanVien, MaKhachHang, ThoiDiemSD,ThoiDiemTT, TongThanhToan) values (?,?,?,?,?,?,?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setString(2, hd.getMaQL());
			stmt.setString(3, hd.getMaNV());
			stmt.setString(4, hd.getMaKH());
			stmt.setString(5, hd.getThoiDiemSD());
			stmt.setString(6, hd.getThoiDiemTT());
			stmt.setDouble(7, hd.getTongThanhToan());
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<HoaDon> getDanhSachHoaDon() {
		List<HoaDon> dshd = new ArrayList<>();
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  HoaDon");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				HoaDon hd = new HoaDon(resultSet.getString("MaHoaDon"), resultSet.getString("MaQuanLy"),
						resultSet.getString("MaNhanVien"), resultSet.getString("MaKhachHang"),
						resultSet.getString("ThoiDiemSD"), resultSet.getString("ThoiDiemTT"),
						resultSet.getDouble("TongThanhToan"));
				dshd.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dshd;
	}

	public HoaDon getHoaDon(String maHD) {
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  HoaDon where MaHoaDon = ?");
			preparedStatement.setString(1, maHD);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				hd = new HoaDon(resultSet.getString("MaHoaDon"), resultSet.getString("MaQuanLy"),
						resultSet.getString("MaNhanVien"), resultSet.getString("MaKhachHang"),
						resultSet.getString("ThoiDiemSD"), resultSet.getString("ThoiDiemTT"),
						resultSet.getDouble("TongThanhToan"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hd;
	}

	public boolean updateHoaDon(HoaDon hd, Double tongThanhToan, String tdtt) throws SQLException {
		try {

			PreparedStatement stmt = con
					.prepareStatement("update HoaDon set ThoiDiemTT = ?, TongThanhToan = ? where MaHoaDon = ?");
			stmt.setString(1, tdtt);
			stmt.setDouble(2, tongThanhToan);
			stmt.setString(3, hd.getMaHD());
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<HoaDon> getDanhSachHoaDonGiuaCacNgay(String ngay1, String ngay2) {
		List<HoaDon> dshd = new ArrayList<>();
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  HoaDon where ThoiDiemTT between ? and ?");
			preparedStatement.setString(1, ngay1);
			preparedStatement.setString(2, ngay2);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				HoaDon hd = new HoaDon(resultSet.getString("MaHoaDon"), resultSet.getString("MaQuanLy"),
						resultSet.getString("MaNhanVien"), resultSet.getString("MaKhachHang"),
						resultSet.getString("ThoiDiemSD"), resultSet.getString("ThoiDiemTT"),
						resultSet.getDouble("TongThanhToan"));
				dshd.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dshd;
	}

	public List<HoaDon> getDanhSachHoaDonTrongThang(int Nam, int Thang) {
		List<HoaDon> dshd = new ArrayList<>();
		try {
			preparedStatement = con.prepareStatement(
					"SELECT * FROM  HoaDon where YEAR(HoaDon.ThoiDiemTT)= ? and MONTH(HoaDon.ThoiDiemTT) =? and TongThanhToan != 0");
			preparedStatement.setInt(1, Nam);
			preparedStatement.setInt(2, Thang);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				HoaDon hd = new HoaDon(resultSet.getString("MaHoaDon"), resultSet.getString("MaQuanLy"),
						resultSet.getString("MaNhanVien"), resultSet.getString("MaKhachHang"),
						resultSet.getString("ThoiDiemSD"), resultSet.getString("ThoiDiemTT"),
						resultSet.getDouble("TongThanhToan"));
				dshd.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dshd;
	}

	public List<HoaDon> getDanhSachHoaDonTheoMaKH(String ma) {
		List<HoaDon> dshd = new ArrayList<>();
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  HoaDon where MaKhachHang = ?");
			preparedStatement.setString(1, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				HoaDon hd = new HoaDon(resultSet.getString("MaHoaDon"), resultSet.getString("MaQuanLy"),
						resultSet.getString("MaNhanVien"), resultSet.getString("MaKhachHang"),
						resultSet.getString("ThoiDiemSD"), resultSet.getString("ThoiDiemTT"),
						resultSet.getDouble("TongThanhToan"));
				dshd.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dshd;
	}

	public List<HoaDon> getDanhSachHoaDonTrongThangCuaNhanVien(int Nam, int Thang, String maNV) {
		List<HoaDon> dshd = new ArrayList<>();
		try {
			preparedStatement = con.prepareStatement(
					"SELECT * FROM  HoaDon where YEAR(HoaDon.ThoiDiemTT)= ? and MONTH(HoaDon.ThoiDiemTT) =? and MaNhanVien = ? and MaQuanLy is null and TongThanhToan != 0");
			preparedStatement.setInt(1, Nam);
			preparedStatement.setInt(2, Thang);
			preparedStatement.setString(3, maNV);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				HoaDon hd = new HoaDon(resultSet.getString("MaHoaDon"), resultSet.getString("MaQuanLy"),
						resultSet.getString("MaNhanVien"), resultSet.getString("MaKhachHang"),
						resultSet.getString("ThoiDiemSD"), resultSet.getString("ThoiDiemTT"),
						resultSet.getDouble("TongThanhToan"));
				dshd.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dshd;
	}

	public List<HoaDon> getDanhSachHoaDonTrongThangCuaQuanLy(int Nam, int Thang, String maQL) {
		List<HoaDon> dshd = new ArrayList<>();
		try {
			preparedStatement = con.prepareStatement(
					"SELECT * FROM  HoaDon where YEAR(HoaDon.ThoiDiemTT)= ? and MONTH(HoaDon.ThoiDiemTT) =? and MaNhanVien is null and MaQuanLy = ? and TongThanhToan != 0");
			preparedStatement.setInt(1, Nam);
			preparedStatement.setInt(2, Thang);
			preparedStatement.setString(3, maQL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				HoaDon hd = new HoaDon(resultSet.getString("MaHoaDon"), resultSet.getString("MaQuanLy"),
						resultSet.getString("MaNhanVien"), resultSet.getString("MaKhachHang"),
						resultSet.getString("ThoiDiemSD"), resultSet.getString("ThoiDiemTT"),
						resultSet.getDouble("TongThanhToan"));
				dshd.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dshd;
	}

}
