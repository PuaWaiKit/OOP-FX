package com.salesmanager.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;

import com.groupfx.JavaFXApp.*;

public class smItemsCtrl {
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
    
    public void rowClick() {

        ViewList selectedItem = viewItemTable.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            String id = selectedItem.getId();
            String name = selectedItem.getName();
            String supplier = selectedItem.getSupplier();
            int stock = selectedItem.getStock();
            double unitPrice = selectedItem.getUnitPrice();
            
//			  For Testing
//            System.out.println("Selected Item:");
//            System.out.println("ID: " + id);
//            System.out.println("Name: " + name);
//            System.out.println("Supplier: " + supplier);
//            System.out.println("Stock: " + stock);
//            System.out.println("Unit Price: " + unitPrice);
            txtItemsID.setText(id);
            txtItemsName.setText(name);
            txtItemSupp.setText(supplier);
            txtItemsStock.setText(String.valueOf(stock));
            txtItemsUP.setText(String.valueOf(unitPrice));
        }
    }
}
