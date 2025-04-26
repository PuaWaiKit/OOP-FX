package com.inventorymanager.UI;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.groupfx.JavaFXApp.PdfGenerator;
import com.inventorymanager.source.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class imStockReportCtrl {
		
	@FXML
    private BarChart<?,?> viewStockChart;
	
	@FXML
    private Button generateBtn;
	
	private List<InventoryM_Stocks> reportData = new ArrayList<>();;
	
	private HashMap<String, Integer> chartStore = new HashMap<>();
	
	public void initialize() throws IOException 
    {
        
        load();
    }
	
	public void load() throws IOException 
    {
    	InventoryM_Stocks listed= new InventoryM_Stocks();
    	String[] row= listed.ReadStockTextFile().toString().split("\n");
    	
    	for(String rows: row) 
    	{
    		String[] spl= rows.split(",");
    		if(spl.length==5) 
    		{	
    			reportData.add(new InventoryM_Stocks(
    					
    					spl[0],
    					spl[1],
    					Integer.parseInt(spl[3])
    						
    					));
    					
    			chartStore.put(spl[1], Integer.parseInt(spl[3]));
    		}
    	}
    	chartload();
    }
	
    public void chartload() {
    	
    	viewStockChart.getData().clear();
    	XYChart.Series series = new XYChart.Series();
    	series.setName("Items Stock");
    	for (Map.Entry<String, Integer> entry : chartStore.entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }
    	
    	viewStockChart.getData().add(series);
    	
    }
    
    public void generateReport() {
    	
    	Stage stage = (Stage) generateBtn.getScene().getWindow();
    	String year = String.valueOf(Year.now().getValue());
    	
    	Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    PdfGenerator generator = new PdfGenerator();

                    // Generate Report PDF can put on background, not involve in UI Threads
                    PDDocument doc = generator.PrepareReport(reportData, year, "STOCK REPORT");

                    // Need Run on the UI Thread FileChooser Save + Alert + open
                    Platform.runLater(() -> {
                        try {
                            generator.savePdfWithChooser(doc, stage,"Report.pdf","Save Financial Report");  //  FileChooser and Alert
                        } catch (IOException e) {
                            e.printStackTrace();
                            showError("Error while saving PDF: " + e.getMessage());
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    Platform.runLater(() -> showError("Failed to generate PDF: " + e.getMessage()));
                }
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
    
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("PDF Generation Failed");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
