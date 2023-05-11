//Huỳnh Hữu Phước
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.TaiKhoan;
import entity.ThongTinTaiKhoan;

public class TaiKhoan_dao {
	private Connection con;

	public TaiKhoan_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public List<TaiKhoan> getDsTK() {
		List<TaiKhoan> ds = new ArrayList<TaiKhoan>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from TaiKhoan");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TaiKhoan a = new TaiKhoan(rs.getString("TenTaiKhoan"), rs.getString("MatKhau"));
				ds.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean suaTaiKhoan(TaiKhoan taiKhoan, String newPass) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement("UPDATE TaiKhoan SET MatKhau = ? WHERE TenTaiKhoan = ?");
			preparedStatement.setString(1, newPass);
			preparedStatement.setString(2, taiKhoan.getTenTK());
			int resultExecute = preparedStatement.executeUpdate();
			if (resultExecute > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean themTaiKhoan(TaiKhoan taiKhoan) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement("insert into TaiKhoan(TenTaiKhoan, MatKhau) values (?,?)");
			preparedStatement.setString(1, taiKhoan.getTenTK());
			preparedStatement.setString(2, taiKhoan.getMatKhau());
			int resultExecute = preparedStatement.executeUpdate();
			if (resultExecute > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean xoaTaiKhoan(String ma) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement("delete from TaiKhoan where TenTaiKhoan = ?");
			preparedStatement.setString(1, ma);
			int resultExecute = preparedStatement.executeUpdate();
			if (resultExecute > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ThongTinTaiKhoan getThongTinTabCaNhan(TaiKhoan tk) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement(
					"SELECT QuanLy.MaQuanLy, QuanLy.HoQL, QuanLy.TenQL, QuanLy.GioiTinh, QuanLy.NgaySinh, tuoi=YEAR(GETDATE())- YEAR( QuanLy.NgaySinh), QuanLy.SoDienThoai,  QuanLy.DiaChi, QuanLy.TenTaiKhoan FROM  QuanLy where TenTaiKhoan = ?");
			preparedStatement.setString(1, tk.getTenTK());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				ThongTinTaiKhoan tttk = new ThongTinTaiKhoan(resultSet.getString("MaQuanLy"),
						resultSet.getString("HoQL"), resultSet.getString("TenQL"), resultSet.getBoolean("GioiTinh"),
						resultSet.getDate("NgaySinh"), resultSet.getInt("tuoi"), resultSet.getString("SoDienThoai"),
						resultSet.getString("DiaChi"), resultSet.getString("TenTaiKhoan"));
				return tttk;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
