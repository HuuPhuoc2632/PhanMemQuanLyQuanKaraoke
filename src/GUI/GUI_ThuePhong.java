package GUI;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import dao.KhachHang_dao;
import dao.Phong_dao;
import dao.ThongTinDat_dao;
import dao.ThongTinSuDungPhong_dao;
import entity.KhachHang;
import entity.Phong;
import entity.ThongTinDat;
import entity.ThongTinSuDungPhong;
import entity.ThongTinTaiKhoan;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_ThuePhong extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtSdt;
	public static JPanel pnDanhSach;
	private static List<ThongTinDat> ls;
	private static String day = java.time.LocalDate.now().getDayOfMonth() + "";
	public static ArrayList<Phong> list;
	private static ThongTinTaiKhoan tttk;
	private static boolean m;
	@SuppressWarnings("unused")
	private static SimpleDateFormat dateFormat;
	private static JDateChooser txtNgayHat;
	@SuppressWarnings("static-access")
	private static List<Phong> lsPhong = (ArrayList<Phong>) new Phong_dao().getDanhSachPhongTrongTheoLoai(false);
	private static JComboBox<String> cboLoaiPhong;
	private static boolean n;
	private static JLabel lblGioVaoHat;
	private static JComboBox<String> cboKhachHang;
	public static String maKH;
	private static String sdtKH;
	private static boolean b;
	@SuppressWarnings("unused")
	private Date at;
	public static JComboBox<String> cboTrangThai;
	private JTextField txtSdtTimKiem;

	@SuppressWarnings({ "static-access", "deprecation" })
	public GUI_ThuePhong(ThongTinTaiKhoan tttk) throws ParseException {
		this.tttk = tttk;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnThongTin = new JPanel();
		pnThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông Tin Thuê Phòng"));
		pnThongTin.setBackground(Color.WHITE);
		pnThongTin.setBounds(0, 0, 883, 98);
		contentPane.add(pnThongTin);
		pnThongTin.setLayout(null);

		JLabel lblSdt = new JLabel("Số điện thoại:");
		lblSdt.setFont(new Font("Constantia", Font.BOLD, 14));
		lblSdt.setBounds(10, 60, 94, 22);
		pnThongTin.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtSdt.setColumns(10);
		txtSdt.setBounds(133, 58, 230, 20);
		pnThongTin.add(txtSdt);

		JLabel lblKhachHang = new JLabel("Khách hàng:");
		lblKhachHang.setFont(new Font("Constantia", Font.BOLD, 14));
		lblKhachHang.setBounds(411, 58, 94, 22);
		pnThongTin.add(lblKhachHang);

		cboKhachHang = new JComboBox<String>();
		cboKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cboKhachHang.setBackground(Color.WHITE);
		cboKhachHang.setBounds(529, 56, 289, 21);
		pnThongTin.add(cboKhachHang);

		JButton btnThem = new JButton("+");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_ManHinhChinh.resetComponentMain();
				GUI_ManHinhChinh.addComponent(new GUI_KhachHang("Thuê").tabKhachHang());
				GUI_ManHinhChinh.setBorderEmpty();
				GUI_ManHinhChinh.resetTab();
				GUI_ManHinhChinh.checkKH = 1;
				GUI_ManHinhChinh.btnKhachHang.setBackground(new Color(176, 196, 222));
				GUI_ManHinhChinh.resetColorTab();
				GUI_ManHinhChinh.btnKhachHang.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
				GUI_ManHinhChinh.btnKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
				GUI_ManHinhChinh.lblNameApp.setText("QUẢN LÝ KHÁCH HÀNG");
			}

		});
		btnThem.setToolTipText("Thêm khách hàng mới");
		btnThem.setBackground(Color.WHITE);
		btnThem.setBounds(828, 54, 45, 30);
		pnThongTin.add(btnThem);

		JLabel lblNgayHat = new JLabel("Ngày hát:");
		lblNgayHat.setFont(new Font("Constantia", Font.BOLD, 14));
		lblNgayHat.setBounds(411, 24, 82, 20);
		pnThongTin.add(lblNgayHat);

		JLabel lblGiohat = new JLabel("Giờ vào:");
		lblGiohat.setFont(new Font("Constantia", Font.BOLD, 14));
		lblGiohat.setBounds(10, 26, 94, 20);
		pnThongTin.add(lblGiohat);

		lblGioVaoHat = new JLabel("00:00");
		lblGioVaoHat.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
		lblGioVaoHat.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGioVaoHat.setBounds(133, 26, 175, 20);
		pnThongTin.add(lblGioVaoHat);

		JPanel pntacvu = new JPanel();
		pntacvu.setBackground(Color.WHITE);
		pntacvu.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Sắp xếp"));
		pntacvu.setBounds(881, 0, 185, 98);
		contentPane.add(pntacvu);
		pntacvu.setLayout(null);

		JLabel lblLoaiPhongTimKiem = new JLabel("Loại:");
		lblLoaiPhongTimKiem.setFont(new Font("Constantia", Font.BOLD, 13));
		lblLoaiPhongTimKiem.setBounds(10, 21, 45, 20);
		pntacvu.add(lblLoaiPhongTimKiem);

		cboLoaiPhong = new JComboBox<String>();
		cboLoaiPhong.setFont(new Font("Constantia", Font.BOLD, 13));
		cboLoaiPhong.setBackground(Color.WHITE);
		cboLoaiPhong.setBounds(56, 21, 119, 21);
		pntacvu.add(cboLoaiPhong);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Constantia", Font.BOLD, 13));
		lblTrangThai.setBounds(10, 67, 73, 20);
		pntacvu.add(lblTrangThai);

		cboTrangThai = new JComboBox<String>();
		cboTrangThai.setFont(new Font("Constantia", Font.BOLD, 10));
		cboTrangThai.setBackground(Color.WHITE);
		cboTrangThai.setBounds(81, 67, 94, 21);
		pntacvu.add(cboTrangThai);
		cboTrangThai.addItem("Phòng trống");
		cboTrangThai.addItem("Đang hát");

		JPanel pnDanhSachPhong = new JPanel();
		pnDanhSachPhong.setLayout(null);
		pnDanhSachPhong.setBounds(0, 136, 1066, 467);
		contentPane.add(pnDanhSachPhong);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1066, 437);
		pnDanhSachPhong.add(scrollPane);

		pnDanhSach = new JPanel();
		scrollPane.setViewportView(pnDanhSach);
		pnDanhSach.setLayout(new GridLayout(0, 6, 15, 25));
		txtNgayHat = new JDateChooser();

		Date at = new SimpleDateFormat("yyyy-MM-dd").parse(java.time.LocalDate.now() + "");
		txtNgayHat.setDate(at);
		txtNgayHat.setDateFormatString("dd-MM-yyyy");
		txtNgayHat.setBounds(529, 24, 231, 19);
		pnThongTin.add(txtNgayHat);

		JLabel lblTitle = new JLabel("PHÒNG TRỐNG CÓ THỂ HÁT");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setBackground(Color.CYAN);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Constantia", Font.BOLD, 25));
		lblTitle.setBounds(0, 97, 1066, 43);
		contentPane.add(lblTitle);

		JLabel lblSdtTimKiem = new JLabel("Tìm SĐT:");
		lblSdtTimKiem.setFont(new Font("Constantia", Font.BOLD, 14));
		lblSdtTimKiem.setBounds(10, 110, 86, 22);
		contentPane.add(lblSdtTimKiem);

		txtSdtTimKiem = new JTextField();

		txtSdtTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtSdtTimKiem.setColumns(10);
		txtSdtTimKiem.setBounds(133, 108, 191, 20);
		contentPane.add(txtSdtTimKiem);

		txtSdt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sdt = txtSdt.getText();
				if (sdt.length() >= 1) {
					cboKhachHang.removeAllItems();
					new KhachHang_dao();
					for (KhachHang khachHang : KhachHang_dao.getDSKHTheoSDT(sdt)) {
						cboKhachHang.addItem(khachHang.getMaKH() + "-" + khachHang.getHoKH() + " "
								+ khachHang.getTenKH() + "-" + khachHang.getSđt());
					}
				} else {
					cboKhachHang.removeAllItems();
				}
			}
		});

		cboLoaiPhong.addItem("Phòng thường");
		cboLoaiPhong.addItem("Phòng VIP");
		cboLoaiPhong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ls = new ThongTinDat_dao().getDanhSachPhongDaDatTheoNgay(day);
					list = (ArrayList<Phong>) new Phong_dao().getDanhSachPhong();
					if (cboLoaiPhong.getSelectedItem() == "Phòng thường") {
						lblTitle.setText("PHÒNG THƯỜNG CÓ THỂ HÁT");
						if (cboTrangThai.getSelectedItem() == "Phòng trống") {
							thayDoiCbo(false);
						} else {
							thayDoiCbo(true);
						}

					} else if (cboLoaiPhong.getSelectedItem() == "Phòng VIP") {
						lblTitle.setText("PHÒNG VIP CÓ THỂ HÁT");
						if (cboTrangThai.getSelectedItem() == "Phòng trống") {
							thayDoiCbo(false);
							lblTitle.setText("PHÒNG TRỐNG CÓ THỂ HÁT");
						} else {
							lblTitle.setText("PHÒNG ĐANG ĐƯỢC SỬ DỤNG");
							thayDoiCbo(true);
						}
					}
				}
			}
		});
		cboTrangThai.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (cboTrangThai.getSelectedItem() == "Phòng trống") {
						txtSdtTimKiem.hide();
						lblSdtTimKiem.hide();
						lblTitle.setText("PHÒNG TRỐNG CÓ THỂ HÁT");
						thayDoiCbo(false);
					} else {
						txtSdtTimKiem.show();
						lblSdtTimKiem.show();
						lblTitle.setText("PHÒNG ĐANG ĐƯỢC SỬ DỤNG");
						thayDoiCbo(true);
					}
				}
			}
		});
		txtSdtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				removeComponent();
				if (txtSdtTimKiem.getText().length() >= 1) {
					KhachHang k = new KhachHang_dao().getKHKhopSDT(txtSdtTimKiem.getText());
					if (k != null) {
						List<ThongTinSuDungPhong> listSDP = new ThongTinSuDungPhong_dao()
								.getDanhSachPhongDangSuDungTheoMaKH(k.getMaKH());
						for (ThongTinSuDungPhong thongTinSuDungPhong : listSDP) {
							Phong ph = new Phong_dao().getPhongTrongTheoMa(thongTinSuDungPhong.getMaPh());
							pnDanhSach.add(new GUI_PhongDanhSach(ph, tttk, false).DanhSachPhong());
						}
						for (int i = 0; i <= 5; i++) {
							pnDanhSach.add(new GUI_PhongTrong().PhongNull());
						}
					} else if (txtSdtTimKiem.getText().length() == 0) {
						removeComponent();
						thayDoiCbo(true);
						cboLoaiPhong.setSelectedIndex(0);
					}
				}
				else {
					thayDoiCbo(true);
				}
			}
		});

