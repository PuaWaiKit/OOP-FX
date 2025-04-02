package com.salesmanager.UI;

import java.io.IOException;

import com.groupfx.JavaFXApp.ViewList;
import com.salesmanager.source.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class smPRsCtrl {
	
	@FXML
    private TableColumn<SalesM_PRs, String> PRsID;

    @FXML
    private TableColumn<SalesM_PRs, String> Item_ID;

    @FXML
    private TableColumn<SalesM_PRs, Integer> Quantity;

    @FXML
    private TableColumn<SalesM_PRs, Integer> Budget;

    @FXML
    private TableColumn<SalesM_PRs, String> SalesM;
    
    @FXML
    private TableColumn<SalesM_PRs, String> Status;

    @FXML
    private TableView<SalesM_PRs> viewPRsTable;
    
    @FXML
    private TextField txtPRsID;
    
    @FXML
    private TextField txtItem_ID;
    
    @FXML
    private TextField txtQuantity;
    
    @FXML
    private TextField txtBudget;
    
    @FXML
    private TextField txtSalesM;
    
    @FXML
    private TextField txtStatus;
    
    public void initialize() throws IOException 
    {
    	PRsID.setCellValueFactory(new PropertyValueFactory<>("id")); // Use the SalesM_PRs.getID method
    	Item_ID.setCellValueFactory(new PropertyValueFactory<>("item_ID"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
        SalesM.setCellValueFactory(new PropertyValueFactory<>("salesM"));
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        load();
    }
    
    public void load() throws IOException 
    {
    	SalesM_PRs listed= new SalesM_PRs();
    	ObservableList<SalesM_PRs> itemList= FXCollections.observableArrayList(); 
    	String[] row= listed.ReadTextFile().toString().split("\n");
    	
    	for(String rows: row) 
    	{
    		String[] spl= rows.split(",");
    		if(spl.length==6) 
    		{
    			itemList.add(new SalesM_PRs(
    					
    					spl[0],
    					spl[1],
    					Integer.parseInt(spl[2]),
    					Integer.parseInt(spl[3]),
    					spl[4],
    					spl[5]
    					));
    			
    		}
    	}
    	
    	viewPRsTable.setItems(itemList);
    }
    
    public void rowClick() {

        SalesM_PRs selectedItem = viewPRsTable.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            String id = selectedItem.getId();
            String itemId = selectedItem.getItem_ID();
            int quantity = selectedItem.getQuantity();
            int budget = selectedItem.getBudget();
            String salesM = selectedItem.getSalesM();
            String status = selectedItem.getStatus();
            
            txtPRsID.setText(id);
            txtItem_ID.setText(itemId);
            txtQuantity.setText(String.valueOf(quantity));
            txtBudget.setText(String.valueOf(budget));
            txtSalesM.setText(salesM);
            txtStatus.setText(status);
        }
    }
}
