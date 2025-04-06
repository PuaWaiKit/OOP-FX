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
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.salesmanager.source.*;

public class smDailySCtrl {

	@FXML
    private TableColumn<SalesM_DailyS, String> ItemsID;

    @FXML
    private TableColumn<SalesM_DailyS, String> ItemsName;

    @FXML
    private TableColumn<SalesM_DailyS, String> ItemsSupp;

    @FXML
    private TableColumn<SalesM_DailyS, Integer> itemsStock;

    @FXML
    private TableColumn<SalesM_DailyS, Double> itemsUP;

    @FXML
    private TableView<SalesM_DailyS> viewItemTable;
    
    @FXML
    private TextField txtItemsID;
    
    @FXML
    private TextField txtItemsName;
    
    @FXML
    private TextField txtItemsStock;
    
    @FXML
    private TextField txtItemsUP;
    
    @FXML
    private TextField txtItemSupp;
    
    @FXML
    private BarChart<?,?> viewStockChart;
    
    ObservableList<SalesM_DailyS> cacheList = FXCollections.observableArrayList(); 
    
    private HashMap<String, Integer> chartStore = new HashMap<>();
    
    public void initialize() throws IOException 
    {
    	ItemsID.setCellValueFactory(new PropertyValueFactory<>("id")); // Use the ViewItemList.getID method
    	ItemsName.setCellValueFactory(new PropertyValueFactory<>("name"));// same as above but the method different
        ItemsSupp.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        itemsStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        itemsUP.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        
        load();
    }
    
    public void load() throws IOException 
    {
    	SalesM_DailyS listed= new SalesM_DailyS();
    	ObservableList<SalesM_DailyS> itemList= FXCollections.observableArrayList(); 
    	String[] row= listed.ReadTextFile().toString().split("\n");
    	
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
    					Double.parseDouble(spl[4])
    					));
    			
    			chartStore.put(spl[1], Integer.parseInt(spl[3]));
    		}
    	}
    	
    	cacheList = itemList;
    	viewItemTable.setItems(cacheList);
    	chartload();
    	clearTextField();
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
    
    public void rowClick() {
    	
    	try {
	    	SalesM_DailyS selectedItem = viewItemTable.getSelectionModel().getSelectedItem();
	        
	        if (selectedItem != null) {
	            String id = selectedItem.getId();
	            String name = selectedItem.getName();
	            String supplier = selectedItem.getSupplier();
	            int stock = selectedItem.getStock();
	            double unitPrice = selectedItem.getUnitPrice();
	            
//				<<For Testing>>
//	            System.out.println("Selected Item:");
//	            System.out.println("ID: " + id);
//	            System.out.println("Name: " + name);
//	            System.out.println("Supplier: " + supplier);
//	            System.out.println("Stock: " + stock);
//	            System.out.println("Unit Price: " + unitPrice);
	            
	            txtItemsID.setText(id);
	            txtItemsName.setText(name);
	            txtItemSupp.setText(supplier);
	            txtItemsStock.setText(String.valueOf(stock));
	            txtItemsUP.setText(String.valueOf(unitPrice));
	        }
	    } catch (Exception e) {
	    	
	    }
    	
    }
    
    private boolean containsID(ObservableList<SalesM_DailyS> List, String id, String Name, String suppId) {
        for (SalesM_DailyS item : List) {
            if (item.getId().equals(id)) {
            	
                return true;
            } else if (item.getName().equals(Name) && item.getSupplier().equals(suppId)) {
            	
            	return true;
            }
        }
        return false;
    }
    
    @FXML
    public void addeditClick() {
    	
    	SalesM_DailyS selectedSupp = viewItemTable.getSelectionModel().getSelectedItem();	
    	int selectedSuppIndex = viewItemTable.getSelectionModel().getSelectedIndex();
    	
    	try {
	    	if(containsID(cacheList, txtItemsID.getText(), txtItemsName.getText(), txtItemSupp.getText()) && selectedSupp != null) {
	
	    		SalesM_DailyS dataEntry = new SalesM_DailyS(
	    				
	    				txtItemsID.getText().trim(),
	    				txtItemsName.getText().trim(),
	    				txtItemSupp.getText().trim(),
	    				Integer.parseInt(txtItemsStock.getText()),
	    				Double.parseDouble(txtItemsUP.getText()),
	    				cacheList, selectedSuppIndex
	    				);
	    		
		    	dataEntry.EditFunc();
		    	ObservableList<SalesM_DailyS>  tempList = dataEntry.getCacheList();
		    	cacheList = tempList;
		    	viewItemTable.setItems(cacheList);
		    	clearTextField();
		    	
	    	} else if (!(containsID(cacheList, txtItemsID.getText().trim(), txtItemsName.getText().trim(), txtItemSupp.getText().trim())) && selectedSupp == null){	
	    		
	    		SalesM_DailyS dataEntry = new SalesM_DailyS(
	    				
	    				txtItemsID.getText().trim(),
	    				txtItemsName.getText().trim(),
	    				txtItemSupp.getText().trim(),
	    				Integer.parseInt(txtItemsStock.getText()),
	    				Double.parseDouble(txtItemsUP.getText()),
	    				cacheList, selectedSuppIndex
	    				);
	    		
			    dataEntry.AddFunc();
			    ObservableList<SalesM_DailyS>  tempList = dataEntry.getCacheList();
			    cacheList = tempList;
			    viewItemTable.setItems(cacheList);
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
    	int selectedSuppIndex = viewItemTable.getSelectionModel().getSelectedIndex();
    	
    	SalesM_DailyS delIndex = new SalesM_DailyS(selectedSuppIndex, cacheList);
    	try {
    		
    		delIndex.DeleteFunc();
    		ObservableList<SalesM_DailyS>  tempList = delIndex.getCacheList();
    		cacheList = tempList;
    		viewItemTable.setItems(cacheList);
    		clearTextField();
    		
    	} catch (Exception e) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Okay this guy tried to remove something that doesnt exist");
    		alert.showAndWait();
    	}
    }
    
    @FXML
    public void saveClick() throws IOException{
    	
    	StringBuilder result = new StringBuilder();
    	for (SalesM_DailyS supplier : cacheList) {
            
            result.append(supplier.getId()).append(",")
                  .append(supplier.getName()).append(",")
                  .append(supplier.getSupplier()).append(",")
                  .append(supplier.getStock()).append(",")
                  .append(supplier.getUnitPrice()).append("\n");  
        }
    	
    	String netString = result.toString();
    	SalesM_DailyS note = new SalesM_DailyS(netString);
    	note.SaveFunc();
    	
    	clearTextField();
    	reloadClick();
    	
    }
    
    @FXML
    public void reloadClick() throws IOException {
    	cacheList.clear();
    	load();
    }
    
    public void clearTextField() {
    	
    	TextField[] textFields = {txtItemsID, txtItemsName, txtItemsStock, txtItemsUP, txtItemSupp};
    	for (TextField field : textFields) {
    	    field.clear();      	
    	}
    }
}
