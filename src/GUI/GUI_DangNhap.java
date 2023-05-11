//Huỳnh Hữu Phước
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import dao.NhanSu_dao;
import dao.TaiKhoan_dao;
import entity.TaiKhoan;
import entity.ThongTinTaiKhoan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI_DangNhap extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTK;
	private JButton btnSubmit;
	private JPasswordField pwMK;
	private JCheckBox jcbShow;
	private JButton btnForget;
	private TaiKhoan_dao TaiKhoan_dao = new TaiKhoan_dao();
	public ThongTinTaiKhoan tttk;
	public TaiKhoan tk;
	public static GUI_ManHinhChinh frame;

	/**
	 * Create the frame.
	 */
	public GUI_DangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(520, 300);
		setLocationRelativeTo(null);
		Cursor cs = Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/cursor.png")).getImage(), new Point(0, 0),
				"Custom cursor");
		setCursor(cs);
		contentPane = new JPanel() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics grphcs) {
				super.paintComponent(grphcs);
				Graphics2D g2d = (Graphics2D) grphcs;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				getBackground();
				GradientPaint gp = new GradientPaint(0, 0, Color.CYAN, 0, getHeight(), new Color(106, 90, 205));
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());

			}
		};
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);

		JPanel pnIcon = new JPanel();
		pnIcon.setBackground(Color.WHITE);
		pnIcon.setBounds(0, 0, 229, 261);
		ImageIcon img = new ImageIcon(GUI_DangNhap.class.getResource("/icon/Logo.png"));
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(229, 261, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg);
		contentPane.add(pnIcon);
		pnIcon.setLayout(null);

		JLabel lblAnh = new JLabel(img);
		lblAnh.setToolTipText("KARAOKE AQUA");
		lblAnh.setBackground(Color.WHITE);
		lblAnh.setBounds(0, 0, 229, 261);
		pnIcon.add(lblAnh);

		JPanel pnDangNhap = new JPanel();
		pnDangNhap.setBackground(Color.WHITE);
		pnDangNhap.setBounds(228, 0, 276, 261);
		contentPane.add(pnDangNhap);
		pnDangNhap.setLayout(null);

		JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Constantia", Font.BOLD, 20));
		lblTitle.setBounds(39, 24, 202, 40);
		pnDangNhap.add(lblTitle);

		JLabel lblTK = new JLabel("Tài khoản:");
		lblTK.setForeground(Color.BLACK);
		lblTK.setToolTipText("Tài khoản là mã nhân viên của bạn");
		lblTK.setFont(new Font("Constantia", Font.BOLD, 13));
		lblTK.setBounds(49, 74, 69, 25);
		pnDangNhap.add(lblTK);

		JLabel lblMK = new JLabel("Mật khẩu:");
		lblMK.setToolTipText("Mật khẩu đăng nhập vào hệ thống");
		lblMK.setFont(new Font("Constantia", Font.BOLD, 13));
		lblMK.setBounds(49, 110, 69, 25);
		pnDangNhap.add(lblMK);

		txtTK = new JTextField();
		txtTK.setToolTipText("Tài khoản là mã nhân viên của bạn");
		txtTK.setBounds(134, 75, 107, 20);
		txtTK.setText("QL0001");
		pnDangNhap.add(txtTK);
		txtTK.setColumns(10);

		btnSubmit = new JButton("Xác nhận", new ImageIcon(GUI_DangNhap.class.getResource("/icon/check-mark.png")));
		btnSubmit.setToolTipText("Phím tắt ALT + Enter");
		btnSubmit.setMnemonic(KeyEvent.VK_ENTER);
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSubmit.setFont(new Font("Constantia", Font.BOLD, 15));
				btnSubmit.setForeground(Color.GREEN);
				btnSubmit.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.GREEN));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSubmit.setFont(new Font("Constantia", Font.BOLD, 14));
				btnSubmit.setForeground(Color.BLACK);
				btnSubmit.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
			}
		});
		btnSubmit.setBackground(Color.GREEN);
		btnSubmit.setFont(new Font("Constantia", Font.BOLD, 14));
		btnSubmit.setBounds(28, 200, 107, 25);
		pnDangNhap.add(btnSubmit);

		pwMK = new JPasswordField();
		pwMK.setToolTipText("Mật khẩu đăng nhập vào hệ thống");
		pwMK.setBounds(134, 111, 107, 20);
		pwMK.setText("1111");
		pnDangNhap.add(pwMK);

		btnForget = new JButton("Quên MK?", new ImageIcon(GUI_DangNhap.class.getResource("/icon/forgot-password.png")));
		btnForget.setBackground(Color.LIGHT_GRAY);
		btnForget.setFont(new Font("Constantia", Font.BOLD, 14));
		btnForget.setBounds(145, 200, 107, 25);
		pnDangNhap.add(btnForget);
		btnForget.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnForget.setFont(new Font("Constantia", Font.BOLD, 15));
				btnForget.setForeground(Color.RED);
				btnForget.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.RED));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnForget.setFont(new Font("Constantia", Font.BOLD, 14));
				btnForget.setForeground(Color.BLACK);
				btnForget.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
			}
		});

		jcbShow = new JCheckBox("Hiện mật khẩu");
		jcbShow.setToolTipText("Tùy chọn ẩn hoặc hiện mật khẩu");
		jcbShow.setFont(new Font("Constantia", Font.PLAIN, 10));
		jcbShow.setBackground(new Color(224, 255, 255));
		jcbShow.setBounds(134, 137, 107, 21);
		jcbShow.setFocusable(false);
		pnDangNhap.add(jcbShow);

		ImageIcon imgUser = new ImageIcon(GUI_DangNhap.class.getResource("/icon/userLog.png"));
		Image imageUser = imgUser.getImage();
		Image newimgUser = imageUser.getScaledInstance(13, 13, java.awt.Image.SCALE_SMOOTH);
		imgUser = new ImageIcon(newimgUser);
		JLabel lblImgUser = new JLabel(imgUser);
		lblImgUser.setBounds(13, 77, 28, 13);
		pnDangNhap.add(lblImgUser);

		ImageIcon imgPass = new ImageIcon(GUI_DangNhap.class.getResource("/icon/key.png"));
		Image imagePass = imgPass.getImage();
		Image newImgPass = imagePass.getScaledInstance(13, 13, java.awt.Image.SCALE_SMOOTH);
		imgPass = new ImageIcon(newImgPass);
		JLabel lblImgPass = new JLabel(imgPass);
		lblImgPass.setBounds(13, 113, 28, 13);
		pnDangNhap.add(lblImgPass);

		jcbShow.addActionListener(this);
		btnForget.addActionListener(this);
		btnSubmit.addActionListener(this);
		pnDangNhap.setOpaque(false);
		pnIcon.setOpaque(false);
		jcbShow.setOpaque(false);
		txtTK.setOpaque(false);
		txtTK.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		pwMK.setOpaque(false);
		pwMK.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		btnSubmit.setContentAreaFilled(false);
		btnForget.setContentAreaFilled(false);
		btnSubmit.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
		btnForget.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		btnForget.setFocusPainted(false);
		btnSubmit.setFocusPainted(false);
	}

	@SuppressWarnings("deprecation")
	public boolean kiemTraDangNhap() {
		for (TaiKhoan i : TaiKhoan_dao.getDsTK()) {
			if (!txtTK.getText().isEmpty() && txtTK.getText().equals(i.getTenTK()) && !pwMK.getText().isEmpty()
					&& chuyenChuoi(pwMK.getPassword()).trim().equals(i.getMatKhau().trim())) {
				String regexMaNs = "^NV\\d{4}$";
				if (kiemTraNhap(txtTK.getText(), regexMaNs)) {
					tttk = new ThongTinTaiKhoan();
					tttk = new NhanSu_dao().getThongTinTabCaNhan(i);
				} else {
					tttk = new ThongTinTaiKhoan();
					tttk = new TaiKhoan_dao().getThongTinTabCaNhan(i);
				}
				frame = new GUI_ManHinhChinh(tttk);
				return true;
			}
		}
		return false;
	}

	public boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

	private String chuyenChuoi(char[] a) {
		// TODO Auto-generated method stub
		String s = "";
		for (int i = 0; i < a.length; i++) {
			s += a[i];
		}
		return s;
	}

	public static void logOut() {
		frame.setVisible(false);

	}

	public static void CloseAll() {
		frame.setVisible(false);
	}

//	Xử lý sự kiện
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnSubmit)) {
			if (kiemTraDangNhap()) {
				setVisible(false);
				frame.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Thông Tin Đăng Nhập Không Chính Xác!!!", "Cảnh Báo Đăng Nhập!!!",
						JOptionPane.WARNING_MESSAGE);
			}

		} else if (o.equals(btnForget)) {
			JOptionPane.showMessageDialog(null, "Xin vui lòng liên hệ với quản lý để được cấp lại mật khẩu!",
					"Cảnh Báo Đăng Nhập!!!", JOptionPane.WARNING_MESSAGE);
		}
		if (e.getSource().equals(jcbShow)) {
			if (jcbShow.isSelected()) {
				pwMK.setEchoChar((char) 0);
			} else {
				pwMK.setEchoChar('*');
			}
		}

	}
}
