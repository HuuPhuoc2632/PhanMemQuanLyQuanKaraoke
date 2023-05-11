//Huỳnh Hữu Phước
package GUI;

import java.awt.Component;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.ThongTinTaiKhoan;

import javax.swing.JTabbedPane;

public class GUI_ThongKe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public GUI_ThongKe(ThongTinTaiKhoan tttk) throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1090, 640);
		tabbedPane.add("Danh sách hóa đơn", new GUI_DanhSachHoaDon(tttk).tabDanhSachHoaDon());
		tabbedPane.add("Thống kê doanh thu", new GUI_ThongKeHoaDon(tttk).tabThongkeHoaDon());
		contentPane.add(tabbedPane);
	}

	public Component tabThongKe() {
		return contentPane;
	}
}
