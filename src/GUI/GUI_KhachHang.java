package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.KhachHang_dao;
import entity.KhachHang;

public class GUI_KhachHang extends JFrame implements MouseListener, ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaKH;
	private JTextField txtHo;
	private JTextField txtSdt;
	private JTextField txtTim;
	private ButtonGroup btnGroup;
	private JTextField txtTimKiem;
	private JTable tblDanhSachKhachHang;
	private DefaultTableModel dtmDSKH;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnTim;
	private JButton btnXoaTrang;
	private JTextField txtTen;
	private JDateChooser txtNgaySinh;
	private JRadioButton rdbtnNam;
	private JRadioButton rdbtnNu;
	private KhachHang_dao KhachHang_dao = new KhachHang_dao();
	private JButton btnLuu;
	private JComboBox<String> jcboTimKH;
	private JComboBox<String> cboSapXep;
	private boolean gt;
	private String parent;
	private int n = 0;

	/**
	 * Create the frame.
	 */
	public GUI_KhachHang(String parent) {
		this.parent = parent;
		// Đã có thay đổi LƯU Ý
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 610);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnThongTin = new JPanel();
		pnThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông Tin khách hàng"));
		pnThongTin.setBackground(Color.WHITE);
		pnThongTin.setBounds(0, 0, 881, 108);
		contentPane.add(pnThongTin);
		pnThongTin.setLayout(null);

		JLabel lblMaNV = new JLabel("Mã KH: ");
		lblMaNV.setFont(new Font("Constantia", Font.BOLD, 13));
		lblMaNV.setBounds(10, 22, 55, 20);
		pnThongTin.add(lblMaNV);

		txtMaKH = new JTextField();
		txtMaKH.setOpaque(false);
		txtMaKH.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMaKH.setColumns(10);
		txtMaKH.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtMaKH.setBounds(63, 21, 66, 19);
		pnThongTin.add(txtMaKH);

		JLabel lblHo = new JLabel("Họ:");
		lblHo.setFont(new Font("Constantia", Font.BOLD, 13));
		lblHo.setBounds(156, 22, 44, 20);
		pnThongTin.add(lblHo);

		txtHo = new JTextField();
		txtHo.setOpaque(false);
		txtHo.setFont(new Font("Constantia", Font.BOLD, 13));
		txtHo.setColumns(10);
		txtHo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtHo.setBounds(210, 24, 148, 19);
		pnThongTin.add(txtHo);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Constantia", Font.BOLD, 13));
		lblGioiTinh.setBounds(10, 57, 66, 20);
		pnThongTin.add(lblGioiTinh);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setSelected(true);
		rdbtnNam.setFont(new Font("Constantia", Font.BOLD, 13));
		rdbtnNam.setBackground(Color.WHITE);
		rdbtnNam.setBounds(82, 56, 54, 21);
		pnThongTin.add(rdbtnNam);

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setFont(new Font("Constantia", Font.BOLD, 13));
		rdbtnNu.setBackground(Color.WHITE);
		rdbtnNu.setBounds(140, 56, 55, 21);
		pnThongTin.add(rdbtnNu);
		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnNu);
		btnGroup.add(rdbtnNam);

		JLabel lblSdt = new JLabel("SĐT:");
		lblSdt.setFont(new Font("Constantia", Font.BOLD, 13));
		lblSdt.setBounds(222, 57, 31, 20);
		pnThongTin.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setOpaque(false);
		txtSdt.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtSdt.setColumns(10);
		txtSdt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtSdt.setBounds(276, 56, 117, 19);
		pnThongTin.add(txtSdt);

		btnLuu = new JButton("Lưu", new ImageIcon(GUI_KhachHang.class.getResource("/icon/save.png")));
		btnLuu.setFont(new Font("Constantia", Font.BOLD, 13));
		btnLuu.setEnabled(false);
		btnLuu.setBounds(672, 22, 166, 62);
		pnThongTin.add(btnLuu);
		btnLuu.setContentAreaFilled(false);
		btnLuu.setFocusPainted(false);
		btnLuu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
		btnLuu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLuu.setForeground(Color.BLUE);
				btnLuu.setFont(new Font("Constantia", Font.BOLD, 17));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLuu.setForeground(Color.BLACK);
				btnLuu.setFont(new Font("Constantia", Font.BOLD, 13));
			}
		});
		

		JLabel lblTen = new JLabel("Tên");
		lblTen.setFont(new Font("Constantia", Font.BOLD, 13));
		lblTen.setBounds(398, 22, 44, 20);
		pnThongTin.add(lblTen);

		txtTen = new JTextField();
		txtTen.setOpaque(false);
		txtTen.setFont(new Font("Constantia", Font.BOLD, 13));
		txtTen.setColumns(10);
		txtTen.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtTen.setBounds(452, 24, 148, 19);
		pnThongTin.add(txtTen);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Constantia", Font.BOLD, 13));
		lblNgaySinh.setBounds(403, 59, 66, 20);
		pnThongTin.add(lblNgaySinh);

		txtNgaySinh = new JDateChooser();
		txtNgaySinh.setOpaque(false);
		txtNgaySinh.setEnabled(true);
		txtNgaySinh.setDateFormatString("dd-MM-yyyy");
		txtNgaySinh.setBackground(Color.WHITE);
		txtNgaySinh.setBounds(468, 57, 164, 20);
		pnThongTin.add(txtNgaySinh);

		JPanel pnThaoTac = new JPanel();
		pnThaoTac.setLayout(null);
		pnThaoTac.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn tác vụ"));
		pnThaoTac.setBackground(Color.WHITE);
		pnThaoTac.setBounds(879, 0, 197, 215);
		contentPane.add(pnThaoTac);

		btnThem = new JButton("Thêm khách hàng", new ImageIcon(GUI_NhanSu.class.getResource("/icon/add.png")));
		btnThem.setBounds(10, 13, 177, 33);
		pnThaoTac.add(btnThem);
		btnThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnThem.getText() != "Hủy") {
					btnThem.setFont(new Font("Constantia", Font.BOLD, 17));
					btnThem.setForeground(Color.GREEN);
				} else {
					btnThem.setFont(new Font("Constantia", Font.BOLD, 17));
					btnThem.setForeground(Color.RED);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnThem.setFont(new Font("Constantia", Font.BOLD, 14));
				btnThem.setForeground(Color.BLACK);
			}
		});
		btnThem.setToolTipText("");
		btnThem.setFont(new Font("Constantia", Font.BOLD, 14));
		btnThem.setContentAreaFilled(false);
		btnThem.setFocusPainted(false);
		btnThem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
		pnThaoTac.add(btnThem);

		btnXoa = new JButton("Xóa khách hàng", new ImageIcon(GUI_NhanSu.class.getResource("/icon/delete.png")));
		btnXoa.setBounds(10, 66, 177, 33);
		pnThaoTac.add(btnXoa);
		btnXoa.setFont(new Font("Constantia", Font.BOLD, 14));
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnXoa.setFont(new Font("Constantia", Font.BOLD, 17));
				btnXoa.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnXoa.setFont(new Font("Constantia", Font.BOLD, 14));
				btnXoa.setForeground(Color.BLACK);
			}
		});
		btnXoa.setContentAreaFilled(false);
		btnXoa.setFocusPainted(false);
		btnXoa.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.red));
		pnThaoTac.add(btnXoa);

		btnSua = new JButton("Cập nhật", new ImageIcon(GUI_NhanSu.class.getResource("/icon/update.png")));
		btnSua.setBounds(10, 119, 177, 33);
		pnThaoTac.add(btnSua);
		btnSua.setFont(new Font("Constantia", Font.BOLD, 14));
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSua.setFont(new Font("Constantia", Font.BOLD, 17));
				btnSua.setForeground(new Color(65, 105, 225));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSua.setFont(new Font("Constantia", Font.BOLD, 14));
				btnSua.setForeground(Color.BLACK);
			}
		});
		btnSua.setContentAreaFilled(false);
		btnSua.setFocusPainted(false);
		btnSua.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(65, 105, 225)));
		pnThaoTac.add(btnSua);

		btnXoaTrang = new JButton("Xóa trắng", new ImageIcon(GUI_NhanSu.class.getResource("/icon/deleteText.png")));
		btnXoaTrang.setBounds(10, 172, 177, 33);
		pnThaoTac.add(btnXoaTrang);
		btnXoaTrang.setFont(new Font("Constantia", Font.BOLD, 14));
		btnXoaTrang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnXoaTrang.setFont(new Font("Constantia", Font.BOLD, 17));
				btnXoaTrang.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnXoaTrang.setFont(new Font("Constantia", Font.BOLD, 14));
				btnXoaTrang.setForeground(Color.BLACK);
			}
		});
		btnXoaTrang.setContentAreaFilled(false);
		btnXoaTrang.setFocusPainted(false);
		btnXoaTrang.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.YELLOW));
		pnThaoTac.add(btnXoaTrang);

		JPanel pnSapXep = new JPanel();
		pnSapXep.setBackground(Color.WHITE);
		pnSapXep.setBounds(0, 107, 881, 108);
		contentPane.add(pnSapXep);
		pnSapXep.setLayout(null);
		pnSapXep.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Tìm kiếm và sắp xếp"));

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBackground(Color.WHITE);
		pnTimKiem.setLayout(null);
		pnTimKiem.setBounds(10, 36, 861, 40);
		pnSapXep.add(pnTimKiem);

		JLabel lblTim = new JLabel("Tìm theo:");
		lblTim.setFont(new Font("Constantia", Font.BOLD, 14));
		lblTim.setBounds(37, 11, 68, 23);
		pnTimKiem.add(lblTim);

		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(184, 11, 206, 21);
		pnTimKiem.add(txtTimKiem);

		txtTim = new JTextField();

		// Văn Quang Phong
		txtTim.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if (jcboTimKH.getSelectedIndex() == 0)
					dtmDSKH.fireTableDataChanged();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if (jcboTimKH.getSelectedIndex() == 0)
					dtmDSKH.fireTableDataChanged();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if (jcboTimKH.getSelectedIndex() == 0)
					dtmDSKH.fireTableDataChanged();
			}
		});
		btnTim = new JButton(new ImageIcon(GUI_KhachHang.class.getResource("/icon/search_gradient.png")));
		btnTim.setFocusPainted(false);
		btnTim.setContentAreaFilled(false);
		btnTim.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
		btnTim.setBounds(393, 5, 45, 32);
		pnTimKiem.add(btnTim);

		JLabel lblSapXep = new JLabel("Sắp xếp theo:");
		lblSapXep.setFont(new Font("Constantia", Font.BOLD, 14));
		lblSapXep.setBounds(524, 10, 106, 23);
		pnTimKiem.add(lblSapXep);

		cboSapXep = new JComboBox<String>();
		cboSapXep.setBackground(Color.WHITE);
		cboSapXep.setFont(new Font("Constantia", Font.PLAIN, 13));
		cboSapXep.setBounds(635, 11, 216, 21);
		cboSapXep.addItem("Theo mã khách hàng");
		cboSapXep.addItem("Theo tên khách hàng");
		cboSapXep.addItem("Theo tuổi khách hàng");
		pnTimKiem.add(cboSapXep);

		jcboTimKH = new JComboBox<String>();
		jcboTimKH.setBackground(Color.WHITE);
		jcboTimKH.setFont(new Font("Constantia", Font.BOLD, 13));
		jcboTimKH.setBounds(106, 10, 75, 21);
		pnTimKiem.add(jcboTimKH);
		jcboTimKH.addItem("Mã KH");
		jcboTimKH.addItem("Tên KH");

		JPanel pnDS = new JPanel();
		pnDS.setLayout(null);
		pnDS.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh Sách khách hàng"));
		pnDS.setBackground(Color.WHITE);
		pnDS.setBounds(0, 218, 1077, 385);
		contentPane.add(pnDS);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 1057, 354);
		pnDS.add(scrollPane);

		tblDanhSachKhachHang = new JTable(dtmDSKH = new DefaultTableModel(
				new String[] { "Mã KH", "Họ", "Tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Bậc" }, 0)) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblDanhSachKhachHang.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowSelected = tblDanhSachKhachHang.getSelectedRow();
				try {
					reviewRowSelected(rowSelected);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tblDanhSachKhachHang.setSelectionForeground(Color.RED);
				tblDanhSachKhachHang.setSelectionBackground(new Color(255, 255, 153));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		tblDanhSachKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDanhSachKhachHang.getTableHeader().setBackground(Color.CYAN);
		scrollPane.setViewportView(tblDanhSachKhachHang);
		tblDanhSachKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 14));
		loadDB();
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThem.addActionListener(this);
		txtMaKH.setEditable(false);
		btnTim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				if (jcboTimKH.getSelectedIndex() == 0) {
					String tim = txtTimKiem.getText();
					if (!tim.isEmpty()) {
						dtmDSKH.setRowCount(0);
						try {
							for (KhachHang k : KhachHang_dao.getKHTheoMa(tim)) {
								if (k.getMaKH().equalsIgnoreCase(tim)) {
									addRow(k);
								}
								if (tblDanhSachKhachHang.getRowCount() > 0)
									reviewRowSelected(0);
							}
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng cần tìm");
						dtmDSKH.setRowCount(0);
						loadDB();
					}
				} else if (jcboTimKH.getSelectedIndex() == 1) {
					String timTen = txtTimKiem.getText();
					if (!timTen.isEmpty()) {
						dtmDSKH.setRowCount(0);
						try {
							for (KhachHang k : KhachHang_dao.getKHTheoTen(timTen)) {
								addRow(k);
							}
							if (tblDanhSachKhachHang.getRowCount() > 0)
								reviewRowSelected(0);
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng cần tìm");
						dtmDSKH.setRowCount(0);
						loadDB();
					}
				}
			}
		});
		cboSapXep.addActionListener(this);
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tblDanhSachKhachHang.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa");
				else if (btnSua.getText() == "Cập nhật") {
					btnSua.setText("Hủy");
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
					txtMaKH.setEditable(false);
					MoKhoa(true);
				} else if (btnSua.getText() == "Hủy") {
					btnSua.setText("Cập nhật");
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					MoKhoa(false);
				}
			}
		});
		MoKhoa(false);
		if (parent != "main") {
			String ma = "KH";
			try {
				txtMaKH.setText(autoId(ma));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			MoKhoa(true);
			btnThem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.red));
			btnXoa.setEnabled(false);
			btnSua.setEnabled(false);
			btnThem.setText("Hủy");
		}
	}

	public Component tabKhachHang() {
		return contentPane;

	}

	public void addRow(KhachHang kh) {
		String gt;
		if (kh.isGioiTinh()) {
			gt = "Nam";

		} else {
			gt = "Nữ";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(kh.getNgaySinh());
		String[] a = { kh.getMaKH(), kh.getHoKH(), kh.getTenKH(), gt, strDate + "", kh.getSđt(), kh.getBacThanhVien() };
		dtmDSKH.addRow(a);
	}

	public String autoId(String ma) throws SQLException {
		int dem = new KhachHang_dao().getTongSoKH() + 1;
		String maSo = dem + "";
		String c;
		if (dem < 10) {
			c = "000";
		} else if (dem >= 10 && dem < 100) {
			c = "00";
		} else {
			c = "0";
		}
		String maKH = ma + c + maSo;
		List<KhachHang> dsKH;
		dsKH = KhachHang_dao.getDanhSachKhachHang();
		for (KhachHang khachhang : dsKH) {
			if (khachhang.getMaKH().equalsIgnoreCase(maKH)) {
				dem += 1;
			}
		}
		maSo = dem + "";
		return ma + c + maSo;
	}

//
	public void loadDB() {
		List<KhachHang> dskh;
		dskh = new KhachHang_dao().getDanhSachKhachHang();
		for (KhachHang kh : dskh) {
			addRow(kh);
		}
	}

	public void reviewRowSelected(int rowSelected) throws ParseException {
		txtMaKH.setText(tblDanhSachKhachHang.getValueAt(rowSelected, 0) + "");
		txtHo.setText(tblDanhSachKhachHang.getValueAt(rowSelected, 1) + "");
		txtTen.setText(tblDanhSachKhachHang.getValueAt(rowSelected, 2) + "");
		String gt = dtmDSKH.getValueAt(rowSelected, 3).toString();
		if (gt.equals("Nam")) {
			rdbtnNam.setSelected(true);
		} else {
			rdbtnNu.setSelected(true);
		}
		txtNgaySinh.setDate(new SimpleDateFormat("dd-MM-yyyy")
				.parse(tblDanhSachKhachHang.getModel().getValueAt(rowSelected, 4).toString()));
		txtSdt.setText(tblDanhSachKhachHang.getValueAt(rowSelected, 5) + "");
	}

	public boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher match = pattern.matcher(input);
		return match.matches();
	};

	public void MoKhoa(boolean b) {
		txtHo.setEditable(b);
		txtTen.setEditable(b);
		txtNgaySinh.setEnabled(b);
		txtSdt.setEditable(b);
		btnLuu.setEnabled(b);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();

		if (object.equals(btnXoaTrang)) {
			txtHo.setText("");
			txtMaKH.setText("");
			txtTen.setText("");
			txtSdt.setText("");
			txtNgaySinh.setDate(null);
			// txtNgaySinh.setDate(null);
		} else if (object.equals(btnXoa)) {
			if (tblDanhSachKhachHang.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Phải Chọn Khách hàng Cần Xóa Trước");
			} else {
				if (JOptionPane.showConfirmDialog(null,
						"Bạn chắc chắn muốn xóa khách hàng "
								+ tblDanhSachKhachHang.getValueAt(tblDanhSachKhachHang.getSelectedRow(), 0).toString()
								+ " Không ?",
						"Xóa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String ma = dtmDSKH.getValueAt(tblDanhSachKhachHang.getSelectedRow(), 0).toString();
					if (KhachHang_dao.deleteKH(ma)) {
						JOptionPane.showMessageDialog(null, "Khách hàng đã được xóa thành công.");
						XoaTrang();
						dtmDSKH.setRowCount(0);
						loadDB();
					} else {
						JOptionPane.showMessageDialog(null, "Xoá Khách Hàng Thất Bại.");
					}
				}
			}
		} else if (object.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm khách hàng")) {
				String ma = "KH";
				try {
					txtMaKH.setText(autoId(ma));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MoKhoa(true);
				btnThem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.red));
				btnXoa.setEnabled(false);
				btnSua.setEnabled(false);
				btnThem.setText("Hủy");
			} else {
				btnThem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
				btnXoa.setEnabled(true);
				btnSua.setEnabled(true);
				btnLuu.setEnabled(false);
				btnThem.setText("Thêm khách hàng");
				XoaTrang();
				// đây
				if (parent == "Thuê") {
					n = 0;
					GUI_ManHinhChinh.loadLaiManHinhDatThuePhong(n);
				} else if (parent == "Đặt") {
					n = 1;
					GUI_ManHinhChinh.loadLaiManHinhDatThuePhong(n);
				}
				// đây
			}
		} else if (object.equals(btnLuu)) {

			if (btnThem.getText() == "Hủy") {

				if (txtMaKH.getText().trim().isEmpty() || txtHo.getText().trim().isEmpty()
						|| txtTen.getText().trim().isEmpty() || txtNgaySinh.getDate().toString().trim().isEmpty()
						|| txtSdt.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
				} else if (!kiemTraNhap(txtSdt.getText(), "^([0]{1})[0-9]{8}([0-9]{1})$")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số điện thoại [0xxxxxxxxx]");
					// }else
					// if(!kiemTraNhap(txtHo.getText(),"^([A-Z]{1}[a-z]{0,5}[\s]{1}){1}([A-Z]{1}[a-z]{0,}[\s]{1}){0,4}([A-Z]{1}[a-z]{0,5}){1}$"
					// )) {
					// JOptionPane.showMessageDialog(null, "Viêt hoa chữ cái đầu");
					// }else if(!kiemTraNhap(txtTen.getText(), "^([A-Z]{1}[a-z]{0,5})$")) {
					// JOptionPane.showMessageDialog(null, "Viêt hoa chữ cái đầu");
				} else {
					if (rdbtnNam.isSelected()) {
						gt = true;
					} else {
						gt = false;
					}
					KhachHang kh = new KhachHang(txtMaKH.getText(), txtHo.getText(), txtTen.getText(), txtSdt.getText(),
							gt, txtNgaySinh.getDate(), "Đồng", true);
					if (KhachHang_dao.insertKh(kh)) {
						JOptionPane.showMessageDialog(null, "Thêm Khách hàng Thành Công.");
						dtmDSKH.setRowCount(0);
						loadDB();
						XoaTrang();
						MoKhoa(false);
						btnXoa.setEnabled(true);
						btnSua.setEnabled(true);
						btnThem.setText("Thêm khách hàng");
						XoaTrang();
						if (parent == "Thuê") {
							n = 0;
							GUI_ManHinhChinh.loadLaiManHinhDatThuePhong(n);
							GUI_ThuePhong.setTextChoKhachHang(kh.getSđt(),
									kh.getMaKH() + "-" + kh.getHoKH() + " " + kh.getTenKH());
							GUI_ThuePhong.maKH= kh.getMaKH();
						} else if (parent == "Đặt") {
							n = 1;
							GUI_ManHinhChinh.loadLaiManHinhDatThuePhong(n);
							GUI_DatPhong.setTextChoKhachHang(kh.getSđt(),
									kh.getMaKH() + "-" + kh.getHoKH() + " " + kh.getTenKH());
							GUI_DatPhong.maKH= kh.getMaKH();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Thêm Khách hàng không thành công!");
					}
				}
			} else if (btnSua.getText() == "Hủy") {
				if (rdbtnNam.isSelected()) {
					gt = true;
				} else {
					gt = false;
				}
				KhachHang kh = new KhachHang(txtMaKH.getText(), txtHo.getText(), txtTen.getText(), txtSdt.getText(), gt,
						txtNgaySinh.getDate(), "Đồng", true);
				String maKH = txtMaKH.getText();
				try {
					if (KhachHang_dao.updateKH(maKH, kh)) {
						int row = tblDanhSachKhachHang.getSelectedRow();
						updateRow(kh, row);
						btnSua.setText("Cập nhật");
						btnThem.setEnabled(true);
						btnXoa.setEnabled(true);
						MoKhoa(false);
						JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");
					} else
						JOptionPane.showMessageDialog(null, "Sửa thông tin không thành công");
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		} else if (object.equals(cboSapXep)) {
			int index = cboSapXep.getSelectedIndex();
			if (index == 0) {
				dtmDSKH.setRowCount(0);
				loadDB();
			} else if (index == 1) {
				dtmDSKH.setRowCount(0);
				for (KhachHang k : KhachHang_dao.getDSKHTheoTen()) {
					addRow(k);
				}
			} else if (index == 2) {
				dtmDSKH.setRowCount(0);
				for (KhachHang k : KhachHang_dao.getDanhHangTheoTuoi()) {
					addRow(k);
				}
			}
		}

	}

	public void XoaTrang() {
		txtHo.setText("");
		txtMaKH.setText("");
		txtTen.setText("");
		txtSdt.setText("");
		txtNgaySinh.setDate(null);
	}

	public void updateRow(KhachHang k, int row) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(k.getNgaySinh());
		if (rdbtnNam.isSelected()) {
			gt = true;
		} else {
			gt = false;
		}
		dtmDSKH.setValueAt(k.getHoKH(), row, 1);
		dtmDSKH.setValueAt(k.getTenKH(), row, 2);
		if (gt == true)
			dtmDSKH.setValueAt("Nam", row, 3);
		else
			dtmDSKH.setValueAt("Nữ", row, 3);
		dtmDSKH.setValueAt(strDate, row, 4);
		dtmDSKH.setValueAt(k.getSđt(), row, 5);
		dtmDSKH.setValueAt(k.getBacThanhVien(), row, 5);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}
