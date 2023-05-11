//Huỳnh Hữu Phước
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Phong;
import entity.SanPhamExportHoaDon;
import entity.ThongTinChuyenPhong;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.SwingConstants;

public class GUI_GioHatTrongHoaDon extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnDanhMuc;
	private double gioVao;
	private double gioRa;
	private String sl;
	private double slg;
	private DecimalFormat formatter;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public GUI_GioHatTrongHoaDon(Phong p, String soLuong, ThongTinChuyenPhong ttcp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 92);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnDanhMuc = new JPanel();
		pnDanhMuc.setLayout(null);
		pnDanhMuc.setBackground(Color.WHITE);
		pnDanhMuc.setBounds(0, 0, 489, 25);
		contentPane.add(pnDanhMuc);

		JLabel lblGioHat = new JLabel("Giờ hát");
		lblGioHat.setFont(new Font("Arial", Font.PLAIN, 12));
		lblGioHat.setBounds(10, 5, 136, 13);
		pnDanhMuc.add(lblGioHat);

		formatter = new DecimalFormat("###,###,###");

		JLabel lblSoLuong = new JLabel("0");
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSoLuong.setBounds(214, 5, 45, 13);
		pnDanhMuc.add(lblSoLuong);

		JLabel lblThanhTien = new JLabel("0.0");
		lblThanhTien.setFont(new Font("Arial", Font.PLAIN, 12));
		lblThanhTien.setBounds(375, 5, 89, 13);
		pnDanhMuc.add(lblThanhTien);

		if (ttcp == null) {
			lblGioHat.setText("Giờ hát");
			lblSoLuong.setText(soLuong);
			lblThanhTien.setText((formatter.format(p.getDonGia() * Double.parseDouble(soLuong + ""))) + " VNĐ");

			SanPhamExportHoaDon spex = new SanPhamExportHoaDon("Giờ hát", soLuong, lblThanhTien.getText());
			GUI_ThanhToan.addListEx(spex);
		} else {
			gioVao = ttcp.getGioVaoCu().getHours()
					+ Double.parseDouble((Double.parseDouble(ttcp.getGioVaoCu().getMinutes() + "") / 60 + ""));
			gioRa = ttcp.getGioVaoMoi().getHours()
					+ Double.parseDouble((Double.parseDouble(ttcp.getGioVaoMoi().getMinutes() + "") / 60 + ""));
			slg = gioRa - gioVao;
			double s = (double) Math.round(slg * 100) / 100;
			sl = s + "";
			lblGioHat.setText("Giờ hát(" + ttcp.getMaPhongCu() + ")");
			lblSoLuong.setText(sl);
			double t = (double) Math.round(ttcp.getTienGioPhongCu() * 1) / 1;
			lblThanhTien.setText(formatter.format(t));
			SanPhamExportHoaDon spex = new SanPhamExportHoaDon("Giờ hát(" + ttcp.getMaPhongCu() + ")", sl,
					formatter.format(t));
			GUI_ThanhToan.addListEx(spex);
		}
	}

	public Component gioHat() {
		return pnDanhMuc;
	}
}