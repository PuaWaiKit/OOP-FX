package com.groupfx.JavaFXApp;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.financemanager.source.FMGenReport;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PdfGenerator {
	
	
	public PdfGenerator() {}
	
	public void GenerateFinanceReport(List<FMGenReport> ReportData, String filepath) throws IOException {
	    PDDocument doc = new PDDocument();
	    PDPage page = new PDPage(PDRectangle.A4);
	    doc.addPage(page);

	    float margin = 50;
	    float y = 750;

	    PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	    y = writeHeader(contentStream, y); // Title and headers

	    contentStream.setFont(PDType1Font.HELVETICA, 10);

	    for (FMGenReport report : ReportData) {
	        if (y < 50) { // New page
	            contentStream.close();
	            page = new PDPage(PDRectangle.A4);
	            doc.addPage(page);
	            contentStream = new PDPageContentStream(doc, page);
	            y = 750;
	            y = writeHeader(contentStream, y); // Repeat header on new page
	        }

	        contentStream.beginText();
	        contentStream.newLineAtOffset(margin, y);
	        contentStream.showText(String.format("%-10s %-6s %-8s %-15s %-5d RM %.2f",
	                report.getPayId(),
	                report.getID(),
	                report.getItemId(),
	                report.getItemName(),
	                report.getQty(),
	                report.getTotal()));
	        contentStream.endText();
	        y -= 15;
	    }

	    contentStream.close();
	    doc.save(filepath);
	    doc.close();
	}
	
	private float writeHeader(PDPageContentStream cs, float y) throws IOException {
	    cs.beginText();
	    cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
	    cs.newLineAtOffset(220, y);
	    cs.showText("OMEGA SDN BHD");
	    cs.endText();

	    y -= 20;

	    cs.beginText();
	    cs.setFont(PDType1Font.HELVETICA_BOLD, 15);
	    cs.newLineAtOffset(220, y);
	    cs.showText("Financial Report");
	    cs.endText();

	    y -= 20;

	    cs.beginText();
	    cs.setFont(PDType1Font.HELVETICA, 12);
	    cs.newLineAtOffset(450, y + 35); // 日期位置上方右角
	    cs.showText("Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	    cs.endText();

	    y -= 30;

	    cs.beginText();
	    cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
	    cs.newLineAtOffset(50, y);
	    cs.showText("PayID    ID     ItemID   ItemName        Qty   Total");
	    cs.endText();

	    y -= 15;

	    return y;
	}
	
	


	}

