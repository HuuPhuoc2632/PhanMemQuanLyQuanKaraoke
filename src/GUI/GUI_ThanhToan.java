//Huỳnh Hữu Phước
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import dao.HoaDon_SanPhamDV_dao;
import dao.HoaDon_dao;
import dao.KhachHang_dao;
import dao.Phong_dao;
import dao.SanPhamDV_dao;
import dao.ThongTinChuyenPhong_dao;
import dao.ThongTinSuDungPhong_dao;
import entity.HoaDon;
import entity.HoaDon_SanPhamDV;
import entity.KhachHang;
import entity.Phong;
import entity.SanPhamDV;
import entity.SanPhamExportHoaDon;
import entity.ThongTinChuyenPhong;
import entity.ThongTinTaiKhoan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JSpinner;
import com.itextpdf.text.DocumentException;
import javax.swing.SpinnerNumberModel;
import javax.swing.ScrollPaneConstants;

public class GUI_ThanhToan extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTienDua;
	@SuppressWarnings("unused")
	private static Phong p;
	private double tongThanhToan = 0;
	private JLabel lblTongThanhToanText;
	private DecimalFormat formatter;
	private Integer n;
	private double thanhToan;
	private JSpinner spGiamGia;
	private Date dt;
	private String sl;
	private double soLuong, gioVao, gioRa;
	public static List<SanPhamExportHoaDon> listEx = new ArrayList<SanPhamExportHoaDon>();
	private String tongHoaDonStr, tienKhachDuaStr, giamGiaStr, tienTraLaiStr, tongThanhToanStr;
	private JLabel lblNgayGioHienTai;
	private JLabel lblGioVaoText;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public GUI_ThanhToan(Phong p, String maHD, ThongTinTaiKhoan tttk) throws SQLException, ParseException {
		setSize(498, 727);
		GUI_ThanhToan.p = p;
//		GUI_ThanhToan.tk = tk;
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(Color.WHITE);
		pnTitle.setBounds(0, 0, 489, 65);
		contentPane.add(pnTitle);
		pnTitle.setLayout(null);

		JLabel lblTitle = new JLabel("HÓA ĐƠN THANH TOÁN");
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 489, 65);
		pnTitle.add(lblTitle);

		JPanel pnHoaDon = new JPanel();
		pnHoaDon.setBackground(Color.WHITE);
		pnHoaDon.setBounds(0, 65, 489, 625);
		contentPane.add(pnHoaDon);
		pnHoaDon.setLayout(null);

		JLabel lblMaHd = new JLabel("Mã hóa đơn: ");
		lblMaHd.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaHd.setBounds(10, 10, 94, 20);
		pnHoaDon.add(lblMaHd);

		JLabel lblNV = new JLabel("Nhân viên thu ngân:");
		lblNV.setFont(new Font("Arial", Font.BOLD, 13));
		lblNV.setBounds(211, 10, 134, 20);
		pnHoaDon.add(lblNV);

		JLabel lblThoiDiem = new JLabel("Thời điểm thanh toán:");
		lblThoiDiem.setFont(new Font("Arial", Font.BOLD, 13));
		lblThoiDiem.setBounds(10, 70, 147, 20);
		pnHoaDon.add(lblThoiDiem);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 0, 489, 10);
		pnHoaDon.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 97, 489, 2);
		pnHoaDon.add(separator_1);

		JLabel lblDanhMuc = new JLabel("Danh mục");
		lblDanhMuc.setFont(new Font("Arial", Font.BOLD, 13));
		lblDanhMuc.setBounds(10, 101, 78, 20);
		pnHoaDon.add(lblDanhMuc);

		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Arial", Font.BOLD, 13));
		lblSoLuong.setBounds(214, 101, 66, 20);
		pnHoaDon.add(lblSoLuong);

		JLabel lblThanhTien = new JLabel("Thành tiền");
		lblThanhTien.setFont(new Font("Arial", Font.BOLD, 13));
		lblThanhTien.setBounds(375, 101, 72, 20);
		pnHoaDon.add(lblThanhTien);

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm aa - dd/MM/yyyy");
		String dateString = df.format(date);

		JPanel pnBang = new JPanel();
		pnBang.setBackground(Color.WHITE);
		pnBang.setLayout(new BoxLayout(pnBang, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(pnBang, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 130, 489, 351);
		pnHoaDon.add(scrollPane);

		JLabel lblTongThanhToan = new JLabel("Tổng thanh toán: ");
		lblTongThanhToan.setFont(new Font("Arial", Font.BOLD, 13));
		lblTongThanhToan.setBounds(10, 551, 120, 20);
		pnHoaDon.add(lblTongThanhToan);

		listEx.clear();

		lblTongThanhToanText = new JLabel("");
		lblTongThanhToanText.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTongThanhToanText.setBounds(124, 551, 100, 20);
		pnHoaDon.add(lblTongThanhToanText);
		HoaDon hd = new HoaDon_dao().getHoaDon(maHD);
		KhachHang khachHang = new KhachHang_dao().getMotKHTheoMa(hd.getMaKH());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		dt = sdf.parse(hd.getThoiDiemSD());
		for (ThongTinChuyenPhong ttcp : new ThongTinChuyenPhong_dao().getThongTinChuyenPhongTheoMaHD(maHD)) {
			if (ttcp != null) {
				pnBang.add(new GUI_GioHatTrongHoaDon(p, sl, ttcp).gioHat());
				tongThanhToan += ttcp.getTienGioPhongCu();
				dt = ttcp.getGioVaoMoi();
			} else {
				dt = sdf.parse(hd.getThoiDiemSD());
			}
		}
		gioVao = dt.getHours() + Double.parseDouble((Double.parseDouble(dt.getMinutes() + "") / 60 + ""));
		gioRa = date.getHours() + Double.parseDouble((Double.parseDouble(date.getMinutes() + "") / 60 + ""));
		soLuong = gioRa - gioVao;
		double s = (double) Math.round(soLuong * 100) / 100;
		sl = s + "";
		pnBang.add(new GUI_GioHatTrongHoaDon(p, sl, null).gioHat());
		tongThanhToan += (double) (p.getDonGia() * s);
		List<HoaDon_SanPhamDV> ls = new HoaDon_SanPhamDV_dao().getDanhSachTheoMaHD(maHD);
		for (HoaDon_SanPhamDV hoaDon_SanPhamDV : ls) {
			SanPhamDV sp = new SanPhamDV_dao().getSanPhamTheoMa(hoaDon_SanPhamDV.getMaDV());
			pnBang.add(new GUI_DanhMucTrongHoaDon(sp, hoaDon_SanPhamDV.getSoLuong()).DanhMuc());
			tongThanhToan += (sp.getDonGia() * hoaDon_SanPhamDV.getSoLuong());
		}
		for (int i = 1; i < 10; i++) {
			pnBang.add(new frmDongNull().dongNull());
			i++;
		}

		JButton btnThanhToan = new JButton("Thanh toán",
				new ImageIcon(GUI_ThanhToan.class.getResource("/icon/pay.png")));
		btnThanhToan.setFont(new Font("Arial", Font.BOLD, 13));
		btnThanhToan.setBounds(89, 585, 140, 30);
		pnHoaDon.add(btnThanhToan);

		JLabel lblGiamGia = new JLabel("Giảm giá:");
		lblGiamGia.setFont(new Font("Arial", Font.BOLD, 13));
		lblGiamGia.setBounds(10, 521, 66, 20);
		pnHoaDon.add(lblGiamGia);

		JLabel lblGiamGiaText = new JLabel("%");
		lblGiamGiaText.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGiamGiaText.setBounds(124, 521, 110, 20);
		pnHoaDon.add(lblGiamGiaText);

		JButton btnHuy = new JButton(new ImageIcon(GUI_ThanhToan.class.getResource("/icon/close.png")));
		btnHuy.setText("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnHuy.setFont(new Font("Arial", Font.BOLD, 13));
		btnHuy.setBounds(327, 585, 78, 30);
		pnHoaDon.add(btnHuy);

		JLabel lblMaHdText = new JLabel("");
		lblMaHdText.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMaHdText.setBounds(96, 10, 120, 20);
		pnHoaDon.add(lblMaHdText);
		lblMaHdText.setText(maHD);

		JLabel lblNvText = new JLabel("");
		lblNvText.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNvText.setBounds(341, 10, 138, 20);
		pnHoaDon.add(lblNvText);
		lblNvText.setText(tttk.getHo() + " " + tttk.getTen());

		lblNgayGioHienTai = new JLabel();
		lblNgayGioHienTai.setText(dateString);
		lblNgayGioHienTai.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNgayGioHienTai.setBounds(167, 70, 191, 20);
		pnHoaDon.add(lblNgayGioHienTai);

		btnThanhToan.setFocusPainted(false);
		btnHuy.setFocusPainted(false);
		btnThanhToan.setBackground(Color.WHITE);
		btnHuy.setBackground(Color.WHITE);
		btnHuy.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		btnThanhToan.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

		JLabel lblTienDua = new JLabel("Tiền khách đưa:");
		lblTienDua.setFont(new Font("Arial", Font.BOLD, 13));
		lblTienDua.setBounds(235, 491, 110, 20);
		pnHoaDon.add(lblTienDua);

		txtTienDua = new JTextField();
		txtTienDua.setFont(new Font("Arial", Font.BOLD, 13));
		txtTienDua.setBounds(341, 491, 110, 19);
		pnHoaDon.add(txtTienDua);
		txtTienDua.setColumns(10);
		txtTienDua.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));

		JLabel lblTienTra = new JLabel("Tiền trả lại:");
		lblTienTra.setFont(new Font("Arial", Font.BOLD, 13));
		lblTienTra.setBounds(235, 521, 100, 20);
		pnHoaDon.add(lblTienTra);

		JLabel lblTienTraLaiText = new JLabel("");
		lblTienTraLaiText.setForeground(Color.RED);
		lblTienTraLaiText.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblTienTraLaiText.setBounds(341, 517, 138, 25);
		pnHoaDon.add(lblTienTraLaiText);
		btnThanhToan.setOpaque(false);
		btnHuy.setOpaque(false);

		JLabel lblGioVao = new JLabel("Giờ vào: ");
		lblGioVao.setFont(new Font("Arial", Font.BOLD, 13));
		lblGioVao.setBounds(10, 40, 78, 20);
		pnHoaDon.add(lblGioVao);

		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = new Date();
		dt = simple.parse(hd.getThoiDiemSD());
		lblGioVaoText = new JLabel();
		lblGioVaoText.setText(df.format(dt));
		lblGioVaoText.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGioVaoText.setBounds(73, 40, 191, 20);
		pnHoaDon.add(lblGioVaoText);
		formatter = new DecimalFormat("###,###,###");
		lblTongThanhToanText.setText(formatter.format(tongThanhToan) + " VNĐ");
		thanhToan = tongThanhToan;
		spGiamGia = new JSpinner();
		spGiamGia.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		spGiamGia.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		spGiamGia.setBounds(73, 521, 41, 20);
		pnHoaDon.add(spGiamGia);
		if (khachHang.getBacThanhVien().equalsIgnoreCase("Đồng")) {
			spGiamGia.setValue(0);
		} else if (khachHang.getBacThanhVien().equalsIgnoreCase("Bạc")) {
			spGiamGia.setValue(5);
		} else if (khachHang.getBacThanhVien().equalsIgnoreCase("Vàng")) {
			spGiamGia.setValue(10);
		} else if (khachHang.getBacThanhVien().equalsIgnoreCase("Kim Cương")) {
			spGiamGia.setValue(15);
		}
		Double tien = Double.parseDouble(spGiamGia.getValue() + "") / 100;
		thanhToan = tongThanhToan - tongThanhToan * tien;
		lblTongThanhToanText.setText(formatter.format(thanhToan) + " VNĐ");
		lblGiamGiaText.setText("% = -" + formatter.format(tongThanhToan * tien) + " VNĐ");
		giamGiaStr = spGiamGia.getValue() + " %";
		spGiamGia.setEnabled(false);

		JLabel lblTongHoaDonText = new JLabel("0 VNĐ");
		lblTongHoaDonText.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTongHoaDonText.setBounds(124, 491, 100, 20);
		pnHoaDon.add(lblTongHoaDonText);
		lblTongHoaDonText.setText(formatter.format(tongThanhToan) + " VNĐ");

		JLabel lblTongHoaDon = new JLabel("Tổng hóa đơn: ");
		lblTongHoaDon.setFont(new Font("Arial", Font.BOLD, 13));
		lblTongHoaDon.setBounds(10, 491, 110, 20);
		pnHoaDon.add(lblTongHoaDon);

		JLabel lblKhachHang = new JLabel("Khách hàng:");
		lblKhachHang.setFont(new Font("Arial", Font.BOLD, 13));
		lblKhachHang.setBounds(211, 40, 134, 20);
		pnHoaDon.add(lblKhachHang);

		JLabel lblKhachHangtext = new JLabel(khachHang.getHoKH() + " " + khachHang.getTenKH());
		lblKhachHangtext.setFont(new Font("Arial", Font.PLAIN, 13));
		lblKhachHangtext.setBounds(298, 40, 181, 20);
		pnHoaDon.add(lblKhachHangtext);

		txtTienDua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtTienDua.getText().length() >= 1) {
					n = Integer.valueOf(txtTienDua.getText());
					tongThanhToan = thanhToan;
					tongThanhToanStr = tongThanhToan + " VNĐ";
					if (n >= tongThanhToan) {
						lblTienTraLaiText.setText(formatter.format(n - tongThanhToan) + " VNĐ");
						lblTienTraLaiText.setForeground(Color.BLACK);
						tienKhachDuaStr = n + " VNĐ";
						tienTraLaiStr = lblTienTraLaiText.getText();
					} else {
						lblTienTraLaiText.setText("Chưa đủ");
						lblTienTraLaiText.setForeground(Color.red);

					}
				} else {
					lblTienTraLaiText.setText("");

				}
			}
		});
		btnThanhToan.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (n == null) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền khách đưa");
				} else if (n >= tongThanhToan) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
						if (new HoaDon_dao().updateHoaDon(hd, Double.parseDouble(tongThanhToan + ""),
								sdf.format(date))) {
							JOptionPane.showMessageDialog(null, "Thanh toán thành công");
							if (JOptionPane.showConfirmDialog(null, "Bạn có muốn in hóa đơn" + maHD, "In hóa đơn",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								tongHoaDonStr = lblTongHoaDonText.getText();
								String gioVaoStr = lblGioVaoText.getText();
								String gioRaStr = lblNgayGioHienTai.getText();
								tongThanhToanStr = lblTongThanhToanText.getText();
								giamGiaStr = spGiamGia.getValue() + " %";
								new export.epHoaDon().exportHoaDon(maHD, tttk.getHo() + " " + tttk.getTen(), gioVaoStr,
										khachHang.getHoKH() + " " + khachHang.getTenKH(), gioRaStr, listEx,
										tongHoaDonStr, tienKhachDuaStr, giamGiaStr, tienTraLaiStr, tongThanhToanStr);
								JOptionPane.showMessageDialog(null, "Xuất thành công!\n'D:\\" + maHD + ".pdf' ");
							}
							new ThongTinSuDungPhong_dao().deleteTTSDP(p.getMaPhong());
							p.setTrangThai(false);
							new Phong_dao().updatePhong(p);
							GUI_ThuePhong.loadDanhSachPhongDangSuDung();
							setVisible(false);
						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Số tiền không đủ");
					txtTienDua.requestFocus();
				}
			}
		});
	}

	public static void addListEx(SanPhamExportHoaDon spex) {
		listEx.add(spex);
	}
}
