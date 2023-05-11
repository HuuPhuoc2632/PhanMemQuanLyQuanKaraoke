//Huỳnh Hữu Phước
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.TaiKhoan;
import entity.ThongTinTaiKhoan;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import javax.swing.border.MatteBorder;

public class GUI_ManHinhChinh extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public static JPanel pnMain;
	public static JButton btnDatThuePhong;
	public static JButton btnQLNs;
	public static JButton btnTrangCN;
	public static int checkQLP = 0, checkQLNS = 0, checkTCN = 0, checkKho = 0, checkTk = 0, checkPC = 0, checkKH = 0,
			checkDTPhong = 0;
	public static JButton btnKho;
	public static JButton btnThongKe;
	public static JButton btnPhanCong;
	public static ThongTinTaiKhoan tttk;
	public TaiKhoan tk;
	public static JButton btnKhachHang;
	public static JLabel lblNameApp;
	public static JButton btnQLPhong;
	public static JButton btnContact;

	/**
	 * Create the frame.
	 */

	@SuppressWarnings("static-access")
	public GUI_ManHinhChinh(ThongTinTaiKhoan tttk) {
		this.tttk = tttk;

		Cursor cs = Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/cursor.png")).getImage(), new Point(0, 0),
				"Custom cursor");
		setCursor(cs);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnNorth = new JPanel();
		pnNorth.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pnNorth.setBackground(Color.CYAN);
		pnNorth.setForeground(Color.BLACK);
		pnNorth.setBounds(181, 0, 1085, 77);
		contentPane.add(pnNorth);
		pnNorth.setLayout(null);

		lblNameApp = new JLabel("QUẢN LÝ KARAOKE AQUA");
		lblNameApp.setBounds(0, 10, 1085, 67);
		lblNameApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameApp.setForeground(Color.BLUE);
		lblNameApp.setFont(new Font("Constantia", Font.BOLD, 40));
		pnNorth.add(lblNameApp);

		JPanel pnCenter = new JPanel();
		pnCenter.setBackground(Color.WHITE);
		pnCenter.setForeground(Color.BLACK);
		pnCenter.setBounds(181, 76, 1085, 607);
		contentPane.add(pnCenter);
		pnCenter.setLayout(null);

		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 1084, 607);
		pnCenter.add(pnMain);
		JPanel pnChucNang = new JPanel() {
			/**
			* 
			*/
			public static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics grphcs) {
				super.paintComponent(grphcs);
				Graphics2D g2d = (Graphics2D) grphcs;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				getBackground();
				GradientPaint gp = new GradientPaint(0, 0, Color.CYAN, 0, getHeight(), new Color(65, 105, 225));
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());

			}
		};
		pnChucNang.setBorder(new MatteBorder(0, 0, 0, 4, (Color) new Color(0, 0, 0)));
		pnChucNang.setBackground(Color.CYAN);
		pnChucNang.setBounds(0, 0, 183, 683);
		contentPane.add(pnChucNang);
		pnChucNang.setLayout(null);

		btnDatThuePhong = new JButton("ĐẶT-THUÊ PHÒNG",
				new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/booking.png")));
		btnDatThuePhong.setToolTipText("Phím tắt ALT + 1");
		btnDatThuePhong.setMnemonic('1');
		btnDatThuePhong.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnDatThuePhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDatThuePhong.setForeground(Color.BLUE);
				btnDatThuePhong.setFont(new Font("Constantia", Font.BOLD, 14));
				if (!checkTabSelected(checkDTPhong)) {
					btnDatThuePhong.setBackground(new Color(180, 205, 205));
				}
				btnDatThuePhong.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDatThuePhong.setForeground(Color.BLACK);
				btnDatThuePhong.setFont(new Font("Constantia", Font.BOLD, 13));
				btnDatThuePhong.setContentAreaFilled(checkTabSelected(checkDTPhong));
			}
		});
		btnDatThuePhong.setHorizontalAlignment(SwingConstants.LEFT);
		btnDatThuePhong.setFont(new Font("Constantia", Font.BOLD, 13));
		btnDatThuePhong.setBounds(0, 75, 180, 53);
		pnChucNang.add(btnDatThuePhong);

		btnQLNs = new JButton("NHÂN SỰ", new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/employee.png")));
		btnQLNs.setToolTipText("Phím tắt ALT + 5");
		btnQLNs.setMnemonic('5');
		btnQLNs.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnQLNs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnQLNs.setForeground(Color.BLUE);
				btnQLNs.setFont(new Font("Constantia", Font.BOLD, 15));
				if (!checkTabSelected(checkQLNS)) {
					btnQLNs.setBackground(new Color(180, 205, 205));
				}
				btnQLNs.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnQLNs.setForeground(Color.BLACK);
				btnQLNs.setFont(new Font("Constantia", Font.BOLD, 13));
				btnQLNs.setContentAreaFilled(checkTabSelected(checkQLNS));
			}
		});
		btnQLNs.setHorizontalAlignment(SwingConstants.LEFT);
		btnQLNs.setFont(new Font("Constantia", Font.BOLD, 13));

		btnQLNs.setBounds(0, 287, 180, 53);
		pnChucNang.add(btnQLNs);

		btnTrangCN = new JButton("TRANG CÁ NHÂN",
				new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/profile.png")));
		btnTrangCN.setToolTipText("Phím tắt ALT + 8");
		btnTrangCN.setMnemonic('8');
		btnTrangCN.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnTrangCN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTrangCN.setForeground(Color.BLUE);
				btnTrangCN.setFont(new Font("Constantia", Font.BOLD, 15));
				if (!checkTabSelected(checkTCN)) {
					btnTrangCN.setBackground(new Color(180, 205, 205));
				}
				btnTrangCN.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnTrangCN.setForeground(Color.BLACK);
				btnTrangCN.setFont(new Font("Constantia", Font.BOLD, 13));
				btnTrangCN.setContentAreaFilled(checkTabSelected(checkTCN));
			}
		});
		btnTrangCN.setHorizontalAlignment(SwingConstants.LEFT);
		btnTrangCN.setFont(new Font("Constantia", Font.BOLD, 13));
		btnTrangCN.setBounds(0, 446, 180, 53);
		pnChucNang.add(btnTrangCN);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = sdf.format(date);

		pnMain.setLayout(new GridLayout(1, 0, 0, 0));
		btnKho = new JButton("QUẢN LÝ KHO", new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/warehouse.png")));
		btnKho.setToolTipText("Phím tắt ALT + 7");
		btnKho.setMnemonic('7');
		btnKho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnKho.setForeground(Color.BLUE);
				btnKho.setFont(new Font("Constantia", Font.BOLD, 15));
				if (!checkTabSelected(checkKho)) {
					btnKho.setBackground(new Color(180, 205, 205));
				}
				btnKho.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnKho.setForeground(Color.BLACK);
				btnKho.setFont(new Font("Constantia", Font.BOLD, 13));
				btnKho.setContentAreaFilled(checkTabSelected(checkKho));
			}
		});
		btnKho.setHorizontalAlignment(SwingConstants.LEFT);
		btnKho.setFont(new Font("Constantia", Font.BOLD, 13));
		btnKho.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnKho.setBounds(0, 393, 180, 53);
		pnChucNang.add(btnKho);

		btnThongKe = new JButton("THỐNG KÊ", new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/analytics.png")));
		btnThongKe.setToolTipText("Phím tắt ALT + 3");
		btnThongKe.setMnemonic('3');
		btnThongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnThongKe.setForeground(Color.BLUE);
				btnThongKe.setFont(new Font("Constantia", Font.BOLD, 15));
				if (!checkTabSelected(checkTk)) {
					btnThongKe.setBackground(new Color(180, 205, 205));
				}
				btnThongKe.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnThongKe.setForeground(Color.BLACK);
				btnThongKe.setFont(new Font("Constantia", Font.BOLD, 13));
				btnThongKe.setContentAreaFilled(checkTabSelected(checkTk));
			}
		});
		btnThongKe.setHorizontalAlignment(SwingConstants.LEFT);
		btnThongKe.setFont(new Font("Constantia", Font.BOLD, 13));
		btnThongKe.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnThongKe.setBounds(0, 181, 180, 53);
		pnChucNang.add(btnThongKe);

		btnPhanCong = new JButton("PHÂN CÔNG", new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/assign.png")));
		btnPhanCong.setToolTipText("Phím tắt ALT + 6");
		btnPhanCong.setMnemonic('6');
		btnPhanCong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPhanCong.setForeground(Color.BLUE);
				btnPhanCong.setFont(new Font("Constantia", Font.BOLD, 15));
				if (!checkTabSelected(checkPC)) {
					btnPhanCong.setBackground(new Color(180, 205, 205));
				}
				btnPhanCong.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPhanCong.setForeground(Color.BLACK);
				btnPhanCong.setFont(new Font("Constantia", Font.BOLD, 13));
				btnPhanCong.setContentAreaFilled(checkTabSelected(checkPC));
			}
		});
		btnPhanCong.setHorizontalAlignment(SwingConstants.LEFT);
		btnPhanCong.setFont(new Font("Constantia", Font.BOLD, 13));
		btnPhanCong.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnPhanCong.setBounds(0, 340, 180, 53);
		pnChucNang.add(btnPhanCong);

		btnKhachHang = new JButton("KHÁCH HÀNG", new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/target.png")));
		btnKhachHang.setMnemonic('4');
		btnKhachHang.setToolTipText("Phím tắt ALT + 4");
		btnKhachHang.setBounds(0, 234, 180, 53);
		btnKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		btnKhachHang.setFont(new Font("Constantia", Font.BOLD, 13));
		btnKhachHang.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		pnChucNang.add(btnKhachHang);
		btnKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnKhachHang.setForeground(Color.BLUE);
				btnKhachHang.setFont(new Font("Constantia", Font.BOLD, 15));
				if (!checkTabSelected(checkKH)) {
					btnKhachHang.setBackground(new Color(180, 205, 205));
				}
				btnKhachHang.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnKhachHang.setForeground(Color.BLACK);
				btnKhachHang.setFont(new Font("Constantia", Font.BOLD, 13));
				btnKhachHang.setContentAreaFilled(checkTabSelected(checkKH));
			}
		});

		JLabel lblNgay = new JLabel("22/12/2022");
		lblNgay.setBounds(0, 0, 181, 37);
		pnChucNang.add(lblNgay);
		lblNgay.setForeground(Color.BLUE);
		lblNgay.setFont(new Font("Britannic Bold", Font.BOLD, 22));
		lblNgay.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgay.setText(dateString);

		JLabel lblClock = new JLabel("16:16:16 AM");
		lblClock.setBounds(0, 35, 181, 40);
		pnChucNang.add(lblClock);
		lblClock.setForeground(Color.RED);
		lblClock.setFont(new Font("Britannic Bold", Font.BOLD, 27));
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		GUI_Clock cl = new GUI_Clock(lblClock, lblNgay);

		btnQLPhong = new JButton("QUẢN LÝ PHÒNG", new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/room.png")));
		btnQLPhong.setMnemonic('2');
		btnQLPhong.setToolTipText("Phím tắt ALT + 2");
		btnQLPhong.setHorizontalAlignment(SwingConstants.LEFT);
		btnQLPhong.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnQLPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnQLPhong.setForeground(Color.BLUE);
				btnQLPhong.setFont(new Font("Constantia", Font.BOLD, 15));
				if (!checkTabSelected(checkQLP)) {
					btnQLPhong.setBackground(new Color(180, 205, 205));
				}
				btnQLPhong.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnQLPhong.setForeground(Color.BLACK);
				btnQLPhong.setFont(new Font("Constantia", Font.BOLD, 13));
				btnQLPhong.setContentAreaFilled(checkTabSelected(checkQLP));
			}
		});
		btnQLPhong.setFont(new Font("Constantia", Font.BOLD, 13));
		btnQLPhong.setBounds(0, 128, 180, 53);
		pnChucNang.add(btnQLPhong);
		cl.start();

		btnContact = new JButton("Contact",
				new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/contact-book.png")));
		btnContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnContact.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 14));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnContact.setFont(new Font("Constantia", Font.BOLD, 13));
			}
		});
		btnContact.setHorizontalAlignment(SwingConstants.LEFT);
		btnContact.setFont(new Font("Constantia", Font.BOLD, 13));
		btnContact.setBounds(0, 658, 90, 25);
		btnContact.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		pnChucNang.add(btnContact);

		btnContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://zalo.me/0348307336"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnDatThuePhong.addActionListener(this);
		btnQLNs.addActionListener(this);
		btnKho.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnPhanCong.addActionListener(this);
		btnTrangCN.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnQLPhong.addActionListener(this);
		pnMain.add(new GUI_ChaoMung().tabChaoMung());
		// set button no background
		btnDatThuePhong.setFocusPainted(false);
		btnDatThuePhong.setContentAreaFilled(false);
		btnQLNs.setFocusPainted(false);
		btnQLNs.setContentAreaFilled(false);
		btnTrangCN.setFocusPainted(false);
		btnTrangCN.setContentAreaFilled(false);
		btnKho.setFocusPainted(false);
		btnKho.setContentAreaFilled(false);
		btnThongKe.setFocusPainted(false);
		btnThongKe.setContentAreaFilled(false);
		btnPhanCong.setFocusPainted(false);
		btnPhanCong.setContentAreaFilled(false);
		btnKhachHang.setFocusPainted(false);
		btnKhachHang.setContentAreaFilled(false);
		btnQLPhong.setFocusPainted(false);
		btnQLPhong.setContentAreaFilled(false);
		btnContact.setFocusPainted(false);
		btnContact.setContentAreaFilled(false);
