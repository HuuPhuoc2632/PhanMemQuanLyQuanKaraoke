//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.KhachHang;

public class KhachHang_dao {
	private static Connection con;
	private static PreparedStatement prstmt;
	private static ResultSet resultSet;
	private static KhachHang kh;
	private String sql;
	private PreparedStatement preparedStatement;

	public KhachHang_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public List<KhachHang> getDanhSachKhachHang() {
		List<KhachHang> dskh = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement(
					"SELECT KhachHang.MaKhachHang, KhachHang.HoKH, KhachHang.TenKH, KhachHang.GioiTinh, KhachHang.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( KhachHang.NgaySinh), KhachHang.SoDienThoai, KhachHang.BacThanhVien, KhachHang.TrangThaiKhachHang FROM  KhachHang order by MaKhachHang");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				KhachHang kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
						resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"),
						resultSet.getBoolean("GioiTinh"), resultSet.getDate("NgaySinh"),
						resultSet.getString("BacThanhVien"), resultSet.getBoolean("TrangThaiKhachHang"));
				dskh.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dskh;
	}

	public static List<KhachHang> getDSKHTheoSDT(String sdt) {
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		String sql = "SELECT KhachHang.MaKhachHang, KhachHang.HoKH, KhachHang.TenKH, KhachHang.GioiTinh, KhachHang.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( KhachHang.NgaySinh), KhachHang.SoDienThoai , KhachHang.BacThanhVien, KhachHang.TrangThaiKhachHang FROM  KhachHang  WHERE SoDienThoai LIKE ?";
		try {
			String forSql = "%" + sdt + "%";
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, forSql);
			resultSet = prstmt.executeQuery();
			while (resultSet.next()) {
				KhachHang kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
						resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"),
						resultSet.getBoolean("GioiTinh"), resultSet.getDate("NgaySinh"),
						resultSet.getString("BacThanhVien"), resultSet.getBoolean("TrangThaiKhachHang"));
				dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}

	public KhachHang getDSKHTheoMa(String ma) {
		String sql = "SELECT KhachHang.MaKhachHang, KhachHang.HoKH, KhachHang.TenKH, KhachHang.GioiTinh, KhachHang.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( KhachHang.NgaySinh), KhachHang.SoDienThoai , KhachHang.BacThanhVien, KhachHang.TrangThaiKhachHang FROM  KhachHang  WHERE MaKhachHang = ?";
		try {
			String forSql = ma;
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, forSql);
			resultSet = prstmt.executeQuery();
			while (resultSet.next()) {
				kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
						resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"),
						resultSet.getBoolean("GioiTinh"), resultSet.getDate("NgaySinh"),
						resultSet.getString("BacThanhVien"), resultSet.getBoolean("TrangThaiKhachHang"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}

	public KhachHang getKHTheoSDT(String sdt) {
		String sql = "SELECT KhachHang.MaKhachHang, KhachHang.HoKH, KhachHang.TenKH, KhachHang.GioiTinh, KhachHang.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( KhachHang.NgaySinh), KhachHang.SoDienThoai, KhachHang.BacThanhVien, KhachHang.TrangThaiKhachHang FROM  KhachHang  WHERE SoDienThoai = ?";
		try {
			String forSql = sdt;
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, forSql);
			resultSet = prstmt.executeQuery();
			while (resultSet.next()) {
				kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
						resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"),
						resultSet.getBoolean("GioiTinh"), resultSet.getDate("NgaySinh"),
						resultSet.getString("BacThanhVien"), resultSet.getBoolean("TrangThaiKhachHang"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}
	public KhachHang getKHKhopSDT(String sdt) {
		String sql = "SELECT KhachHang.MaKhachHang, KhachHang.HoKH, KhachHang.TenKH, KhachHang.GioiTinh, KhachHang.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( KhachHang.NgaySinh), KhachHang.SoDienThoai, KhachHang.BacThanhVien, KhachHang.TrangThaiKhachHang FROM  KhachHang  WHERE SoDienThoai like ?";
		try {
			String forSql = "%" + sdt + "%";
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, forSql);
			resultSet = prstmt.executeQuery();
			while (resultSet.next()) {
				kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
						resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"),
						resultSet.getBoolean("GioiTinh"), resultSet.getDate("NgaySinh"),
						resultSet.getString("BacThanhVien"), resultSet.getBoolean("TrangThaiKhachHang"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}

	public boolean deleteKH(String maKH) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from KhachHang where MaKhachHang = ?");
			stmt.setString(1, maKH);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean insertKh(KhachHang k) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"insert into KhachHang(MaKhachHang, HoKH, TenKH, GioiTinh,SoDienThoai, NgaySinh, BacThanhVien, TrangThaiKhachHang) values (?,?,?,?,?,?,?,?)");
			stmt.setString(1, k.getMaKH());
			stmt.setString(2, k.getHoKH());
			stmt.setString(3, k.getTenKH());
			stmt.setBoolean(4, k.isGioiTinh());
			stmt.setString(5, k.getSđt());
			stmt.setDate(6, new java.sql.Date(k.getNgaySinh().getTime()));
			stmt.setString(7, k.getBacThanhVien());
			stmt.setBoolean(8, k.isTrangThai());
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public List<KhachHang> getKHTheoTen(String tenKH) {
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		String sql = "select * from KhachHang where TenKH like ?";
		try {
			String forSql = "%" + tenKH + "%";
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, forSql);
			resultSet = prstmt.executeQuery();
			while (resultSet.next()) {
				KhachHang kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
						resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"),
						resultSet.getBoolean("GioiTinh"), resultSet.getDate("NgaySinh"),
						resultSet.getString("BacThanhVien"), resultSet.getBoolean("TrangThaiKhachHang"));
				dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}

	public List<KhachHang> getDSKHTheoTen() {
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		String sql = "SELECT KhachHang.MaKhachHang, KhachHang.HoKH, KhachHang.TenKH, KhachHang.GioiTinh, KhachHang.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( KhachHang.NgaySinh), KhachHang.SoDienThoai, KhachHang.BacThanhVien, KhachHang.TrangThaiKhachHang FROM  KhachHang order by TenKH";
		try {
			prstmt = con.prepareStatement(sql);
			resultSet = prstmt.executeQuery();
			while (resultSet.next()) {
				KhachHang kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
						resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"),
						resultSet.getBoolean("GioiTinh"), resultSet.getDate("NgaySinh"),
						resultSet.getString("BacThanhVien"), resultSet.getBoolean("TrangThaiKhachHang"));
				dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}

	public List<KhachHang> getDanhHangTheoTuoi() {
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			preparedStatement = con.prepareStatement(
					"SELECT KhachHang.MaKhachHang, KhachHang.HoKH, KhachHang.TenKH, KhachHang.GioiTinh, KhachHang.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( KhachHang.NgaySinh), KhachHang.SoDienThoai, KhachHang.BacThanhVien, KhachHang.TrangThaiKhachHang FROM  KhachHang order by Tuoi");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				KhachHang kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
						resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"),
						resultSet.getBoolean("GioiTinh"), resultSet.getDate("NgaySinh"),
						resultSet.getString("BacThanhVien"), resultSet.getBoolean("TrangThaiKhachHang"));
				dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}

	public int getTongSoKH() throws SQLException {
		preparedStatement = con.prepareStatement("SELECT * FROM KhachHang");
		resultSet = preparedStatement.executeQuery();
		int n = 0;
		while (resultSet.next()) {
			n += 1;

		}
		return n;
	}

	public List<KhachHang> getDSKHTheoMaKH(String maKH) {
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		sql = "SELECT KhachHang.MaKhachHang, KhachHang.HoKH, KhachHang.TenKH, KhachHang.GioiTinh, KhachHang.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( KhachHang.NgaySinh), KhachHang.SoDienThoai, KhachHang.BacThanhVien, KhachHang.TrangThaiKhachHang FROM  KhachHang order by MaKhachHang";
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maKH);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				KhachHang kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
						resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"),
						resultSet.getBoolean("GioiTinh"), resultSet.getDate("NgaySinh"),
						resultSet.getString("BacThanhVien"), resultSet.getBoolean("TrangThaiKhachHang"));
				dskh.add(kh);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}

	public boolean updateKH(String maKH, KhachHang kh) {
		try {
			prstmt = con.prepareStatement(
					"update KhachHang set HoKH = ?, TenKH = ?, GioiTinh = ?, SoDienThoai = ?, NgaySinh = ?, BacThanhVien = ? where MaKhachHang = ?");
			prstmt.setString(1, kh.getHoKH());
			prstmt.setString(2, kh.getTenKH());
			prstmt.setBoolean(3, kh.isGioiTinh());
			prstmt.setString(4, kh.getSđt());
			prstmt.setDate(5, new java.sql.Date(kh.getNgaySinh().getTime()));
			prstmt.setString(6, kh.getBacThanhVien());
			prstmt.setString(7, maKH);
			int n = prstmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<KhachHang> getKHTheoMa(String maKH) throws SQLException {
		List<KhachHang> dskh = new ArrayList<>();
		String sql = "select *  from KhachHang where MaKhachHang = ?";
		preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, maKH);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			KhachHang kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
					resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"), resultSet.getBoolean("GioiTinh"),
					resultSet.getDate("NgaySinh"), resultSet.getString("BacThanhVien"),
					resultSet.getBoolean("TrangThaiKhachHang"));
			dskh.add(kh);
		}
		return dskh;
	}

	public KhachHang getMotKHTheoMa(String maKH) throws SQLException {
		String sql = "select *  from KhachHang where MaKhachHang = ?";
		preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, maKH);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			kh = new KhachHang(resultSet.getString("MaKhachHang"), resultSet.getString("HoKH"),
					resultSet.getString("TenKH"), resultSet.getString("SoDienThoai"), resultSet.getBoolean("GioiTinh"),
					resultSet.getDate("NgaySinh"), resultSet.getString("BacThanhVien"),
					resultSet.getBoolean("TrangThaiKhachHang"));
		}
		return kh;
	}
}
