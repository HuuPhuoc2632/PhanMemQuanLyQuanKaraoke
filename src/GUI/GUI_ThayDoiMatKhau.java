//Huỳnh Hữu Phước
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

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
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

public class GUI_ThayDoiMatKhau extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtOldPass;
	private JPasswordField txtNewPass;
	private JPasswordField txtConfirm;
	private JButton btnXacNhan;
	private JButton btnHuy;
	private JCheckBox chcMk;
	public ThongTinTaiKhoan tttk;
	public TaiKhoan tk;
	private JLabel lblLogo;

	/**
	 * Create the frame.
	 */
	public GUI_ThayDoiMatKhau(ThongTinTaiKhoan taiKhoan, TaiKhoan tk) {
		this.tk = tk;
		this.tttk = taiKhoan;
		setBounds(100, 100, 693, 383);
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
				GradientPaint gp = new GradientPaint(0, 0, Color.CYAN, 0, getHeight(), new Color(65, 105, 225));
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());

			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(SystemColor.info);
		pnNorth.setBounds(295, 0, 384, 59);
		contentPane.add(pnNorth);
		pnNorth.setLayout(null);

		JLabel lblTitle = new JLabel("ĐỔI MẬT KHẨU");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Constantia", Font.BOLD, 25));
		lblTitle.setBounds(0, 0, 374, 48);
		pnNorth.add(lblTitle);

		JPanel pnMain = new JPanel();
		pnMain.setBackground(SystemColor.info);
		pnMain.setBounds(295, 55, 384, 291);
		contentPane.add(pnMain);
		pnMain.setLayout(null);

		JLabel lblOldPass = new JLabel("Mật khẩu cũ:");
		lblOldPass.setFont(new Font("Constantia", Font.BOLD, 17));
		lblOldPass.setBounds(10, 20, 104, 21);
		pnMain.add(lblOldPass);

		txtOldPass = new JPasswordField();
		txtOldPass.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtOldPass.setBounds(189, 20, 153, 19);
		pnMain.add(txtOldPass);
		txtOldPass.setColumns(10);

		JLabel lblNewPass = new JLabel("Mật khẩu mới:");
		lblNewPass.setFont(new Font("Constantia", Font.BOLD, 17));
		lblNewPass.setBounds(10, 77, 129, 21);
		pnMain.add(lblNewPass);

		txtNewPass = new JPasswordField();
		txtNewPass.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtNewPass.setBounds(189, 77, 153, 19);
		pnMain.add(txtNewPass);
		txtNewPass.setColumns(10);

		JLabel lblConfirm = new JLabel("Xác nhận mật khẩu:");
		lblConfirm.setFont(new Font("Constantia", Font.BOLD, 17));
		lblConfirm.setBounds(10, 131, 169, 21);
		pnMain.add(lblConfirm);

		txtConfirm = new JPasswordField();
		txtConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtConfirm.setBounds(189, 131, 154, 19);
		pnMain.add(txtConfirm);
		txtConfirm.setColumns(10);

		chcMk = new JCheckBox("Hiện mật khẩu:");
		chcMk.setBackground(SystemColor.info);
		chcMk.setFont(new Font("Arial", Font.BOLD, 17));
		chcMk.setBounds(189, 171, 153, 29);
		pnMain.add(chcMk);

		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setFont(new Font("Constantia", Font.BOLD, 17));
		btnXacNhan.setBounds(69, 217, 110, 40);
		pnMain.add(btnXacNhan);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Constantia", Font.BOLD, 17));
		btnHuy.setBounds(236, 217, 110, 40);
		pnMain.add(btnHuy);

		ImageIcon img = new ImageIcon(GUI_DangNhap.class.getResource("/icon/Logo.png"));
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(229, 261, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg);

		btnXacNhan.addActionListener(this);
		btnHuy.addActionListener(this);
		chcMk.addActionListener(this);
		pnNorth.setOpaque(false);
		pnMain.setOpaque(false);
		chcMk.setOpaque(false);
		btnXacNhan.setContentAreaFilled(false);
		btnXacNhan.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
		btnXacNhan.setFocusPainted(false);
		btnHuy.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		btnHuy.setContentAreaFilled(false);
		btnHuy.setFocusPainted(false);

		lblLogo = new JLabel(img);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(0, 0, 296, 346);
		contentPane.add(lblLogo);

	}

	private String chuyenChuoi(char[] a) {
		// TODO Auto-generated method stub
		String s = "";
		for (int i = 0; i < a.length; i++) {
			s += a[i];
		}
		return s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			this.setVisible(false);
		} else if (o.equals(chcMk)) {
			if (chcMk.isSelected()) {
				txtOldPass.setEchoChar((char) 0);
				txtNewPass.setEchoChar((char) 0);
				txtConfirm.setEchoChar((char) 0);
			} else {
				txtOldPass.setEchoChar('*');
				txtNewPass.setEchoChar('*');
				txtConfirm.setEchoChar('*');

			}
		} else if (o.equals(btnXacNhan)) {
			if (!chuyenChuoi(txtOldPass.getPassword()).trim().equals(tk.getMatKhau())) {
				JOptionPane.showMessageDialog(this, "Mật khẩu cũ không chính xác!");
			} else if (chuyenChuoi(txtOldPass.getPassword()).trim().isEmpty()
					|| chuyenChuoi(txtNewPass.getPassword()).trim().isEmpty()
					|| chuyenChuoi(txtConfirm.getPassword()).trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui Lòng điền đầy đủ thông tin!");
			} else if (!chuyenChuoi(txtNewPass.getPassword()).trim()
					.equals(chuyenChuoi(txtConfirm.getPassword()).trim())) {
				JOptionPane.showMessageDialog(this, "Mật khẩu mới và xác nhận không trùng khớp");
			} else if (chuyenChuoi(txtOldPass.getPassword()).trim().equals(tk.getMatKhau())) {
				TaiKhoan_dao updateTK = new TaiKhoan_dao();
				String newPass = chuyenChuoi(txtNewPass.getPassword()).trim();
				if (updateTK.suaTaiKhoan(tk, newPass)) {
					JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công");
					txtOldPass.setText("");
					txtNewPass.setText("");
					txtConfirm.setText("");
					this.setVisible(false);
					;
					GUI_DangNhap.CloseAll();
					new GUI_DangNhap().setVisible(true);

				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu không thành công");
				}
			}
		}
	}
}
