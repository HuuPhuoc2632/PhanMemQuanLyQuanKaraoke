//Huỳnh Hữu Phước
package GUI;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import entity.NhanSu;
import entity.TaiKhoan;
import entity.ThongTinTaiKhoan;
import dao.NhanSu_dao;
import dao.TaiKhoan_dao;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI_NhanSu extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnNhanSu;
	private JTextField txtMaNV;
	private JTextField txtHo;
	private JTextField txtTen;
	private ButtonGroup btnGroup;
	private JDateChooser txtNgaySinh;
	private JTextField txtSdt;
	private JTextField txtTim;
	private JScrollPane scrDSNV;
	private JTable tblDsNhanVien;
	private JComboBox<String> cboSapXep;
	private DefaultTableModel dtmDSNV;
	private JRadioButton rdbtnNu;
	private JRadioButton rdbtnNam;
	private JSpinner spTuoi;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
	private JButton btnTim;
	private NhanSu_dao NhanSu_dao = new NhanSu_dao();
	private JComboBox<String> jcboTimNV;
	private JButton btnLuu;
	private String chucVu;
	public ThongTinTaiKhoan tk;
	private JComboBox<String> cboChucVu;
	private JLabel lblSoNha;
	private JTextField txtSoNha;
	private JPanel pnThaoTac;
	private boolean gt;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public GUI_NhanSu(ThongTinTaiKhoan taiKhoan) throws SQLException {
		this.tk = taiKhoan;
		setBounds(new Rectangle(0, 0, 950, 610));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1091, 649);
		pnNhanSu = new JPanel();
		pnNhanSu.setBounds(new Rectangle(0, 0, 920, 600));
		pnNhanSu.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnNhanSu);
		pnNhanSu.setLayout(null);

		JPanel pnThongTin = new JPanel();
		pnThongTin.setBackground(Color.WHITE);
		pnThongTin.setBounds(0, 0, 880, 173);
		pnNhanSu.add(pnThongTin);
		pnThongTin.setLayout(null);
		pnThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông Tin Nhân Viên"));
		JLabel lblMaNV = new JLabel("Mã NV: ");
		lblMaNV.setFont(new Font("Constantia", Font.BOLD, 13));
		lblMaNV.setBounds(10, 15, 55, 20);
		pnThongTin.add(lblMaNV);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMaNV.setBounds(82, 15, 117, 19);
		pnThongTin.add(txtMaNV);
		txtMaNV.setEditable(false);
		txtMaNV.setColumns(10);

		JLabel lblHo = new JLabel("Họ:");
		lblHo.setFont(new Font("Constantia", Font.BOLD, 13));
		lblHo.setBounds(266, 15, 30, 20);
		pnThongTin.add(lblHo);

		txtHo = new JTextField();
		txtHo.setFont(new Font("Constantia", Font.BOLD, 13));
		txtHo.setBounds(298, 15, 155, 19);
		pnThongTin.add(txtHo);
		txtHo.setColumns(10);

		JLabel lblTen = new JLabel("Tên:");
		lblTen.setFont(new Font("Constantia", Font.BOLD, 13));
		lblTen.setBounds(463, 15, 36, 20);
		pnThongTin.add(lblTen);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Constantia", Font.BOLD, 13));
		txtTen.setBounds(500, 15, 133, 19);
		pnThongTin.add(txtTen);
		txtTen.setColumns(10);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Constantia", Font.BOLD, 13));
		lblGioiTinh.setBounds(659, 19, 66, 20);
		pnThongTin.add(lblGioiTinh);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBackground(Color.WHITE);
		rdbtnNam.setFont(new Font("Constantia", Font.BOLD, 13));
		rdbtnNam.setSelected(true);
		rdbtnNam.setBounds(731, 15, 54, 21);
		pnThongTin.add(rdbtnNam);

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBackground(Color.WHITE);
		rdbtnNu.setFont(new Font("Constantia", Font.BOLD, 13));
		rdbtnNu.setBounds(789, 15, 55, 21);
		pnThongTin.add(rdbtnNu);

		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnNu);
		btnGroup.add(rdbtnNam);

		JLabel lblNgSinh = new JLabel("Ngày sinh:");
		lblNgSinh.setFont(new Font("Constantia", Font.BOLD, 13));
		lblNgSinh.setBounds(10, 71, 71, 20);
		pnThongTin.add(lblNgSinh);

		txtNgaySinh = new JDateChooser();
		txtNgaySinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNgaySinh.setBounds(82, 68, 130, 20);
		txtNgaySinh.setDateFormatString("dd-MM-yyyy");
		pnThongTin.add(txtNgaySinh);

		JLabel lblTuoi = new JLabel("Tuổi:");
		lblTuoi.setFont(new Font("Constantia", Font.BOLD, 13));
		lblTuoi.setBounds(260, 71, 45, 20);
		pnThongTin.add(lblTuoi);

		spTuoi = new JSpinner();
		spTuoi.setFont(new Font("Times New Roman", Font.BOLD, 14));
		spTuoi.setBounds(310, 68, 143, 20);
		pnThongTin.add(spTuoi);

		JLabel lblSdt = new JLabel("SĐT:");
		lblSdt.setFont(new Font("Constantia", Font.BOLD, 13));
		lblSdt.setBounds(500, 71, 31, 20);
		pnThongTin.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtSdt.setBounds(541, 68, 133, 19);
		pnThongTin.add(txtSdt);
		txtSdt.setColumns(10);

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBounds(0, 170, 878, 40);
		pnNhanSu.add(pnTimKiem);
		pnTimKiem.setLayout(null);

		JLabel lblTim = new JLabel("Tìm theo:");
		lblTim.setFont(new Font("Constantia", Font.BOLD, 14));
		lblTim.setBounds(37, 11, 68, 23);
		pnTimKiem.add(lblTim);

		txtTim = new JTextField();
		txtTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (jcboTimNV.getSelectedItem() == "Tên NV") {
					int index = jcboTimNV.getSelectedIndex();
					if (index == 0) {
						String maNVTim = txtTim.getText();
						if (maNVTim.length() >= 1) {
							dtmDSNV.setRowCount(0);
							for (NhanSu ns : NhanSu_dao.getDSNSTheoTen(maNVTim)) {
								addRow(ns);
								if (tblDsNhanVien.getRowCount() > 0)
									try {
										reviewRowSelected(0);
									} catch (ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							}
						} else {
							dtmDSNV.setRowCount(0);
							loadDB();
						}
					}
				}
			}
		});
		txtTim.setBounds(190, 11, 206, 21);
		pnTimKiem.add(txtTim);
		txtTim.setColumns(10);

		btnTim = new JButton(new ImageIcon(GUI_NhanSu.class.getResource("/icon/search_gradient.png")));
		btnTim.setBounds(400, 5, 45, 32);
		pnTimKiem.add(btnTim);

		JLabel lblSapXep = new JLabel("Sắp xếp theo:");
		lblSapXep.setFont(new Font("Constantia", Font.BOLD, 14));
		lblSapXep.setBounds(524, 10, 106, 23);
		pnTimKiem.add(lblSapXep);

		cboSapXep = new JComboBox<String>();
		cboSapXep.setFont(new Font("Constantia", Font.PLAIN, 13));
		cboSapXep.setBounds(635, 11, 216, 21);
		cboSapXep.addItem("Theo mã nhân viên(mặc định).");
		cboSapXep.addItem("Theo tên nhân viên.");
		cboSapXep.addItem("Theo tuổi nhân viên.");
		pnTimKiem.add(cboSapXep);

		jcboTimNV = new JComboBox<String>();
		jcboTimNV.setFont(new Font("Constantia", Font.BOLD, 13));
		jcboTimNV.setBounds(106, 10, 77, 21);
		pnTimKiem.add(jcboTimNV);
		jcboTimNV.addItem("Tên NV");
		jcboTimNV.addItem("Mã NV");

		JPanel pnDS = new JPanel();
		pnDS.setBounds(0, 216, 1077, 385);
		pnNhanSu.add(pnDS);
		pnDS.setLayout(null);
		pnDS.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh Sách Nhân Sự"));

		tblDsNhanVien = new JTable(dtmDSNV = new DefaultTableModel(
				new String[] { "Mã NV", "Họ", "Tên", "Giới tính", "Ngày sinh", "Tuổi", "SĐT", "Địa chỉ" }, 0)) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblDsNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsNhanVien.getTableHeader().setBackground(Color.CYAN);
		tblDsNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 15));
		scrDSNV = new JScrollPane(tblDsNhanVien);
		scrDSNV.setBounds(10, 15, 1057, 360);
		pnDS.setBackground(Color.WHITE);
		((DefaultTableCellRenderer) tblDsNhanVien.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		pnDS.add(scrDSNV);

//		set chiều rộng column trong table
		tblDsNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblDsNhanVien.getColumnModel().getColumn(0).setPreferredWidth(80);
		tblDsNhanVien.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblDsNhanVien.getColumnModel().getColumn(2).setPreferredWidth(120);
		tblDsNhanVien.getColumnModel().getColumn(3).setPreferredWidth(74);
		tblDsNhanVien.getColumnModel().getColumn(4).setPreferredWidth(90);
		tblDsNhanVien.getColumnModel().getColumn(6).setPreferredWidth(90);
		tblDsNhanVien.getColumnModel().getColumn(5).setPreferredWidth(60);
		tblDsNhanVien.getColumnModel().getColumn(7).setPreferredWidth(445);
//		sự kiện click vào table sẽ hiển thị dữ liệu lên textfield
		tblDsNhanVien.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowSelected = tblDsNhanVien.getSelectedRow();
				try {
					reviewRowSelected(rowSelected);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tblDsNhanVien.setSelectionForeground(Color.RED);
				tblDsNhanVien.setSelectionBackground(new Color(255, 255, 153));

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
		txtNgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnLuu = new JButton("Lưu", new ImageIcon(GUI_NhanSu.class.getResource("/icon/save.png")));
		btnLuu.setFont(new Font("Constantia", Font.BOLD, 13));
		btnLuu.setBounds(690, 59, 166, 33);
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

		JLabel lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setFont(new Font("Constantia", Font.BOLD, 13));
		lblChucVu.setBounds(10, 127, 71, 20);
		pnThongTin.add(lblChucVu);

		cboChucVu = new JComboBox<String>();
		cboChucVu.setFont(new Font("Constantia", Font.BOLD, 13));
		cboChucVu.setBounds(82, 123, 117, 21);
		pnThongTin.add(cboChucVu);

		lblSoNha = new JLabel("Số nhà, tên đường:");
		lblSoNha.setFont(new Font("Arial", Font.BOLD, 13));
		lblSoNha.setBounds(266, 128, 133, 15);
		pnThongTin.add(lblSoNha);

		txtSoNha = new JTextField();
		txtSoNha.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtSoNha.setBounds(409, 128, 453, 19);
		pnThongTin.add(txtSoNha);
		txtSoNha.setColumns(10);
		cboChucVu.addItem("Nhân viên");
		cboChucVu.addItem("Quản lý");

		loadDB();
		btnTim.addActionListener(this);
		cboSapXep.addActionListener(this);
		btnLuu.addActionListener(this);
		cboChucVu.addItemListener(this);
		MoKhoa(false);

		tblDsNhanVien.getTableHeader().setForeground(Color.BLUE);
		tblDsNhanVien.getTableHeader().setFont(new Font("Constantia", Font.BOLD, 13));
		txtMaNV.setOpaque(false);
		txtMaNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtHo.setOpaque(false);
		txtHo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtTen.setOpaque(false);
		txtTen.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtSdt.setOpaque(false);
		txtSdt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtSoNha.setOpaque(false);
		txtSoNha.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		spTuoi.setOpaque(false);
		spTuoi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		btnTim.setContentAreaFilled(false);
		btnTim.setFocusPainted(false);
		btnTim.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));

		pnThaoTac = new JPanel();
		pnThaoTac.setBackground(Color.WHITE);
		pnThaoTac.setBounds(880, 0, 197, 215);
		pnNhanSu.add(pnThaoTac);
		pnThaoTac.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn tác vụ"));
		pnThaoTac.setLayout(null);

		btnThem = new JButton("Thêm nhân viên", new ImageIcon(GUI_NhanSu.class.getResource("/icon/add.png")));
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
		btnThem.addActionListener(this);

		btnThem.setContentAreaFilled(false);
		btnThem.setFocusPainted(false);
		btnThem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));

		btnXoa = new JButton("Xóa nhân viên", new ImageIcon(GUI_NhanSu.class.getResource("/icon/delete.png")));
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
		btnXoa.addActionListener(this);
		btnXoa.setContentAreaFilled(false);
		btnXoa.setFocusPainted(false);
		btnXoa.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.red));

		btnSua = new JButton("Cập nhật", new ImageIcon(GUI_NhanSu.class.getResource("/icon/update.png")));
		btnSua.setBounds(10, 119, 177, 33);
		pnThaoTac.add(btnSua);
		btnSua.setFont(new Font("Constantia", Font.BOLD, 14));
		// VanQuangPhong
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
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tblDsNhanVien.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa");
				else if (btnSua.getText() == "Cập nhật") {
					btnSua.setText("Hủy");
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
					txtMaNV.setEditable(false);
					MoKhoa(true);
				} else if (btnSua.getText() == "Hủy") {
					btnSua.setText("Cập nhật");
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					MoKhoa(false);
				}

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
		btnXoaTrang.addActionListener(this);
		btnXoaTrang.setContentAreaFilled(false);
		btnXoaTrang.setFocusPainted(false);
		btnXoaTrang.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.YELLOW));
		txtNgaySinh.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				// TODO Auto-generated method stub
				if ("date".equals(e.getPropertyName())) {
					if (txtNgaySinh.getDate() != null) {
						@SuppressWarnings("deprecation")
						int tuoi = java.time.LocalDate.now().getYear() - 1900 - txtNgaySinh.getDate().getYear();
						spTuoi.setValue(tuoi);
						if (Integer.parseInt(spTuoi.getValue() + "") < 16) {
							JOptionPane.showMessageDialog(null, "Không thể nhận nhân viên dưới 16 tuổi!!!");
							txtNgaySinh.setDate(null);
							spTuoi.setValue(0);
						}
					}
				}
			}

		});

	}

	public void MoKhoa(boolean b) {
		txtHo.setEditable(b);
		txtTen.setEditable(b);
		txtNgaySinh.setEnabled(b);
		txtSdt.setEditable(b);
		spTuoi.setEnabled(b);
		btnLuu.setEnabled(b);
		cboChucVu.setEnabled(b);
	}

	public void reviewRowSelected(int rowSelected) throws ParseException {
		txtMaNV.setText(tblDsNhanVien.getValueAt(rowSelected, 0) + "");
		txtHo.setText(tblDsNhanVien.getValueAt(rowSelected, 1) + "");
		txtTen.setText(tblDsNhanVien.getValueAt(rowSelected, 2) + "");
		String gt = dtmDSNV.getValueAt(rowSelected, 3).toString();
		if (gt.equals("Nam")) {
			rdbtnNam.setSelected(true);
		} else {
			rdbtnNu.setSelected(true);
		}
		txtNgaySinh.setDate(new SimpleDateFormat("dd-MM-yyyy")
				.parse(tblDsNhanVien.getModel().getValueAt(rowSelected, 4).toString()));
		spTuoi.setValue(Integer.parseInt(dtmDSNV.getValueAt(rowSelected, 5).toString()));
		txtSdt.setText(tblDsNhanVien.getValueAt(rowSelected, 6) + "");
		txtSoNha.setText(tblDsNhanVien.getValueAt(rowSelected, 7) + "");
		tblDsNhanVien.setRowSelectionInterval(rowSelected, rowSelected);
		String regexMaNs = "^NV\\d{4}$";
		if (!kiemTraNhap(txtMaNV.getText(), regexMaNs)) {
			cboChucVu.setSelectedIndex(0);
		} else
			cboChucVu.setSelectedIndex(1);
	}

	// Regex nhập vào
	public boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

