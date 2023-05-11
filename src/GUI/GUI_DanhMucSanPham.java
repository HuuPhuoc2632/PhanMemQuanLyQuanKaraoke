//Huỳnh Hữu Phước
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.HoaDon_SanPhamDV;
import entity.Phong;
import entity.SanPhamDV;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;

import dao.HoaDon_SanPhamDV_dao;
import dao.SanPhamDV_dao;

import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;

public class GUI_DanhMucSanPham extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnDanhMuc;
	private JLabel lblThanhTien;
	private JLabel lblTenDanhMuc;
	private JSpinner spSoLuong;
	private double thanhTien;
	private String b;
	private SanPhamDV spdv;
	private int sl;
	private boolean check;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("removal")
	public GUI_DanhMucSanPham(SanPhamDV sp, Phong p, String hd) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(725, 92);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnDanhMuc = new JPanel();
		pnDanhMuc.setBackground(Color.WHITE);
		pnDanhMuc.setBounds(0, 0, 666, 50);
		pnDanhMuc.setPreferredSize(new Dimension(666, 40));
		contentPane.add(pnDanhMuc);
		pnDanhMuc.setLayout(null);

		lblTenDanhMuc = new JLabel("");
		lblTenDanhMuc.setFont(new Font("Constantia", Font.PLAIN, 14));
		lblTenDanhMuc.setBounds(20, 15, 115, 20);
		pnDanhMuc.add(lblTenDanhMuc);

		lblThanhTien = new JLabel("");
		lblThanhTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblThanhTien.setBounds(505, 15, 89, 13);
		pnDanhMuc.add(lblThanhTien);
		lblThanhTien.setText(sp.getDonGia() + " VNĐ");

		spSoLuong = new JSpinner();
		spSoLuong.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

		b = spSoLuong.getValue() + "";
		check = true;
		spSoLuong.addChangeListener(new ChangeListener() {
			private int n;

			public void stateChanged(ChangeEvent e) {
				HoaDon_SanPhamDV hdsp = new HoaDon_SanPhamDV_dao().getHoaDonSanPham(sp.getMaDV(), hd);
				try {
					spdv = new SanPhamDV_dao().getSanPhamTheoMa(sp.getMaDV());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				sl = spdv.getSoLuongTon();
				String a = spSoLuong.getValue() + "";
				double v = Double.parseDouble(a);
				int c = Integer.parseInt(a);
				int x = Integer.parseInt(b);
				if (hdsp != null) {
					n = hdsp.getSoLuong();
				}
				if (c >= 1) {
					List<HoaDon_SanPhamDV> ls = new HoaDon_SanPhamDV_dao().getDanhSach(sp.getMaDV(), hd);
					if (ls.size() > 0) {
						try {
							if (c - x >= 0 && spdv.getSoLuongTon() > 0) {
								spdv.setSoLuongTon(spdv.getSoLuongTon() - 1);
								new SanPhamDV_dao().updateSanPham(sp.getMaDV(), spdv);
								new HoaDon_SanPhamDV_dao().updateHoaDonSP(hd, sp.getMaDV(), n + 1);
								GUI_ThemDichVu.loadDB(hd);
								b = a;
								check = true;
							} else if (c - x >= 0 && spdv.getSoLuongTon() == 0 && check) {
								JOptionPane.showMessageDialog(null, "Sản phẩm này đã hết");
								check = false;
								spSoLuong.setValue(spSoLuong.getPreviousValue());
								thanhTien = sp.getDonGia() * v;
								LoadThanhTien(thanhTien);
							} else if (c - x < 0) {
								new HoaDon_SanPhamDV_dao().updateHoaDonSP(hd, sp.getMaDV(), hdsp.getSoLuong() - 1);
								spdv.setSoLuongTon(spdv.getSoLuongTon() + 1);
								new SanPhamDV_dao().updateSanPham(sp.getMaDV(), spdv);
								GUI_ThemDichVu.loadDB(hd);
								b = a;
								check = true;
							}
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						if (spdv.getSoLuongTon() == 0 && check) {
							JOptionPane.showMessageDialog(null, "Sản phẩm này đã hết");
							spSoLuong.setValue(spSoLuong.getPreviousValue());
							thanhTien = sp.getDonGia() * v;
							LoadThanhTien(thanhTien);
							check = false;
						} else {
							new HoaDon_SanPhamDV_dao().insertHoaDon_SanPhamDV(hd, sp.getMaDV(),
									spSoLuong.getValue() + "");
							spdv.setSoLuongTon(spdv.getSoLuongTon() - 1);
							try {
								new SanPhamDV_dao().updateSanPham(sp.getMaDV(), spdv);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							GUI_ThemDichVu.addRow(sp, c);
						}

					}
				} else if (Integer.parseInt(spSoLuong.getValue() + "") == 0) {
					boolean boo = false;
					try {
						boo = new HoaDon_SanPhamDV_dao().checkSanPhamTonTaiTrongHoaDon(sp.getMaDV());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if (boo) {
						try {
							new HoaDon_SanPhamDV_dao().updateHoaDonSP(hd, sp.getMaDV(), hdsp.getSoLuong() - 1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						spdv.setSoLuongTon(spdv.getSoLuongTon() + 1);
						try {
							new SanPhamDV_dao().updateSanPham(sp.getMaDV(), spdv);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (hdsp.getSoLuong() == 1) {
							new HoaDon_SanPhamDV_dao().delete(sp.getMaDV());
						}
						GUI_ThemDichVu.loadDB(hd);
						b = a;
						check = true;
					} else {
						try {
							if (spdv.getSoLuongTon() != 0) {
								new HoaDon_SanPhamDV_dao().updateHoaDonSP(hd, sp.getMaDV(), n - 1);
								spdv.setSoLuongTon(sl + 1);
								new SanPhamDV_dao().updateSanPham(sp.getMaDV(), spdv);
								GUI_ThemDichVu.loadDB(hd);
							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						GUI_ThemDichVu.updateRow(sp, c);
					}
				}

				if (a.equalsIgnoreCase("0")) {
					lblThanhTien.setText(sp.getDonGia() + " VNĐ");
				} else if (spdv.getSoLuongTon() != 0) {
					thanhTien = sp.getDonGia() * v;
					LoadThanhTien(thanhTien);
				}

			}
		});
		spSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		spSoLuong.setBounds(303, 12, 44, 20);
		pnDanhMuc.add(spSoLuong);
		lblTenDanhMuc.setText(sp.getTenDV());

	}

	public void LoadThanhTien(Double thanhTien) {
		lblThanhTien.setText(thanhTien + " VNĐ");
	}

	public Component danhSachSanPham() {
		return pnDanhMuc;
	}
}