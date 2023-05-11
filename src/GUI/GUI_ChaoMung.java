//Huỳnh Hữu Phước
package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;

public class GUI_ChaoMung extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ChaoMung frame = new GUI_ChaoMung();
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
	public GUI_ChaoMung() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 608);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Chào mừng bạn đến với hệ thống quản lý");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 33, 1066, 98);
		contentPane.add(lblTitle);

		JLabel lblName = new JLabel("KARAOKE AQUA");
		lblName.setForeground(Color.BLUE);
		lblName.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 70));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(0, 108, 1066, 90);
		contentPane.add(lblName);

		ImageIcon img = new ImageIcon(GUI_ChaoMung.class.getResource("/icon/Logo.png"));
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg);
		JLabel lblAnh = new JLabel(img);
		lblAnh.setBounds(0, 198, 1076, 373);
		contentPane.add(lblAnh);
	}

	public Component tabChaoMung() {
		return contentPane;
	}
}
