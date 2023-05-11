//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.ThongTinChuyenPhong;

public class ThongTinChuyenPhong_dao {
	private Connection con;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	private SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private ThongTinChuyenPhong td;

	public ThongTinChuyenPhong_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public List<ThongTinChuyenPhong> getThongTinChuyenPhongTheoMaHD(String ma) {
		List<ThongTinChuyenPhong> ls = new ArrayList<>();
		try {
			preparedStatement = con.prepareStatement("SELECT * from ThongTinChuyenPhong where MaHoaDon = ?");
			preparedStatement.setString(1, ma);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				td = new ThongTinChuyenPhong(resultSet.getString("MaHoaDon"), resultSet.getString("MaPhongCu"),
						resultSet.getString("MaPhongMoi"), resultSet.getTimestamp("GioVaoCu"),
						resultSet.getTimestamp("GioVaoMoi"), resultSet.getDouble("TienGioPhongCu"));
				ls.add(td);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	public boolean insertChuyenPhong(ThongTinChuyenPhong td) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"insert into ThongTinChuyenPhong(MaHoaDon, MaPhongCu, MaPhongMoi, GioVaoCu, GioVaoMoi, TienGioPhongCu) values (?,?,?,?,?,?)");
			stmt.setString(1, td.getMaHD());
			stmt.setString(2, td.getMaPhongCu());
			stmt.setString(3, td.getMaPhongMoi());
			stmt.setString(4, sp.format(td.getGioVaoCu()));
			stmt.setString(5, sp.format(td.getGioVaoMoi()));
			stmt.setDouble(6, td.getTienGioPhongCu());
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