//		Load danh sách phòng
		thayDoiCbo(false);
		txtSdtTimKiem.hide();
		lblSdtTimKiem.hide();
//		set speed of scrollPane
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);

	}

	public static void removeComponent() {
		Component[] components = pnDanhSach.getComponents();
		for (Component component : components) {
			pnDanhSach.remove(component);
		}
		pnDanhSach.revalidate();
		pnDanhSach.repaint();
	}

	public static void loadDanhSachPhong(boolean b) throws ParseException {
		removeComponent();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = sp.format(txtNgayHat.getDate());
		String year = date.substring(0, date.indexOf("-"));
		String mon = date.substring(date.indexOf("-") + 1);
		String month = mon.substring(0, mon.indexOf("-"));
		String day = date.substring(date.indexOf("-") + 4);
		int yearInt = Integer.parseInt(year);
		int monthInt = Integer.parseInt(month) - 1;
		int dayOfMonthInt = Integer.parseInt(day);
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		calendar.set(yearInt, monthInt, dayOfMonthInt);
		@SuppressWarnings("static-access")
		List<ThongTinDat> ls = new ThongTinDat_dao()
				.getDanhSachPhongDaDatTheoNgay(calendar.get(calendar.DAY_OF_MONTH) + "");
		for (Phong p : lsPhong) {
			if (ls.size() == 0 && p.isTrangThai() == b) {
				pnDanhSach.add(new GUI_PhongDanhSach(p, tttk, false).DanhSachPhong());
			} else if (p.isTrangThai() == b) {
				for (int i = 0; i < ls.size(); i++) {
					if (p.getMaPhong().equalsIgnoreCase(ls.get(i).getMaPh())) {
						m = false;
						break;
					} else {
						m = true;
					}
				}
				if (m && p.isTrangThai() == b) {
					pnDanhSach.add(new GUI_PhongDanhSach(p, tttk, false).DanhSachPhong());
				}
			}
		}
		for (int i = 0; i <= 5; i++) {
			pnDanhSach.add(new GUI_PhongTrong().PhongNull());
		}
	}

	@SuppressWarnings("static-access")
	public static void thayDoiCbo(boolean b) {
		if (cboLoaiPhong.getSelectedItem() == "Phòng thường") {
			n = false;
		} else if (cboLoaiPhong.getSelectedItem() == "Phòng VIP") {
			n = true;
		}
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = sp.format(txtNgayHat.getDate());
		String year = date.substring(0, date.indexOf("-"));
		String mon = date.substring(date.indexOf("-") + 1);
		String month = mon.substring(0, mon.indexOf("-"));
		String day = date.substring(date.indexOf("-") + 4);
		int yearInt = Integer.parseInt(year);
		int monthInt = Integer.parseInt(month) - 1;
		int dayOfMonthInt = Integer.parseInt(day);
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		calendar.set(yearInt, monthInt, dayOfMonthInt);
		List<ThongTinDat> ls = new ThongTinDat_dao()
				.getDanhSachPhongDaDatTheoNgay(java.time.LocalDate.now().getDayOfMonth() + "");
		removeComponent();
		new Phong_dao();
		for (Phong p : new Phong_dao().getDanhSachPhongTrongTheoLoai(n)) {
			if (ls.size() == 0 && p.isTrangThai() == b) {
				pnDanhSach.add(new GUI_PhongDanhSach(p, tttk, false).DanhSachPhong());
			} else if (p.isTrangThai() == b) {
				for (int i = 0; i < ls.size(); i++) {
					if (p.getMaPhong().equalsIgnoreCase(ls.get(i).getMaPh())) {
						m = false;
						break;
					} else {
						m = true;
					}
				}
				if (m && p.isTrangThai() == b) {
					pnDanhSach.add(new GUI_PhongDanhSach(p, tttk, false).DanhSachPhong());
				}
			}
		}
		for (int i = 0; i <= 5; i++) {
			pnDanhSach.add(new GUI_PhongTrong().PhongNull());
		}
	}

