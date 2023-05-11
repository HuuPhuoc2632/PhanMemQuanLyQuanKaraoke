//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.ThongTinDat;

public class ThongTinDat_dao {
	private Connection con;
	private ThongTinDat ttd;

//	private String sql;
//	private PreparedStatement prstmt;
//	private ResultSet resultSet;
	public ThongTinDat_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public boolean insertThongTinDat(ThongTinDat i, String dateTime) {
		try {
			String dateTimeSql = dateTime;
			PreparedStatement stmt = con
					.prepareStatement("insert into ThongTinDat(MaPhong, MaKhachHang, ThoiGian) values (?,?,?)");
			stmt.setString(1, i.getMaPh());
			stmt.setString(2, i.getMaKH());
			stmt.setString(3, dateTimeSql);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<ThongTinDat> getDanhSachMaPhong() {
		List<ThongTinDat> dsph = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  ThongTinDat");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ThongTinDat ttd = new ThongTinDat(resultSet.getString("MaKhachHang"), resultSet.getString("MaPhong"),
						null);
				dsph.add(ttd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public List<ThongTinDat> getDanhSachPhongDaDatTheoNgay(String date) {
		List<ThongTinDat> dsph = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  ThongTinDat where DAY(ThongTinDat.ThoiGian) = ?");
			preparedStatement.setString(1, date);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ThongTinDat ttd = new ThongTinDat(resultSet.getString("MaKhachHang"), resultSet.getString("MaPhong"),
						resultSet.getTimestamp("ThoiGian"));
				dsph.add(ttd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public List<ThongTinDat> getDanhSachPhongDaDatTheoNgayVaMaKH(String date, String ma) {
		List<ThongTinDat> dsph = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement(
					"SELECT * FROM  ThongTinDat where DAY(ThongTinDat.ThoiGian) = ? and MaKhachHang = ?");
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ThongTinDat ttd = new ThongTinDat(resultSet.getString("MaKhachHang"), resultSet.getString("MaPhong"),
						resultSet.getTimestamp("ThoiGian"));
				dsph.add(ttd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public ThongTinDat getPhongDaDatTheoNgayVaMaPhong(String date, String ma) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con
					.prepareStatement("SELECT * FROM  ThongTinDat where DAY(ThongTinDat.ThoiGian) = ? and MaPhong = ?");
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ttd = new ThongTinDat(resultSet.getString("MaKhachHang"), resultSet.getString("MaPhong"),
						resultSet.getTimestamp("ThoiGian"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ttd;
	}

	public List<ThongTinDat> getDanhSachPhongDaDatTheoMa(String ma) {
		List<ThongTinDat> dsph = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  ThongTinDat where MaPhong = ?");
			preparedStatement.setString(1, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ThongTinDat ttd = new ThongTinDat(resultSet.getString("MaKhachHang"), resultSet.getString("MaPhong"),
						resultSet.getDate("ThoiGian"));
				dsph.add(ttd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public List<ThongTinDat> getDanhSachPhongDaDatTheoMaKH(String ma) {
		List<ThongTinDat> dsph = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  ThongTinDat where MaKhachHang = ?");
			preparedStatement.setString(1, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ThongTinDat ttd = new ThongTinDat(resultSet.getString("MaKhachHang"), resultSet.getString("MaPhong"),
						resultSet.getDate("ThoiGian"));
				dsph.add(ttd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public List<ThongTinDat> getDanhSachPhongDaDatTheoMaVaNgay(String ma, String ngay) {
		List<ThongTinDat> dsph = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con
					.prepareStatement("SELECT * FROM  ThongTinDat where MaPhong = ? and DAY(ThongTinDat.ThoiGian) = ?");
			preparedStatement.setString(1, ma);
			preparedStatement.setString(2, ngay);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ThongTinDat ttd = new ThongTinDat(resultSet.getString("MaKhachHang"), resultSet.getString("MaPhong"),
						resultSet.getTimestamp("ThoiGian"));
				dsph.add(ttd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public boolean deleteThongTinDatPhong(String maPh, String maKH, String ngay) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"delete from ThongTinDat where MaPhong = ? AND MaKhachHang = ? AND DAY(ThongTinDat.ThoiGian) = ? ");
			stmt.setString(1, maPh);
			stmt.setString(2, maKH);
			stmt.setString(3, ngay);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