//	Load dữ liệu từ database
	public void addRow(NhanSu ns) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(ns.getNgaySinh());
		String gt = ns.getGioiTinh() ? "Nam" : "Nữ";
		String[] a = { ns.getMaNV(), ns.getHoNV(), ns.getTenNV(), gt, strDate, ns.getTuoi() + "", ns.getSDT(),
				ns.getDiaChi(), };
		dtmDSNV.addRow(a);
	}

	public String autoId(String cv) throws SQLException {
		int dem = new NhanSu_dao().getTongSoNhanSu() + 1;
		String maSo = dem + "";
		String c;
		if (dem < 10) {
			c = "000";
		} else if (dem >= 10 && dem < 100) {
			c = "00";
		} else {
			c = "0";
		}
		String maNV = cv + c + maSo;
		List<NhanSu> dsNS;
		dsNS = NhanSu_dao.getDanhSachNhanSu();
		for (NhanSu nhanSu : dsNS) {
			if (nhanSu.getMaNV().equalsIgnoreCase(maNV)) {
				dem += 1;
			}
		}
		maSo = dem + "";
		return cv + c + maSo;
	}

	public void loadDB() {
		List<NhanSu> dsNS;
		dsNS = NhanSu_dao.getDanhSachNhanSu();
		for (NhanSu nhanSu : dsNS) {
			addRow(nhanSu);
		}
	}

