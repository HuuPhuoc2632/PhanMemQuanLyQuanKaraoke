package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

public class GUI_DongNullSanPham extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnDanhMuc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_DongNullSanPham frame = new GUI_DongNullSanPham();
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
	public GUI_DongNullSanPham() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		pnDanhMuc = new JPanel();
		pnDanhMuc.setLayout(null);
		pnDanhMuc.setPreferredSize(new Dimension(666, 40));
		pnDanhMuc.setBackground(Color.WHITE);
		contentPane.add(pnDanhMuc);
	}

	public Component dongSanPhamNull() {
		return pnDanhMuc;
	}

}
