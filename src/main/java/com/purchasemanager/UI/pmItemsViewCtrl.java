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
    private TableColumn<ViewItemList, String> ItemsID;

    @FXML
    private TableColumn<ViewItemList, String> ItemsName;

    @FXML
    private TableColumn<ViewItemList, String> ItemsSupp;

    @FXML
    private TableColumn<ViewItemList, Integer> itemsStock;

    @FXML
    private TableColumn<ViewItemList, Double> itemsUP;

    @FXML
    private TableView<ViewItemList> viewItemTable;
    
    
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
    	ViewItemList listed= new ViewItemList();
    	ObservableList<ViewItemList> itemList= FXCollections.observableArrayList(); 
    	String[] row= listed.ReadTextFile().toString().split("\n");
    	
    	for(String rows: row) 
    	{
    		String[] spl= rows.split(",");
    		if(spl.length==5) 
    		{
    			itemList.add(new ViewItemList(
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
