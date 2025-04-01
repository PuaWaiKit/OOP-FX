package com.purchasemanager.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import com.groupfx.JavaFXApp.*;

public class pmItemsViewCtrl {

    @FXML
    private TableColumn<ViewList, String> ItemsID;

    @FXML
    private TableColumn<ViewList, String> ItemsName;

    @FXML
    private TableColumn<ViewList, String> ItemsSupp;

    @FXML
    private TableColumn<ViewList, Integer> itemsStock;

    @FXML
    private TableColumn<ViewList, Double> itemsUP;

    @FXML
    private TableView<ViewList> viewItemTable;
    
    
    public void initialize() throws IOException 
    {
    	ItemsID.setCellValueFactory(new PropertyValueFactory<>("id")); // Use the ViewList.getID method
    	ItemsName.setCellValueFactory(new PropertyValueFactory<>("name"));// same as above but the method different
        ItemsSupp.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        itemsStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        itemsUP.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        
        load();
    }
    
    public void load() throws IOException 
    {
    	ViewList listed= new ViewList();
    	ObservableList<ViewList> itemList= FXCollections.observableArrayList(); 
    	String[] row= listed.ReadTextFile().toString().split("\n");
    	
    	for(String rows: row) 
    	{
    		String[] spl= rows.split(",");
    		if(spl.length==5) 
    		{
    			itemList.add(new ViewList(
    					spl[0],
    					spl[1],
    					spl[2],
    					Integer.parseInt(spl[3]),
    					Double.parseDouble(spl[4])
    					));
    		}
    	}
    	
    	viewItemTable.setItems(itemList);
    }
    

}
