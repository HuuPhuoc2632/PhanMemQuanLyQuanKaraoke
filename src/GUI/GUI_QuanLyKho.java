//Huỳnh Hữu Phước
package GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import dao.SanPhamDV_dao;
import entity.SanPhamDV;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GUI_QuanLyKho extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaSp;
	private JTextField txtTenSp;
	private JTextField txtGiaBan;
	private JTextField txtTim;
	private DefaultTableModel dtmDSSP;
	private JTable tblDanhSachSp;
	private JDateChooser txtHSD;
	private JDateChooser txtNsx;
	private JButton btnSua;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnLuu;
	private JButton btnXoaTrang;
	private JSpinner spSoLuong;
	private SanPhamDV_dao SanPhamDV_dao = new SanPhamDV_dao();
	private JComboBox<String> jcboTim;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GUI_QuanLyKho() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnThongTinSP = new JPanel();
		pnThongTinSP.setBackground(new Color(255, 255, 255));
		pnThongTinSP.setBounds(0, 0, 876, 112);
		contentPane.add(pnThongTinSP);
		pnThongTinSP.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông tin sản phẩm"));
		pnThongTinSP.setLayout(null);

		JLabel lblMaSp = new JLabel("Mã sản phẩm:");
		lblMaSp.setFont(new Font("Constantia", Font.BOLD, 13));
		lblMaSp.setBounds(10, 21, 96, 20);
		pnThongTinSP.add(lblMaSp);

		txtMaSp = new JTextField();
		txtMaSp.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMaSp.setBounds(116, 20, 96, 19);
		pnThongTinSP.add(txtMaSp);
		txtMaSp.setColumns(10);

		JLabel lblTenSp = new JLabel("Tên sản phẩm:");
		lblTenSp.setFont(new Font("Constantia", Font.BOLD, 13));
		lblTenSp.setBounds(10, 65, 96, 20);
		pnThongTinSP.add(lblTenSp);

		txtTenSp = new JTextField();
		txtTenSp.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTenSp.setBounds(116, 64, 146, 19);
		pnThongTinSP.add(txtTenSp);
		txtTenSp.setColumns(10);

		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblSoLuong.setBounds(238, 21, 66, 20);
		pnThongTinSP.add(lblSoLuong);

		spSoLuong = new JSpinner();
		spSoLuong.setBounds(314, 20, 53, 20);
		pnThongTinSP.add(spSoLuong);

		JLabel lblGiaBan = new JLabel("Giá bán:");
		lblGiaBan.setFont(new Font("Constantia", Font.BOLD, 13));
		lblGiaBan.setBounds(314, 66, 66, 20);
		pnThongTinSP.add(lblGiaBan);

		txtGiaBan = new JTextField();
		txtGiaBan.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtGiaBan.setBounds(386, 65, 110, 19);
		pnThongTinSP.add(txtGiaBan);
		txtGiaBan.setColumns(10);

		JLabel lblNsx = new JLabel("Ngày SX:");
		lblNsx.setFont(new Font("Constantia", Font.BOLD, 13));
		lblNsx.setBounds(382, 21, 66, 20);
		pnThongTinSP.add(lblNsx);
		txtNsx = new JDateChooser();
		txtNsx.setBackground(new Color(255, 255, 255));
		txtNsx.setBounds(447, 19, 164, 20);
		txtNsx.setDateFormatString("dd-MM-yyyy");
		pnThongTinSP.add(txtNsx);

		JLabel lblHSD = new JLabel("Hạn SD:");
		lblHSD.setFont(new Font("Constantia", Font.BOLD, 13));
		lblHSD.setBounds(621, 21, 66, 20);
		pnThongTinSP.add(lblHSD);

		txtHSD = new JDateChooser();
		txtHSD.setBackground(Color.WHITE);
		txtHSD.setDateFormatString("dd-MM-yyyy");
		txtHSD.setBounds(677, 19, 164, 20);
		pnThongTinSP.add(txtHSD);

		JPanel pnTacVu = new JPanel();
		pnTacVu.setBackground(Color.WHITE);
		pnTacVu.setBounds(876, 0, 200, 182);
		contentPane.add(pnTacVu);
		pnTacVu.setLayout(null);
		pnTacVu.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn tác vụ"));

		btnThem = new JButton("Thêm sản phẩm", new ImageIcon(GUI_NhanSu.class.getResource("/icon/add.png")));
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
		btnThem.setFocusPainted(false);
		btnThem.setContentAreaFilled(false);
		btnThem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
		btnThem.setBounds(10, 10, 177, 33);
		pnTacVu.add(btnThem);

		btnXoa = new JButton("Xóa sản phẩm", new ImageIcon(GUI_NhanSu.class.getResource("/icon/delete.png")));
		btnXoa.setFont(new Font("Constantia", Font.BOLD, 14));
		btnXoa.setFocusPainted(false);
		btnXoa.setContentAreaFilled(false);
		btnXoa.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.red));
		btnXoa.setBounds(10, 53, 177, 33);
		pnTacVu.add(btnXoa);
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

		btnSua = new JButton("Cập nhật", new ImageIcon(GUI_NhanSu.class.getResource("/icon/update.png")));
		btnSua.setFont(new Font("Constantia", Font.BOLD, 14));
		btnSua.setFocusPainted(false);
		btnSua.setContentAreaFilled(false);
		btnSua.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(65, 105, 225)));
		btnSua.setBounds(10, 96, 177, 33);
		pnTacVu.add(btnSua);
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

		btnXoaTrang = new JButton("Xóa trắng", new ImageIcon(GUI_NhanSu.class.getResource("/icon/deleteText.png")));
		btnXoaTrang.setFont(new Font("Constantia", Font.BOLD, 14));
		btnXoaTrang.setFocusPainted(false);
		btnXoaTrang.setContentAreaFilled(false);
		btnXoaTrang.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.YELLOW));
		btnXoaTrang.setBounds(10, 139, 177, 33);
		pnTacVu.add(btnXoaTrang);
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

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setLayout(null);
		pnTimKiem.setBounds(0, 112, 876, 70);
		contentPane.add(pnTimKiem);
		pnTimKiem.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Tìm kiếm và sắp xếp"));

		JLabel lblTim = new JLabel("Tìm theo:");
		lblTim.setFont(new Font("Constantia", Font.BOLD, 14));
		lblTim.setBounds(34, 25, 68, 23);
		pnTimKiem.add(lblTim);

		txtTim = new JTextField();
		txtTim.setColumns(10);
		txtTim.setBounds(184, 24, 206, 21);
		pnTimKiem.add(txtTim);

		JButton btnTim = new JButton(new ImageIcon(GUI_KhachHang.class.getResource("/icon/search_gradient.png")));
		btnTim.setFocusPainted(false);
		btnTim.setContentAreaFilled(false);
		btnTim.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
		btnTim.setBounds(400, 16, 45, 32);
		pnTimKiem.add(btnTim);

		JLabel lblSapXep = new JLabel("Sắp xếp theo:");
		lblSapXep.setFont(new Font("Constantia", Font.BOLD, 14));
		lblSapXep.setBounds(516, 25, 106, 23);
		pnTimKiem.add(lblSapXep);

		JComboBox<String> cboSapXep = new JComboBox<String>();
		cboSapXep.setBackground(Color.WHITE);
		cboSapXep.setFont(new Font("Constantia", Font.PLAIN, 13));
		cboSapXep.setBounds(635, 24, 216, 21);
		pnTimKiem.add(cboSapXep);

		jcboTim = new JComboBox<String>();
		jcboTim.setFont(new Font("Constantia", Font.BOLD, 13));
		jcboTim.setBounds(106, 24, 68, 21);
		pnTimKiem.add(jcboTim);

		JPanel pnDanhSachSp = new JPanel();
		pnDanhSachSp.setBackground(Color.WHITE);
		pnDanhSachSp.setBounds(0, 184, 1076, 419);
		contentPane.add(pnDanhSachSp);
		pnDanhSachSp.setLayout(null);
		txtMaSp.setOpaque(false);
		txtMaSp.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
		txtTenSp.setOpaque(false);
		txtTenSp.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
		txtHSD.setOpaque(false);
		txtNsx.setOpaque(false);
		txtNsx.setEnabled(true);
		txtGiaBan.setOpaque(false);
		txtGiaBan.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));

		btnLuu = new JButton("Lưu", new ImageIcon(GUI_KhachHang.class.getResource("/icon/save.png")));
		btnLuu.setFont(new Font("Constantia", Font.BOLD, 13));
		btnLuu.setEnabled(false);
		btnLuu.setBounds(700, 63, 166, 33);
		pnThongTinSP.add(btnLuu);
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

		pnDanhSachSp.setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1076, 419);
		pnDanhSachSp.add(scrollPane);
		MoKhoa(false);
		tblDanhSachSp = new JTable(dtmDSSP = new DefaultTableModel(
				new String[] { "Mã SP", "Tên sản phẩm", "Số lượng tồn", "Giá bán", "Hạn sử dụng" }, 0)) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(tblDanhSachSp);
		((DefaultTableCellRenderer) tblDanhSachSp.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		tblDanhSachSp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDanhSachSp.getTableHeader().setBackground(Color.CYAN);
		tblDanhSachSp.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
		tblDanhSachSp.getTableHeader().setForeground(Color.BLUE);
		tblDanhSachSp.setFont(new Font("Times New Roman", Font.BOLD, 15));

		// Nguyen Nhat Khanh
		TableRowSorter sorter = new TableRowSorter(dtmDSSP);
		tblDanhSachSp.setRowSorter(sorter);
		sorter.setRowFilter(new RowFilter() {
			private String name = null;
			private String tim = null;

			@Override
			public boolean include(Entry entry) {
				if (jcboTim.getSelectedIndex() == 0) {
					// TODO Auto-generated method stub
					name = entry.getValue(1).toString().toLowerCase();
					tim = txtTim.getText();
					return name.startsWith(tim);
				}
				return name.startsWith(tim);

			}

		});

		cboSapXep.addItem("Mã sản phẩm (Mặc định)");
		cboSapXep.addItem("Tên sản phẩm");
		cboSapXep.addItem("Giá bán");
		cboSapXep.addItem("Hạn sử dụng");

		jcboTim.addItem("Tên");
		jcboTim.addItem("Mã");

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (btnThem.getText().equalsIgnoreCase("Thêm sản phẩm")) {
					MoKhoa(true);
					btnThem.setText("Hủy");
					btnXoa.setEnabled(false);
					btnSua.setEnabled(false);
					xoaTrang();
					try {
						txtMaSp.setText(autoId());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
					MoKhoa(false);
					btnThem.setText("Thêm sản phẩm");
					btnXoa.setEnabled(true);
					btnSua.setEnabled(true);
					xoaTrang();
				}

			}
		});

		btnXoaTrang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xoaTrang();
			}
		});

		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (btnThem.getText().equalsIgnoreCase("Hủy")) {
					if (txtMaSp.getText().trim().isEmpty() || spSoLuong.getValue().toString().isEmpty()
							|| txtNsx.getDate() == null || txtHSD.getDate() == null
							|| txtTenSp.getText().trim().isEmpty()
							|| txtGiaBan.getText().trim().isEmpty())
						JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
					else if (txtNsx.getDate().after(txtHSD.getDate()))
						JOptionPane.showMessageDialog(null, "Hạn sử dụng phải sau ngày sản xuất");
					else {
						SanPhamDV sp = new SanPhamDV(txtMaSp.getText(), txtTenSp.getText(),
								Integer.parseInt(spSoLuong.getValue() + ""), Double.parseDouble(txtGiaBan.getText()),
								txtHSD.getDate(), "Loại", true);
//							SanPhamDV_dao.insertSanPham(sp);
						if (SanPhamDV_dao.insertSanPham(sp)) {
							xoaTrang();
							dtmDSSP.setRowCount(0);
							loadDB();
							MoKhoa(false);
							btnThem.setText("Thêm sản phẩm");
							btnXoa.setEnabled(true);
							btnSua.setEnabled(true);
							JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
						} else
							JOptionPane.showMessageDialog(null, "Thêm sản phẩm không thành công");
					}
				} else if (btnSua.getText() == "Hủy") {
					SanPhamDV sp = new SanPhamDV(txtMaSp.getText(), txtTenSp.getText(),
							Integer.parseInt(spSoLuong.getValue() + ""), Double.parseDouble(txtGiaBan.getText()),
							txtHSD.getDate(), "Loại", true);
					String masp = txtMaSp.getText();
					try {
						if (SanPhamDV_dao.updateSanPham(masp, sp)) {
							int row = tblDanhSachSp.getSelectedRow();
							updateRow(sp, row);
							btnSua.setText("Cập nhật");
							btnThem.setEnabled(true);
							btnXoa.setEnabled(true);
							MoKhoa(false);
							JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");
						} else
							JOptionPane.showMessageDialog(null, "Sửa thông tin không thành công");
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tblDanhSachSp.getSelectedRow();
				if (row == -1)
					JOptionPane.showMessageDialog(null, "Phải chọn dòng cần xóa");
				else {
					int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xóa",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						String masp = tblDanhSachSp.getValueAt(row, 0).toString();
						try {
							if (SanPhamDV_dao.deleteSanPham(masp)) {
								dtmDSSP.removeRow(row);
								xoaTrang();
								JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công");
							} else
								JOptionPane.showMessageDialog(null, "Xóa sản phẩm không thành công");
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tblDanhSachSp.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần sửa");
				else if (btnSua.getText() == "Cập nhật") {
					btnSua.setText("Hủy");
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
					MoKhoa(true);
					txtMaSp.setEditable(false);
				} else if (btnSua.getText() == "Hủy") {
					btnSua.setText("Cập nhật");
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					MoKhoa(false);
				}
			}
		});

		cboSapXep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = cboSapXep.getSelectedIndex();
				if (index == 0) {
					dtmDSSP.setRowCount(0);
					loadDB();
				} else if (index == 1) {
					dtmDSSP.setRowCount(0);
					try {
						for (SanPhamDV s : SanPhamDV_dao.getDSSanPhamTheoTen()) {
							addRow(s);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (index == 2) {
					dtmDSSP.setRowCount(0);
					try {
						for (SanPhamDV s : SanPhamDV_dao.getDSSanPhamTheoGia()) {
							addRow(s);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (index == 3) {
					dtmDSSP.setRowCount(0);
					try {
						for (SanPhamDV s : SanPhamDV_dao.getDSSanPhamTheoHsd()) {
							addRow(s);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnTim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jcboTim.getSelectedIndex() == 1) {
					String tim = txtTim.getText();
					if (!tim.isEmpty()) {
						dtmDSSP.setRowCount(0);
						try {
							for (SanPhamDV s : SanPhamDV_dao.getDSSanPhamTheoMa(tim)) {
								if (s.getMaDV().equalsIgnoreCase(tim)) {
//									JOptionPane.showMessageDialog(null, "kkk");
//									System.out.println(s);
									addRow(s);
								}
								if (tblDanhSachSp.getRowCount() > 0)
									reviewRowSelected(0);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						dtmDSSP.setRowCount(0);
						loadDB();
						JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm cần tìm");
					}
				}
			}
		});
		txtTim.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if (jcboTim.getSelectedIndex() == 0)
					dtmDSSP.fireTableDataChanged();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if (jcboTim.getSelectedIndex() == 0)
					dtmDSSP.fireTableDataChanged();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if (jcboTim.getSelectedIndex() == 0)
					dtmDSSP.fireTableDataChanged();
			}
		});

		tblDanhSachSp.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowSelected = tblDanhSachSp.getSelectedRow();
				try {
					reviewRowSelected(rowSelected);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tblDanhSachSp.setSelectionForeground(Color.RED);
				tblDanhSachSp.setSelectionBackground(new Color(255, 255, 153));

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
		loadDB();
	}

	public Component tabQuanLyKho() {
		return contentPane;
	}

	public void addRow(SanPhamDV sp) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(sp.getHanSD());
		String[] a = { sp.getMaDV(), sp.getTenDV(), sp.getSoLuongTon() + "", sp.getDonGia() + "", strDate };
		dtmDSSP.addRow(a);
	}

	public void loadDB() {
		for (SanPhamDV sp : new SanPhamDV_dao().getDanhSachSP()) {
			addRow(sp);
		}
	}

	public void reviewRowSelected(int rowSelected) throws ParseException {
		txtMaSp.setText(tblDanhSachSp.getValueAt(rowSelected, 0) + "");
		txtTenSp.setText(tblDanhSachSp.getValueAt(rowSelected, 1) + "");
		spSoLuong.setValue(Integer.parseInt(tblDanhSachSp.getValueAt(rowSelected, 2).toString()));
		txtGiaBan.setText(tblDanhSachSp.getValueAt(rowSelected, 3) + "");
		txtHSD.setDate(new SimpleDateFormat("dd-MM-yyyy")
				.parse(tblDanhSachSp.getModel().getValueAt(rowSelected, 4).toString()));
	}

	// Nguyen Nhat Khanh

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public void MoKhoa(boolean b) {
		txtMaSp.setEditable(b);
		txtTenSp.setEditable(b);
		txtGiaBan.setEditable(b);
		txtHSD.setEnabled(b);
		txtNsx.setEnabled(b);
		btnLuu.setEnabled(b);
		spSoLuong.setEnabled(b);
	}

	public void xoaTrang() {
		txtMaSp.setText("");
		spSoLuong.setValue(0);
		txtNsx.setDate(null);
		txtHSD.setDate(null);
		txtTenSp.setText("");
		txtGiaBan.setText("");
	}

	public String autoId() throws SQLException {
		int d = new SanPhamDV_dao().getTongSanPham() + 1;
		String pd = "";
		if (d < 10)
			pd = "000";
		else if (d >= 10 && d < 100)
			pd = "00";
		else if (d >= 1000)
			pd = "0";
		String id = "DV" + pd + d;
		return id;
	}

	public void updateRow(SanPhamDV s, int row) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(s.getHanSD());

		dtmDSSP.setValueAt(s.getTenDV(), row, 1);
		dtmDSSP.setValueAt(s.getSoLuongTon(), row, 2);
		dtmDSSP.setValueAt(s.getDonGia(), row, 3);
		dtmDSSP.setValueAt(strDate, row, 4);
	}
}
