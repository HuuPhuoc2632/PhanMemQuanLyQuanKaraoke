package GUI;

import java.awt.Component;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import entity.ThongTinTaiKhoan;

import javax.swing.JTabbedPane;

public class GUI_DatThuePhong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTabbedPane tabbedPane;

	public GUI_DatThuePhong(ThongTinTaiKhoan tttk) throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1076, 603);
		contentPane.add(tabbedPane);
		tabbedPane.add("Thuê phòng", new GUI_ThuePhong(tttk).tabThuePhong());
		tabbedPane.add("Đặt phòng", new GUI_DatPhong(tttk).tabDatPhong());
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				GUI_ThuePhong.refresh();
				try {
					GUI_DatPhong.refresh();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	public Component datThuePhong() {
		return contentPane;
	}

	public static void chonTab(int n) {
		tabbedPane.setSelectedIndex(n);
	}
}
