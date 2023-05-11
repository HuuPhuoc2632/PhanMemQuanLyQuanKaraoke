//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.Phong;

public class Phong_dao {
	private static Connection con;
	private Phong ph;
	private PreparedStatement prstmt;
	private ResultSet resultSet;

	public Phong_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	// Ngô Nhật Thái
	public List<Phong> getDanhSachPhong() {
		List<Phong> dsph = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  Phong where TrangThaiSuDung=1");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Phong ph = new Phong(resultSet.getString("MaPhong"), resultSet.getBoolean("LoaiPhong"),
						resultSet.getBoolean("TinhTrang"), resultSet.getDouble("DonGia"), resultSet.getInt("SucChua"),
						resultSet.getBoolean("TrangThaiSuDung"));
				dsph.add(ph);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public static List<Phong> getDanhSachPhongTrongTheoLoai(boolean loai) {
		List<Phong> dsph = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  Phong where LoaiPhong = ? and TrangThaiSuDung=1");
			preparedStatement.setBoolean(1, loai);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Phong ph = new Phong(resultSet.getString("MaPhong"), resultSet.getBoolean("LoaiPhong"),
						resultSet.getBoolean("TinhTrang"), resultSet.getDouble("DonGia"), resultSet.getInt("SucChua"),
						resultSet.getBoolean("TrangThaiSuDung"));
				dsph.add(ph);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public List<Phong> getDanhSachPhongTrongTheoMa(String ma) {
		List<Phong> dsph = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  Phong where MaPhong = ? and TrangThaiSuDung=1");
			preparedStatement.setString(1, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Phong ph = new Phong(resultSet.getString("MaPhong"), resultSet.getBoolean("LoaiPhong"),
						resultSet.getBoolean("TinhTrang"), resultSet.getDouble("DonGia"), resultSet.getInt("SucChua"),
						resultSet.getBoolean("TrangThaiSuDung"));
				dsph.add(ph);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public Phong getPhongTrongTheoMa(String ma) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  Phong where MaPhong = ? and TrangThaiSuDung=1");
			preparedStatement.setString(1, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ph = new Phong(resultSet.getString("MaPhong"), resultSet.getBoolean("LoaiPhong"),
						resultSet.getBoolean("TinhTrang"), resultSet.getDouble("DonGia"), resultSet.getInt("SucChua"),
						resultSet.getBoolean("TrangThaiSuDung"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ph;
	}

	public boolean updatePhong(Phong p) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("update Phong set TinhTrang = ? where MaPhong = ? and TrangThaiSuDung=1");
			stmt.setBoolean(1, p.isTrangThai());
			stmt.setString(2, p.getMaPhong());
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deletePhong(String maPhong) {
		try {
			System.out.println(maPhong);
			// Xoa HoaDon..
			PreparedStatement stmt = con.prepareStatement("update Phong set TrangThaiSuDung=0 where MaPhong = ?");
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

	public boolean addPhong(Phong phong) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT [dbo].[Phong] ([MaPhong], [LoaiPhong], [TinhTrang], [DonGia], [SucChua], [TrangThaiSuDung]) VALUES (?, ?, ?, ?, ?, ?)");
			stmt.setString(1, phong.getMaPhong());
			stmt.setBoolean(2, phong.isLoaiPhong());
			stmt.setBoolean(3, phong.isTrangThai());
			stmt.setDouble(4, phong.getDonGia());
			stmt.setInt(5, phong.getSucChua());
			stmt.setInt(6, 1);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean capNhatPhong(Phong phong) {
		try {
			int loaiPhong = 0;
			if (phong.isLoaiPhong())
				loaiPhong = 1;
			int tinhTrang = 0;
			if (phong.isTrangThai())
				tinhTrang = 1;
			PreparedStatement stmt = con.prepareStatement(
					"update Phong set LoaiPhong=?, TinhTrang=?, DonGia=?, SucChua=? where MaPhong=? and TrangThaiSuDung=1");
			stmt.setInt(1, loaiPhong);
			stmt.setInt(2, tinhTrang);
			stmt.setDouble(3, phong.getDonGia());
			stmt.setInt(4, phong.getSucChua());
			stmt.setString(5, phong.getMaPhong());
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean kiemTraMaPhong(String maPhong) {
		try {
			PreparedStatement stmt = con.prepareStatement("select * from Phong where MaPhong=?");
			stmt.setString(1, maPhong);
			ResultSet rs = stmt.executeQuery();
			int n = 0;
			while (rs.next()) {
				n += 1;
			}
			if (n == 0)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// VanQuangPhong_final
	public List<Phong> getPhongTheoSucChua() {
		List<Phong> dsph = new ArrayList<>();
		String sql = "select MaPhong, LoaiPhong, TinhTrang, DonGia, SucChua, TrangThaiSuDung from Phong order by SucChua";
		try {
			prstmt = con.prepareStatement(sql);
			resultSet = prstmt.executeQuery();
			while (resultSet.next()) {
				Phong ph = new Phong(resultSet.getString("MaPhong"), resultSet.getBoolean("LoaiPhong"),
						resultSet.getBoolean("TinhTrang"), resultSet.getDouble("DonGia"), resultSet.getInt("SucChua"),
						resultSet.getBoolean("TrangThaiSuDung"));
				dsph.add(ph);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public List<Phong> getPhongTheoGia() {
		List<Phong> dsph = new ArrayList<>();
		String sql = "select * from Phong order by DonGia";
		try {
			prstmt = con.prepareStatement(sql);
			resultSet = prstmt.executeQuery();
			while (resultSet.next()) {
				Phong ph = new Phong(resultSet.getString("MaPhong"), resultSet.getBoolean("LoaiPhong"),
						resultSet.getBoolean("TinhTrang"), resultSet.getDouble("DonGia"), resultSet.getInt("SucChua"),
						resultSet.getBoolean("TrangThaiSuDung"));
				dsph.add(ph);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public List<Phong> getPhongVip() {
		List<Phong> dsph = new ArrayList<>();
		String sql = "select * from Phong where LoaiPhong = 1";
		try {
			prstmt = con.prepareStatement(sql);
			resultSet = prstmt.executeQuery();
			while (resultSet.next()) {
				Phong ph = new Phong(resultSet.getString("MaPhong"), resultSet.getBoolean("LoaiPhong"),
						resultSet.getBoolean("TinhTrang"), resultSet.getDouble("DonGia"), resultSet.getInt("SucChua"),
						resultSet.getBoolean("TrangThaiSuDung"));
				dsph.add(ph);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsph;
	}

	public List<Phong> getPhongTheoMa(String ma) throws SQLException {
		List<Phong> dsph = new ArrayList<>();
		String sql = "select * from Phong where MaPhong = ?";
		prstmt = con.prepareStatement(sql);
		prstmt.setString(1, ma);
		resultSet = prstmt.executeQuery();
		while (resultSet.next()) {
			Phong ph = new Phong(resultSet.getString("MaPhong"), resultSet.getBoolean("LoaiPhong"),
					resultSet.getBoolean("TinhTrang"), resultSet.getDouble("DonGia"), resultSet.getInt("SucChua"),
					resultSet.getBoolean("TrangThaiSuDung"));
			dsph.add(ph);
		}
		return dsph;
	}
}
