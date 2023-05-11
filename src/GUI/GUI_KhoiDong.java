//Huỳnh Hữu Phước
package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JProgressBar;

public class GUI_KhoiDong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar pgbLoading;
	private int i = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_KhoiDong frame = new GUI_KhoiDong();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_KhoiDong() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(520, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		Cursor cs = Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(GUI_ManHinhChinh.class.getResource("/icon/cursor.png")).getImage(), new Point(0, 0),
				"Custom cursor");
		setCursor(cs);

		JPanel pnHinhAnh = new JPanel() {
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
		pnHinhAnh.setBounds(0, 0, 506, 241);
		contentPane.add(pnHinhAnh);
		pnHinhAnh.setLayout(null);

		ImageIcon img = new ImageIcon(GUI_KhoiDong.class.getResource("/icon/Logo.png"));
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(215, 241, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg);
		JLabel lblLogo = new JLabel(img);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(0, 0, 215, 241);
		pnHinhAnh.add(lblLogo);

		JLabel lblKaraoke = new JLabel("Karaoke ");
		lblKaraoke.setForeground(Color.BLUE);
		lblKaraoke.setHorizontalAlignment(SwingConstants.LEFT);
		lblKaraoke.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 55));
		lblKaraoke.setBounds(215, 50, 291, 74);
		pnHinhAnh.add(lblKaraoke);

		JLabel lblNewLabel = new JLabel("AQUA");
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 70));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(244, 125, 262, 86);
		pnHinhAnh.add(lblNewLabel);

		JPanel pnLoading = new JPanel();
		pnLoading.setBounds(0, 239, 506, 24);
		contentPane.add(pnLoading);
		pnLoading.setLayout(null);

		pgbLoading = new JProgressBar();
		pgbLoading.setStringPainted(true);
		pgbLoading.setString("Đang khởi động");
		pgbLoading.setForeground(Color.BLUE);
		pgbLoading.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		pgbLoading.setBounds(0, 0, 506, 24);
		pnLoading.add(pgbLoading);
		setLocationRelativeTo(null);
		loading ld = new loading();
		ld.start();

	}

	private class loading extends Thread {
		@Override
		public void run() {
			while (i <= 100) {
				i = pgbLoading.getValue() + 1;
				pgbLoading.setValue(i);
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (pgbLoading.getValue() < 33) {
					pgbLoading.setString("Đang khởi động...");
				} else if (pgbLoading.getValue() >= 33 && pgbLoading.getValue() <= 66) {
					pgbLoading.setString("Đang tải dữ liệu hệ thống...");
				} else if (pgbLoading.getValue() > 66 && pgbLoading.getValue() <= 90) {
					pgbLoading.setString("Đang hoàn tất...");
				} else {
					pgbLoading.setString("Sẵn sàng");
				}
			}
			if (pgbLoading.getValue() == 100) {
				setVisible(false);
				new GUI_DangNhap().setVisible(true);
			}
		}

	}
}