// hàm để gọi màn hình qua tab quản lý
	public Component tabNhanSu() {
		return pnNhanSu;
	}

//	xử lý sự kiện
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm nhân viên")) {
				if (cboChucVu.getSelectedIndex() == 1) {
					chucVu = "QL";
					try {
						txtMaNV.setText(autoId(chucVu));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					chucVu = "NV";
				try {
					txtMaNV.setText(autoId(chucVu));
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
				MoKhoa(false);
				btnXoa.setEnabled(true);
				btnSua.setEnabled(true);
				btnThem.setText("Thêm nhân viên");
				xoaTrang();
			}

		} else if (o.equals(btnLuu)) {
			if (btnThem.getText() == "Hủy") {// trạng thái nút thêm đang được kích hoạt
				if (txtMaNV.getText().trim().isEmpty() || txtHo.getText().trim().isEmpty()
						|| txtTen.getText().trim().isEmpty() || txtNgaySinh.getDate().toString().trim().isEmpty()
						|| txtSdt.getText().trim().isEmpty() || txtSoNha.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
				} else {
					if (rdbtnNam.isSelected()) {
						gt = true;
					} else {
						gt = false;
					}
					NhanSu ns = new NhanSu(txtMaNV.getText(), txtHo.getText(), txtTen.getText(), gt,
							txtNgaySinh.getDate(), Integer.parseInt(spTuoi.getValue() + ""), txtSdt.getText(),
							txtSoNha.getText(), txtMaNV.getText(), true);
					TaiKhoan tkhoan = new TaiKhoan(ns.getMaNV(), "1111");
					if (new TaiKhoan_dao().themTaiKhoan(tkhoan) && new NhanSu_dao().insertNS(ns)) {
						JOptionPane.showMessageDialog(null, "Thêm Nhân Sự Thành Công.");
						dtmDSNV.setRowCount(0);
						loadDB();
						xoaTrang();
						MoKhoa(false);
						btnXoa.setEnabled(true);
						btnSua.setEnabled(true);
						btnThem.setText("Thêm nhân viên");
						xoaTrang();
					} else {
						JOptionPane.showMessageDialog(null, "Thêm Nhân Sự Không Thành Công.");
					}
				}

			} else if (btnSua.getText() == "Hủy") {
				if (rdbtnNam.isSelected()) {
					gt = true;
				} else {
					gt = false;
				}
				NhanSu ns = new NhanSu(txtMaNV.getText(), txtHo.getText(), txtTen.getText(), gt, txtNgaySinh.getDate(),
						Integer.parseInt(spTuoi.getValue() + ""), txtSdt.getText(), txtSoNha.getText(),
						txtMaNV.getText(), true);
				String maNS = txtMaNV.getText();
				try {
					if (NhanSu_dao.updateNS(maNS, ns)) {
						int row = tblDsNhanVien.getSelectedRow();
						updateRow(ns, row);
						btnSua.setText("Cập nhật");
						btnThem.setEnabled(true);
						btnXoa.setEnabled(true);
						MoKhoa(false);
						JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");

					} else {
						JOptionPane.showMessageDialog(null, "Sửa thông tin không thành công");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} else if (o.equals(btnXoa)) {
			if (tblDsNhanVien.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Phải Chọn Nhân Viên Cần Xóa Trước");
			} else {
				if (JOptionPane.showConfirmDialog(null,
						"Bạn chắc chắn muốn xóa nhân sự "
								+ dtmDSNV.getValueAt(tblDsNhanVien.getSelectedRow(), 0).toString() + " Không ?",
						"Xóa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String ma = dtmDSNV.getValueAt(tblDsNhanVien.getSelectedRow(), 0).toString();
					if (NhanSu_dao.deleteNS(ma) && new TaiKhoan_dao().xoaTaiKhoan(ma)) {
						dtmDSNV.removeRow(tblDsNhanVien.getSelectedRow());
						JOptionPane.showMessageDialog(null, "Nhân Sự Đã Được Xóa Thành Công.");
						xoaTrang();
						dtmDSNV.setRowCount(0);
						loadDB();
					} else {
						JOptionPane.showMessageDialog(null, "Xoá Nhân Sự Thất Bại.");
					}
				}
			}
		} else if (o.equals(btnTim)) {
			int index = jcboTimNV.getSelectedIndex();
			if (index == 1) {
				String maNVTim = txtTim.getText();
				if (!maNVTim.isEmpty()) {

					dtmDSNV.setRowCount(0);
					for (NhanSu ns : NhanSu_dao.getDSNS(maNVTim)) {
						if (ns.getMaNV().equalsIgnoreCase(maNVTim))
							addRow(ns);
						if (tblDsNhanVien.getRowCount() > 0)
							try {
								reviewRowSelected(0);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên cần tìm!");
					loadDB();
				}
			} else {
				String maNVTim = txtTim.getText();
				if (!maNVTim.isEmpty()) {
					dtmDSNV.setRowCount(0);
					for (NhanSu ns : NhanSu_dao.getDSNSTheoTen(maNVTim)) {
						addRow(ns);
						if (tblDsNhanVien.getRowCount() > 0)
							try {
								reviewRowSelected(0);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên cần tìm!");
					dtmDSNV.setRowCount(0);
					loadDB();

				}
			}

		} else if (o.equals(cboSapXep)) {
			int index = cboSapXep.getSelectedIndex();
			if (index == 0) {
				dtmDSNV.setRowCount(0);
				loadDB();
			} else if (index == 1) {
				dtmDSNV.setRowCount(0);
				for (NhanSu i : NhanSu_dao.getDanhSachNhanSuTheoTen()) {
					addRow(i);
				}
			} else if (index == 2) {
				dtmDSNV.setRowCount(0);
				for (NhanSu i : NhanSu_dao.getDanhSachNhanSuTheoTuoi()) {
					addRow(i);
				}
			}
		}

	}

	public void xoaTrang() {
		txtMaNV.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtNgaySinh.setDate(null);
		rdbtnNam.setSelected(true);
		spTuoi.setValue(0);
		txtSdt.setText("");
		txtSoNha.setText("");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getItem() == "Quản lý") {
			chucVu = "QL";
			try {
				txtMaNV.setText(autoId(chucVu));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {
			chucVu = "NV";
			try {
				txtMaNV.setText(autoId(chucVu));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public void updateRow(NhanSu ns, int row) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(ns.getNgaySinh());
		if (rdbtnNam.isSelected()) {
			gt = true;
		} else {
			gt = false;
		}
		dtmDSNV.setValueAt(ns.getHoNV(), row, 1);
		dtmDSNV.setValueAt(ns.getTenNV(), row, 2);
		if (gt == true)
			dtmDSNV.setValueAt("Nam", row, 3);
		else
			dtmDSNV.setValueAt("Nữ", row, 3);
		dtmDSNV.setValueAt(strDate, row, 4);
		dtmDSNV.setValueAt(ns.getTuoi(), row, 5);
		dtmDSNV.setValueAt(ns.getSDT(), row, 6);
		dtmDSNV.setValueAt(ns.getDiaChi(), row, 7);
	}
}
