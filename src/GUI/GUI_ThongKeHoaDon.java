//Huỳnh Hữu Phước
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.HoaDon_dao;
import dao.Phong_HoaDon_dao;
import entity.HoaDon;
import entity.Phong_HoaDon;
import entity.ThongTinTaiKhoan;

import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ItemEvent;

public class GUI_ThongKeHoaDon extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel dtmDSHD;
	private JTable tblDsHoaDon;
	private JComboBox<String> cboNam;
	private JComboBox<String> cboLoc;
	private JComboBox<String> cboThang;
	public static JPanel pnBieuDo;
	private static ThongTinTaiKhoan tk;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public GUI_ThongKeHoaDon(ThongTinTaiKhoan tk) {
		this.tk = tk;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 610);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnBieuDo = new JPanel();
		pnBieuDo.setBackground(SystemColor.control);
		pnBieuDo.setBounds(0, 155, 865, 414);
		contentPane.add(pnBieuDo);
		pnBieuDo.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Biểu đồ thống kê"));

		JPanel pnDanhSachHoaDon = new JPanel();
		pnDanhSachHoaDon.setBackground(Color.WHITE);
		pnDanhSachHoaDon.setBounds(0, 0, 865, 153);
		contentPane.add(pnDanhSachHoaDon);
		pnDanhSachHoaDon.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách hóa đơn"));
		pnDanhSachHoaDon.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 15, 845, 128);
		pnDanhSachHoaDon.add(scrollPane);

		tblDsHoaDon = new JTable(dtmDSHD = new DefaultTableModel(
				new String[] { "Mã HĐ", "Mã KH", "Mã Phòng", "Thành tiền", "Ngày lập" }, 0)) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblDsHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsHoaDon.getTableHeader().setBackground(Color.CYAN);
		tblDsHoaDon.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
		tblDsHoaDon.getTableHeader().setForeground(Color.BLUE);
		tblDsHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 15));
		scrollPane.setViewportView(tblDsHoaDon);

		JPanel pnTacVu = new JPanel();
		pnTacVu.setBackground(Color.WHITE);
		pnTacVu.setBounds(875, 0, 191, 569);
		contentPane.add(pnTacVu);
		pnTacVu.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn tác vụ"));
		pnTacVu.setLayout(null);

		JLabel lblTheoThang = new JLabel("Tháng:");
		lblTheoThang.setFont(new Font("Constantia", Font.BOLD, 13));
		lblTheoThang.setBounds(10, 57, 57, 20);
		pnTacVu.add(lblTheoThang);

		cboThang = new JComboBox<String>();
		cboThang.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cboThang.setBackground(Color.WHITE);
		cboThang.setBounds(70, 54, 111, 21);
		pnTacVu.add(cboThang);
		for (int n = 1; n <= 13; n++) {
			if (n == 13) {
				cboThang.addItem("Cả năm");
			} else {
				cboThang.addItem("Tháng " + n);
			}
		}

		cboLoc = new JComboBox<String>();
		cboLoc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cboLoc.setBackground(Color.WHITE);
		cboLoc.setBounds(70, 20, 111, 21);
		pnTacVu.add(cboLoc);

		JLabel lblLoc = new JLabel("Lọc: ");
		lblLoc.setFont(new Font("Constantia", Font.BOLD, 13));
		lblLoc.setBounds(10, 23, 57, 20);
		pnTacVu.add(lblLoc);

		cboNam = new JComboBox<String>();
		cboNam.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cboNam.setBackground(Color.WHITE);
		cboNam.setBounds(70, 87, 111, 21);
		pnTacVu.add(cboNam);
		cboNam.addItem("2022");
		cboNam.addItem("2021");

		JLabel lblNam = new JLabel("Năm: ");
		lblNam.setFont(new Font("Constantia", Font.BOLD, 13));
		lblNam.setBounds(10, 90, 57, 20);
		pnTacVu.add(lblNam);

		pnBieuDo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		cboLoc.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					removeComponent();
					pnBieuDo.add(new GUI_BieuDoThongKe(tk, cboLoc.getSelectedItem() + "",
							cboThang.getSelectedItem() + "", cboNam.getSelectedItem() + "").loadChart());
					loadDB();
				}
			}
		});
		cboThang.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					removeComponent();
					pnBieuDo.add(new GUI_BieuDoThongKe(tk, cboLoc.getSelectedItem() + "",
							cboThang.getSelectedItem() + "", cboNam.getSelectedItem() + "").loadChart());
					loadDB();
				}
			}
		});
		cboNam.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					removeComponent();
					pnBieuDo.add(new GUI_BieuDoThongKe(tk, cboLoc.getSelectedItem() + "",
							cboThang.getSelectedItem() + "", cboNam.getSelectedItem() + "").loadChart());
					loadDB();
				}
			}
		});
		String regexMaNs = "^NV\\d{4}$";
		if (kiemTraNhap(tk.getMa(), regexMaNs)) {
			cboLoc.removeAll();
			cboLoc.addItem("Cá nhân");
		} else {
			cboLoc.removeAll();
			cboLoc.addItem("Cá nhân");
			cboLoc.addItem("Cả cơ sở");
		}
		cboThang.setSelectedIndex(java.time.LocalDate.now().getMonthValue() - 1);

	}

	public void removeComponent() {
		Component[] components = pnBieuDo.getComponents();
		for (Component component : components) {
			pnBieuDo.remove(component);
		}
		pnBieuDo.revalidate();
		pnBieuDo.repaint();
	}

	public Component tabThongkeHoaDon() {
		return contentPane;
	}

	public static void showMessage() {
		JOptionPane.showMessageDialog(null, "Không phát sinh hóa đơn trong tháng này");
	}

	public void addRow(HoaDon hd) {
		Phong_HoaDon ph = new Phong_HoaDon_dao().getPhong_HoaDon(hd.getMaHD());
		String[] a = { hd.getMaHD(), hd.getMaKH(), ph.getMaPh(), hd.getTongThanhToan() + "", hd.getThoiDiemTT() };
		dtmDSHD.addRow(a);
	}

	public void loadDB() {
		dtmDSHD.setRowCount(0);
		List<HoaDon> dshd;
		if (cboLoc.getSelectedItem() == "Cả cơ sở"&&cboThang.getSelectedItem()!="Cả năm") {
			dshd = new HoaDon_dao().getDanhSachHoaDonTrongThang(Integer.parseInt(cboNam.getSelectedItem() + ""),
					Integer.parseInt(subStringThang(cboThang.getSelectedItem() + "")));
			for (HoaDon hd : dshd) {
				addRow(hd);
			}
		} else if(cboThang.getSelectedItem()!="Cả năm"){
			String regexMaNs = "^NV\\d{4}$";
			if (kiemTraNhap(tk.getMa(), regexMaNs)) {
				dshd = new HoaDon_dao().getDanhSachHoaDonTrongThangCuaNhanVien(
						Integer.parseInt(cboNam.getSelectedItem() + ""),
						Integer.parseInt(subStringThang(cboThang.getSelectedItem() + "")), tk.getMa());
				for (HoaDon hd : dshd) {
					addRow(hd);
				}
			} else {
				dshd = new HoaDon_dao().getDanhSachHoaDonTrongThangCuaQuanLy(
						Integer.parseInt(cboNam.getSelectedItem() + ""),
						Integer.parseInt(subStringThang(cboThang.getSelectedItem() + "")), tk.getMa());
				for (HoaDon hd : dshd) {
					addRow(hd);
				}
			}
		}

	}

	public static boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

	public static String subStringThang(String thang) {
		String a = thang.substring(thang.indexOf(" ") + 1);
		return a;
	}
}
