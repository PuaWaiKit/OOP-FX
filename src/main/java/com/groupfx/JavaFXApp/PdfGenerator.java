package com.groupfx.JavaFXApp;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.financemanager.source.FMGenReport;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PdfGenerator {
	
	
	public PdfGenerator() {}
	
	public void GenerateFinancialReport(List<FMGenReport> reportData, String year, Stage stage) throws IOException {
		
	    PDDocument doc = new PDDocument();
	    PDPage page = new PDPage(PDRectangle.A4);
	    doc.addPage(page);

	    PDPageContentStream cs = new PDPageContentStream(doc, page);
	    float margin = 50;
	    float y = 750;
	    float lineHeight = 20;

	    // Title
	    cs.beginText();
	    cs.setFont(PDType1Font.HELVETICA_BOLD, 16);
	    cs.newLineAtOffset(200, y);
	    cs.showText("OMEGA WHOLESALE SDN BHD");
	    cs.endText();

	    y -= lineHeight;

	    cs.beginText();
	    cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
	    cs.newLineAtOffset(225, y);
	    cs.showText("FINANCIAL STATEMENT");
	    cs.endText();

	    y -= lineHeight;

	    cs.beginText();
	    cs.setFont(PDType1Font.HELVETICA, 12);
	    cs.newLineAtOffset(230, y);
	    cs.showText("For Year " + year);
	    cs.endText();
	    
	    cs.beginText();
	    cs.setFont(PDType1Font.HELVETICA, 10);
	    cs.newLineAtOffset(450, y);
	    DateTimeFormatter format= DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate date=LocalDate.now();
	    
	    cs.showText("Date Created: "+ date.format(format));
	    cs.endText();
	    
	    y-=lineHeight;
	    
	    y -= (lineHeight * 2);

	    // Header
	    drawLine(cs, margin, y, 500);
	    drawText(cs,"Item ID",margin,y-15,true);
	    drawText(cs, "Item", margin+150, y - 15, true);
	    drawText(cs, "Qty", margin + 300, y - 15, true);
	    drawText(cs, "Total (RM)", margin + 400, y - 15, true);
	    y -= lineHeight;
	    drawLine(cs, margin, y, 500);

	    // content
	    double totalIncome = 0.0;
	    for (FMGenReport report : reportData) {
	    	drawText(cs,report.getItemId(),margin,y-15,false);
	        drawText(cs, report.getItemName(), margin+150, y - 15, false);
	        drawText(cs, String.valueOf(report.getQty()), margin + 300, y - 15, false);
	        drawText(cs, String.format("%.2f", report.getTotal()), margin + 400, y - 15, false);
	        totalIncome += report.getTotal();
	        y -= lineHeight;
	    }

	    drawLine(cs, margin, y, 500);
	    y -= lineHeight;

	    // Tincome
	    drawText(cs, "Total Income:", margin + 300, y - 15, true);
	    drawText(cs, "RM " + String.format("%.2f", totalIncome), margin + 400, y - 15, true);

	    cs.close();
	    SavePdf(doc,stage);
	    doc.close();
	}

	private void SavePdf(PDDocument pdf,Stage stage) 
	{
		FileChooser chooser= new FileChooser();
		chooser.setTitle("Financial_Report");
		chooser.setInitialFileName("Financial_Report.pdf");
		
		FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter("PDF File (*.pdf)", "*.pdf");
		chooser.getExtensionFilters().add(filter);
		
		File file= chooser.showSaveDialog(stage);
		
		if (file != null) {
	        try 
	        {
	        	pdf.save(file); // Save PDF
	        	Alert alert= new Alert(AlertType.INFORMATION);
	 	        alert.setHeaderText("Save");
	 	        alert.setContentText("File Save into "+ file.getAbsolutePath());
	 	        alert.showAndWait();
	 	        pdf.close();
	 	        
	 	        if(Desktop.isDesktopSupported()) 
	 	        {
	 	        	Desktop.getDesktop().open(file);
	 	        }
	 	        
	 	        
	        	
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            Alert alert= new Alert(AlertType.ERROR);
		        alert.setHeaderText("ERROR");
		        alert.setContentText("Process Cancelled,Please open yourself!");
		        alert.showAndWait();
	        } finally 
	        {
	            try 
	            {
	                pdf.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    } else 
	    {
	        Alert alert= new Alert(AlertType.INFORMATION);
	        alert.setHeaderText("Cancel");
	        alert.setContentText("Process Cancelled");
	        alert.showAndWait();
	    }
		
		
	}
	
	
	
	private void drawLine(PDPageContentStream cs, float x, float y, float width) throws IOException {
	    cs.moveTo(x, y);
	    cs.lineTo(x + width, y);
	    cs.stroke();
	}

	private void drawText(PDPageContentStream cs, String text, float x, float y, boolean bold) throws IOException {
	    cs.beginText();
	    cs.setFont(bold ? PDType1Font.HELVETICA_BOLD : PDType1Font.HELVETICA, 11);
	    cs.newLineAtOffset(x, y);
	    cs.showText(text);
	    cs.endText();
	}


	


	}

