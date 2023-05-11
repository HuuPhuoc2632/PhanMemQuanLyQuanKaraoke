//Huỳnh Hữu Phước
package GUI;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.SanPhamDV;
import entity.SanPhamExportHoaDon;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class GUI_DanhMucTrongHoaDon extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnDanhMuc;


	/**
	 * Create the frame.
	 */
	public GUI_DanhMucTrongHoaDon(SanPhamDV sp, int sl) {
		// this.sp = sp;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(501, 80);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension(489, 25));

		pnDanhMuc = new JPanel();
		pnDanhMuc.setBackground(Color.WHITE);
		pnDanhMuc.setBounds(0, 0, 489, 25);
		contentPane.add(pnDanhMuc);
		pnDanhMuc.setLayout(null);

		JLabel lblTenDV = new JLabel("");
		lblTenDV.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTenDV.setBounds(10, 5, 149, 13);
		pnDanhMuc.add(lblTenDV);
		lblTenDV.setText(sp.getTenDV());

		JLabel lblSoLuong = new JLabel("");
		lblSoLuong.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setBounds(214, 5, 45, 13);
		pnDanhMuc.add(lblSoLuong);
		lblSoLuong.setText(sl + "");

		JLabel lblThanhTien = new JLabel("");
		lblThanhTien.setFont(new Font("Arial", Font.PLAIN, 12));
		lblThanhTien.setBounds(374, 5, 90, 13);
		lblThanhTien.setText(sp.getDonGia() * sl + " VNĐ");
		SanPhamExportHoaDon spex = new SanPhamExportHoaDon(sp.getTenDV(), sl + "", sp.getDonGia() * sl + " VNĐ");
		GUI_ThanhToan.addListEx(spex);

		pnDanhMuc.add(lblThanhTien);
	}

	public Component DanhMuc() {
		return pnDanhMuc;
	}
}