//		set cursor for button
		Cursor csclick = Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/click.png")).getImage(), new Point(0, 0),
				"Custom cursor");
		btnDatThuePhong.setCursor(csclick);
		btnQLNs.setCursor(csclick);
		btnTrangCN.setCursor(csclick);
		btnPhanCong.setCursor(csclick);
		btnThongKe.setCursor(csclick);
		btnKho.setCursor(csclick);
		btnKhachHang.setCursor(csclick);
		btnQLPhong.setCursor(csclick);

		checkPhanQuyen();

	}

	public void checkPhanQuyen() {
		String regex = ".*Q.*";
		if (kiemTraNhap(tttk.getTenTaiKhoan(), regex)) {
			btnPhanCong.setEnabled(true);
			btnQLNs.setEnabled(true);
		} else {
			btnPhanCong.setEnabled(false);
			btnQLNs.setEnabled(false);
		}
	}

	public boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

	// set border rỗng cho all button
	public static void setBorderEmpty() {
		btnContact.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnDatThuePhong.setBorder(new MatteBorder(2, 0, 0, 0, (Color) Color.BLACK));
		btnQLNs.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnTrangCN.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnPhanCong.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnThongKe.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnKho.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnKhachHang.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnQLPhong.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDatThuePhong)) {
			resetComponentMain();
			try {
				pnMain.add(new GUI_DatThuePhong(tttk).datThuePhong());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setBorderEmpty();
			resetTab();
			checkDTPhong = 1;
			btnDatThuePhong.setBackground(new Color(176, 196, 222));
			resetColorTab();
			btnDatThuePhong.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
			btnDatThuePhong.setHorizontalAlignment(SwingConstants.CENTER);
			lblNameApp.setText("ĐẶT - THUÊ PHÒNG HÁT");
		} else if (o.equals(btnQLNs)) {
			resetComponentMain();
			setBorderEmpty();
			resetTab();
			checkQLNS = 1;
			btnQLNs.setBackground(new Color(176, 196, 222));
			resetColorTab();
			btnQLNs.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
			try {
				pnMain.add(new GUI_NhanSu(tttk).tabNhanSu());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			btnQLNs.setHorizontalAlignment(SwingConstants.CENTER);
			lblNameApp.setText("QUẢN LÝ NHÂN SỰ");
		} else if (o.equals(btnKho)) {
			resetComponentMain();
			setBorderEmpty();
			resetTab();
			checkKho = 1;
			btnKho.setBackground(new Color(176, 196, 222));
			resetColorTab();
			btnKho.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
			pnMain.add(new GUI_QuanLyKho().tabQuanLyKho());
			btnKho.setHorizontalAlignment(SwingConstants.CENTER);
			lblNameApp.setText("QUẢN LÝ KHO");
		} else if (o.equals(btnThongKe)) {
			resetComponentMain();
			setBorderEmpty();
			resetTab();
			checkTk = 1;
			btnThongKe.setBackground(new Color(176, 196, 222));
			resetColorTab();
			btnThongKe.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
			try {
				pnMain.add(new GUI_ThongKe(tttk).tabThongKe());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			btnThongKe.setHorizontalAlignment(SwingConstants.CENTER);
			lblNameApp.setText("QUẢN LÝ THỐNG KÊ");
		} else if (o.equals(btnPhanCong)) {
			resetComponentMain();
			setBorderEmpty();
			resetTab();
			checkPC = 1;
			btnPhanCong.setBackground(new Color(176, 196, 222));
			resetColorTab();
			btnPhanCong.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
			pnMain.add(new GUI_PhanCong().tabPhanCong());
			btnPhanCong.setHorizontalAlignment(SwingConstants.CENTER);
			lblNameApp.setText("PHÂN CÔNG NHÂN SỰ");
		} else if (o.equals(btnTrangCN)) {
			resetComponentMain();
			pnMain.add(new GUI_CaNhan(tttk, tk).tabCaNhan());
			setBorderEmpty();
			resetTab();
			checkTCN = 1;
			btnTrangCN.setBackground(new Color(176, 196, 222));
			resetColorTab();
			btnTrangCN.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
			btnTrangCN.setHorizontalAlignment(SwingConstants.CENTER);
			lblNameApp.setText("THÔNG TIN CÁ NHÂN");
		} else if (o.equals(btnKhachHang)) {
			resetComponentMain();
			pnMain.add(new GUI_KhachHang("main").tabKhachHang());
			setBorderEmpty();
			resetTab();
			checkKH = 1;
			btnKhachHang.setBackground(new Color(176, 196, 222));
			resetColorTab();
			btnKhachHang.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
			btnKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
			lblNameApp.setText("QUẢN LÝ KHÁCH HÀNG");
		} else if (o.equals(btnQLPhong)) {
			resetComponentMain();
			pnMain.add(new GUI_QuanLyPhong().tabQuanLyPhong());
			setBorderEmpty();
			resetTab();
			checkQLP = 1;
			btnQLPhong.setBackground(new Color(176, 196, 222));
			resetColorTab();
			btnQLPhong.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
			btnQLPhong.setHorizontalAlignment(SwingConstants.CENTER);
			lblNameApp.setText("QUẢN LÝ PHÒNG");
		}
	}

	// reset tab được chọn
	public static void resetTab() {
		checkQLP = checkQLNS = checkTCN = checkKho = checkPC = checkTk = checkKH = checkDTPhong = 0;
		btnDatThuePhong.setHorizontalAlignment(SwingConstants.LEFT);
		btnQLNs.setHorizontalAlignment(SwingConstants.LEFT);
		btnKho.setHorizontalAlignment(SwingConstants.LEFT);
		btnThongKe.setHorizontalAlignment(SwingConstants.LEFT);
		btnPhanCong.setHorizontalAlignment(SwingConstants.LEFT);
		btnTrangCN.setHorizontalAlignment(SwingConstants.LEFT);
		btnKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		btnQLPhong.setHorizontalAlignment(SwingConstants.LEFT);
	}

	// reset màu tab chọn
	public static void resetColorTab() {
		if (checkQLNS == 1) {
			btnQLPhong.setContentAreaFilled(false);
			btnTrangCN.setContentAreaFilled(false);
			btnDatThuePhong.setContentAreaFilled(false);
			btnQLNs.setContentAreaFilled(true);
			btnKho.setContentAreaFilled(false);
			btnThongKe.setContentAreaFilled(false);
			btnPhanCong.setContentAreaFilled(false);
			btnKhachHang.setContentAreaFilled(false);
		} else if (checkQLP == 1) {
			btnQLPhong.setContentAreaFilled(true);
			btnTrangCN.setContentAreaFilled(false);
			btnDatThuePhong.setContentAreaFilled(false);
			btnQLNs.setContentAreaFilled(false);
			btnKho.setContentAreaFilled(false);
			btnThongKe.setContentAreaFilled(false);
			btnPhanCong.setContentAreaFilled(false);
			btnKhachHang.setContentAreaFilled(false);
		} else if (checkKho == 1) {
			btnQLPhong.setContentAreaFilled(false);
			btnTrangCN.setContentAreaFilled(false);
			btnDatThuePhong.setContentAreaFilled(false);
			btnQLNs.setContentAreaFilled(false);
			btnKho.setContentAreaFilled(true);
			btnThongKe.setContentAreaFilled(false);
			btnPhanCong.setContentAreaFilled(false);
			btnKhachHang.setContentAreaFilled(false);
		} else if (checkPC == 1) {
			btnQLPhong.setContentAreaFilled(false);
			btnTrangCN.setContentAreaFilled(false);
			btnDatThuePhong.setContentAreaFilled(false);
			btnQLNs.setContentAreaFilled(false);
			btnKho.setContentAreaFilled(false);
			btnThongKe.setContentAreaFilled(false);
			btnPhanCong.setContentAreaFilled(true);
			btnKhachHang.setContentAreaFilled(false);
		} else if (checkTk == 1) {
			btnQLPhong.setContentAreaFilled(false);
			btnTrangCN.setContentAreaFilled(false);
			btnDatThuePhong.setContentAreaFilled(false);
			btnQLNs.setContentAreaFilled(false);
			btnKho.setContentAreaFilled(false);
			btnThongKe.setContentAreaFilled(true);
			btnPhanCong.setContentAreaFilled(false);
			btnKhachHang.setContentAreaFilled(false);
		} else if (checkKH == 1) {
			btnQLPhong.setContentAreaFilled(false);
			btnKhachHang.setContentAreaFilled(true);
			btnTrangCN.setContentAreaFilled(false);
			btnDatThuePhong.setContentAreaFilled(false);
			btnQLNs.setContentAreaFilled(false);
			btnKho.setContentAreaFilled(false);
			btnThongKe.setContentAreaFilled(false);
			btnPhanCong.setContentAreaFilled(false);
		} else if (checkDTPhong == 1) {
			btnQLPhong.setContentAreaFilled(false);
			btnKhachHang.setContentAreaFilled(false);
			btnTrangCN.setContentAreaFilled(false);
			btnDatThuePhong.setContentAreaFilled(true);
			btnQLNs.setContentAreaFilled(false);
			btnKho.setContentAreaFilled(false);
			btnThongKe.setContentAreaFilled(false);
			btnPhanCong.setContentAreaFilled(false);
		} else if (checkTCN == 1) {
			btnQLPhong.setContentAreaFilled(false);
			btnTrangCN.setContentAreaFilled(true);
			btnDatThuePhong.setContentAreaFilled(false);
			btnQLNs.setContentAreaFilled(false);
			btnKho.setContentAreaFilled(false);
			btnThongKe.setContentAreaFilled(false);
			btnPhanCong.setContentAreaFilled(false);
			btnKhachHang.setContentAreaFilled(false);
		}
	}

//check tab có đang được chọn
	public Boolean checkTabSelected(int n) {
		if (n == 1) {
			return true;
		}
		return false;
	}

	public static void resetComponentMain() {
		Component[] components = pnMain.getComponents();

		for (Component component : components) {
			pnMain.remove(component);
		}
		pnMain.revalidate();
		pnMain.repaint();
	}

	public static void addComponent(Component c) {
		pnMain.add(c);
	}

	public static void themMoiKhachHang() {
		setBorderEmpty();
		resetTab();
		checkKH = 1;
		btnKhachHang.setBackground(new Color(176, 196, 222));
		resetColorTab();
		btnKhachHang.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
		btnKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameApp.setText("QUẢN LÝ KHÁCH HÀNG");
	}

	public static void loadLaiManHinhDatThuePhong(int n) {
		resetComponentMain();
		try {
			pnMain.add(new GUI_DatThuePhong(tttk).datThuePhong());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setBorderEmpty();
		resetTab();
		checkDTPhong = 1;
		btnDatThuePhong.setBackground(new Color(176, 196, 222));
		resetColorTab();
		btnDatThuePhong.setBorder(new MatteBorder(3, 0, 3, 0, (Color) Color.BLUE));
		btnDatThuePhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameApp.setText("ĐẶT - THUÊ PHÒNG HÁT");
		GUI_DatThuePhong.chonTab(n);
	}
}