//	public String chuyen24h() {
//		String b = timePicker.getSelectedTime();
//		String hour = b.substring(0, b.indexOf(":"));
//		String min = b.substring(b.indexOf(":") + 1);
//		String minNoAa = min.substring(0, min.indexOf(" "));
//		int hourInt = Integer.parseInt(hour);
//		String regex = ".*P.*";
//		if (kiemTraNhap(b, regex)) {
//			hourInt = hourInt + 12;
//			if(hourInt==24)
//				hourInt=12;
//		}
//		else {
//			if(hourInt == 12)
//				hourInt = 00;
//		}
//		return hourInt + ":" + minNoAa ;
//	}
	public boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

	public static boolean checkNull() {
		if (lblGioVaoHat.getText() != null && txtNgayHat.getDate() != null && txtSdt.getText() != null
				&& cboKhachHang.getSelectedItem() != null) {
			return true;
		} else
			return false;
	}

	public Component tabThuePhong() {
		return contentPane;
	}

	@SuppressWarnings("static-access")
	public boolean rangBuocNgayDat() {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = sp.format(txtNgayHat.getDate());
		String year = date.substring(0, date.indexOf("-"));
		String mon = date.substring(date.indexOf("-") + 1);
		String month = mon.substring(0, mon.indexOf("-"));
		String day = mon.substring(3);
		int yearInt = Integer.parseInt(year);
		int monthInt = Integer.parseInt(month) - 1;
		int dayOfMonthInt = Integer.parseInt(day);
		Calendar c = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		c.set(yearInt, monthInt, dayOfMonthInt);
		if (txtNgayHat.getDate() == null) {
			return false;
		} else if (c.get(c.DAY_OF_MONTH) - java.time.LocalDate.now().getDayOfMonth() > 1
				&& c.get(c.MONTH) - (java.time.LocalDate.now().getMonthValue() - 1) == 0
				|| c.get(c.DAY_OF_MONTH) - java.time.LocalDate.now().getDayOfMonth() < 0
						&& c.get(c.MONTH) - (java.time.LocalDate.now().getMonthValue() - 1) == 0) {
			return false;
		} else if (c.get(c.DAY_OF_MONTH) > 1 && c.get(c.MONTH) - (java.time.LocalDate.now().getMonthValue() - 1) >= 1) {
			return false;
		}
		return true;
	}

	public static String getMaKH() {
		if (cboKhachHang.getSelectedItem() != null) {
			String b = cboKhachHang.getSelectedItem() + "";
			maKH = b.substring(0, b.indexOf("-"));
		} else {
			maKH = null;
		}
		return maKH;

	}

	public static String getSdtKH() {
		if (cboKhachHang.getSelectedItem() != null) {
			String b = cboKhachHang.getSelectedItem() + "";
			String a = b.substring(b.indexOf("-"));
			sdtKH = a.substring(a.indexOf("-"));
		} else {
			sdtKH = null;
		}
		return sdtKH;
	}

	public static void removeComponent2() {
		Component[] components = GUI_ThuePhong.pnDanhSach.getComponents();
		for (Component component : components) {
			GUI_ThuePhong.pnDanhSach.remove(component);
		}
		GUI_ThuePhong.pnDanhSach.revalidate();
		GUI_ThuePhong.pnDanhSach.repaint();
	}

	public static void loadLaiDanhSachPhongDaDat() {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		String dateStr = sp.format(date);
		String year = dateStr.substring(0, dateStr.indexOf("-"));
		String mon = dateStr.substring(dateStr.indexOf("-") + 1);
		String month = mon.substring(0, mon.indexOf("-"));
		String day = dateStr.substring(dateStr.indexOf("-") + 4);
		int yearInt = Integer.parseInt(year);
		int monthInt = Integer.parseInt(month) - 1;
		int dayOfMonthInt = Integer.parseInt(day);
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		calendar.set(yearInt, monthInt, dayOfMonthInt);
		Component[] components = pnDanhSach.getComponents();
		for (Component component : components) {
			pnDanhSach.remove(component);
		}
		pnDanhSach.revalidate();
		pnDanhSach.repaint();
		ThongTinDat_dao ttd_d = new ThongTinDat_dao();
		List<String> dsMaPhong = new ArrayList<String>();
		for (ThongTinDat ttd : ttd_d.getDanhSachPhongDaDatTheoNgay(calendar.get(Calendar.DAY_OF_MONTH) + "")) {
			if (dsMaPhong.size() == 0) {
				dsMaPhong.add(ttd.getMaPh());
			} else if (dsMaPhong.size() > 0) {
				for (int i = 0; i < dsMaPhong.size(); i++) {
					if (ttd.getMaPh().equalsIgnoreCase(dsMaPhong.get(i))) {
						b = false;
						break;
					}
					b = true;
				}
				if (b) {
					dsMaPhong.add(ttd.getMaPh());
				}
			}
		}
		for (String ma : dsMaPhong) {
			for (Phong pTest : new Phong_dao().getDanhSachPhongTrongTheoMa(ma)) {
				GUI_PhongDatDanhSach pdds = new GUI_PhongDatDanhSach(pTest, tttk,
						calendar.get(Calendar.DAY_OF_MONTH) + "");
				pnDanhSach.add(pdds.DanhSachPhong());
			}
		}
		for (int i = 0; i <= 5; i++) {
			pnDanhSach.add(new GUI_PhongTrong().PhongNull());

		}
	}

	public static void loadDanhSachPhongDangSuDung() {
		Component[] components = pnDanhSach.getComponents();
		for (Component component : components) {
			pnDanhSach.remove(component);
		}
		pnDanhSach.revalidate();
		pnDanhSach.repaint();
		if (cboLoaiPhong.getSelectedItem() == "Phòng VIP") {
			for (Phong pTest : new Phong_dao().getDanhSachPhong()) {
				if (pTest.isTrangThai() == true && pTest.isLoaiPhong() == true) {
					pnDanhSach.add(new GUI_PhongDanhSach(pTest, tttk, false).DanhSachPhong());
				}
			}
			for (int i = 0; i <= 5; i++) {
				pnDanhSach.add(new GUI_PhongTrong().PhongNull());
			}

		} else {
			for (Phong pTest : new Phong_dao().getDanhSachPhong()) {
				if (pTest.isTrangThai() == true && pTest.isLoaiPhong() == false) {
					pnDanhSach.add(new GUI_PhongDanhSach(pTest, tttk, false).DanhSachPhong());
				}

			}
			for (int i = 0; i <= 5; i++) {
				pnDanhSach.add(new GUI_PhongTrong().PhongNull());
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void loadDanhSachPhongTrong() {
		ls = new ThongTinDat_dao().getDanhSachPhongDaDatTheoNgay(day);
		list = (ArrayList<Phong>) new Phong_dao().getDanhSachPhong();
		for (ThongTinDat thongTinDat : ls) {
			for (int i = 0; i < list.size(); i++) {
				{
					if (thongTinDat.getMaPh().equalsIgnoreCase(list.get(i).getMaPhong())
							&& thongTinDat.getThoiGianDat().getHours() - java.time.LocalTime.now().getHour() < 3
							&& thongTinDat.getThoiGianDat().getHours() - java.time.LocalTime.now().getHour() >= 0) {
						list.remove(i);
					}
				}
			}
		}
		if (cboLoaiPhong.getSelectedItem() == "Phòng thường") {
			Component[] components = pnDanhSach.getComponents();
			for (Component component : components) {
				pnDanhSach.remove(component);
			}
			pnDanhSach.revalidate();
			pnDanhSach.repaint();
			if (cboTrangThai.getSelectedItem() == "Phòng trống") {
				for (Phong pTest : list) {
					if (pTest.isLoaiPhong() == false && pTest.isTrangThai() == false) {
						pnDanhSach.add(new GUI_PhongDanhSach(pTest, tttk, false).DanhSachPhong());
					}
				}
				for (int i = 0; i <= 5; i++) {
					pnDanhSach.add(new GUI_PhongTrong().PhongNull());
				}
			} else if (cboLoaiPhong.getSelectedItem() == "Đang hát") {
				for (Phong pTest : list) {
					if (pTest.isLoaiPhong() == false && pTest.isTrangThai() == true) {
						pnDanhSach.add(new GUI_PhongDanhSach(pTest, tttk, false).DanhSachPhong());
					}

				}
				for (int i = 0; i <= 5; i++) {
					pnDanhSach.add(new GUI_PhongTrong().PhongNull());
				}
			}
		} else {
			Component[] components = pnDanhSach.getComponents();
			for (Component component : components) {
				pnDanhSach.remove(component);
			}
			pnDanhSach.revalidate();
			pnDanhSach.repaint();
			if (cboTrangThai.getSelectedItem() == "Phòng trống") {
				for (Phong pTest : list) {
					if (pTest.isLoaiPhong() == true && pTest.isTrangThai() == false) {
						pnDanhSach.add(new GUI_PhongDanhSach(pTest, tttk, true).DanhSachPhong());
					}

				}
				for (int i = 0; i <= 5; i++) {
					pnDanhSach.add(new GUI_PhongTrong().PhongNull());
				}
			} else {
				for (Phong pTest : list) {
					if (pTest.isLoaiPhong() == true && pTest.isTrangThai() == true) {
						pnDanhSach.add(new GUI_PhongDanhSach(pTest, tttk, true).DanhSachPhong());
					}

				}
				for (int i = 0; i <= 5; i++) {
					pnDanhSach.add(new GUI_PhongTrong().PhongNull());
				}
			}
		}
	}

	public static void refresh() {
		thayDoiCbo(false);
		cboLoaiPhong.setSelectedIndex(0);
		cboTrangThai.setSelectedIndex(0);
	}

	public static void setTextChoKhachHang(String sdt, String kh) {
		txtSdt.setText(sdt);
		cboKhachHang.removeAllItems();
		cboKhachHang.addItem(kh);
	}

	public static void setTextPhongDangHat(String gioVao, String sdt, String kh) {
		lblGioVaoHat.setText(gioVao);
		txtSdt.setText(sdt);
		cboKhachHang.removeAllItems();
		cboKhachHang.addItem(kh);
	}
}
