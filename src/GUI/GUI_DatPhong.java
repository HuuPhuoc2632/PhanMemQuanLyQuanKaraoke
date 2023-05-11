package GUI;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import dao.KhachHang_dao;
import dao.Phong_dao;
import dao.ThongTinDat_dao;
import entity.KhachHang;
import entity.Phong;
import entity.ThongTinDat;
import entity.ThongTinTaiKhoan;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.raven.swing.TimePicker;
import java.awt.event.HierarchyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class GUI_DatPhong extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtSdt;
	public static JPanel pnDanhSach;
	@SuppressWarnings("unused")
	private static List<ThongTinDat> ls;
	private static String day = java.time.LocalDate.now().getDayOfMonth() + "";
	public static ArrayList<Phong> list;
	private static ThongTinTaiKhoan tttk;
	private static boolean m;
	private static SimpleDateFormat dateFormat;
	private static JDateChooser txtNgayHat;
	public static JComboBox<String> cboLoaiPhong;
	private static boolean n;
	private static JLabel lblGioDat;
	private static TimePicker timePicker;
	private static JComboBox<String> cboKhachHang;
	private static Date at;
	private static JButton btnPre;
	private static JButton btnNext;
	private static JLabel lblTitle;
	private static String dateTime;
	private static boolean b;
	private static ThongTinDat ttd;
	public static String maKH, ngay;
	@SuppressWarnings("unused")
	private static String sdtKH;
	private JTextField txtSdtTimKiem;

	@SuppressWarnings({ "static-access", "deprecation" })
	public GUI_DatPhong(ThongTinTaiKhoan tttk) throws ParseException {
		this.tttk = tttk;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		timePicker = new TimePicker();
		timePicker.setBounds(-1111, 0, 247, 351);
		contentPane.add(timePicker);
		JPanel pnThongTin = new JPanel();
		pnThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông Tin Đặt Phòng"));
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
				GUI_ManHinhChinh.addComponent(new GUI_KhachHang("Đặt").tabKhachHang());
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

		JLabel lblGiohat = new JLabel("Giờ đặt:");
		lblGiohat.setFont(new Font("Constantia", Font.BOLD, 14));
		lblGiohat.setBounds(10, 26, 94, 20);
		pnThongTin.add(lblGiohat);

		lblGioDat = new JLabel("00:00");
		lblGioDat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timePicker.showPopup(contentPane, 360, 0);
			}
		});
		lblGioDat.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
		lblGioDat.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGioDat.setBounds(133, 26, 94, 20);
		pnThongTin.add(lblGioDat);

		timePicker.addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent e) {
				String aa;
				String hour = chuyen24h().substring(0, chuyen24h().indexOf(":"));
				int hourInt = Integer.parseInt(hour);
				if (hourInt >= 0 && hourInt < 12) {
					aa = " AM";
				} else {
					aa = " PM";
				}
				lblGioDat.setText(chuyen24h() + aa);
			}
		});

		JPanel pntacvu = new JPanel();
		pntacvu.setBackground(Color.WHITE);
		pntacvu.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Sắp xếp"));
		pntacvu.setBounds(881, 0, 185, 98);
		contentPane.add(pntacvu);
		pntacvu.setLayout(null);

		JLabel lblLoaiPhongTimKiem = new JLabel("Loại:");
		lblLoaiPhongTimKiem.setFont(new Font("Constantia", Font.BOLD, 13));
		lblLoaiPhongTimKiem.setBounds(10, 24, 45, 20);
		pntacvu.add(lblLoaiPhongTimKiem);

		cboLoaiPhong = new JComboBox<String>();
		cboLoaiPhong.setFont(new Font("Constantia", Font.BOLD, 13));
		cboLoaiPhong.setBackground(Color.WHITE);
		cboLoaiPhong.setBounds(56, 24, 119, 21);
		pntacvu.add(cboLoaiPhong);

		JLabel lblSdtTimKiem = new JLabel("SĐT:");
		lblSdtTimKiem.setFont(new Font("Constantia", Font.BOLD, 13));
		lblSdtTimKiem.setBounds(10, 60, 45, 20);
		pntacvu.add(lblSdtTimKiem);

		txtSdtTimKiem = new JTextField();

		txtSdtTimKiem.setBounds(56, 59, 119, 19);
		pntacvu.add(txtSdtTimKiem);
		txtSdtTimKiem.setColumns(10);

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
		txtNgayHat.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				// TODO Auto-generated method stub
				if ("date".equals(e.getPropertyName())) {
					if (rangBuocNgayDat()) {
						try {
							loadDanhSachPhong();
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Không thể đặt trước quá 1 ngày hoặc trước ngày hiện tại");
						try {
							at = new SimpleDateFormat("yyyy-MM-dd").parse(java.time.LocalDate.now() + "");
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						txtNgayHat.setDate(at);
					}
				}
			}

		});

		Date at = new SimpleDateFormat("yyyy-MM-dd").parse(java.time.LocalDate.now() + "");
		txtNgayHat.setDate(at);
		txtNgayHat.setDateFormatString("dd-MM-yyyy");
		txtNgayHat.setBounds(529, 24, 231, 19);
		pnThongTin.add(txtNgayHat);

		lblTitle = new JLabel("PHÒNG THƯỜNG CÓ THỂ ĐẶT");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setBackground(Color.CYAN);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Constantia", Font.BOLD, 25));
		lblTitle.setBounds(0, 97, 1066, 43);
		contentPane.add(lblTitle);

		btnPre = new JButton("<");
		btnPre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPre.setBounds(280, 100, 45, 30);
		contentPane.add(btnPre);

		btnNext = new JButton(">");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNext.setBounds(743, 100, 45, 30);
		contentPane.add(btnNext);
		btnPre.hide();
		btnNext.hide();

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
						maKH = khachHang.getMaKH();
					}

				} else {
					cboKhachHang.removeAllItems();
				}
			}
		});

		cboLoaiPhong.addItem("Phòng thường");
		cboLoaiPhong.addItem("Phòng VIP");
		cboLoaiPhong.addItem("Phòng đã đặt");
		cboLoaiPhong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ls = new ThongTinDat_dao().getDanhSachPhongDaDatTheoNgay(day);
					list = (ArrayList<Phong>) new Phong_dao().getDanhSachPhong();
					if (cboLoaiPhong.getSelectedItem() == "Phòng thường") {
						lblTitle.setText("PHÒNG THƯỜNG CÓ THỂ ĐẶT");
						try {
							loadDanhSachPhong();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						btnPre.hide();
						btnNext.hide();
						txtNgayHat.show();
						lblNgayHat.show();
						lblGioDat.setBounds(133, 26, 94, 20);
						lblGioDat.setText("00:00");
						txtSdt.setText("");
						cboKhachHang.removeAllItems();
						lblSdtTimKiem.hide();
						txtSdtTimKiem.hide();
					} else if (cboLoaiPhong.getSelectedItem() == "Phòng VIP") {
						lblTitle.setText("PHÒNG VIP CÓ THỂ ĐẶT");
						try {
							loadDanhSachPhong();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						lblSdtTimKiem.hide();
						txtSdtTimKiem.hide();
						btnPre.hide();
						btnNext.hide();
						txtNgayHat.show();
						lblNgayHat.show();
						lblGioDat.setBounds(133, 26, 94, 20);
						lblGioDat.setText("00:00");
						txtSdt.setText("");
						cboKhachHang.removeAllItems();
					} else {
						btnPre.setEnabled(false);
						lblTitle.setText("PHÒNG ĐÃ ĐẶT HÔM NAY");
						removeComponent();
						loadLaiDanhSachPhongDaDat();
						btnPre.show();
						btnNext.show();
						txtNgayHat.hide();
						lblNgayHat.hide();
						lblGioDat.setBounds(133, 26, 144, 20);
						lblSdtTimKiem.show();
						txtSdtTimKiem.show();
					}
				}
			}
		});
		txtSdtTimKiem.addKeyListener(new KeyAdapter() {
			private List<Phong> phn;

			@Override
			public void keyReleased(KeyEvent e) {
				removeComponent();
				if (lblTitle.getText() == "PHÒNG ĐÃ ĐẶT HÔM NAY") {
					ngay = java.time.LocalDate.now().getDayOfMonth() + "";
				} else {
					ngay = java.time.LocalDate.now().getDayOfMonth() + 1 + "";
				}
				String sdt = txtSdtTimKiem.getText();
				if (sdt.length() >=1) {
					removeComponent();
					for (KhachHang khachHang : new KhachHang_dao().getDSKHTheoSDT(sdt)) {
						List<ThongTinDat> ttdd = new ThongTinDat_dao().getDanhSachPhongDaDatTheoNgayVaMaKH(ngay,
								khachHang.getMaKH());
						for (ThongTinDat thongTinDat : ttdd) {
							phn = new Phong_dao().getDanhSachPhongTrongTheoMa(thongTinDat.getMaPh());
							for (Phong phn : phn) {
								pnDanhSach.add(new GUI_PhongDatDanhSach(phn, tttk, ngay).DanhSachPhong());
							}
						}
						for (int i = 0; i < 6; i++) {
							pnDanhSach.add(new GUI_PhongTrong().PhongNull());
						}
					}
				}
				if (sdt.length() == 0) {
					List<ThongTinDat> ttdd = new ThongTinDat_dao().getDanhSachPhongDaDatTheoNgay(ngay);
					for (ThongTinDat thongTinDat : ttdd) {
						phn = new Phong_dao().getDanhSachPhongTrongTheoMa(thongTinDat.getMaPh());
						for (Phong phn : phn) {
							pnDanhSach.add(new GUI_PhongDatDanhSach(phn, tttk, ngay).DanhSachPhong());
						}
					}
					for (int i = 0; i < 6; i++) {
						pnDanhSach.add(new GUI_PhongTrong().PhongNull());
					}
				}
			}
		});
		btnNext.addActionListener(this);
		btnPre.addActionListener(this);
		lblSdtTimKiem.hide();
		txtSdtTimKiem.hide();
