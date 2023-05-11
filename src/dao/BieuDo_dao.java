
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.MyConnection;

public class BieuDo_dao {
	private Connection con;

	public BieuDo_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public float loadDoanhThuQuy1() {
		float tongDoanhThu = 0;
		try {
			PreparedStatement smt = con.prepareStatement("select * from HoaDon where month(ThoiDiemTT) in (1,2,3)");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				tongDoanhThu += rs.getFloat("TongThanhToan");

			}
			return tongDoanhThu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}

	public float loadDoanhThuQuy2() {
		float tongDoanhThu = 0;
		try {
			PreparedStatement smt = con.prepareStatement("select * from HoaDon where month(ThoiDiemTT) in (4,5,6)");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				tongDoanhThu += rs.getFloat("TongThanhToan");

			}
			return tongDoanhThu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}

	public float loadDoanhThuQuy3() {
		float tongDoanhThu = 0;
		try {
			PreparedStatement smt = con.prepareStatement("select * from HoaDon where month(ThoiDiemTT) in (7,8,9)");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				tongDoanhThu += rs.getFloat("TongThanhToan");

			}
			return tongDoanhThu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}

	public float loadDoanhThuQuy4() {
		float tongDoanhThu = 0;
		try {
			PreparedStatement smt = con.prepareStatement("select * from HoaDon where month(ThoiDiemTT) in (10,11,12)");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				tongDoanhThu += rs.getFloat("TongThanhToan");

			}
			return tongDoanhThu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}

	public float loadDoanhThuTheoThang(String thang, String nam) {
		float tongDoanhThu = 0;
		try {
			PreparedStatement smt = con
					.prepareStatement("select * from HoaDon where month(ThoiDiemTT) = ? and year(ThoiDiemTT) = ?");
			smt.setString(1, thang);
			smt.setString(2, nam);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				tongDoanhThu += rs.getFloat("TongThanhToan");
			}
			return tongDoanhThu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public float loadDoanhThuTheoThangCuaNhanVien(String thang, String nam, String maNV) {
		float tongDoanhThu = 0;
		try {
			PreparedStatement smt = con.prepareStatement(
					"select * from HoaDon where month(ThoiDiemTT) = ? and year(ThoiDiemTT) = ? and MaNhanVien = ? and MaQuanLy is null");
			smt.setString(1, thang);
			smt.setString(2, nam);
			smt.setString(3, maNV);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				tongDoanhThu += rs.getFloat("TongThanhToan");
			}
			return tongDoanhThu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public float loadDoanhThuTheoThangCuaQuanLy(String thang, String nam, String maQL) {
		float tongDoanhThu = 0;
		try {
			PreparedStatement smt = con.prepareStatement(
					"select * from HoaDon where month(ThoiDiemTT) = ? and year(ThoiDiemTT) = ? and MaNhanVien is null and MaQuanLy = ?");
			smt.setString(1, thang);
			smt.setString(2, nam);
			smt.setString(3, maQL);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				tongDoanhThu += rs.getFloat("TongThanhToan");
			}
			return tongDoanhThu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public double loadDoanhThuCaNam(String nam) {
		double tongDoanhThu = 0;
		try {
			PreparedStatement smt = con
					.prepareStatement("select * from HoaDon where TongThanhToan != 0 and year(ThoiDiemTT)=?");
			smt.setString(1, nam);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				tongDoanhThu += rs.getDouble("TongThanhToan");
			}
			return tongDoanhThu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public double loadDoanhThuCaNamCuaNhanVien(String nam, String maNV) {
		double tongDoanhThu = 0;
		try {
			PreparedStatement smt = con.prepareStatement(
					"select * from HoaDon where TongThanhToan != 0 and year(ThoiDiemTT)=? and MaNhanVien = ? and MaQuanLy is null");
			smt.setString(1, nam);
			smt.setString(2, maNV);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				tongDoanhThu += rs.getDouble("TongThanhToan");
			}
			return tongDoanhThu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tongDoanhThu;
	}

	public double loadDoanhThuCaNamCuaQuanLy(String nam, String maQL) {
		double tongDoanhThu = 0;
		try {
			PreparedStatement smt = con.prepareStatement(
					"select * from HoaDon where TongThanhToan != 0 and year(ThoiDiemTT)=? and MaNhanVien is null and MaQuanLy =? ");
			smt.setString(1, nam);
			smt.setString(2, maQL);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				tongDoanhThu += rs.getDouble("TongThanhToan");
			}
			return tongDoanhThu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
