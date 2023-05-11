//Huỳnh Hữu Phước
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.HoaDon_SanPhamDV_dao;
import dao.KhachHang_dao;
import dao.SanPhamDV_dao;
import dao.ThongTinSuDungPhong_dao;
import entity.HoaDon_SanPhamDV;
import entity.KhachHang;
import entity.Phong;
import entity.SanPhamDV;
import entity.ThongTinSuDungPhong;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI_ThemDichVu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tblDV;
	private static DefaultTableModel dtmDSDV;
	private String kh;
	private JPanel pnDanhSachDichVu;
	private Phong p;
	private String hd;
	private JComboBox<String> cboSapXep;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public GUI_ThemDichVu(Phong p, String hd) throws SQLException {
		this.p = p;
		this.hd = hd;
		setBounds(100, 100, 1152, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel pnDichVu = new JPanel();
		pnDichVu.setBackground(Color.WHITE);
		pnDichVu.setBounds(0, 0, 723, 640);
		contentPane.add(pnDichVu);
		pnDichVu.setLayout(null);

		JPanel pnDichVu_1 = new JPanel();
		pnDichVu_1.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn dịch vụ"));
		pnDichVu_1.setLayout(null);
		pnDichVu_1.setBackground(Color.WHITE);
		pnDichVu_1.setBounds(0, 0, 723, 640);
		pnDichVu.add(pnDichVu_1);

		JLabel lblDanhMuc = new JLabel("Danh mục");
		lblDanhMuc.setFont(new Font("Arial", Font.BOLD, 13));
		lblDanhMuc.setBounds(58, 71, 80, 20);
		pnDichVu_1.add(lblDanhMuc);

		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Arial", Font.BOLD, 13));
		lblSoLuong.setBounds(328, 71, 66, 20);
		pnDichVu_1.add(lblSoLuong);

		JLabel lblThanhTien = new JLabel("Thành tiền");
		lblThanhTien.setFont(new Font("Arial", Font.BOLD, 13));
		lblThanhTien.setBounds(543, 71, 72, 20);
		pnDichVu_1.add(lblThanhTien);

		JLabel lblTim = new JLabel("Tìm theo tên:");
		lblTim.setFont(new Font("Constantia", Font.BOLD, 14));
		lblTim.setBounds(31, 23, 107, 23);
		pnDichVu_1.add(lblTim);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(138, 22, 188, 21);
		pnDichVu_1.add(textField);

		JLabel lblSapXep = new JLabel("Lọc");
		lblSapXep.setFont(new Font("Constantia", Font.BOLD, 14));
		lblSapXep.setBounds(347, 22, 72, 23);
		pnDichVu_1.add(lblSapXep);

		cboSapXep = new JComboBox<String>();
		cboSapXep.addItem("Tất cả");
		cboSapXep.addItem("Nước uống");
		cboSapXep.addItem("Trái cây");
		cboSapXep.addItem("Đồ ăn vặt");

		cboSapXep.setFont(new Font("Constantia", Font.PLAIN, 13));
		cboSapXep.setBackground(Color.WHITE);
		cboSapXep.setBounds(414, 21, 271, 21);
		pnDichVu_1.add(cboSapXep);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(10, 55, 703, 6);
		pnDichVu_1.add(separator_2);

		JPanel pnDichVuDaOrder = new JPanel();
		pnDichVuDaOrder.setBackground(Color.WHITE);
		pnDichVuDaOrder.setBounds(722, 0, 416, 640);
		contentPane.add(pnDichVuDaOrder);
		pnDichVuDaOrder.setLayout(null);
		pnDichVuDaOrder.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Dịch vụ đã chọn"));

		JLabel lblMaPhong = new JLabel("Mã phòng:");
		lblMaPhong.setFont(new Font("Constantia", Font.BOLD, 13));
		lblMaPhong.setBounds(10, 26, 80, 20);
		pnDichVuDaOrder.add(lblMaPhong);

		JLabel lblMaPhongText = new JLabel("PH0001");
		lblMaPhongText.setForeground(Color.RED);
		lblMaPhongText.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblMaPhongText.setBounds(100, 26, 80, 20);
		pnDichVuDaOrder.add(lblMaPhongText);

		JLabel lblKH = new JLabel("Khách hàng:");
		lblKH.setForeground(new Color(0, 0, 0));
		lblKH.setFont(new Font("Constantia", Font.BOLD, 13));
		lblKH.setBounds(10, 65, 80, 20);
		pnDichVuDaOrder.add(lblKH);

		JLabel lblKhText = new JLabel("");
		lblKhText.setFont(new Font("Constantia", Font.BOLD, 13));
		lblKhText.setBounds(100, 65, 118, 20);
		pnDichVuDaOrder.add(lblKhText);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 95, 396, 476);
		pnDichVuDaOrder.add(scrollPane_1);

		tblDV = new JTable(dtmDSDV = new DefaultTableModel(new String[] { "Tên DV", "Số lượng", "Thành tiền" }, 0)) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblDV.setFont(new Font("Times New Roman", Font.BOLD, 11));
		scrollPane_1.setViewportView(tblDV);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(31, 101, 666, 529);
		pnDichVu_1.add(scrollPane);

		pnDanhSachDichVu = new JPanel();
		scrollPane.setViewportView(pnDanhSachDichVu);
		pnDanhSachDichVu.setBackground(Color.WHITE);
		pnDanhSachDichVu.setLayout(new GridLayout(0, 1, 0, 0));
//		for (SanPhamDV sp : new SanPhamDV_dao().getDanhSachSP()) {
//			if(sp.getSoLuongTon()>0) {
//				pnDanhSachDichVu.add(new GUI_DanhMucSanPham(sp, p, hd).danhSachSanPham());
//			}
//			
//		}
		tblDV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDV.getTableHeader().setBackground(Color.CYAN);
		tblDV.setFont(new Font("Times New Roman", Font.BOLD, 15));

		JButton btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JOptionPane.showMessageDialog(null, "Thêm sản phẩm và dịch vụ thành công");
			}
		});
		btnXacNhan.setFont(new Font("Constantia", Font.BOLD, 13));
		btnXacNhan.setBounds(156, 581, 112, 35);
		pnDichVuDaOrder.add(btnXacNhan);
		lblMaPhongText.setText(p.getMaPhong());
		for (ThongTinSuDungPhong ttsd : new ThongTinSuDungPhong_dao()
				.getDanhSachPhongDangSuDungTheoMa(p.getMaPhong())) {
			KhachHang k = new KhachHang_dao().getDSKHTheoMa(ttsd.getMaKhachHang());
			kh = k.getHoKH() + " " + k.getTenKH();
			lblKhText.setText(kh);
		}
		cboSapXep.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cboSapXep.getSelectedItem() == "Tất cả") {
					loadDanhSachSanPham();
				} else {
					try {
						loadDanhSachSanPhamTheoLoai();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textField.getText().length() > 0) {
					try {
						loadDanhSachSanPhamTheoTen(textField.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					loadDanhSachSanPham();
				}
			}
		});
		loadDanhSachSanPham();

		loadDB(hd);

	}

	public void loadDanhSachSanPham() {
		removeComponent();
		for (SanPhamDV sp : new SanPhamDV_dao().getDanhSachSP()) {
			if (sp.getSoLuongTon() > 0) {
				try {
					pnDanhSachDichVu.add(new GUI_DanhMucSanPham(sp, p, hd).danhSachSanPham());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public void loadDanhSachSanPhamTheoTen(String ten) throws SQLException {
		removeComponent();
		for (SanPhamDV sp : new SanPhamDV_dao().getDanhSachSPTheoTen(ten)) {
			if (sp.getSoLuongTon() > 0) {
				try {
					pnDanhSachDichVu.add(new GUI_DanhMucSanPham(sp, p, hd).danhSachSanPham());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			pnDanhSachDichVu.add(new GUI_DongNullSanPham().dongSanPhamNull());
		}

	}

	public void loadDanhSachSanPhamTheoLoai() throws SQLException {
		removeComponent();
		for (SanPhamDV sp : new SanPhamDV_dao().getDanhSachSanPhamTheoLoai(cboSapXep.getSelectedItem() + "")) {
			if (sp.getSoLuongTon() > 0) {
				try {
					pnDanhSachDichVu.add(new GUI_DanhMucSanPham(sp, p, hd).danhSachSanPham());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		for (int i = 0; i < 10; i++) {
			pnDanhSachDichVu.add(new GUI_DongNullSanPham().dongSanPhamNull());
		}
	}

	public void removeComponent() {
		Component[] components = pnDanhSachDichVu.getComponents();
		for (Component component : components) {
			pnDanhSachDichVu.remove(component);
		}
		pnDanhSachDichVu.revalidate();
		pnDanhSachDichVu.repaint();
	}

	public Component tabThemDichVu() {
		return contentPane;
	}

	public static void addRow(SanPhamDV sp, int sl) {
		String thanhTien = (sp.getDonGia() * sl) + "";
		String[] a = { sp.getTenDV(), sl + "", thanhTien };
		dtmDSDV.addRow(a);
	}

	public static void loadDB(String maHD) {
		dtmDSDV.setRowCount(0);
		List<HoaDon_SanPhamDV> hdsp = new HoaDon_SanPhamDV_dao().getDanhSachTheoMaHD(maHD);
		for (HoaDon_SanPhamDV hoaDon_SanPhamDV : hdsp) {
			SanPhamDV sp = new SanPhamDV();
			try {
				sp = new SanPhamDV_dao().getSanPhamTheoMa(hoaDon_SanPhamDV.getMaDV());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addRow(sp, hoaDon_SanPhamDV.getSoLuong());
		}
	}

	public static void updateRow(SanPhamDV sp, int sl) {
		String thanhTien = sp.getDonGia() * sl + "";
		int row = dtmDSDV.getRowCount();
		for (int i = 0; i < row; i++) {
			if (sp.getTenDV() == dtmDSDV.getValueAt(i, 0)) {
				dtmDSDV.setValueAt(Integer.parseInt(dtmDSDV.getValueAt(i, 1) + "") + sl, i, 1);
				dtmDSDV.setValueAt(thanhTien, i, 2);
			}
		}
	}

	public static void deleteRow(SanPhamDV sp) {
		int row = dtmDSDV.getRowCount();
		for (int i = 0; i < row; i++) {
			if (sp.getTenDV() == dtmDSDV.getValueAt(i, 0)) {
				dtmDSDV.removeRow(i);
			}
		}
	}

}
