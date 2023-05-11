//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.ThongTinSuDungPhong;

public class ThongTinSuDungPhong_dao {
	private Connection con;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	private SimpleDateFormat sp = new SimpleDateFormat("HH:mm aa dd-MM-yyyy");
	private ThongTinSuDungPhong td;

	public ThongTinSuDungPhong_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public List<ThongTinSuDungPhong> getDanhSachPhongDangSuDungTheoMa(String ma) {
		List<ThongTinSuDungPhong> ds = new ArrayList<>();
		try {
			preparedStatement = con.prepareStatement("SELECT * from ThongTinSuDungPhong where MaPhong = ?");
			preparedStatement.setString(1, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ThongTinSuDungPhong td = new ThongTinSuDungPhong(resultSet.getString("MaPhong"),
						sp.format(resultSet.getTimestamp("ThoiDiemSuDung")), resultSet.getString("MaKhachHang"),
						resultSet.getString("MaHoaDon"));
				ds.add(td);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ThongTinSuDungPhong getPhongDangSuDungTheoMa(String ma) {
		try {
			preparedStatement = con.prepareStatement("SELECT * from ThongTinSuDungPhong where MaPhong = ?");
			preparedStatement.setString(1, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				td = new ThongTinSuDungPhong(resultSet.getString("MaPhong"),
						sp.format(resultSet.getTimestamp("ThoiDiemSuDung")), resultSet.getString("MaKhachHang"),
						resultSet.getString("MaHoaDon"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return td;
	}

	public List<ThongTinSuDungPhong> getDanhSachPhongDangSuDungTheoMaKH(String ma) {
		List<ThongTinSuDungPhong> ds = new ArrayList<>();
		try {
			preparedStatement = con.prepareStatement("SELECT * from ThongTinSuDungPhong where MaKhachHang = ?");
			preparedStatement.setString(1, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ThongTinSuDungPhong td = new ThongTinSuDungPhong(resultSet.getString("MaPhong"),
						sp.format(resultSet.getTimestamp("ThoiDiemSuDung")), resultSet.getString("MaKhachHang"),
						resultSet.getString("MaHoaDon"));
				ds.add(td);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean insertThoiDiem(ThongTinSuDungPhong td) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"insert into ThongTinSuDungPhong(MaPhong, ThoiDiemSuDung, MaKhachHang, MaHoaDon) values (?,?,?,?)");
			stmt.setString(1, td.getMaPh());
			stmt.setString(2, td.getGioVao());
			stmt.setString(3, td.getMaKhachHang());
			stmt.setString(4, td.getMaHD());
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteTTSDP(String ma) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from ThongTinSuDungPhong where MaPhong = ?");
			stmt.setString(1, ma);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean updateThongTinSDP(String maPhong, String maHD) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("update ThongTinSuDungPhong set MaPhong = ? where MaHoaDon = ?");
			stmt.setString(1, maPhong);
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
}
