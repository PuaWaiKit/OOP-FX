package com.financemanager.UI;

import java.io.IOException;

import com.financemanager.source.FMGenReport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class FMGenReportCtrl {

    @FXML
    private TableColumn<FMGenReport, String> PayDate;

    @FXML
    private TableColumn<FMGenReport, String> PayId;
    @FXML
    private TableColumn<FMGenReport, String> PoId;

    @FXML
    private TableColumn<FMGenReport, String> PayItemName;

    @FXML
    private TableColumn<FMGenReport, String> PayItemsd;

    @FXML
    private TableColumn<FMGenReport, Integer> PayQty;

    @FXML
    private TableColumn<FMGenReport, String> PaySupp;

    @FXML
    private TableColumn<FMGenReport, Double> PayTot;

    @FXML
    private Button PrintBtn;

    @FXML
    private LineChart<?, ?> ReportChart;

    @FXML
    private TextField ReportId;

    @FXML
    private TextField TotPay;

    @FXML
    private TableView<FMGenReport> ViewPayment;

    @FXML
    private ComboBox<String> Yearbox;

    
    public void initialize() throws IOException 
    {
    	PayDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    	PayId.setCellValueFactory(new PropertyValueFactory<>("payId"));
    	PoId.setCellValueFactory(new PropertyValueFactory<>("ID")); // PO ID
    	PayItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
    	PayItemsd.setCellValueFactory(new PropertyValueFactory<>("itemId"));
    	PayQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    	PaySupp.setCellValueFactory(new PropertyValueFactory<>("supplier"));
    	PayTot.setCellValueFactory(new PropertyValueFactory<>("total"));
    	load();
    }
    
    public void load() throws IOException
    {	int index=0;
    	FMGenReport source=new FMGenReport();
    	ObservableList<FMGenReport> obList= FXCollections.observableArrayList();
    	String[] data=source.ReadTextFile().toString().split("\n");
    	
    	for(String Line:data) 
    	{	//String PayId,String ID,String ItemId,String ItemName,String Supplier,int Qty,double Total,String date)
    		String[] parts=Line.split(",");
    		 if(parts.length==10) 
    		 {
    		
    		String[] ItemName=source.RetriveItemName().toArray(new String[0]);
    		//PY001,PO001,I0003,1501,111.0,Approve,S003,166611.00,Paid,13-04-2025
    		
    		
    		obList.add(new FMGenReport(
    				
    					parts[0],
    					parts[1],
    					parts[2],
    					ItemName[index],
    					parts[6],
    					Integer.parseInt(parts[3]),
    					Double.parseDouble(parts[7]),
    					parts[9]
    			
    				));
    		index++;
    		 }else 
    	    	{
    	    		ViewPayment.setPlaceholder(new Label("No Contents Here"));
    	    	}
    	}
    	ViewPayment.setItems(obList);
    	Yearbox.getItems().clear();
    	Yearbox.getItems().addAll(source.RetriveYear());
    	}
    
    
    
    @FXML
    void PrintClick(MouseEvent event) {

    }

}
