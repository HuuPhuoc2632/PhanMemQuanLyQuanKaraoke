//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.HoaDon_SanPhamDV;

public class HoaDon_SanPhamDV_dao {
	private Connection con;
	private ResultSet resultSet;
	private HoaDon_SanPhamDV hdsp;

	public HoaDon_SanPhamDV_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public boolean insertHoaDon_SanPhamDV(String mahd, String ma, String soLuong) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into HoaDon_SanPhamDV(MaHoaDon, MaDV, SoLuong) values (?,?,?)");
			stmt.setString(1, mahd);
			stmt.setString(2, ma);
			stmt.setString(3, soLuong);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(String madv) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from HoaDon_SanPhamDV where MaDV = ?");
			stmt.setString(1, madv);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean updateHoaDonSP(String MaHD, String MaDV, int sl) throws SQLException {
		try {
			PreparedStatement stmt = con
					.prepareStatement("update HoaDon_SanPhamDV set SoLuong = ? where MaHoaDon = ? and MaDV = ?");
			stmt.setString(2, MaHD);
			stmt.setString(3, MaDV);
			stmt.setInt(1, sl);
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<HoaDon_SanPhamDV> getDanhSach(String maDV, String maHD) {
		List<HoaDon_SanPhamDV> dshd = new ArrayList<>();
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from HoaDon_SanPhamDV where MaDV = ? and MaHoaDon = ?");
			stmt.setString(1, maDV);
			stmt.setString(2, maHD);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				HoaDon_SanPhamDV hdsp = new HoaDon_SanPhamDV(resultSet.getString("MaHoaDon"),
						resultSet.getString("MaDV"), resultSet.getInt("SoLuong"));
				dshd.add(hdsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dshd;
	}

	public HoaDon_SanPhamDV getHoaDonSanPham(String maDV, String maHD) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from HoaDon_SanPhamDV where MaDV = ? and MaHoaDon = ?");
			stmt.setString(1, maDV);
			stmt.setString(2, maHD);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				hdsp = new HoaDon_SanPhamDV(resultSet.getString("MaHoaDon"), resultSet.getString("MaDV"),
						resultSet.getInt("SoLuong"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hdsp;
	}

	public List<HoaDon_SanPhamDV> getDanhSachTheoMaHD(String maHD) {
		List<HoaDon_SanPhamDV> dshd = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from HoaDon_SanPhamDV where MaHoaDon = ?");
			stmt.setString(1, maHD);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				HoaDon_SanPhamDV hdsp = new HoaDon_SanPhamDV(resultSet.getString("MaHoaDon"),
						resultSet.getString("MaDV"), resultSet.getInt("SoLuong"));
				dshd.add(hdsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dshd;
	}

	public boolean checkSanPhamTonTaiTrongHoaDon(String ma) throws SQLException {
		hdsp = null;
		PreparedStatement stmt = con.prepareStatement("select * from HoaDon_SanPhamDV where MaDV = ?");
		stmt.setString(1, ma);
		resultSet = stmt.executeQuery();
		while (resultSet.next()) {
			hdsp = new HoaDon_SanPhamDV(resultSet.getString("MaHoaDon"), resultSet.getString("MaDV"),
					resultSet.getInt("SoLuong"));
		}
		if (hdsp != null) {
			return true;
		}
		return false;
	}
}
