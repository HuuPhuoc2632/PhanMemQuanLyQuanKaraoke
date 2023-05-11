//Huỳnh Hữu Phước
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import dao.HoaDon_dao;
import dao.KhachHang_dao;
import dao.Phong_HoaDon_dao;
import dao.Phong_dao;
import dao.ThongTinDat_dao;
import dao.ThongTinSuDungPhong_dao;
import entity.HoaDon;
import entity.KhachHang;
import entity.Phong;
import entity.ThongTinDat;
import entity.ThongTinSuDungPhong;
import entity.ThongTinTaiKhoan;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPopupMenu;

public class GUI_PhongDanhSach extends JFrame implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblMaPhong;
	private ImageIcon img;
	private Image image;
	private Image newimg;
	private JLabel lblAnh;
	public JPopupMenu popupMenu;
	private Phong phong = new Phong();
	private JLabel lblLoaiPhong;
	private JLabel lblGia;
	private JMenuItem itDatPhong;
	private JMenuItem itTinhTien;
	private JMenuItem itChuyenPhong;
	private JMenuItem itThemDv;
	@SuppressWarnings("unused")
	private String dateString;
	private JLabel lblSucChua;
	private ThongTinTaiKhoan tttk;
	@SuppressWarnings("unused")
	private String maHD, hd;
	private List<ThongTinDat> ls;
	private String day = java.time.LocalDate.now().getDayOfMonth() + "";
	private boolean b;
	private HoaDon hdon;
	private int n;
	@SuppressWarnings("unused")
	private ArrayList<Phong> list;
	private KhachHang khang;

	/**
	 * Create the frame.
	 */

	public GUI_PhongDanhSach(Phong p, ThongTinTaiKhoan tttk, boolean b) {
		this.b = b;
		this.phong = p;
		this.tttk = tttk;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		setBounds(100, 100, 176, 241);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension(150, 195));
		contentPane.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.setBackground(Color.white);
		Cursor cs = Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/pointer.png")).getImage(), new Point(0, 0),
				"Custom cursor");
		contentPane.setCursor(cs);

		if (phong.isTrangThai() == true) {
			img = new ImageIcon(GUI_PhongDanhSach.class.getResource("/icon/television_Playing.png"));
		} else {
			if (phong.isLoaiPhong() == true) {
				img = new ImageIcon(GUI_PhongDanhSach.class.getResource("/icon/television_VIP.png"));
			} else {
				img = new ImageIcon(GUI_PhongDanhSach.class.getResource("/icon/television.png"));

			}
		}

		image = img.getImage();
		newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg);
		lblAnh = new JLabel();
		lblAnh.setIcon(img);
		lblAnh.setBounds(32, 7, 100, 100);
		contentPane.add(lblAnh);

		lblMaPhong = new JLabel();
		lblMaPhong.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblMaPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaPhong.setBounds(0, 101, 162, 23);
		contentPane.add(lblMaPhong);

		lblMaPhong.setText(phong.getMaPhong());

		popupMenu = new JPopupMenu();
		contentPane.add(popupMenu);
		contentPane.addMouseListener(this);

		itTinhTien = new JMenuItem("Tính tiền");
		popupMenu.add(itTinhTien);
		itChuyenPhong = new JMenuItem("Chuyển phòng");
		popupMenu.add(itChuyenPhong);
		itThemDv = new JMenuItem("Thêm dịch vụ");
		popupMenu.add(itThemDv);

		lblLoaiPhong = new JLabel();
		lblLoaiPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoaiPhong.setFont(new Font("Constantia", Font.BOLD, 11));
		lblLoaiPhong.setBounds(0, 124, 162, 23);
		contentPane.add(lblLoaiPhong);

		lblGia = new JLabel();
		lblGia.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblGia.setHorizontalAlignment(SwingConstants.CENTER);
		lblGia.setBounds(0, 170, 162, 23);
		lblGia.setText("0.0 VNĐ/Giờ");
		lblGia.setText(phong.getDonGia() + " VNĐ/Giờ");
		contentPane.add(lblGia);
		if (phong.isLoaiPhong()) {
			lblLoaiPhong.setText("Phòng VIP");
		} else {
			lblLoaiPhong.setText("Phòng thường");
		}

		itTinhTien.addActionListener(this);
		itChuyenPhong.addActionListener(this);
		itThemDv.addActionListener(this);
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));

		lblSucChua = new JLabel("Sức chứa: 10");
		lblSucChua.setHorizontalAlignment(SwingConstants.CENTER);
		lblSucChua.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblSucChua.setBounds(0, 147, 162, 23);
		lblSucChua.setText("Sức chứa: " + phong.getSucChua() + " người");
		contentPane.add(lblSucChua);
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm aa - dd/MM/yyyy");
		dateString = df.format(date);
		checkMenu();
		list = (ArrayList<Phong>) new Phong_dao().getDanhSachPhong();

	}

	public void checkMenu() {
		// check phòng có đang sử dụng hay không
		if (phong.isTrangThai() == true) {
			itTinhTien.setEnabled(true);
			itChuyenPhong.setEnabled(true);
			itThemDv.setEnabled(true);
		} else {
			itTinhTien.setEnabled(false);
			itChuyenPhong.setEnabled(false);
			itThemDv.setEnabled(false);
		}
	}

	public Component DanhSachPhong() {
		return contentPane;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		if (phong.isTrangThai()) {
			ThongTinSuDungPhong ttsd = new ThongTinSuDungPhong_dao().getPhongDangSuDungTheoMa(phong.getMaPhong());
			try {
				khang = new KhachHang_dao().getMotKHTheoMa(ttsd.getMaKhachHang());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			GUI_ThuePhong.setTextPhongDangHat(ttsd.getGioVao(), khang.getSđt(),
					khang.getMaKH() + "-" + khang.getHoKH() + " " + khang.getTenKH());

		} else if (b) {
			if (GUI_DatPhong.checkNull()) {
				if (JOptionPane.showConfirmDialog(null, "Xác nhận đặt phòng hát " + phong.getMaPhong(), "Đặt Phòng",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						GUI_DatPhong.DatPhong(phong.getMaPhong());
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin đặt phòng");
			}

		} else if (!phong.isTrangThai()) {
			if (GUI_ThuePhong.getMaKH() == null) {
				JOptionPane.showMessageDialog(null, "Khách hàng không được trống");
			} else if (JOptionPane.showConfirmDialog(null, "Xác nhận sử dụng phòng hát " + phong.getMaPhong(),
					"Thuê Phòng", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION
					&& phong.isTrangThai() == false) {
				if (java.time.LocalDateTime.now().getHour() > 22) {
					JOptionPane.showMessageDialog(null, "Đã quá tối không thể nhận thêm khách");
				} else if (java.time.LocalDateTime.now().getHour() < 8) {
					JOptionPane.showMessageDialog(null, "Cửa hàng mở cửa sau 8h sáng!");
				} else {
					ls = new ThongTinDat_dao().getDanhSachPhongDaDatTheoNgay(day);
					boolean a = true;
					for (ThongTinDat thongTinDat : ls) {
						{
							if (thongTinDat.getMaPh().equalsIgnoreCase(phong.getMaPhong())) {
								a = false;
								if (JOptionPane.showConfirmDialog(null,
										"Phòng này đã được đặt lúc " + thongTinDat.getThoiGianDat().getHours() + ":"
												+ thongTinDat.getThoiGianDat().getMinutes() + " Bạn có muốn sử dụng ?",
										"Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
									if (GUI_ThuePhong.getMaKH() != null) {
										phong.setTrangThai(true);
										GUI_ThuePhong.removeComponent2();
										GUI_ThuePhong.loadDanhSachPhongTrong();
										new Phong_dao().updatePhong(phong);
										new KhachHang_dao();
										SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
										java.util.Date date = new java.util.Date();
										String gioVao = sp.format(date);
										String regex = ".*QL.*", ql = null, nv = null;
										if (kiemTraNhap(tttk.getMa(), regex)) {
											ql = tttk.getMa();
											nv = null;
										} else {
											ql = null;
											nv = tttk.getMa();
										}
										hdon = new HoaDon(autoIDHoaDon(), ql, nv, GUI_ThuePhong.getMaKH(), gioVao,
												null, 0.0);
										if (new HoaDon_dao().insertHoaDon(hdon)) {
											new Phong_HoaDon_dao().insertPhong_HoaDon(hdon, phong.getMaPhong());
											ThongTinSuDungPhong td = new ThongTinSuDungPhong(phong.getMaPhong(), gioVao,
													GUI_ThuePhong.getMaKH(), hdon.getMaHD());
											new ThongTinSuDungPhong_dao().insertThoiDiem(td);
											phong.setTrangThai(true);
											if (JOptionPane.showConfirmDialog(null,
													"Bạn có muốn gọi thêm các dịch vụ của quán?", "Thêm dịch vụ",
													JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
												try {
													new GUI_ThemDichVu(phong, hdon.getMaHD()).setVisible(true);
												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											} else {
												JOptionPane.showMessageDialog(null, "Thuê phòng thành công");
											}

										} else {
											JOptionPane.showMessageDialog(null, "Lỗi!!!");
										}
									}

									break;
								} else {
									a = false;
									break;
								}
							}

						}
						a = true;
					}
					if (a) {

						if (java.time.LocalDateTime.now().getHour() > 22) {
							JOptionPane.showMessageDialog(null, "Đã quá tối không thể nhận thêm khách");
						} else if (java.time.LocalDateTime.now().getHour() < 8) {
							JOptionPane.showMessageDialog(null, "Cửa hàng mở cửa sau 8h sáng!");
						} else {// Thuê phòng ở đây
							if (GUI_ThuePhong.getMaKH() != null) {
								phong.setTrangThai(true);
								GUI_ThuePhong.removeComponent2();
								new Phong_dao().updatePhong(phong);
								new KhachHang_dao();
								GUI_ThuePhong.loadDanhSachPhongTrong();
								SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
								java.util.Date date = new java.util.Date();
								String gioVao = sp.format(date);
								String regex = ".*QL.*", ql = null, nv = null;
								if (kiemTraNhap(tttk.getMa(), regex)) {
									ql = tttk.getMa();
									nv = null;
								} else {
									ql = null;
									nv = tttk.getMa();
								}
								hdon = new HoaDon(autoIDHoaDon(), ql, nv, GUI_ThuePhong.getMaKH(), gioVao, null, 0.0);
								if (new HoaDon_dao().insertHoaDon(hdon)) {
									new Phong_HoaDon_dao().insertPhong_HoaDon(hdon, phong.getMaPhong());
									ThongTinSuDungPhong td = new ThongTinSuDungPhong(phong.getMaPhong(), gioVao,
											GUI_ThuePhong.getMaKH(), hdon.getMaHD());
									new ThongTinSuDungPhong_dao().insertThoiDiem(td);
									phong.setTrangThai(true);
									if (JOptionPane.showConfirmDialog(null,
											"Bạn có muốn gọi thêm các dịch vụ của quán?", "Thêm dịch vụ",
											JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
										try {
											new GUI_ThemDichVu(phong, hdon.getMaHD()).setVisible(true);
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									} else {
										JOptionPane.showMessageDialog(null, "Thuê phòng thành công");
									}

								} else {
									JOptionPane.showMessageDialog(null, "Lỗi!!!");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Khách hàng không được trống");
							}
						}

					}

				}

			}

		}
	}

	public String autoIDHoaDon() {
		String a = "HD";
		try {
			n = new HoaDon_dao().getTongSoHoaDon() + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date = new Date();
		SimpleDateFormat sp = new SimpleDateFormat("ddMMyyyy");
		String c = sp.format(date);
		String maHd = a + c + n + "";
		for (HoaDon hd : new HoaDon_dao().getDanhSachHoaDon()) {
			if (hd.getMaHD().equalsIgnoreCase(maHd)) {
				n += 1;
			}
		}
		return a + c + n + "";
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (SwingUtilities.isRightMouseButton(e)) {
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		lblMaPhong.setForeground(Color.RED);
		contentPane.setBackground(Color.lightGray);
		lblMaPhong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblLoaiPhong.setForeground(Color.BLUE);
		lblLoaiPhong.setFont(new Font("Constantia", Font.ITALIC | Font.BOLD, 13));
		lblGia.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 13));
		lblGia.setForeground(Color.BLUE);
		lblSucChua.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 13));
		lblSucChua.setForeground(Color.BLUE);
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLUE));
		for (ThongTinSuDungPhong ttsd : new ThongTinSuDungPhong_dao()
				.getDanhSachPhongDangSuDungTheoMa(phong.getMaPhong())) {
			maHD = ttsd.getMaHD();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		lblMaPhong.setForeground(Color.BLACK);
		contentPane.setBackground(Color.white);
		lblMaPhong.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblLoaiPhong.setForeground(Color.BLACK);
		lblLoaiPhong.setFont(new Font("Constantia", Font.BOLD, 11));
		lblGia.setForeground(Color.BLACK);
		lblGia.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblSucChua.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblSucChua.setForeground(Color.BLACK);
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
	}

	public void updateBacThanhVien() {
		double tongTien = 0;
		List<KhachHang> listKH = new KhachHang_dao().getDanhSachKhachHang();
		for (KhachHang khachHang : listKH) {
			List<HoaDon> dshdtong = new HoaDon_dao().getDanhSachHoaDonTheoMaKH(khachHang.getMaKH());
			for (HoaDon hd : dshdtong) {
				tongTien += hd.getTongThanhToan();
			}
			if (tongTien >= 1000000 && tongTien < 3000000) {
				khachHang.setBacThanhVien("Bạc");
				new KhachHang_dao().updateKH(khachHang.getMaKH(), khachHang);
			} else if (tongTien >= 3000000 && tongTien < 6000000) {
				khachHang.setBacThanhVien("Vàng");
				new KhachHang_dao().updateKH(khachHang.getMaKH(), khachHang);
			} else if (tongTien >= 6000000 && tongTien < 10000000) {
				khachHang.setBacThanhVien("Kim Cương");
				new KhachHang_dao().updateKH(khachHang.getMaKH(), khachHang);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(itDatPhong)) {

		} else if (o.equals(itTinhTien)) {
			updateBacThanhVien();
			for (ThongTinSuDungPhong ttsd : new ThongTinSuDungPhong_dao()
					.getDanhSachPhongDangSuDungTheoMa(phong.getMaPhong())) {
				maHD = ttsd.getMaHD();
			}
			try {
				try {
					new GUI_ThanhToan(phong, maHD, tttk).setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (o.equals(itChuyenPhong)) {
			HoaDon hoaDon = new HoaDon_dao().getHoaDon(maHD);
			new GUI_ChuyenPhong(phong, hoaDon).setVisible(true);
		} else if (o.equals(itThemDv)) {
			for (ThongTinSuDungPhong ttsd : new ThongTinSuDungPhong_dao()
					.getDanhSachPhongDangSuDungTheoMa(phong.getMaPhong())) {
				maHD = ttsd.getMaHD();
			}
			try {
				new GUI_ThemDichVu(phong, maHD).setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void suDungPhong() {
		phong.setTrangThai(true);
	}

	public boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

}
