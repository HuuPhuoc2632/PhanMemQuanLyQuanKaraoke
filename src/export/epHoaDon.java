package export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import GUI.Application;
import GUI.GUI_DangNhap;
import entity.SanPhamExportHoaDon;

public class epHoaDon {
	public static void main(String[] args) throws DocumentException, IOException {

	}

	public static void exportHoaDon(String maHD, String tenNV, String gioVao, String khachHang, String gioThanhToan,
			List<SanPhamExportHoaDon> list, String tongHoaDon, String tienKhachDua, String giamGia, String tienTraLai,
			String tongThanhToan) throws DocumentException, IOException {
		URL pathUni = Application.class.getResource("/icon/arial-unicode-ms/ARIALUNI.TTF");
		BaseFont bf = BaseFont.createFont(pathUni+"", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		// created PDF document instance
		Document doc = new Document();
		try {
			String path = "D:\\" + maHD + ".pdf";
			// generate a PDF at the specified location
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path));
			// opens the PDF
			doc.open();
			// adds paragraph to the PDF file
			Paragraph paragraph1 = new Paragraph("KARAOKE AQUA", new Font(bf, 42, Font.BOLD));
			paragraph1.setAlignment(Element.ALIGN_CENTER);
			doc.add(paragraph1);
			Paragraph paragraph2 = new Paragraph("HÓA ĐƠN THANH TOÁN", new Font(bf, 22, Font.BOLD));
			paragraph2.setAlignment(Element.ALIGN_CENTER);
			doc.add(paragraph2);

			doc.add(new Paragraph("____________________________________________________________________________"));

			float[] columnWidths1 = { 3, 4, 2, 4 };
			PdfPTable table1 = new PdfPTable(columnWidths1);
			PdfPCell cell7 = new PdfPCell(new Phrase("Mã hóa đơn: ", new Font(bf, 15, Font.BOLD)));
			cell7.setVerticalAlignment(Element.ALIGN_CENTER);
			cell7.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell7);
			PdfPCell cell8 = new PdfPCell(new Phrase(maHD, new Font(bf, 15)));
			cell8.setVerticalAlignment(Element.ALIGN_LEFT);
			cell8.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell8);
			PdfPCell cell9 = new PdfPCell(new Phrase("Nhân viên:", new Font(bf, 15, Font.BOLD)));
			cell9.setVerticalAlignment(Element.ALIGN_CENTER);
			cell9.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell9);
			PdfPCell cell10 = new PdfPCell(new Phrase(tenNV, new Font(bf, 15)));
			cell10.setVerticalAlignment(Element.ALIGN_CENTER);
			cell10.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell10);
			table1.setWidthPercentage(100);

			PdfPCell cell11 = new PdfPCell(new Phrase("Giờ vào: ", new Font(bf, 15, Font.BOLD)));
			cell11.setVerticalAlignment(Element.ALIGN_LEFT);
			cell11.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell11);
			PdfPCell cell12 = new PdfPCell(new Phrase(gioVao, new Font(bf, 15)));
			cell12.setVerticalAlignment(Element.ALIGN_LEFT);
			cell12.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell12);

			PdfPCell cell30 = new PdfPCell(new Phrase("Khách: ", new Font(bf, 15, Font.BOLD)));
			cell30.setVerticalAlignment(Element.ALIGN_LEFT);
			cell30.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell30);
			PdfPCell cell31 = new PdfPCell(new Phrase(khachHang, new Font(bf, 15)));
			cell31.setVerticalAlignment(Element.ALIGN_LEFT);
			cell31.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell31);

			PdfPCell cell13 = new PdfPCell(new Phrase("Giờ thanh toán: ", new Font(bf, 15, Font.BOLD)));
			cell13.setVerticalAlignment(Element.ALIGN_LEFT);
			cell13.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell13);

			PdfPCell cell14 = new PdfPCell(new Phrase(gioThanhToan, new Font(bf, 15)));
			cell14.setVerticalAlignment(Element.ALIGN_LEFT);
			cell14.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell14);

			PdfPCell cell27 = new PdfPCell(new Phrase(" ", new Font(bf, 15)));
			cell27.setVerticalAlignment(Element.ALIGN_CENTER);
			cell27.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell27);
			PdfPCell cell28 = new PdfPCell(new Phrase(" ", new Font(bf, 15)));
			cell28.setVerticalAlignment(Element.ALIGN_CENTER);
			cell28.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell28);
			doc.add(table1);

			List<SanPhamExportHoaDon> ls = new ArrayList<SanPhamExportHoaDon>();
			for (SanPhamExportHoaDon sanPhamExportHoaDon : list) {
				ls.add(sanPhamExportHoaDon);
			}

			float[] columnWidths = { 4, 3, 3 };
			PdfPTable table = new PdfPTable(columnWidths);
			PdfPCell cell1 = new PdfPCell(new Phrase("Danh Mục", new Font(bf, 15, Font.BOLD)));
			cell1.setBorder(Rectangle.NO_BORDER);
			cell1.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
			PdfPCell cell2 = new PdfPCell(new Phrase("Số Lượng", new Font(bf, 15, Font.BOLD)));
			cell2.setVerticalAlignment(Element.ALIGN_CENTER);
			cell2.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell2);
			PdfPCell cell3 = new PdfPCell(new Phrase("Thành tiền", new Font(bf, 15, Font.BOLD)));
			cell3.setVerticalAlignment(Element.ALIGN_CENTER);
			cell3.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell3);
			table.setWidthPercentage(100);
			doc.add(new Paragraph("____________________________________________________________________________"));

			for (SanPhamExportHoaDon sanPhamExportHoaDon : ls) {
				PdfPCell cell4 = new PdfPCell(new Phrase(sanPhamExportHoaDon.getTenSp(), new Font(bf, 15)));
				cell4.setVerticalAlignment(Element.ALIGN_CENTER);
				cell4.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell4);
				PdfPCell cell5 = new PdfPCell(new Phrase(sanPhamExportHoaDon.getSoLuong() + "", new Font(bf, 15)));
				cell5.setVerticalAlignment(Element.ALIGN_CENTER);
				cell5.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell5);
				PdfPCell cell6 = new PdfPCell(new Phrase(sanPhamExportHoaDon.getThanhTien(), new Font(bf, 15)));
				cell6.setVerticalAlignment(Element.ALIGN_CENTER);
				cell6.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell6);
			}

			doc.add(table);

			doc.add(new Paragraph("____________________________________________________________________________"));

			float[] columnWidths3 = { 3, 3, 3, 4 };
			PdfPTable table3 = new PdfPTable(columnWidths3);
			PdfPCell cell15 = new PdfPCell(new Phrase("Tổng hóa đơn: ", new Font(bf, 15, Font.BOLD)));
			cell15.setVerticalAlignment(Element.ALIGN_CENTER);
			cell15.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell15);
			PdfPCell cell16 = new PdfPCell(new Phrase(tongHoaDon, new Font(bf, 15)));
			cell16.setVerticalAlignment(Element.ALIGN_CENTER);
			cell16.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell16);
			PdfPCell cell17 = new PdfPCell(new Phrase("Tiền khách đưa: ", new Font(bf, 15, Font.BOLD)));
			cell17.setVerticalAlignment(Element.ALIGN_CENTER);
			cell17.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell17);
			PdfPCell cell18 = new PdfPCell(new Phrase(tienKhachDua, new Font(bf, 15)));
			cell18.setVerticalAlignment(Element.ALIGN_CENTER);
			cell18.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell18);
			PdfPCell cell19 = new PdfPCell(new Phrase("Giảm giá: ", new Font(bf, 15, Font.BOLD)));
			cell19.setVerticalAlignment(Element.ALIGN_CENTER);
			cell19.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell19);
			PdfPCell cell20 = new PdfPCell(new Phrase(giamGia, new Font(bf, 15)));
			cell20.setVerticalAlignment(Element.ALIGN_CENTER);
			cell20.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell20);
			PdfPCell cell21 = new PdfPCell(new Phrase("Tiền trả lại: ", new Font(bf, 15, Font.BOLD)));
			cell21.setVerticalAlignment(Element.ALIGN_CENTER);
			cell21.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell21);
			PdfPCell cell22 = new PdfPCell(new Phrase(tienTraLai, new Font(bf, 15)));
			cell22.setVerticalAlignment(Element.ALIGN_CENTER);
			cell22.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell22);
			PdfPCell cell23 = new PdfPCell(new Phrase("Tổng thanh toán", new Font(bf, 15, Font.BOLD)));
			cell23.setVerticalAlignment(Element.ALIGN_CENTER);
			cell23.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell23);
			PdfPCell cell24 = new PdfPCell(new Phrase(tongThanhToan, new Font(bf, 15)));
			cell24.setVerticalAlignment(Element.ALIGN_CENTER);
			cell24.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell24);
			table3.setWidthPercentage(100);
			PdfPCell cell25 = new PdfPCell(new Phrase(" ", new Font(bf, 15)));
			cell25.setVerticalAlignment(Element.ALIGN_CENTER);
			cell25.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell25);
			PdfPCell cell26 = new PdfPCell(new Phrase(" ", new Font(bf, 15)));
			cell26.setVerticalAlignment(Element.ALIGN_CENTER);
			cell26.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell26);
			doc.add(table3);
			Paragraph paragraph7 = new Paragraph(
					"Giá các sản phẩm trên hóa đơn đã bao gồm thuế giá trị gia tăng - Mọi thắc mắc xin vui lòng liên hệ hotline: 1800 0000 để được hỗ trợ và tư vấn!",
					new Font(bf, 16));
			paragraph7.setAlignment(Element.ALIGN_CENTER);
			doc.add(paragraph7);
			Paragraph paragraph8 = new Paragraph("Để đặt phòng liên hệ: 0123 456 789 - Quản lí Elon Musk",
					new Font(bf, 16));
			paragraph8.setAlignment(Element.ALIGN_CENTER);
			doc.add(paragraph8);
			Paragraph paragraph4 = new Paragraph("Karaoke Aqua cảm ơn quý khách đã sử dụng dịch vụ", new Font(bf, 16));
			paragraph4.setAlignment(Element.ALIGN_CENTER);
			doc.add(paragraph4);
			Paragraph paragraph5 = new Paragraph("Thân chào và hẹn gặp lại quý khách!", new Font(bf, 16));
			paragraph5.setAlignment(Element.ALIGN_CENTER);
			doc.add(paragraph5);
			Paragraph paragraph6 = new Paragraph(
					"KARAOKE AQUA: số 12, Nguyễn Văn Bảo, Phường 4, quận Gò Vấp, Thành phố Hồ Chí Minh",
					new Font(bf, 16));
			paragraph6.setAlignment(Element.ALIGN_CENTER);
			doc.add(paragraph6);

			Image image = Image.getInstance(GUI_DangNhap.class.getResource("/icon/qrcode.png"));
			image.scaleToFit(100, 100);
			image.setAlignment(Element.ALIGN_CENTER);
			doc.add(image);

			// close the PDF file
			doc.close();
			// closes the writer
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