//		thayDoiCbo();
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
		if (btnNext.isEnabled() == true) {
			dayOfMonthInt = Integer.parseInt(day);
		} else {
			dayOfMonthInt = Integer.parseInt(day) + 1;
		}
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

	@SuppressWarnings("static-access")
	public void loadDanhSachPhong() throws ParseException {
		boolean x = false;
		removeComponent();
		if (cboLoaiPhong.getSelectedItem() == "Phòng thường") {
			x = false;
		} else if (cboLoaiPhong.getSelectedItem() == "Phòng VIP") {
			x = true;
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
		List<Phong> lsPhong = (ArrayList<Phong>) new Phong_dao().getDanhSachPhongTrongTheoLoai(x);
		List<ThongTinDat> ls = new ThongTinDat_dao()
				.getDanhSachPhongDaDatTheoNgay(calendar.get(calendar.DAY_OF_MONTH) + "");
		for (Phong p : lsPhong) {
			if (ls.size() == 0 && p.isTrangThai() == false) {
				pnDanhSach.add(new GUI_PhongDanhSach(p, tttk, true).DanhSachPhong());
			} else if (p.isTrangThai() == false) {
				for (int i = 0; i < ls.size(); i++) {
					if (p.getMaPhong().equalsIgnoreCase(ls.get(i).getMaPh())) {
						m = false;
						break;
					} else {
						m = true;
					}
				}
				if (m && p.isTrangThai() == false) {
					pnDanhSach.add(new GUI_PhongDanhSach(p, tttk, true).DanhSachPhong());
				}
			}
		}
	}

	public static void thayDoiCbo() {
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
		@SuppressWarnings("static-access")
		List<ThongTinDat> ls = new ThongTinDat_dao()
				.getDanhSachPhongDaDatTheoNgay(calendar.get(calendar.DAY_OF_MONTH) + "");
		removeComponent();
		new Phong_dao();
		for (Phong p : Phong_dao.getDanhSachPhongTrongTheoLoai(n)) {
			if (ls.size() == 0 && p.isTrangThai() == false) {
				pnDanhSach.add(new GUI_PhongDanhSach(p, tttk, true).DanhSachPhong());
			} else if (p.isTrangThai() == false) {
				for (int i = 0; i < ls.size(); i++) {
					if (p.getMaPhong().equalsIgnoreCase(ls.get(i).getMaPh())) {
						m = false;
						break;
					} else {
						m = true;
					}
				}
				if (m && p.isTrangThai() == false) {
					pnDanhSach.add(new GUI_PhongDanhSach(p, tttk, true).DanhSachPhong());
				}
			}
		}
	}

	public static String chuyen24h() {
		String b = timePicker.getSelectedTime();
		String hour = b.substring(0, b.indexOf(":"));
		String min = b.substring(b.indexOf(":") + 1);
		String minNoAa = min.substring(0, min.indexOf(" "));
		int hourInt = Integer.parseInt(hour);
		String regex = ".*P.*";
		if (kiemTraNhap(b, regex)) {
			hourInt = hourInt + 12;
			if (hourInt == 24)
				hourInt = 12;
		} else {
			if (hourInt == 12)
				hourInt = 00;
		}
		return hourInt + ":" + minNoAa;
	}

	public static boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

	public static boolean checkNull() {
		if (lblGioDat.getText() != null && txtNgayHat.getDate() != null && txtSdt.getText() != null
				&& cboKhachHang.getSelectedItem() != null) {
			return true;
		} else
			return false;
	}

	public Component tabDatPhong() {
		return contentPane;
	}

	public static boolean rangBuocNgayDat() {
		Date d = txtNgayHat.getDate();
		Date dt = new Date();
		long getDiff = d.getTime() - dt.getTime();
		long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
		if (txtNgayHat.getDate() == null) {
			return false;
		} else if (getDaysDiff > 0 || getDaysDiff < 0) {
			return false;
		}

		return true;

	}

	public static void removePhongDat() {
		btnNext.setEnabled(false);
		btnPre.setEnabled(true);
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
		if (btnNext.isEnabled() == true) {
			dayOfMonthInt = Integer.parseInt(day) + 1;
		} else {
			dayOfMonthInt = Integer.parseInt(day);
		}
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
				pnDanhSach.add(new GUI_PhongDatDanhSach(pTest, tttk, calendar.get(Calendar.DAY_OF_MONTH) + "")
						.DanhSachPhong());
			}
		}
		for (int i = 0; i <= 5; i++) {
			pnDanhSach.add(new GUI_PhongTrong().PhongNull());
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public static void DatPhong(String maPh) throws HeadlessException, ParseException {
		String aa;
		String hour = chuyen24h().substring(0, chuyen24h().indexOf(":"));
		String min = chuyen24h().substring(chuyen24h().indexOf(":") + 1);
		float minF = Float.parseFloat(min);
		int hourInt = Integer.parseInt(hour);
		float time = hourInt + (minF / 60);
		if (hourInt >= 0 && hourInt < 12) {
			aa = " AM";
		} else {
			aa = " PM";
		}
		float gioHienTai = java.time.LocalDateTime.now().getHour()
				+ ((float) java.time.LocalDateTime.now().getMinute() / 60);
			if (txtNgayHat.getDate().getDate() - java.time.LocalDate.now().getDayOfMonth() == 0&&time - gioHienTai <= 0.1) {
				JOptionPane.showMessageDialog(null, "Thời gian đặt đang sớm hơn hiện tại");
				lblGioDat.setText("Chọn lại");
			} else {
				Date date;
				date = txtNgayHat.getDate();
				if (date != null && !timePicker.getSelectedTime().isEmpty() && rangBuocGioDat() && rangBuocNgayDat()
						&& rangBuocDatSauGioHienTai() && cboKhachHang.getItemCount() != 0) {
					SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
					dateFormat = null;
					dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					dateTime = sp.format(txtNgayHat.getDate()) + " " + chuyen24h();
					try {
						ttd = new ThongTinDat(maKH, maPh, dateFormat.parse(dateTime));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (new ThongTinDat_dao().insertThongTinDat(ttd, dateTime)) {
						JOptionPane.showMessageDialog(null, "Đặt phòng thành công");
						thayDoiCbo();
					} else {
						JOptionPane.showMessageDialog(null, "Lỗi đặt phòng");
					}

				} else if (date == null && timePicker.getSelectedTime().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ngày và giờ đặt không được bỏ trống");
				} else if (cboKhachHang.getItemCount() == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần đặt phòng");
				} else if (!rangBuocGioDat()) {
					JOptionPane.showMessageDialog(null,
							"Giờ đặt không hợp lệ.\nChỉ được phòng sau 8h sáng và trước 23 giờ tối");
				} else if (!rangBuocNgayDat()) {
					JOptionPane.showMessageDialog(null,
							"Ngày đặt không hợp lệ.\nChỉ được phòng trong hôm nay hoặc ngày mai");
				} else if (!rangBuocDatSauGioHienTai()) {
					JOptionPane.showMessageDialog(null,
							"Thời gian đặt không hợp lệ.\nKhông thể đặt phòng sớm hơn thời gian hiện tại");
				}
				else {
					JOptionPane.showMessageDialog(null,
							"Lỗi");
				}
			}
	}

	public static boolean rangBuocGioDat() {
		String hour = chuyen24h().substring(0, chuyen24h().indexOf(":"));
		int hourInt = Integer.parseInt(hour);
		if (hourInt > 22) {
			return false;
		} else if (hourInt < 8) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public static boolean rangBuocDatSauGioHienTai() throws ParseException {
		Date date = new Date();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat = null;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateTime = sp.format(txtNgayHat.getDate()) + " " + chuyen24h();
		if (dateFormat.parse(dateTime).getHours() - date.getHours() < 1
				&& dateFormat.parse(dateTime).getMinutes() - date.getMinutes() <= 0
				&& dateFormat.parse(dateTime).getDay() - date.getDay() == 0) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public static void loadDanhSachPhongTrong() throws ParseException {
		ls = null;
		list = null;
		List<ThongTinDat> ls = new ThongTinDat_dao().getDanhSachPhongDaDatTheoNgay(day);
		ArrayList<Phong> list = (ArrayList<Phong>) new Phong_dao().getDanhSachPhong();
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
			for (Phong pTest : list) {
				if (pTest.isLoaiPhong() == false && pTest.isTrangThai() == false) {
					pnDanhSach.add(new GUI_PhongDanhSach(pTest, tttk, true).DanhSachPhong());
				}
			}
			for (int i = 0; i <= 5; i++) {
				pnDanhSach.add(new GUI_PhongTrong().PhongNull());
			}
		} else {
			Component[] components = pnDanhSach.getComponents();
			for (Component component : components) {
				pnDanhSach.remove(component);
			}
			pnDanhSach.revalidate();
			pnDanhSach.repaint();
			for (Phong pTest : list) {
				if (pTest.isLoaiPhong() == true && pTest.isTrangThai() == false) {
					pnDanhSach.add(new GUI_PhongDanhSach(pTest, tttk, true).DanhSachPhong());
				}
			}
			for (int i = 0; i <= 5; i++) {
				pnDanhSach.add(new GUI_PhongTrong().PhongNull());
			}
		}
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnNext)) {
			lblTitle.setText("PHÒNG ĐÃ ĐẶT NGÀY MAI");
			btnNext.setEnabled(false);
			btnPre.setEnabled(true);
			SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new java.util.Date();
			String dateStr = sp.format(date);
			String year = dateStr.substring(0, dateStr.indexOf("-"));
			String mon = dateStr.substring(dateStr.indexOf("-") + 1);
			String month = mon.substring(0, mon.indexOf("-"));
			String day = dateStr.substring(dateStr.indexOf("-") + 4);
			int yearInt = Integer.parseInt(year);
			int monthInt = Integer.parseInt(month) - 1;
			int dayOfMonthInt = Integer.parseInt(day) + 1;
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
			for (ThongTinDat ttd : ttd_d.getDanhSachPhongDaDatTheoNgay(calendar.get(calendar.DAY_OF_MONTH) + "")) {
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
					GUI_PhongDatDanhSach gui_pdsd = new GUI_PhongDatDanhSach(pTest, tttk,
							calendar.get(Calendar.DAY_OF_MONTH) + "");
					pnDanhSach.add(gui_pdsd.DanhSachPhong());
				}
			}
			for (int i = 0; i <= 5; i++) {
				pnDanhSach.add(new GUI_PhongTrong().PhongNull());
			}

		} else if (o.equals(btnPre)) {
			btnNext.setEnabled(true);
			btnPre.setEnabled(false);
			lblTitle.setText("PHÒNG ĐÃ ĐẶT HÔM NAY");
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
	}

	public static void loadThongTinDatPhong(String gioDat, String sdt, String KH) {
		lblGioDat.setText(gioDat);
		txtSdt.setText(sdt);
		cboKhachHang.removeAllItems();
		cboKhachHang.addItem(KH);
	}

	public static String getMaKH() {
		String ma = cboKhachHang.getSelectedItem() + "";
		String m = ma.substring(0, ma.indexOf("-"));
		return m;
	}

	public static boolean checkNut() {
		if (lblTitle.getText() == "PHÒNG ĐÃ ĐẶT NGÀY MAI") {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("deprecation")
	public static void refresh() throws ParseException {
		cboLoaiPhong.setSelectedIndex(0);
		xoaPhongDatQuaThoiGian();
		at = new SimpleDateFormat("yyyy-MM-dd").parse(java.time.LocalDate.now() + "");
		txtNgayHat.setDate(at);
		List<ThongTinDat> ls1 = new ThongTinDat_dao()
				.getDanhSachPhongDaDatTheoNgay(java.time.LocalDate.now().getDayOfMonth() + "");
		ArrayList<Phong> list1 = (ArrayList<Phong>) new Phong_dao().getDanhSachPhong();
		for (ThongTinDat thongTinDat : ls1) {
			for (int i = 0; i < list1.size(); i++) {
				{
					if (thongTinDat.getMaPh().equalsIgnoreCase(list1.get(i).getMaPhong())
							&& thongTinDat.getThoiGianDat().getHours() - java.time.LocalTime.now().getHour() < 3
							&& thongTinDat.getThoiGianDat().getHours() - java.time.LocalTime.now().getHour() >= 0) {
						list1.remove(i);
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
			for (Phong pTest : list1) {
				if (pTest.isLoaiPhong() == false && pTest.isTrangThai() == false) {
					pnDanhSach.add(new GUI_PhongDanhSach(pTest, tttk, true).DanhSachPhong());
				}
			}
			for (int i = 0; i <= 5; i++) {
				pnDanhSach.add(new GUI_PhongTrong().PhongNull());
			}
		} else {
			Component[] components = pnDanhSach.getComponents();
			for (Component component : components) {
				pnDanhSach.remove(component);
			}
			pnDanhSach.revalidate();
			pnDanhSach.repaint();
			for (Phong pTest : list1) {
				if (pTest.isLoaiPhong() == true && pTest.isTrangThai() == false) {
					pnDanhSach.add(new GUI_PhongDanhSach(pTest, tttk, true).DanhSachPhong());
				}
			}
			for (int i = 0; i <= 5; i++) {
				pnDanhSach.add(new GUI_PhongTrong().PhongNull());
			}
		}

	}

	public static void setTextChoKhachHang(String sdt, String kh) {
		txtSdt.setText(sdt);
		cboKhachHang.removeAll();
		cboKhachHang.addItem(kh);
	}

	@SuppressWarnings("deprecation")
	public static void xoaPhongDatQuaThoiGian() {
		List<ThongTinDat> lsPhong = new ThongTinDat_dao()
				.getDanhSachPhongDaDatTheoNgay(java.time.LocalDate.now().getDayOfMonth() + "");
		for (ThongTinDat thongTinDat : lsPhong) {
			float gioHienTai = java.time.LocalDateTime.now().getHour()
					+ ((float) java.time.LocalDateTime.now().getMinute() / 60);
			float gioDat = thongTinDat.getThoiGianDat().getHours()
					+ ((float) thongTinDat.getThoiGianDat().getMinutes() / 60);
			if (gioHienTai - gioDat > 0) {
				new ThongTinDat_dao().deleteThongTinDatPhong(thongTinDat.getMaPh(), thongTinDat.getMaKH(),
						java.time.LocalDate.now().getDayOfMonth() + "");
			}
		}

	}
}
