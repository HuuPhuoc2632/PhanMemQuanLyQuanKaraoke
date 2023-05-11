//Huỳnh Hữu Phước
package GUI;

import java.awt.Color;
import java.awt.Component;
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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPopupMenu;

public class GUI_PhongDatDanhSach extends JFrame implements MouseListener, ActionListener {

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
	private List<ThongTinDat> ls;
	@SuppressWarnings("unused")
	private String dateString;
	private JLabel lblSucChua;
	private JMenuItem itHuy;
	private String day;
	private String gio = null, kh = null, maKH = null;
	private ThongTinTaiKhoan tttk;
	private JMenuItem itSuDung;
	private String sdt;
	@SuppressWarnings("unused")
	private KhachHang khg;
	private ArrayList<Phong> list;
	private int n;
	private HoaDon hdon;

	/**
	 * Create the frame.
	 */

	public GUI_PhongDatDanhSach(Phong p, ThongTinTaiKhoan tttk, String day) {
		this.phong = p;
		this.day = day;
		this.tttk = tttk;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 176, 241);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension(150, 195));
		contentPane.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.setBackground(Color.white);

		img = new ImageIcon(GUI_PhongDatDanhSach.class.getResource("/icon/booking_Room.png"));
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

//		add item vào popup menu
		itSuDung = new JMenuItem("Sử dụng");
		popupMenu.add(itSuDung);
		itSuDung.setEnabled(GUI_DatPhong.checkNut());
		if (GUI_DatPhong.checkNut()) {
			itSuDung.setEnabled(checkThoiGianDat());
		}
		itHuy = new JMenuItem("Hủy đặt");
		popupMenu.add(itHuy);

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
		for (ThongTinDat ttd : new ThongTinDat_dao().getDanhSachPhongDaDatTheoMaVaNgay(phong.getMaPhong(), day)) {
			gio = ttd.getThoiGianDat() + "";
			KhachHang k = new KhachHang_dao().getDSKHTheoMa(ttd.getMaKH());
			khg = k;
			kh = k.getHoKH() + " " + k.getTenKH();
			maKH = k.getMaKH();
		}
		itHuy.addActionListener(this);
		itSuDung.addActionListener(this);
		list = (ArrayList<Phong>) new Phong_dao().getDanhSachPhong();

	}

	public Component DanhSachPhong() {
		return contentPane;
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
		this.contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLUE));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		lblMaPhong.setForeground(Color.BLACK);
		contentPane.setBackground(Color.white);
		lblMaPhong.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblLoaiPhong.setForeground(Color.BLACK);
		lblLoaiPhong.setFont(new Font("Constantia", Font.BOLD, 11));
		lblGia.setForeground(Color.BLACK);
		lblGia.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblSucChua.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblSucChua.setForeground(Color.BLACK);
		this.contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
	}

	public KhachHang getKhachHang() {
		return new KhachHang_dao().getDSKHTheoMa(maKH);
	}

	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(itHuy)) {
			if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn hủy đặt phòng không ", "Hủy đặt phòng",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if (new ThongTinDat_dao().deleteThongTinDatPhong(phong.getMaPhong(), maKH, day)) {
					GUI_DatPhong.loadLaiDanhSachPhongDaDat();
					JOptionPane.showMessageDialog(null, "Đã hủy thành công");
				} else {
					JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi");
				}
			}
		} else if (o.equals(itSuDung)) {
			if (!phong.isTrangThai()) {
				if (JOptionPane.showConfirmDialog(null, "Xác nhận sử dụng phòng hát " + phong.getMaPhong(),
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
													+ thongTinDat.getThoiGianDat().getMinutes()
													+ " Bạn có muốn sử dụng ?",
											"Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
										phong.setTrangThai(true);
										if (GUI_DatPhong.cboLoaiPhong.getSelectedItem() != "Phòng đã đặt") {
											for (Phong pTest : list) {
												if (pTest.isLoaiPhong() == false && pTest.isTrangThai() == false) {
													GUI_DatPhong.pnDanhSach.add(
															new GUI_PhongDanhSach(pTest, tttk, true).DanhSachPhong());
												}
											}
											for (int i = 0; i <= 5; i++) {
												GUI_DatPhong.pnDanhSach.add(new GUI_PhongTrong().PhongNull());
											}
										} else {
											GUI_DatPhong.loadLaiDanhSachPhongDaDat();
										}
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
										hdon = new HoaDon(autoIDHoaDon(), ql, nv, GUI_DatPhong.getMaKH(), gioVao, null,
												0.0);
										if (new HoaDon_dao().insertHoaDon(hdon)) {
											new Phong_HoaDon_dao().insertPhong_HoaDon(hdon, phong.getMaPhong());
											ThongTinSuDungPhong td = new ThongTinSuDungPhong(phong.getMaPhong(), gioVao,
													GUI_DatPhong.getMaKH(), hdon.getMaHD());
											new ThongTinSuDungPhong_dao().insertThoiDiem(td);
											phong.setTrangThai(true);
											new GUI_PhongDatDanhSach(phong, tttk,
													java.time.LocalDate.now().getDayOfMonth() + "")
													.xoaPhongDatKhiSuDung();
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
												setVisible(false);
												JOptionPane.showMessageDialog(null, "Thuê phòng thành công");
											}

										} else {
											JOptionPane.showMessageDialog(null, "Lỗi!!!");
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

					}

				}

			}
		}

	}

	public boolean xoaPhongDatKhiSuDung() {
		if (new ThongTinDat_dao().deleteThongTinDatPhong(phong.getMaPhong(), maKH, day)
				&& GUI_DatPhong.cboLoaiPhong.getSelectedItem() == "Phòng đã đặt") {
			GUI_DatPhong.removePhongDat();
			return true;
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		List<ThongTinDat> ls = new ThongTinDat_dao().getDanhSachPhongDaDatTheoMa(phong.getMaPhong());
		if (ls.size() == 0) {
			gio = null;
			kh = null;
		} else {
			for (ThongTinDat ttd : new ThongTinDat_dao().getDanhSachPhongDaDatTheoMaVaNgay(phong.getMaPhong(), day)) {
				gio = ttd.getThoiGianDat() + "";
				KhachHang k = new KhachHang_dao().getDSKHTheoMa(ttd.getMaKH());
				kh = k.getHoKH() + " " + k.getTenKH();
				maKH = k.getMaKH();
				sdt = k.getSđt();
			}
		}
		GUI_DatPhong.loadThongTinDatPhong(gio, sdt, maKH + "-" + kh);
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

	public boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

	@SuppressWarnings("deprecation")
	public boolean checkThoiGianDat() {
		ThongTinDat ttd = new ThongTinDat_dao().getPhongDaDatTheoNgayVaMaPhong(day, phong.getMaPhong());
		if (ttd == null) {
			return true;
		}
		if (ttd.getThoiGianDat().getHours() - java.time.LocalDateTime.now().getHour() > 1) {
			return false;
		}
		return true;
	}

}
