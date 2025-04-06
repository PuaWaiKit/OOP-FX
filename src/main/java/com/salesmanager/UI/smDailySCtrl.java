package com.salesmanager.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.salesmanager.source.*;

public class smDailySCtrl {

	@FXML
    private TableColumn<SalesM_DailyS, String> DSID;

    @FXML
    private TableColumn<SalesM_DailyS, String> itemID;

    @FXML
    private TableColumn<SalesM_DailyS, String> itemName;

    @FXML
    private TableColumn<SalesM_DailyS, Integer> totalSales;

    @FXML
    private TableColumn<SalesM_DailyS, String> author;

    @FXML
    private TableView<SalesM_DailyS> viewSalesTable;
    
    @FXML
    private ChoiceBox<String> sortChoice;
    
    @FXML
    private TextField txtDSID;
    
    @FXML
    private TextField txtitemID;
    
    @FXML
    private TextField txtitemName;
    
    @FXML
    private TextField txttotalSales;
    
    @FXML
    private TextField txtAuthor;
    
    @FXML
    private BarChart<?,?> viewSalesChart;
    
    ObservableList<SalesM_DailyS> cacheList = FXCollections.observableArrayList(); 
    
    private HashMap<String, Integer> chartStore = new HashMap<>();
    
    public void initialize() throws IOException 
    {
    	DSID.setCellValueFactory(new PropertyValueFactory<>("id")); 
    	itemID.setCellValueFactory(new PropertyValueFactory<>("itemId"));//
    	itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        totalSales.setCellValueFactory(new PropertyValueFactory<>("totalSales"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
       
        load();
    }
    
    public void load() throws IOException 
    {
    	SalesM_DailyS listed= new SalesM_DailyS();
    	ObservableList<SalesM_DailyS> itemList= FXCollections.observableArrayList(); 
    	String[] row= listed.ReadTextFile().toString().split("\n");
    	
    	try {
	    	for(String rows: row) 
	    	{
	    		String[] spl= rows.split(",");
	    		if(spl.length==5) 
	    		{
	    			itemList.add(new SalesM_DailyS(
	    					spl[0],
	    					spl[1],
	    					spl[2],
	    					Integer.parseInt(spl[3]),
	    					spl[4]
	    					));
	    			
	    			chartStore.put(spl[1], Integer.parseInt(spl[3]));
	    		}
	    	}
	    	
	    	cacheList = itemList;
	    	viewSalesTable.setItems(cacheList);
	    	chartload();
	    	clearTextField();
    	} catch (Exception e) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    	    alert.setTitle("Error");
    	    alert.setHeaderText("Something went wrong");
    	    alert.setContentText("Error: " + e.getMessage());
    	    alert.showAndWait();
    	}
    }
    
    public void chartload() {
    	
    	viewSalesChart.getData().clear();
    	XYChart.Series series = new XYChart.Series();
    	series.setName("Items Stock");
    	for (Map.Entry<String, Integer> entry : chartStore.entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }
    	
    	viewSalesChart.getData().add(series);
    	
    }
    
	public void rowClick() {
    	
    	try {
	    	SalesM_DailyS selectedItem = viewSalesTable.getSelectionModel().getSelectedItem();
	        
	        if (selectedItem != null) {
	            String id = selectedItem.getId();
	            String itemId = selectedItem.getItemId();
	            String itemName = selectedItem.getItemName();
	            int totalSales = selectedItem.getTotalSales();
	            String auhor = selectedItem.getAuthor();
	            
//				<<For Testing>>
//	            System.out.println("Selected Item:");
//	            System.out.println("ID: " + id);
//	            System.out.println("Name: " + name);
//	            System.out.println("Supplier: " + supplier);
//	            System.out.println("Stock: " + stock);
//	            System.out.println("Unit Price: " + unitPrice);
	            
	            txtDSID.setText(id);
	            txtitemID.setText(itemId);
	            txtitemName.setText(itemName);
	            txttotalSales.setText(String.valueOf(totalSales));
	            txtAuthor.setText(String.valueOf(author));
	        }
	    } catch (Exception e) {
	    	
	    	Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error");
	        alert.setHeaderText("Something went wrong");
	        alert.setContentText("Error: " + e.getMessage());
	        alert.showAndWait();
	    }
    	
    }
    
	private boolean containsID(ObservableList<SalesM_DailyS> List, String id, String itemId, String itemName) {
		
	    for (SalesM_DailyS item : List) {
	        if (item.getId().equals(id)) {
	        	
	            return true;
	        } else if (item.getItemName().equals(itemName) && item.getItemId().equals(itemId)) {
	        	
	        	return true;
	        }
	    }
	    return false;
	}
    
    @FXML
    public void addeditClick() {
    	
    	SalesM_DailyS selectedSupp = viewSalesTable.getSelectionModel().getSelectedItem();	
    	int selectedSuppIndex = viewSalesTable.getSelectionModel().getSelectedIndex();
    	
    	try {
	    	if(containsID(cacheList, txtDSID.getText().trim(), txtitemID.getText().trim(), txtitemName.getText().trim()) && selectedSupp != null) {
	
	    		SalesM_DailyS dataEntry = new SalesM_DailyS(
	    				
	    				txtDSID.getText().trim(),
	    				txtitemID.getText().trim(),
	    				txtitemName.getText().trim(),
	    				Integer.parseInt(txttotalSales.getText()),
	    				"temp", //Use the UserID in the superclass (author), so  the system will record who edit this record
	    				cacheList, 
	    				selectedSuppIndex
	    				);
	    		
		    	dataEntry.EditFunc();
		    	ObservableList<SalesM_DailyS>  tempList = dataEntry.getCacheList();
		    	cacheList = tempList;
		    	viewSalesTable.setItems(cacheList);
		    	clearTextField();
		    	
	    	} else if (!(containsID(cacheList, txtDSID.getText().trim(), txtitemID.getText().trim(), txtitemName.getText().trim())) && selectedSupp == null){	
	    		
	    		SalesM_DailyS dataEntry = new SalesM_DailyS(
	    				
	    				txtDSID.getText().trim(),
	    				txtitemID.getText().trim(),
	    				txtitemName.getText().trim(),
	    				Integer.parseInt(txttotalSales.getText()),
	    				"temp", //Use the UserID in the superclass (author), so  the system will record who edit this record
	    				cacheList, 
	    				selectedSuppIndex
	    				);
	    		
			    dataEntry.AddFunc();
			    ObservableList<SalesM_DailyS>  tempList = dataEntry.getCacheList();
			    cacheList = tempList;
			    viewSalesTable.setItems(cacheList);
			    clearTextField();
	    	} else {
	    		
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Information");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Please select the supplier if you want to edit\n OR \n If you want to add a supplier please dont repeat the ID");
	    		alert.showAndWait();
	    	}
    	} catch (Exception e) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(String.format("Error: %s", e.toString()));
            alert.showAndWait();
    	}
    }
    
    @FXML
    public void deleteClick() {
    	int selectedSuppIndex = viewSalesTable.getSelectionModel().getSelectedIndex();
    	
    	SalesM_DailyS delIndex = new SalesM_DailyS(selectedSuppIndex, cacheList);
    	try {
    		
    		delIndex.DeleteFunc();
    		ObservableList<SalesM_DailyS>  tempList = delIndex.getCacheList();
    		cacheList = tempList;
    		viewSalesTable.setItems(cacheList);
    		clearTextField();
    		
    	} catch (Exception e) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    	    alert.setTitle("Error");
    	    alert.setHeaderText("Something went wrong");
    	    alert.setContentText("Error: " + e.getMessage());
    	    alert.showAndWait();
    	}
    }
    
    @FXML
    public void saveClick() throws IOException{
    	
    	StringBuilder result = new StringBuilder();
    	try {
	    	for (SalesM_DailyS dailyS : cacheList) {
	            
	            result.append(dailyS.getId()).append(",")
	                  .append(dailyS.getItemId()).append(",")
	                  .append(dailyS.getItemName()).append(",")
	                  .append(dailyS.getTotalSales()).append(",")
	                  .append(dailyS.getAuthor()).append("\n");  
	        }
	    	
	    	String netString = result.toString();
	    	SalesM_DailyS note = new SalesM_DailyS(netString);
	    	note.SaveFunc();
	    	
	    	clearTextField();
	    	reloadClick();
    	} catch (Exception e) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    	    alert.setTitle("Error");
    	    alert.setHeaderText("Something went wrong");
    	    alert.setContentText("Error: " + e.getMessage());
    	    alert.showAndWait();
    	}
    }
    
    @FXML
    public void reloadClick() throws IOException {
    	cacheList.clear();
    	load();
    }
    
    public void clearTextField() {
    	
    	TextField[] textFields = {txtDSID, txtitemID, txtitemName, txttotalSales, txtAuthor};
    	for (TextField field : textFields) {
    	    field.clear();      	
    	}
    }
}
