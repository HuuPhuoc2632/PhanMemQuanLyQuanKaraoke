package GUI;

import java.awt.Component;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import connectDB.MyConnection;
import dao.BieuDo_dao;
import entity.ThongTinTaiKhoan;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("unused")
public class GUI_BieuDoThongKe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ChartPanel chartPanel;
	private Connection con = MyConnection.getInstance().getConnection();
	private static String loc, thang, nam;
	private static ThongTinTaiKhoan tk;
	private static double dtThang = 1, dtNam = 1;

	@SuppressWarnings("static-access")
	public GUI_BieuDoThongKe(ThongTinTaiKhoan tk, String loc, String thang, String nam) {
		this.tk = tk;
		this.loc = loc;
		this.thang = thang;
		this.nam = nam;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		if (thang.equalsIgnoreCase("Cả năm")) {
			chartPanel = new ChartPanel(createChart());
		} else {
			chartPanel = new ChartPanel(createChartPie(createDatasetForPie(subStringThang(thang), nam, loc)));
		}

		chartPanel.setPreferredSize(new java.awt.Dimension(835, 365));
		contentPane.add(chartPanel);

	}

	public static JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU 2022", "Quý", "Doanh thu",
				createDataset(loc), PlotOrientation.VERTICAL, false, false, false);
		return barChart;
	}

	public static CategoryDataset createDataset(String loc) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		BieuDo_dao bieuDo_dao = new BieuDo_dao();
		if(loc.equalsIgnoreCase("Cá nhân")) {
			
		}
		dataset.addValue(bieuDo_dao.loadDoanhThuQuy1(), "Doanh thu", "Quý 1");
		dataset.addValue(bieuDo_dao.loadDoanhThuQuy2(), "Doanh thu", "Quý 2");
		dataset.addValue(bieuDo_dao.loadDoanhThuQuy3(), "Doanh thu", "Quý 3");
		dataset.addValue(bieuDo_dao.loadDoanhThuQuy4(), "Doanh thu", "Quý 4");
		return dataset;
	}

	@SuppressWarnings("rawtypes")
	private static JFreeChart createChartPie(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("CƠ CẤU DOANH THU CỦA THÁNG", dataset, true, true, true);
		return chart;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static PieDataset createDatasetForPie(String n, String nam, String loc) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		String regexMaNs = "^NV\\d{4}$";
		if (kiemTraNhap(tk.getMa(), regexMaNs) && loc.equalsIgnoreCase("Cá nhân")) {
			dtThang = new BieuDo_dao().loadDoanhThuTheoThangCuaNhanVien(subStringThang(thang), nam, tk.getMa());
			dtNam = new BieuDo_dao().loadDoanhThuCaNamCuaNhanVien(nam, tk.getMa());
			dataset.setValue("Tháng " + n, dtThang);
			dataset.setValue("Còn lại", dtNam - dtThang);
			return dataset;
		} else if (!kiemTraNhap(tk.getMa(), regexMaNs) && loc.equalsIgnoreCase("Cá nhân")) {
			dtThang = new BieuDo_dao().loadDoanhThuTheoThangCuaQuanLy(subStringThang(thang), nam, tk.getMa());
			dtNam = new BieuDo_dao().loadDoanhThuCaNamCuaQuanLy(nam, tk.getMa());
			dataset.setValue("Tháng " + n, dtThang);
			dataset.setValue("Còn lại", dtNam - dtThang);
			return dataset;
		} else {
			dtThang = new BieuDo_dao().loadDoanhThuTheoThang(subStringThang(thang), nam);
			dtNam = new BieuDo_dao().loadDoanhThuCaNam(nam);
			dataset.setValue("Tháng " + n, dtThang);
			dataset.setValue("Còn lại", dtNam - dtThang);
			return dataset;
		}
	}

	public static String subStringThang(String thang) {
		String a = thang.substring(thang.indexOf(" "));
		return a;
	}

	public Component loadChart() {
		return contentPane;
	}

	public static boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

}
