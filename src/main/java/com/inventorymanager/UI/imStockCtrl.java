package com.inventorymanager.UI;

import java.io.IOException;

import com.inventorymanager.source.*;
import com.salesmanager.source.SalesM_Items;
import com.salesmanager.source.SalesM_Suppliers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class imStockCtrl {
	
	//For Purchase Order Table
	@FXML
	private TableView<InventoryM_Stocks> POtable;
	
	@FXML
    private TableColumn<InventoryM_Stocks, String> posID;
	
	@FXML
    private TableColumn<InventoryM_Stocks, String> itemsID;

    @FXML
    private TableColumn<InventoryM_Stocks, String> posStatus;

    @FXML
    private TableColumn<InventoryM_Stocks, Integer> posQuantity;

    @FXML
    private TableColumn<InventoryM_Stocks, Double> posPrice;
    
    
    //For Purchase Order Table
    
  	@FXML
  	private TableView<InventoryM_Stocks> stocktable;
  	
  	@FXML
    private TableColumn<InventoryM_Stocks, String> itemID;

    @FXML
    private TableColumn<InventoryM_Stocks, String> itemName;

    @FXML
    private TableColumn<InventoryM_Stocks, Integer> itemStock;
    
    //For TextField
    @FXML
    private TextField txtPOID;
    
    @FXML
    private TextField txtUpdateStock;
    
    @FXML
    private TextField txtItemsID;
    
    @FXML
    private TextField txtItemsName;
    
    @FXML
    private TextField txtItemsStock;
    
    @FXML
    private ObservableList<InventoryM_Stocks> cachePOList = FXCollections.observableArrayList();
    
    @FXML
    private ObservableList<InventoryM_Stocks> cacheStockList = FXCollections.observableArrayList();
    
    public void initialize() throws IOException 
    {
    	posID.setCellValueFactory(new PropertyValueFactory<>("posID")); // Use the ViewItemList.getID method
    	itemsID.setCellValueFactory(new PropertyValueFactory<>("itemsID"));// same as above but the method different
    	posStatus.setCellValueFactory(new PropertyValueFactory<>("posStatus"));
        posQuantity.setCellValueFactory(new PropertyValueFactory<>("posQuantity"));
        posPrice.setCellValueFactory(new PropertyValueFactory<>("posPrice"));
        
        itemID.setCellValueFactory(new PropertyValueFactory<>("itemStockID"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemStockName"));
        itemStock.setCellValueFactory(new PropertyValueFactory<>("itemStock"));
        
        load();
    }
   
    public void load() throws IOException 
    {
    	InventoryM_Stocks listed= new InventoryM_Stocks();
    	ObservableList<InventoryM_Stocks> POList = FXCollections.observableArrayList();
    	
    	//Use String Builder to form a string that contain data we need
    	String[] poRow = listed.ReadTextFile().toString().split("\n");
    	
    	for(String rows: poRow) {
    		
    		String[] spl= rows.split(",");
    		if(spl.length==8) 
    		{	
    			if(spl[5].equals("Approve") && spl[7].equals("Pending")) {
    				
	    			POList.add(new InventoryM_Stocks(
	    					
	    				spl[0],
	    				spl[1],
	    				Integer.parseInt(spl[2]),
	    				Double.parseDouble(spl[3]),
	    				spl[7]
	    				));
    			}
    		}	
    	}
    	
    	cachePOList = POList;
    	POtable.setItems(cachePOList);
    	
    	
    	ObservableList<InventoryM_Stocks> stockList = FXCollections.observableArrayList();
    	
    	//For Stock Table
    	String[] stockRow = listed.ReadStockTextFile().toString().split("\n");
    	
    	for(String rows: stockRow) {
    		
    		String[] spl= rows.split(",");
    		if(spl.length==5) 
    		{
    			stockList.add(new InventoryM_Stocks(
    					
    					spl[0],
    					spl[1],
    					Integer.parseInt(spl[3])
    					));
    		}	
    	}
    	
    	cacheStockList = stockList;
    	stocktable.setItems(cacheStockList);
    }
    
    public void reloadClick() throws IOException {
    	
    	cachePOList.clear();
    	cacheStockList.clear();
    	
    	load();
    }
    
    public void poRowClick() {
    	
    	try {
	    	InventoryM_Stocks selectedItem = POtable.getSelectionModel().getSelectedItem();
	        
	        if (selectedItem != null) {
	        	
	            String id = selectedItem.getPosID();
	            int uptQuantity = selectedItem.getPosQuantity();
	            
	            txtPOID.setText(id);
	            txtUpdateStock.setText(String.valueOf(uptQuantity));
	            
	        }
	    } catch (Exception e) {
	    	
	    	System.out.println("Error: " + e);
	    }
    }
    
    public void stockRowClick() {
    	
    	try {
	    	InventoryM_Stocks selectedItem = stocktable.getSelectionModel().getSelectedItem();
	        
	        if (selectedItem != null) {
	        	
	            String id = selectedItem.getItemStockID();
	            String name = selectedItem.getItemStockName();
	            int stock = selectedItem.getItemStock();
	            
	            txtItemsID.setText(id);
	            txtItemsName.setText(name);
	            txtItemsStock.setText(String.valueOf(stock));
	            
	        }
	    } catch (Exception e) {
	    	
	    	System.out.println("Error: " + e);
	    }
    }

    public void saveClick() {
    	
    }
    
    public void updateStockClick() {
    	
    	InventoryM_Stocks selectedPO = POtable.getSelectionModel().getSelectedItem();	
    	
    	try {
    		
    		
    	} catch (Exception e) {
    		
    		
    	}
    }
}
