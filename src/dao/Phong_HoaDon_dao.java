//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectDB.MyConnection;
import entity.HoaDon;
import entity.Phong_HoaDon;

public class Phong_HoaDon_dao {
	private Connection con;
	private Phong_HoaDon ph;

	public Phong_HoaDon_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public boolean insertPhong_HoaDon(HoaDon hd, String ma) {
		try {
			PreparedStatement stmt = con.prepareStatement("insert into Phong_Hoadon(MaPhong,MaHoaDon) values (?,?)");
			stmt.setString(1, ma);
			stmt.setString(2, hd.getMaHD());
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Phong_HoaDon getPhong_HoaDon(String mahd) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  Phong_HoaDon");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ph = new Phong_HoaDon(resultSet.getString("MaPhong"), resultSet.getString("MaHoaDon"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ph;
	}

	public boolean updatePhong_HoaDon(String maPh, String maHD) {
		try {
			PreparedStatement stmt = con.prepareStatement("update Phong_HoaDon set MaPhong = ? where MaHoaDon = ?");
			stmt.setString(1, maPh);
			stmt.setString(2, maHD);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deletePhong_HoaDon(String maPhong) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from Phong_HoaDon where MaPhong = ?");
			stmt.setString(1, maPhong);
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
