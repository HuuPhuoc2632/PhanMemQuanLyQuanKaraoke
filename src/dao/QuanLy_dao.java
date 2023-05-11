//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.MyConnection;
import entity.QuanLy;

public class QuanLy_dao {
	private Connection con;
	private String sql;
	private PreparedStatement prstmt;
	private ResultSet resultSet;
	private QuanLy ns;

	public QuanLy_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public QuanLy getQL(String ma) {
		sql = "SELECT QuanLy.MaQuanLy, QuanLy.HoQL, QuanLy.TenQL, QuanLy.GioiTinh, QuanLy.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( QuanLy.NgaySinh), QuanLy.SoDienThoai, QuanLy.DiaChi, QuanLy.TenTaiKhoan FROM  QuanLy where MaQuanLy = ?";
		try {
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, ma);
			resultSet = prstmt.executeQuery();
			while (resultSet.next()) {
				ns = new QuanLy(resultSet.getString("MaQuanLy"), resultSet.getString("HoQL"),
						resultSet.getString("TenQL"), resultSet.getBoolean("GioiTinh"), resultSet.getString("DiaChi"),
						resultSet.getDate("NgaySinh"), resultSet.getString("SoDienThoai"), resultSet.getInt("tuoi"),
						resultSet.getString("TenTaiKhoan"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ns;
	}
}
