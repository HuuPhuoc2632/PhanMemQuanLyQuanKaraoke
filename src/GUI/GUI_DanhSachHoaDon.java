//Huỳnh Hữu Phước
package GUI;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import dao.HoaDon_dao;
import dao.KhachHang_dao;
import dao.NhanSu_dao;
import dao.Phong_HoaDon_dao;
import dao.Phong_dao;
import dao.QuanLy_dao;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanSu;
import entity.Phong;
import entity.Phong_HoaDon;
import entity.QuanLy;
import entity.ThongTinTaiKhoan;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import com.toedter.calendar.JYearChooser;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GUI_DanhSachHoaDon extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSdt;
	private JTable tblDanhSachHoaDon;
	private static DefaultTableModel dtmDSHD;
	private JPanel pnTacVu;
	private DecimalFormat formatter;
	private JYearChooser txtNam;
	private JComboBox<String> cboThang;
	@SuppressWarnings("unused")
	private SimpleDateFormat dateFormat;
	private JDateChooser txtTruoc;
	private JDateChooser txtSau;
	private int thang, nam;
	private JRadioButton rdbtnNgay;
	private JRadioButton rdbtnNam;
	private ButtonGroup btng;
	private JRadioButton rdbtnSdt;
	private Double tongGiaTri = 0.0;
	private JLabel lblTongGiaTriText;
	@SuppressWarnings("unused")
	private ThongTinTaiKhoan tttk;

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */

	public GUI_DanhSachHoaDon(ThongTinTaiKhoan tttk) throws ParseException {
		this.tttk = tttk;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 610);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		formatter = new DecimalFormat("###,###,###");

		JPanel lblTimSdt = new JPanel();
		lblTimSdt.setBackground(Color.WHITE);
		lblTimSdt.setBounds(10, 0, 879, 101);
		contentPane.add(lblTimSdt);
		lblTimSdt.setLayout(null);
		lblTimSdt.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Tìm kiếm và sắp xếp"));

		JLabel lblTruoc = new JLabel("Từ:");
		lblTruoc.setFont(new Font("Constantia", Font.BOLD, 13));
		lblTruoc.setBounds(10, 20, 36, 20);
		lblTimSdt.add(lblTruoc);

		txtTruoc = new JDateChooser();
		txtTruoc.setEnabled(true);
		txtTruoc.setDateFormatString("dd-MM-yyyy");
		txtTruoc.setBackground(Color.WHITE);
		txtTruoc.setBounds(70, 20, 136, 20);
		lblTimSdt.add(txtTruoc);

		JLabel lblSau = new JLabel("Đến:");
		lblSau.setFont(new Font("Constantia", Font.BOLD, 13));
		lblSau.setBounds(263, 20, 40, 20);
		lblTimSdt.add(lblSau);

		txtSau = new JDateChooser();
		txtSau.addPropertyChangeListener(new PropertyChangeListener() {
			@SuppressWarnings({ "static-access" })
			public void propertyChange(PropertyChangeEvent evt) {
				tongGiaTri = 0.0;
				if (txtSau.getDate() != null && txtTruoc.getDate() != null && rdbtnNgay.isSelected()) {
					Calendar sau = chuyenCalendar(txtSau.getDate());
					Calendar truoc = chuyenCalendar(txtTruoc.getDate());
					if (sau.get(sau.YEAR) - truoc.get(truoc.YEAR) >= 0
							&& sau.get(sau.MONTH) - truoc.get(truoc.MONTH) >= 0
							&& sau.get(sau.DAY_OF_MONTH) - truoc.get(truoc.DAY_OF_MONTH) >= 0) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String ngay1 = sdf.format(txtTruoc.getDate()) + " 00:00:00";
						String ngay2 = sdf.format(txtSau.getDate()) + " 23:59:59";
						List<HoaDon> ls = new HoaDon_dao().getDanhSachHoaDonGiuaCacNgay(ngay1, ngay2);
						dtmDSHD.setRowCount(0);
						for (HoaDon hoaDon : ls) {
							if (hoaDon.getThoiDiemTT() != null) {
								addRow(hoaDon);
								tongGiaTri += hoaDon.getTongThanhToan();
							}
						}
						lblTongGiaTriText.setText(formatter.format(tongGiaTri) + " VNĐ");
					}
				}
			}
		});
		txtSau.setOpaque(false);
		txtSau.setEnabled(true);
		txtSau.setDateFormatString("dd-MM-yyyy");
		txtSau.setBackground(Color.WHITE);
		txtSau.setBounds(305, 20, 136, 20);
		lblTimSdt.add(txtSau);
		txtTruoc.addPropertyChangeListener(new PropertyChangeListener() {
			@SuppressWarnings({ "static-access" })
			public void propertyChange(PropertyChangeEvent evt) {
				tongGiaTri = 0.0;
				if (txtSau.getDate() != null && txtTruoc.getDate() != null && rdbtnNgay.isSelected()) {
					Calendar sau = chuyenCalendar(txtSau.getDate());
					Calendar truoc = chuyenCalendar(txtTruoc.getDate());
					if (sau.get(sau.YEAR) - truoc.get(truoc.YEAR) >= 0
							&& sau.get(sau.MONTH) - truoc.get(truoc.MONTH) >= 0
							&& sau.get(sau.DAY_OF_MONTH) - truoc.get(truoc.DAY_OF_MONTH) >= 0) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String ngay1 = sdf.format(txtTruoc.getDate()) + " 00:00:00";
						String ngay2 = sdf.format(txtSau.getDate()) + " 23:59:59";
						List<HoaDon> ls = new HoaDon_dao().getDanhSachHoaDonGiuaCacNgay(ngay1, ngay2);
						dtmDSHD.setRowCount(0);
						for (HoaDon hoaDon : ls) {
							if (hoaDon.getThoiDiemTT() != null) {
								addRow(hoaDon);
								tongGiaTri += hoaDon.getTongThanhToan();
							}
						}
						lblTongGiaTriText.setText(formatter.format(tongGiaTri) + " VNĐ");
					}
				}
			}
		});

		JLabel lblSdt = new JLabel("Tìm theo SĐT của khách hàng:");
		lblSdt.setFont(new Font("Constantia", Font.BOLD, 13));
		lblSdt.setBounds(497, 20, 190, 20);
		lblTimSdt.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtSdt.setBounds(697, 18, 146, 19);
		lblTimSdt.add(txtSdt);
		txtSdt.setColumns(10);

		JLabel lblTheoThang = new JLabel("Tháng");
		lblTheoThang.setFont(new Font("Constantia", Font.BOLD, 13));
		lblTheoThang.setBounds(10, 60, 50, 20);
		lblTimSdt.add(lblTheoThang);

		JLabel lblNm = new JLabel("Năm");
		lblNm.setFont(new Font("Constantia", Font.BOLD, 13));
		lblNm.setBounds(263, 60, 50, 20);
		lblTimSdt.add(lblNm);

		rdbtnNgay = new JRadioButton();
		rdbtnNgay.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnNgay.isSelected()) {
					txtTruoc.setEnabled(true);
					txtSau.setEnabled(true);
				} else {
					txtTruoc.setEnabled(false);
					txtSau.setEnabled(false);
				}
			}
		});
		rdbtnNgay.setSelected(true);
		rdbtnNgay.setBackground(Color.WHITE);
		rdbtnNgay.setBounds(450, 18, 18, 21);
		lblTimSdt.add(rdbtnNgay);

		rdbtnNam = new JRadioButton("New radio button");
		rdbtnNam.addChangeListener(new ChangeListener() {
			@SuppressWarnings("deprecation")
			public void stateChanged(ChangeEvent e) {
				if (rdbtnNam.isSelected()) {
					cboThang.setEnabled(true);
					txtNam.setEnabled(true);
					Date now = java.util.Calendar.getInstance().getTime();
					cboThang.setSelectedIndex(now.getMonth());
				} else {
					cboThang.setEnabled(false);
					txtNam.setEnabled(false);
				}
			}
		});
		rdbtnNam.setBackground(Color.WHITE);
		rdbtnNam.setBounds(450, 58, 18, 21);
		lblTimSdt.add(rdbtnNam);

		JPanel pnDanhSach = new JPanel();
		pnDanhSach.setBackground(Color.WHITE);
		pnDanhSach.setBounds(10, 111, 1056, 462);
		contentPane.add(pnDanhSach);
		pnDanhSach.setLayout(null);
		pnDanhSach.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách hóa đơn"));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 1036, 432);
		pnDanhSach.add(scrollPane);

		tblDanhSachHoaDon = new JTable(dtmDSHD = new DefaultTableModel(
				new String[] { "Mã HĐ", "Tên KH", "Tổng tiền", "Nhân viên", "Thời điểm thanh toán" }, 0)) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblDanhSachHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDanhSachHoaDon.getTableHeader().setBackground(Color.CYAN);
		tblDanhSachHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tblDanhSachHoaDon.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
		tblDanhSachHoaDon.getTableHeader().setForeground(Color.BLUE);
		scrollPane.setViewportView(tblDanhSachHoaDon);

		pnTacVu = new JPanel();
		pnTacVu.setBackground(Color.WHITE);
		pnTacVu.setBounds(888, 0, 178, 102);
		contentPane.add(pnTacVu);
		pnTacVu.setLayout(null);
		pnTacVu.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn tác vụ"));

		JButton btnTimKiem = new JButton("Tìm kiếm",
				new ImageIcon(GUI_ThuePhong.class.getResource("/icon/search_gradient.png")));
		btnTimKiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTimKiem.setFont(new Font("Constantia", Font.BOLD, 16));
				btnTimKiem.setForeground(Color.GREEN);
				btnTimKiem.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.GREEN));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnTimKiem.setFont(new Font("Constantia", Font.BOLD, 14));
				btnTimKiem.setForeground(Color.BLACK);
				btnTimKiem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tongGiaTri = 0.0;
				if (rdbtnSdt.isSelected()) {
					KhachHang k = new KhachHang_dao().getKHTheoSDT(txtSdt.getText());
					List<HoaDon> ls = new HoaDon_dao().getDanhSachHoaDonTheoMaKH(k.getMaKH());
					dtmDSHD.setRowCount(0);
					for (HoaDon hoaDon : ls) {
						if (hoaDon.getThoiDiemTT() != null) {
							addRow(hoaDon);
							tongGiaTri += hoaDon.getTongThanhToan();
						}
					}
					lblTongGiaTriText.setText(formatter.format(tongGiaTri) + " VNĐ");
				}
			}
		});
		btnTimKiem.setToolTipText("");
		btnTimKiem.setFont(new Font("Constantia", Font.BOLD, 14));
		btnTimKiem.setFocusPainted(false);
		btnTimKiem.setContentAreaFilled(false);
		btnTimKiem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
		btnTimKiem.setBounds(10, 35, 158, 33);
		pnTacVu.add(btnTimKiem);

		rdbtnSdt = new JRadioButton("New radio button");
		rdbtnSdt.setBackground(Color.WHITE);
		rdbtnSdt.setBounds(849, 18, 18, 21);
		lblTimSdt.add(rdbtnSdt);
		rdbtnSdt.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnSdt.isSelected()) {
					txtSdt.setEnabled(true);
				} else {
					txtSdt.setEnabled(false);
				}
			}
		});

		btng = new ButtonGroup();
		btng.add(rdbtnNgay);
		btng.add(rdbtnNam);
		btng.add(rdbtnSdt);

		txtNam = new JYearChooser();
		txtNam.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				tongGiaTri = 0.0;
				if (rdbtnNam.isSelected()) {
					thang = cboThang.getSelectedIndex() + 1;
					nam = txtNam.getValue();
					List<HoaDon> ls = new HoaDon_dao().getDanhSachHoaDonTrongThang(nam, thang);
					dtmDSHD.setRowCount(0);
					for (HoaDon hoaDon : ls) {
						if (hoaDon.getThoiDiemTT() != null) {
							addRow(hoaDon);
							tongGiaTri += hoaDon.getTongThanhToan();
						}
					}
					lblTongGiaTriText.setText(formatter.format(tongGiaTri) + " VNĐ");
				}
			}
		});

		txtNam.setBounds(305, 61, 136, 19);
		lblTimSdt.add(txtNam);

		cboThang = new JComboBox<String>();
		cboThang.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				tongGiaTri = 0.0;
				if (rdbtnNam.isSelected()) {
					thang = cboThang.getSelectedIndex() + 1;
					nam = txtNam.getValue();
					List<HoaDon> ls = new HoaDon_dao().getDanhSachHoaDonTrongThang(nam, thang);
					dtmDSHD.setRowCount(0);
					for (HoaDon hoaDon : ls) {
						if (hoaDon.getThoiDiemTT() != null) {
							addRow(hoaDon);
							tongGiaTri += hoaDon.getTongThanhToan();
						}
					}
					lblTongGiaTriText.setText(formatter.format(tongGiaTri) + " VNĐ");
				}
			}
		});
		cboThang.setBackground(Color.WHITE);
		cboThang.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cboThang.setBounds(70, 58, 136, 21);
		for (int i = 1; i <= 12; i++) {
			cboThang.addItem("Tháng " + i);
		}
		lblTimSdt.add(cboThang);
		txtNam.setEnabled(false);
		cboThang.setEnabled(false);
		txtSdt.setEnabled(false);

		JButton btnXemChiTiet = new JButton("Xem chi tiết", null);
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = -1;
				n = tblDanhSachHoaDon.getSelectedRow();
				if (n == -1) {
					JOptionPane.showMessageDialog(null, "Bạn phải chọn hóa đơn cần xem!");
				} else {
					Phong_HoaDon phd = new Phong_HoaDon_dao().getPhong_HoaDon(getmaHoaDon());
					Phong ph = new Phong_dao().getPhongTrongTheoMa(phd.getMaPh());
					HoaDon hoaDon = new HoaDon_dao().getHoaDon(getmaHoaDon());
					try {
						new GUI_ChiTietHoaDonThongKe(ph, hoaDon).setVisible(true);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnXemChiTiet.setToolTipText("");
		btnXemChiTiet.setFont(new Font("Constantia", Font.BOLD, 14));
		btnXemChiTiet.setFocusPainted(false);
		btnXemChiTiet.setContentAreaFilled(false);
		btnXemChiTiet.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
		btnXemChiTiet.setBounds(763, 54, 106, 33);
		lblTimSdt.add(btnXemChiTiet);

		JLabel lblTongGiaTri = new JLabel("Tổng doanh thu:");
		lblTongGiaTri.setFont(new Font("Constantia", Font.BOLD, 13));
		lblTongGiaTri.setBounds(497, 62, 107, 20);
		lblTimSdt.add(lblTongGiaTri);

		lblTongGiaTriText = new JLabel("");
		lblTongGiaTriText.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTongGiaTriText.setBounds(619, 62, 107, 20);
		lblTimSdt.add(lblTongGiaTriText);
		loadNgayHienTai();
		btnXemChiTiet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnXemChiTiet.setFont(new Font("Constantia", Font.BOLD, 16));
				btnXemChiTiet.setForeground(Color.BLUE);
				btnXemChiTiet.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.BLUE));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnXemChiTiet.setFont(new Font("Constantia", Font.BOLD, 14));
				btnXemChiTiet.setForeground(Color.BLACK);
				btnXemChiTiet.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
			}
		});

	}

	public void loadNgayHienTai() throws ParseException {
		Date now = java.util.Calendar.getInstance().getTime();
		txtTruoc.setDate(now);
		txtSau.setDate(now);

	}

	public Calendar chuyenCalendar(java.util.Date date2) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = sp.format(date2);
		String year = date.substring(0, date.indexOf("-"));
		String mon = date.substring(date.indexOf("-") + 1);
		String month = mon.substring(0, mon.indexOf("-"));
		String day = mon.substring(3);
		int yearInt = Integer.parseInt(year);
		int monthInt = Integer.parseInt(month) - 1;
		int dayOfMonthInt = Integer.parseInt(day);
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		calendar.set(yearInt, monthInt, dayOfMonthInt);
		return calendar;

	}

	public void addRow(HoaDon hd) {
		KhachHang kh = new KhachHang_dao().getDSKHTheoMa(hd.getMaKH());
		String hoTen;
		if (hd.getMaNV() == null) {
			QuanLy ql = new QuanLy_dao().getQL(hd.getMaQL());
			hoTen = ql.getHoQL() + " " + ql.getTenQL();
		} else {
			NhanSu ns = new NhanSu_dao().getNS(hd.getMaNV());
			hoTen = ns.getHoNV() + " " + ns.getTenNV();
		}
		formatter = new DecimalFormat("###,###,###");
		String thanhToan = formatter.format(hd.getTongThanhToan()) + " VNĐ";
		String[] a = { hd.getMaHD(), kh.getHoKH() + " " + kh.getTenKH(), thanhToan, hoTen, hd.getThoiDiemTT() };
		dtmDSHD.addRow(a);
	}

	public Component tabDanhSachHoaDon() {
		return contentPane;
	}

	public String getmaHoaDon() {
		String ma = dtmDSHD.getValueAt(tblDanhSachHoaDon.getSelectedRow(), 0).toString();
		return ma;
	}
}
