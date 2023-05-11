//Huỳnh Hữu Phước
package GUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import com.toedter.calendar.JDateChooser;

import dao.NhanSu_dao;
import entity.CaTruc;
import entity.NhanSu;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GUI_PhanCong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaNV;
	private JTextField txtCaTruc;
	private JTable tblDanhSachCaTruc;
	private DefaultTableModel dtmDSCT;
	private JTable tblDanhSachNhanVienRanh;
	private JTable tblDanhSachNhanVienTrucCa;
	private DefaultTableModel dtmDSNVR;
	private DefaultTableModel dtmDSNVTC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new GUI_PhanCong().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GUI_PhanCong() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnThongTin = new JPanel();
		pnThongTin.setBackground(Color.WHITE);
		pnThongTin.setBounds(0, 0, 880, 67);
		contentPane.add(pnThongTin);
		pnThongTin.setLayout(null);
		pnThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông Tin phân công"));

		JLabel lblMaNV = new JLabel("Mã NV: ");
		lblMaNV.setFont(new Font("Constantia", Font.BOLD, 13));
		lblMaNV.setBounds(584, 24, 55, 20);
		pnThongTin.add(lblMaNV);

		txtMaNV = new JTextField();
		txtMaNV.setOpaque(false);
		txtMaNV.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMaNV.setEditable(false);
		txtMaNV.setColumns(10);
		txtMaNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtMaNV.setBounds(649, 23, 117, 19);
		pnThongTin.add(txtMaNV);

		JLabel lblCaTruc = new JLabel("Ca trực: ");
		lblCaTruc.setFont(new Font("Constantia", Font.BOLD, 13));
		lblCaTruc.setBounds(321, 24, 63, 20);
		pnThongTin.add(lblCaTruc);

		txtCaTruc = new JTextField();
		txtCaTruc.setOpaque(false);
		txtCaTruc.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtCaTruc.setEditable(false);
		txtCaTruc.setColumns(10);
		txtCaTruc.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtCaTruc.setBounds(393, 23, 117, 19);
		pnThongTin.add(txtCaTruc);

		JLabel lblNgayTruc = new JLabel("Ngày trực:");
		lblNgayTruc.setFont(new Font("Constantia", Font.BOLD, 13));
		lblNgayTruc.setBounds(69, 24, 71, 20);
		pnThongTin.add(lblNgayTruc);

		JDateChooser txtNgayTruc = new JDateChooser();
		txtNgayTruc.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNgayTruc.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtNgayTruc.setEnabled(false);
		txtNgayTruc.setDateFormatString("dd-MM-yyyy");
		txtNgayTruc.setBounds(144, 24, 130, 20);
		pnThongTin.add(txtNgayTruc);

		JPanel pnThaoTac = new JPanel();
		pnThaoTac.setLayout(null);
		pnThaoTac.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn tác vụ"));
		pnThaoTac.setBackground(Color.WHITE);
		pnThaoTac.setBounds(879, 0, 197, 175);
		contentPane.add(pnThaoTac);

		JButton btnPhnCng = new JButton("Phân công", new ImageIcon(GUI_NhanSu.class.getResource("/icon/check.png")));
		btnPhnCng.setToolTipText("");
		btnPhnCng.setFont(new Font("Constantia", Font.BOLD, 14));
		btnPhnCng.setFocusPainted(false);
		btnPhnCng.setContentAreaFilled(false);
		btnPhnCng.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
		btnPhnCng.setBounds(10, 42, 177, 33);
		pnThaoTac.add(btnPhnCng);
		btnPhnCng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPhnCng.setFont(new Font("Constantia", Font.BOLD, 17));
				btnPhnCng.setForeground(Color.GREEN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPhnCng.setFont(new Font("Constantia", Font.BOLD, 14));
				btnPhnCng.setForeground(Color.BLACK);
			}
		});

		JButton btnXoa = new JButton("Hủy phân công", new ImageIcon(GUI_NhanSu.class.getResource("/icon/close.png")));
		btnXoa.setFont(new Font("Constantia", Font.BOLD, 14));
		btnXoa.setFocusPainted(false);
		btnXoa.setContentAreaFilled(false);
		btnXoa.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.red));
		btnXoa.setBounds(10, 106, 177, 33);
		pnThaoTac.add(btnXoa);
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

		JPanel pnDanhSachCaTruc = new JPanel();
		pnDanhSachCaTruc.setBackground(Color.WHITE);
		pnDanhSachCaTruc.setBounds(0, 67, 880, 108);
		contentPane.add(pnDanhSachCaTruc);
		pnDanhSachCaTruc.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách ca trực"));
		pnDanhSachCaTruc.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 14, 860, 89);
		pnDanhSachCaTruc.add(scrollPane);

		tblDanhSachCaTruc = new JTable(dtmDSCT = new DefaultTableModel(
				new String[] { "Mã ca", "Ca trực", "Thời gian", "Ngày trực", "Ghi chú" }, 0)) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblDanhSachCaTruc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDanhSachCaTruc.getTableHeader().setBackground(Color.CYAN);
		scrollPane.setViewportView(tblDanhSachCaTruc);

		JPanel pnDanhSachNhanVienRanh = new JPanel();
		pnDanhSachNhanVienRanh.setBounds(0, 172, 538, 431);
		contentPane.add(pnDanhSachNhanVienRanh);
		pnDanhSachNhanVienRanh.setBorder(
				new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách nhân viên chưa phân công"));
		pnDanhSachNhanVienRanh.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 14, 518, 411);
		pnDanhSachNhanVienRanh.add(scrollPane_1);

		tblDanhSachNhanVienRanh = new JTable(
				dtmDSNVR = new DefaultTableModel(new String[] { "Mã NV", "Họ", "Tên", "SĐT" }, 0)) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane_1.setViewportView(tblDanhSachNhanVienRanh);
		tblDanhSachNhanVienRanh.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDanhSachNhanVienRanh.getTableHeader().setBackground(Color.CYAN);

		JPanel pnDanhSachNhanVienTrucCa = new JPanel();
		pnDanhSachNhanVienTrucCa.setBounds(538, 172, 538, 431);
		contentPane.add(pnDanhSachNhanVienTrucCa);
		pnDanhSachNhanVienTrucCa.setBorder(
				new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách nhân viên trực ca"));
		pnDanhSachNhanVienTrucCa.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 14, 518, 411);
		pnDanhSachNhanVienTrucCa.add(scrollPane_2);
		tblDanhSachNhanVienTrucCa = new JTable(
				dtmDSNVTC = new DefaultTableModel(new String[] { "Mã NV", "Họ", "Tên", "SĐT" }, 0)) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane_2.setViewportView(tblDanhSachNhanVienTrucCa);
		tblDanhSachNhanVienTrucCa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDanhSachNhanVienTrucCa.getTableHeader().setBackground(Color.CYAN);

		// Nguyen Nhat Khanh
		loadDB();
