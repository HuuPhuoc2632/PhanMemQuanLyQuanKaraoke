//Huỳnh Hữu Phước
package GUI;

import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.NhanSu_dao;
import entity.CaTruc;
import entity.NhanSu;
import entity.TaiKhoan;
import entity.ThongTinTaiKhoan;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GUI_CaNhan extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ThongTinTaiKhoan tttk;
	public TaiKhoan tk;
	private JPanel pnCaNhan;
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JDateChooser txtNgaySinh;
	private JTextField txtSđt;
	private JTextField txtDc;
	private JTable tblDsCa;
	private JScrollPane scrDSCa;
	private ButtonGroup btnGroup;
	public NhanSu nhanSu;
	private JRadioButton rdbtnNam;
	private JRadioButton rdbtnNu;
	private ImageIcon img;
	private GUI_ThayDoiMatKhau doiMK;
	private JPanel pnTacVu;
	private JButton btnDoiMK;
	private JButton btnDangXuat;
	private DefaultTableModel dtmDSCT;

	public GUI_CaNhan(ThongTinTaiKhoan tk, TaiKhoan tkhoan) {
		this.tttk = tk;
		this.tk = tkhoan;
		doiMK = new GUI_ThayDoiMatKhau(tttk, this.tk);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1118, 646);
		pnCaNhan = new JPanel();
		pnCaNhan.setBackground(new Color(255, 255, 240));
		pnCaNhan.setBounds(new Rectangle(0, 0, 880, 600));
		pnCaNhan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnCaNhan);
		pnCaNhan.setLayout(null);
		if (tttk.getGioiTinh()) {
			img = new ImageIcon(GUI_CaNhan.class.getResource("/icon/user.png"));
		} else {
			img = new ImageIcon(GUI_CaNhan.class.getResource("/icon/female-student.png"));
		}

		Image image = img.getImage();
		Image newimg = image.getScaledInstance(150, 200, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg);
		JLabel lblAvt = new JLabel(img);
		lblAvt.setBackground(new Color(176, 224, 230));
		lblAvt.setBounds(20, 17, 155, 205);
		pnCaNhan.add(lblAvt);
		lblAvt.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));

		JLabel lblTitle = new JLabel("THÔNG TIN CÁ NHÂN");
		lblTitle.setFont(new Font("Constantia", Font.BOLD, 22));
		lblTitle.setBounds(408, 11, 250, 53);
		pnCaNhan.add(lblTitle);

		JLabel lblNewLabel = new JLabel("Mã nhân viên:");
		lblNewLabel.setFont(new Font("Constantia", Font.BOLD, 15));
		lblNewLabel.setBounds(200, 73, 111, 24);
		pnCaNhan.add(lblNewLabel);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtMaNV.setBounds(321, 74, 238, 20);
		pnCaNhan.add(txtMaNV);
		txtMaNV.setColumns(10);

		JLabel lblHoTen = new JLabel("Họ và tên: ");
		lblHoTen.setFont(new Font("Constantia", Font.BOLD, 15));
		lblHoTen.setBounds(200, 111, 100, 24);
		pnCaNhan.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtHoTen.setBounds(321, 112, 238, 20);
		pnCaNhan.add(txtHoTen);
		txtHoTen.setColumns(10);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Constantia", Font.BOLD, 15));
		lblNgaySinh.setBounds(588, 111, 100, 24);
		pnCaNhan.add(lblNgaySinh);

		txtNgaySinh = new JDateChooser();
		txtNgaySinh.setBounds(672, 111, 203, 20);
		txtNgaySinh.setDateFormatString("dd-MM-yyyy");
		pnCaNhan.add(txtNgaySinh);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Constantia", Font.BOLD, 15));
		lblGioiTinh.setBounds(588, 73, 78, 24);
		pnCaNhan.add(lblGioiTinh);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setSelected(true);
		rdbtnNam.setBackground(new Color(255, 255, 240));
		rdbtnNam.setFont(new Font("Constantia", Font.BOLD, 15));
		rdbtnNam.setBounds(672, 75, 65, 21);
		pnCaNhan.add(rdbtnNam);

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBackground(new Color(255, 255, 240));
		rdbtnNu.setFont(new Font("Constantia", Font.BOLD, 15));
		rdbtnNu.setBounds(749, 75, 65, 21);
		pnCaNhan.add(rdbtnNu);

		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnNu);
		btnGroup.add(rdbtnNam);

		JLabel lblSdt = new JLabel("Số điện thoại: ");
		lblSdt.setFont(new Font("Constantia", Font.BOLD, 15));
		lblSdt.setBounds(200, 145, 111, 24);
		pnCaNhan.add(lblSdt);

		txtSđt = new JTextField();
		txtSđt.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtSđt.setBounds(321, 146, 238, 20);
		pnCaNhan.add(txtSđt);
		txtSđt.setColumns(10);

		JLabel lblDc = new JLabel("Địa chỉ:");
		lblDc.setFont(new Font("Constantia", Font.BOLD, 15));
		lblDc.setBounds(200, 179, 100, 24);
		pnCaNhan.add(lblDc);

		txtDc = new JTextField();
		txtDc.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtDc.setBounds(321, 180, 552, 20);
		pnCaNhan.add(txtDc);
		txtDc.setColumns(10);

		JPanel pnDSCA = new JPanel();
		pnDSCA.setBounds(0, 232, 872, 370);
		pnCaNhan.add(pnDSCA);
		pnDSCA.setLayout(null);
		pnDSCA.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách ca trực trong tuần"));

		tblDsCa = new JTable(dtmDSCT = new DefaultTableModel(new String[] { "Mã ca", "Ca trực", "Thời gian" }, 0)) {
			/*** 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblDsCa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsCa.getTableHeader().setBackground(Color.CYAN);
		scrDSCa = new JScrollPane(tblDsCa);
		scrDSCa.setBounds(7, 15, 858, 345);
		pnDSCA.setBackground(Color.WHITE);
		pnDSCA.add(scrDSCa);
		loadDataToTextField(tttk);
		tblDsCa.getTableHeader().setForeground(Color.BLUE);
		tblDsCa.getTableHeader().setFont(new Font("Constantia", Font.BOLD, 15));
		txtMaNV.setOpaque(false);
		txtMaNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtHoTen.setOpaque(false);
		txtHoTen.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtSđt.setOpaque(false);
		txtSđt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtDc.setOpaque(false);
		txtDc.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));

		pnTacVu = new JPanel();
		pnTacVu.setLayout(null);
		pnTacVu.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn tác vụ"));
		pnTacVu.setBackground(Color.WHITE);
		pnTacVu.setBounds(878, 0, 203, 602);
		pnCaNhan.add(pnTacVu);

		btnDoiMK = new JButton("Đổi mật khẩu", new ImageIcon(GUI_CaNhan.class.getResource("/icon/changepass.png")));
		btnDoiMK.setFont(new Font("Constantia", Font.BOLD, 14));
		btnDoiMK.setFocusPainted(false);
		btnDoiMK.setContentAreaFilled(false);
		btnDoiMK.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
		btnDoiMK.setBounds(10, 20, 180, 35);
		pnTacVu.add(btnDoiMK);

		btnDangXuat = new JButton("Đăng xuất", new ImageIcon(GUI_CaNhan.class.getResource("/icon/logOut.png")));
		btnDangXuat.setFont(new Font("Constantia", Font.BOLD, 14));
		btnDangXuat.setFocusPainted(false);
		btnDangXuat.setContentAreaFilled(false);
		btnDangXuat.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		btnDangXuat.setBounds(10, 65, 180, 35);
		pnTacVu.add(btnDangXuat);
		btnDoiMK.addActionListener(this);
		btnDangXuat.addActionListener(this);
		loaddb();

	}

	/**
	 * Create the frame.
	 */
	public Component tabCaNhan() {

		return pnCaNhan;
	}

	public void loadDataToTextField(ThongTinTaiKhoan tk) {
		txtMaNV.setText(tttk.getMa());
		String hoTen = tttk.getHo() + " " + tttk.getTen();
		txtHoTen.setText(hoTen);
		txtSđt.setText(tttk.getSDT());
		txtDc.setText(tttk.getDiaChi());
		txtNgaySinh.setDate(tttk.getNgaySinh());
		if (tttk.getGioiTinh()) {
			rdbtnNam.setSelected(true);
		} else {
			rdbtnNu.setSelected(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDangXuat)) {
			int logOutoption = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát?", "Thông báo",
					JOptionPane.YES_OPTION);
			if (logOutoption == JOptionPane.YES_OPTION) {
				GUI_DangNhap.logOut();
				new GUI_DangNhap().setVisible(true);
			}
		} else if (o.equals(btnDoiMK)) {
			doiMK.setVisible(true);
		}
	}

	public void addRowCT(CaTruc ct) {
		String[] a = { ct.getMaCaTruc(), ct.getCa(), ct.getThoiGian() };
		dtmDSCT.addRow(a);
	}

	public void loaddb() {
		List<CaTruc> dsct;
		String ma;
		ma = txtMaNV.getText();
		if(ma.contains("QL")==true) {
			dsct =  new NhanSu_dao().getDanhSachQuanLyCT(ma);
			for(CaTruc ct : dsct) {
				addRowCT(ct);
			}
		}else {
			dsct = new NhanSu_dao().getDanhSachNhanVienCT(ma);
			for(CaTruc ct : dsct) {
				addRowCT(ct);
			}
		}
	}
}
