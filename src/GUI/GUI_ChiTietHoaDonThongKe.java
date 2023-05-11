package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import dao.HoaDon_SanPhamDV_dao;
import dao.NhanSu_dao;
import dao.QuanLy_dao;
import dao.SanPhamDV_dao;
import dao.ThongTinChuyenPhong_dao;
import entity.HoaDon;
import entity.HoaDon_SanPhamDV;
import entity.NhanSu;
import entity.Phong;
import entity.QuanLy;
import entity.SanPhamDV;
import entity.ThongTinChuyenPhong;

import javax.swing.BoxLayout;

public class GUI_ChiTietHoaDonThongKe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Date dt;
	private String sl;
	private double tongThanhToan = 0;
	private double gioVao;
	private double gioRa;
	private double soLuong;
	private Date dtt;
	private DecimalFormat formatter;

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 * @throws SQLException
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	public GUI_ChiTietHoaDonThongKe(Phong p, HoaDon hd) throws ParseException, SQLException {
		setSize(498, 633);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("HÓA ĐƠN THANH TOÁN");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setBounds(0, 0, 489, 65);
		contentPane.add(lblTitle);

		JLabel lblMaHd = new JLabel("Mã hóa đơn: ");
		lblMaHd.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaHd.setBounds(10, 75, 94, 20);
		contentPane.add(lblMaHd);

		JLabel lblMaHdText = new JLabel("<dynamic>");
		lblMaHdText.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMaHdText.setBounds(96, 75, 120, 20);
		contentPane.add(lblMaHdText);
		lblMaHdText.setText(hd.getMaHD());

		JLabel lblNV = new JLabel("Nhân viên thu ngân:");
		lblNV.setFont(new Font("Arial", Font.BOLD, 13));
		lblNV.setBounds(201, 75, 134, 20);
		contentPane.add(lblNV);

		JLabel lblNvText = new JLabel("<dynamic> <dynamic>");
		lblNvText.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNvText.setBounds(341, 75, 138, 20);
		contentPane.add(lblNvText);
		String thuNgan;
		if (hd.getMaNV() == null) {
			QuanLy ql = new QuanLy_dao().getQL(hd.getMaQL());
			thuNgan = ql.getHoQL() + " " + ql.getTenQL();
		} else {
			NhanSu ns = new NhanSu_dao().getNS(hd.getMaNV());
			thuNgan = ns.getHoNV() + " " + ns.getTenNV();
		}
		lblNvText.setText(thuNgan);

		JLabel lblGioVao = new JLabel("Giờ vào: ");
		lblGioVao.setFont(new Font("Arial", Font.BOLD, 13));
		lblGioVao.setBounds(10, 105, 78, 20);
		contentPane.add(lblGioVao);

		JLabel lblGioVaoText = new JLabel();
		lblGioVaoText.setText((String) null);
		lblGioVaoText.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGioVaoText.setBounds(89, 105, 191, 20);
		contentPane.add(lblGioVaoText);
		lblGioVaoText.setText(hd.getThoiDiemSD());

		JLabel lblThoiDiem = new JLabel("Thời điểm thanh toán:");
		lblThoiDiem.setFont(new Font("Arial", Font.BOLD, 13));
		lblThoiDiem.setBounds(10, 135, 147, 20);
		contentPane.add(lblThoiDiem);

		JLabel lblNgayGioHienTai = new JLabel();
		lblNgayGioHienTai.setText("12:44 PM - 01/12/2022");
		lblNgayGioHienTai.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNgayGioHienTai.setBounds(167, 135, 191, 20);
		contentPane.add(lblNgayGioHienTai);
		lblNgayGioHienTai.setText(hd.getThoiDiemTT());

		JLabel lblDanhMuc = new JLabel("Danh mục");
		lblDanhMuc.setFont(new Font("Arial", Font.BOLD, 13));
		lblDanhMuc.setBounds(10, 166, 78, 20);
		contentPane.add(lblDanhMuc);

		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Arial", Font.BOLD, 13));
		lblSoLuong.setBounds(214, 166, 66, 20);
		contentPane.add(lblSoLuong);

		JLabel lblThanhTien = new JLabel("Thành tiền");
		lblThanhTien.setFont(new Font("Arial", Font.BOLD, 13));
		lblThanhTien.setBounds(375, 166, 72, 20);
		contentPane.add(lblThanhTien);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 202, 489, 351);
		contentPane.add(scrollPane);

		JPanel pnBang = new JPanel();
		pnBang.setBackground(Color.WHITE);
		scrollPane.setViewportView(pnBang);
		pnBang.setLayout(new BoxLayout(pnBang, BoxLayout.Y_AXIS));

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm aa - dd/MM/yyyy");
		String dateString = df.format(date);

		JLabel lblTongThanhToan = new JLabel("Tổng thanh toán: ");
		lblTongThanhToan.setFont(new Font("Arial", Font.BOLD, 13));
		lblTongThanhToan.setBounds(10, 566, 120, 20);
		contentPane.add(lblTongThanhToan);

		JLabel lblTongThanhToanText = new JLabel("0 VNĐ");
		lblTongThanhToanText.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTongThanhToanText.setBounds(140, 566, 100, 20);
		contentPane.add(lblTongThanhToanText);
		

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		dt = sdf.parse(hd.getThoiDiemSD());
		for (ThongTinChuyenPhong ttcp : new ThongTinChuyenPhong_dao().getThongTinChuyenPhongTheoMaHD(hd.getMaHD())) {
			if (ttcp != null) {
				pnBang.add(new GUI_GioHatTrongHoaDon(p, sl, ttcp).gioHat());
				tongThanhToan += ttcp.getTienGioPhongCu();
				dt = ttcp.getGioVaoMoi();
			} else {
				dt = sdf.parse(hd.getThoiDiemSD());
			}
		}
		dtt = sdf.parse(hd.getThoiDiemTT());
		gioVao = dt.getHours() + Double.parseDouble((Double.parseDouble(dt.getMinutes() + "") / 60 + ""));
		gioRa = dtt.getHours() + Double.parseDouble((Double.parseDouble(dtt.getMinutes() + "") / 60 + ""));
		soLuong = gioRa - gioVao;
		double s = (double) Math.round(soLuong * 100) / 100;
		sl = s + "";
		pnBang.add(new GUI_GioHatTrongHoaDon(p, sl, null).gioHat());
		tongThanhToan += (double) (p.getDonGia() * s);
		List<HoaDon_SanPhamDV> ls = new HoaDon_SanPhamDV_dao().getDanhSachTheoMaHD(hd.getMaHD());
		for (HoaDon_SanPhamDV hoaDon_SanPhamDV : ls) {
			SanPhamDV sp = new SanPhamDV_dao().getSanPhamTheoMa(hoaDon_SanPhamDV.getMaDV());
			pnBang.add(new GUI_DanhMucTrongHoaDon(sp, hoaDon_SanPhamDV.getSoLuong()).DanhMuc());
			tongThanhToan += (sp.getDonGia() * hoaDon_SanPhamDV.getSoLuong());
		}
		for (int i = 1; i < 10; i++) {
			pnBang.add(new frmDongNull().dongNull());
			i++;
		}
		formatter = new DecimalFormat("###,###,###");
		lblTongThanhToanText
				.setText(formatter.format(tongThanhToan)+" VNĐ");
	}
}