//		reviewRowSelected(tblDanhSachNhanVienRanh.getSelectedRow(), tblDanhSachCaTruc.getSelectedRow());

		tblDanhSachCaTruc.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtCaTruc.setText(tblDanhSachCaTruc.getValueAt(tblDanhSachCaTruc.getSelectedRow(), 1) + "");
				String maCT = tblDanhSachCaTruc.getValueAt(tblDanhSachCaTruc.getSelectedRow(), 0).toString();
				if (dtmDSNVTC.getRowCount() > 0) {
					for (int i = dtmDSNVTC.getRowCount() - 1; i > -1; i--) {
						dtmDSNVTC.removeRow(i);
					}
				}
				for (NhanSu ns : new NhanSu_dao().getDanhSachNhanSuTrucCa(maCT)) {
					String[] a = { ns.getMaNV(), ns.getHoNV(), ns.getTenNV(), ns.getSDT() };
					dtmDSNVTC.addRow(a);
				}
			}
		});

		tblDanhSachNhanVienRanh.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtMaNV.setText(tblDanhSachNhanVienRanh.getValueAt(tblDanhSachNhanVienRanh.getSelectedRow(), 0) + "");
			}
		});

		tblDanhSachNhanVienTrucCa.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtMaNV.setText(
						tblDanhSachNhanVienTrucCa.getValueAt(tblDanhSachNhanVienTrucCa.getSelectedRow(), 0) + "");
			}
		});

		btnPhnCng.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rowMaNV = tblDanhSachNhanVienRanh.getSelectedRow();
				int rowCaTruc = tblDanhSachCaTruc.getSelectedRow();
				if (rowMaNV == -1)
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần phân công");
				else if (rowCaTruc == -1)
					JOptionPane.showMessageDialog(null, "Vui lòng chọn ca trực");
				else
					try {
						String maNV = tblDanhSachNhanVienRanh.getValueAt(rowMaNV, 0).toString();
						String maCT = tblDanhSachCaTruc.getValueAt(rowCaTruc, 0).toString();
						if (new NhanSu_dao().phanCong(maNV, maCT)) {
							String[] ns = { tblDanhSachNhanVienRanh.getValueAt(rowMaNV, 0) + "",
									tblDanhSachNhanVienRanh.getValueAt(rowMaNV, 1) + "",
									tblDanhSachNhanVienRanh.getValueAt(rowMaNV, 2) + "",
									tblDanhSachNhanVienRanh.getValueAt(rowMaNV, 3) + "" };
							dtmDSNVTC.addRow(ns);
							dtmDSNVR.removeRow(rowMaNV);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});

		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tblDanhSachNhanVienTrucCa.getSelectedRow();
				if (row == -1)
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên muốn xóa");
				else {
					int result = JOptionPane.showConfirmDialog(null,
							"Bạn có chắc chắn muốn xóa nhân viên này khỏi lịch trực?", "Xóa",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						try {
							if (new NhanSu_dao().huyPhanCong(tblDanhSachNhanVienTrucCa.getValueAt(row, 0).toString())) {
								String[] ns = { tblDanhSachNhanVienTrucCa.getValueAt(row, 0) + "",
										tblDanhSachNhanVienTrucCa.getValueAt(row, 1) + "",
										tblDanhSachNhanVienTrucCa.getValueAt(row, 2) + "",
										tblDanhSachNhanVienTrucCa.getValueAt(row, 3) + "" };
								dtmDSNVR.addRow(ns);
								dtmDSNVTC.removeRow(row);
							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}

	public Component tabPhanCong() {
		return contentPane;
	}

	// Nguyen Nhat Khanh

	public void addRowNS(NhanSu ns) {
		String[] a = { ns.getMaNV(), ns.getHoNV(), ns.getTenNV(), ns.getSDT() };
		dtmDSNVR.addRow(a);
	}

	public void addRowCT(CaTruc ct) {
		String[] a = { ct.getMaCaTruc(), ct.getCa(), ct.getThoiGian() };
		dtmDSCT.addRow(a);
	}

	public void addRowNSCT(NhanSu ns) {
		String[] a = { ns.getMaNV(), ns.getHoNV(), ns.getTenNV(), ns.getSDT() };
		dtmDSNVTC.addRow(a);
	}

	public void loadDB() {
		for (NhanSu ns : new NhanSu_dao().getDanhSachNhanSuChuaPhanCong()) {
			addRowNS(ns);
		}
		for (CaTruc ct : new NhanSu_dao().getDanhSachCaTruc()) {
			addRowCT(ct);
		}
	}
}
