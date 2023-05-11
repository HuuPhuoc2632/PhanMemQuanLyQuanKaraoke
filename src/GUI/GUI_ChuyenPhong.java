//Huỳnh Hữu Phước
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dao.Phong_HoaDon_dao;
import dao.Phong_dao;
import dao.ThongTinChuyenPhong_dao;
import dao.ThongTinDat_dao;
import dao.ThongTinSuDungPhong_dao;
import entity.HoaDon;
import entity.Phong;
import entity.ThongTinChuyenPhong;
import entity.ThongTinDat;

import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("unused")
public class GUI_ChuyenPhong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cboLoaiPhong;
	private SimpleDateFormat dateFormat;
	private JLabel lblGiaText;

	/**
	 * Create the frame.
	 */
	public GUI_ChuyenPhong(Phong p, HoaDon hd) {
		setBounds(100, 100, 825, 194);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane
				.setBorder(new TitledBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Chuy\u1EC3n ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP, null,
								new Color(0, 0, 0)),
						"Chuy\u1EC3n ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblPhongHienTai = new JLabel("Phòng hiện tại:");
		lblPhongHienTai.setFont(new Font("Constantia", Font.BOLD, 13));
		lblPhongHienTai.setBounds(10, 26, 104, 20);
		contentPane.add(lblPhongHienTai);

		JLabel lblMaPhongHienTai = new JLabel("PH0001");
		lblMaPhongHienTai.setForeground(Color.RED);
		lblMaPhongHienTai.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblMaPhongHienTai.setBounds(124, 26, 65, 20);
		contentPane.add(lblMaPhongHienTai);

		JLabel lblPhongChuyen = new JLabel("Phòng có thể chuyển:");
		lblPhongChuyen.setFont(new Font("Constantia", Font.BOLD, 13));
		lblPhongChuyen.setBounds(470, 28, 141, 20);
		contentPane.add(lblPhongChuyen);
		JComboBox<String> cboMaPhong = new JComboBox<String>();
		cboMaPhong.setBackground(Color.WHITE);
		cboMaPhong.setForeground(Color.RED);
		cboMaPhong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboMaPhong.setBounds(621, 26, 181, 21);
		contentPane.add(cboMaPhong);

		JLabel lblLoaiPhong = new JLabel("Loại:");
		lblLoaiPhong.setFont(new Font("Constantia", Font.BOLD, 13));
		lblLoaiPhong.setBounds(199, 28, 47, 20);
		contentPane.add(lblLoaiPhong);

		JSlider slSucChua = new JSlider();
		slSucChua.setValue(25);
		slSucChua.setSnapToTicks(true);
		slSucChua.setPaintLabels(true);
		slSucChua.setMinorTickSpacing(5);
		slSucChua.setMinimum(10);
		slSucChua.setMaximum(25);
		slSucChua.setMajorTickSpacing(5);
		slSucChua.setBackground(Color.WHITE);
		slSucChua.setBounds(112, 56, 230, 35);
		contentPane.add(slSucChua);
		lblMaPhongHienTai.setText(p.getMaPhong());

		cboLoaiPhong = new JComboBox<String>();
		cboLoaiPhong.setBackground(Color.WHITE);
		cboLoaiPhong.setForeground(Color.BLACK);
		cboLoaiPhong.setFont(new Font("Constantia", Font.BOLD, 13));
		cboLoaiPhong.setBounds(243, 26, 181, 21);
		contentPane.add(cboLoaiPhong);
		cboLoaiPhong.addItem("Chọn loại phòng");
		cboLoaiPhong.addItem("Phòng thường");
		cboLoaiPhong.addItem("Phòng VIP");
		cboLoaiPhong.addItemListener(new ItemListener() {
			private List<Phong> list;

			@SuppressWarnings({ "static-access", "deprecation" })
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					boolean n = true, m = false;
					if (cboLoaiPhong.getSelectedItem() == "Phòng thường") {
						n = false;
					} else {
						n = true;
					}
					if (cboLoaiPhong.getSelectedIndex() == 0) {
						cboMaPhong.removeAllItems();
					} else {
						SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
						dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date d = new Date();
						String date = sp.format(d.getDate());
						String year = date.substring(0, date.indexOf("-"));
						String mon = date.substring(date.indexOf("-") + 1);
						String month = mon.substring(0, mon.indexOf("-"));
						String day = date.substring(date.indexOf("-") + 4);
						int yearInt = Integer.parseInt(year);
						int monthInt = Integer.parseInt(month);
						int dayOfMonthInt = Integer.parseInt(day);
						Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
						calendar.set(yearInt, monthInt, dayOfMonthInt);
						List<ThongTinDat> ls = new ThongTinDat_dao()
								.getDanhSachPhongDaDatTheoNgay(java.time.LocalDate.now().getDayOfMonth()+"");
						cboMaPhong.removeAllItems();
						list = Phong_dao.getDanhSachPhongTrongTheoLoai(n);
						for (Phong p : list) {
							if (ls.size() == 0 && p.isTrangThai() == false) {
								cboMaPhong.addItem(p.getMaPhong());
							} else if (p.isTrangThai() == false) {
								for (int i = 0; i < ls.size(); i++) {
									if (p.getMaPhong().equalsIgnoreCase(ls.get(i).getMaPh())) {
										m = false;
										break;
									} else {
										m = true;
									}
								}
								if (m && p.isTrangThai() == false) {
									cboMaPhong.addItem(p.getMaPhong());
								}
							}
						}
					}
				}
			}
		});

		JButton btnChuyenPhong = new JButton("Chuyển phòng");
		btnChuyenPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChuyenPhong.setForeground(Color.BLUE);
				btnChuyenPhong.setFont(new Font("Constantia", Font.BOLD, 15));
				btnChuyenPhong.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.BLUE));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnChuyenPhong.setForeground(Color.BLACK);
				btnChuyenPhong.setFont(new Font("Constantia", Font.BOLD, 13));
				btnChuyenPhong.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
			}
		});
		btnChuyenPhong.setFocusPainted(false);
		btnChuyenPhong.setContentAreaFilled(false);
		btnChuyenPhong.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
		btnChuyenPhong.addActionListener(new ActionListener() {
			private double gioVao;
			private Date date;
			private double gioRa;
			private Date vao;
			private String slg;

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (cboMaPhong.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Phải chọn phòng mới để chuyển");
				} else {
					if (JOptionPane.showConfirmDialog(null,
							"Xác nhận chuyển từ phòng " + lblMaPhongHienTai.getText() + " sang phòng "
									+ cboMaPhong.getSelectedItem(),
							"Chuyển phòng", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						Date dat = new Date();
						Date da = new Date();
						Date m = new Date();
						String gio = sp.format(m);
						try {
							List<ThongTinChuyenPhong> ls = new ThongTinChuyenPhong_dao()
									.getThongTinChuyenPhongTheoMaHD(hd.getMaHD());
							if (ls.size() == 0) {
								da = sp.parse(hd.getThoiDiemSD());
							} else {
								da = sp.parse(ls.get(ls.size() - 1).getGioVaoMoi() + "");
							}
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							dat = sp.parse(gio);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						gioVao = da.getHours()
								+ Double.parseDouble((Double.parseDouble(da.getMinutes() + "") / 60 + ""));
						gioRa = java.time.LocalDateTime.now().getHour() + Double.parseDouble(
								(Double.parseDouble(java.time.LocalDateTime.now().getMinute() + "") / 60 + ""));
						slg = (gioRa - gioVao) + "";
						Double tongTienGio = p.getDonGia() * (Double.parseDouble(slg));
						ThongTinChuyenPhong td = new ThongTinChuyenPhong(hd.getMaHD(), p.getMaPhong(),
								cboMaPhong.getSelectedItem() + "", da, dat, tongTienGio);
						new ThongTinChuyenPhong_dao().insertChuyenPhong(td);

						Boolean a = new ThongTinSuDungPhong_dao().updateThongTinSDP(cboMaPhong.getSelectedItem() + "",
								hd.getMaHD());
						Boolean b = new Phong_HoaDon_dao().updatePhong_HoaDon(cboMaPhong.getSelectedItem() + "",
								hd.getMaHD());
						p.setTrangThai(false);
						Boolean c = new Phong_dao().updatePhong(p);
						Phong ph = new Phong_dao().getPhongTrongTheoMa(cboMaPhong.getSelectedItem() + "");
						ph.setTrangThai(true);
						Boolean d = new Phong_dao().updatePhong(ph);

						if (a && b && c && d) {
							GUI_ThuePhong.loadDanhSachPhongDangSuDung();
							setVisible(false);
							JOptionPane.showMessageDialog(null, "Chuyển phòng thành công");

						} else {
							JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi chuyển phòng");
						}
					}
				}
			}
		});
		btnChuyenPhong.setFont(new Font("Constantia", Font.BOLD, 13));
		btnChuyenPhong.setBounds(194, 101, 148, 34);
		contentPane.add(btnChuyenPhong);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setFocusPainted(false);
		btnHuy.setContentAreaFilled(false);
		btnHuy.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		btnHuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHuy.setForeground(Color.RED);
				btnHuy.setFont(new Font("Constantia", Font.BOLD, 15));
				btnHuy.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.RED));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnHuy.setForeground(Color.BLACK);
				btnHuy.setFont(new Font("Constantia", Font.BOLD, 13));
				btnHuy.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
			}
		});
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnHuy.setFont(new Font("Constantia", Font.BOLD, 13));
		btnHuy.setBounds(470, 101, 148, 34);
		contentPane.add(btnHuy);

		JLabel lblSucChua = new JLabel("Sức chứa:");
		lblSucChua.setFont(new Font("Constantia", Font.BOLD, 13));
		lblSucChua.setBounds(10, 67, 64, 20);
		contentPane.add(lblSucChua);

		JLabel lblGia = new JLabel("Giá/giờ:");
		lblGia.setFont(new Font("Constantia", Font.BOLD, 14));
		lblGia.setBounds(370, 70, 65, 22);
		contentPane.add(lblGia);

		lblGiaText = new JLabel("0 VNĐ");
		lblGiaText.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGiaText.setBounds(470, 67, 94, 22);
		contentPane.add(lblGiaText);
		
		cboMaPhong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (cboMaPhong.getSelectedItem() != null) {
						Phong phg = new Phong_dao().getPhongTrongTheoMa(cboMaPhong.getSelectedItem() + "");
						lblGiaText.setText(phg.getDonGia() + " VNĐ");
						slSucChua.setValue(phg.getSucChua());
					}
				}
			}
		});

	}
}
