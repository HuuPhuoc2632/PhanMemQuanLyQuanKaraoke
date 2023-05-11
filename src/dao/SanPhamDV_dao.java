//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.SanPhamDV;

public class SanPhamDV_dao {
	private Connection con;
	private PreparedStatement PreparedStatement;
	private ResultSet ResultSet;
	private SanPhamDV sp;

	public SanPhamDV_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public List<SanPhamDV> getDanhSachSP() {
		List<SanPhamDV> dssp = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM  SanPhamDV");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				SanPhamDV sp = new SanPhamDV(resultSet.getString("MaDV"), resultSet.getString("TenDichVu"),
						resultSet.getInt("SoLuongTon"), resultSet.getDouble("DonGia"), resultSet.getDate("HanSuDung"),
						resultSet.getString("Loai"), resultSet.getBoolean("TrangThaiKinhDoanh"));
				dssp.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dssp;
	}

	// Nguyen Nhat Khanh
	public boolean insertSanPham(SanPhamDV s) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"insert into SanPhamDV (MaDV, TenDichVu, SoLuongTon, DonGia, " + "HanSuDung) values (?,?,?,?,?)");
			stmt.setString(1, s.getMaDV());
			stmt.setString(2, s.getTenDV());
			stmt.setInt(3, s.getSoLuongTon());
			stmt.setDouble(4, s.getDonGia());
			stmt.setDate(5, new java.sql.Date(s.getHanSD().getTime()));

			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSanPham(String masp) throws SQLException {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from SanPhamDV where MaDV = ?");
			stmt.setString(1, masp);
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateSanPham(String masp, SanPhamDV s) throws SQLException {
		try {
			PreparedStatement stmt = con.prepareStatement("update SanPhamDV set TenDichVu = ?, SoLuongTon = ?, "
					+ "DonGia = ?, HanSuDung = ? where MaDV = ?");
			stmt.setString(1, s.getTenDV());
			stmt.setInt(2, s.getSoLuongTon());
			stmt.setDouble(3, s.getDonGia());
			stmt.setDate(4, new java.sql.Date(s.getHanSD().getTime()));
			stmt.setString(5, masp);
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getTongSanPham() throws SQLException {
		PreparedStatement = con.prepareStatement("select * from SanPhamDV");
		ResultSet = PreparedStatement.executeQuery();
		int n = 0;
		while (ResultSet.next())
			n += 1;
		return n;
	}

	public List<SanPhamDV> getDSSanPhamTheoTen() throws SQLException {
		List<SanPhamDV> dssp = new ArrayList<>();
		String sql = "select * from SanPhamDV order by TenDichVu";
		PreparedStatement = con.prepareStatement(sql);
		ResultSet = PreparedStatement.executeQuery();
		while (ResultSet.next()) {
			SanPhamDV sp = new SanPhamDV(ResultSet.getString(1), ResultSet.getString(5), ResultSet.getInt(2),
					ResultSet.getDouble(3), ResultSet.getDate(4), ResultSet.getString(5), ResultSet.getBoolean(6));
			dssp.add(sp);
		}
		return dssp;
	}

	public List<SanPhamDV> getDSSanPhamTheoGia() throws SQLException {
		List<SanPhamDV> dssp = new ArrayList<>();
		String sql = "select * from SanPhamDV order by DonGia";
		PreparedStatement = con.prepareStatement(sql);
		ResultSet = PreparedStatement.executeQuery();
		while (ResultSet.next()) {
			SanPhamDV sp = new SanPhamDV(ResultSet.getString(1), ResultSet.getString(5), ResultSet.getInt(2),
					ResultSet.getDouble(3), ResultSet.getDate(4), ResultSet.getString(5), ResultSet.getBoolean(6));
			dssp.add(sp);
		}
		return dssp;
	}

	public List<SanPhamDV> getDSSanPhamTheoHsd() throws SQLException {
		List<SanPhamDV> dssp = new ArrayList<>();
		String sql = "select * from SanPhamDV order by HanSuDung";
		PreparedStatement = con.prepareStatement(sql);
		ResultSet = PreparedStatement.executeQuery();
		while (ResultSet.next()) {
			SanPhamDV sp = new SanPhamDV(ResultSet.getString(1), ResultSet.getString(5), ResultSet.getInt(2),
					ResultSet.getDouble(3), ResultSet.getDate(4), ResultSet.getString(5), ResultSet.getBoolean(6));
			dssp.add(sp);
		}
		return dssp;
	}

	public List<SanPhamDV> getDSSanPhamTheoMa(String madv) throws SQLException {
		List<SanPhamDV> dssp = new ArrayList<>();
		String sql = "select * from SanPhamDV where MaDV = ?";
		PreparedStatement = con.prepareStatement(sql);
		PreparedStatement.setString(1, madv);
		ResultSet = PreparedStatement.executeQuery();
		while (ResultSet.next()) {
			SanPhamDV sp = new SanPhamDV(ResultSet.getString(1), ResultSet.getString(5), ResultSet.getInt(2),
					ResultSet.getDouble(3), ResultSet.getDate(4), ResultSet.getString(5), ResultSet.getBoolean(6));
			dssp.add(sp);
		}
		return dssp;
	}

	public SanPhamDV getSanPhamTheoMa(String madv) throws SQLException {
		String sql = "select * from SanPhamDV where MaDV = ?";
		PreparedStatement = con.prepareStatement(sql);
		PreparedStatement.setString(1, madv);
		ResultSet = PreparedStatement.executeQuery();
		while (ResultSet.next()) {
			sp = new SanPhamDV(ResultSet.getString(1), ResultSet.getString(5), ResultSet.getInt(2),
					ResultSet.getDouble(3), ResultSet.getDate(4), ResultSet.getString(5), ResultSet.getBoolean(6));
		}
		return sp;
	}

	public List<SanPhamDV> getDanhSachSanPhamTheoLoai(String loai) throws SQLException {
		List<SanPhamDV> dssp = new ArrayList<>();
		String sql = "select * from SanPhamDV where Loai = ?";
		PreparedStatement = con.prepareStatement(sql);
		PreparedStatement.setString(1, loai);
		ResultSet = PreparedStatement.executeQuery();
		while (ResultSet.next()) {
			SanPhamDV sp = new SanPhamDV(ResultSet.getString(1), ResultSet.getString(5), ResultSet.getInt(2),
					ResultSet.getDouble(3), ResultSet.getDate(4), ResultSet.getString(5), ResultSet.getBoolean(6));
			dssp.add(sp);
		}
		return dssp;
	}

	public List<SanPhamDV> getDanhSachSPTheoTen(String ten) throws SQLException {
		List<SanPhamDV> dssp = new ArrayList<>();
		String sql = "select * from SanPhamDV where TenDichVu like ?";
		PreparedStatement = con.prepareStatement(sql);
		String a = "%" + ten + "%";
		PreparedStatement.setString(1, a);
		ResultSet = PreparedStatement.executeQuery();
		while (ResultSet.next()) {
			SanPhamDV sp = new SanPhamDV(ResultSet.getString(1), ResultSet.getString(5), ResultSet.getInt(2),
					ResultSet.getDouble(3), ResultSet.getDate(4), ResultSet.getString(5), ResultSet.getBoolean(6));
			dssp.add(sp);
		}
		return dssp;
	}
}
