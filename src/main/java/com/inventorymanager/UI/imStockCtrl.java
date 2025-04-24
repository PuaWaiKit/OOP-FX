package com.inventorymanager.UI;

import java.io.IOException;

import com.inventorymanager.source.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableColumn<InventoryM_Stocks, String> itemsName;

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
    
    
   
}
