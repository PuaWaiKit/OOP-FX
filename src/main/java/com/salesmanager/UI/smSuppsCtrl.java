package com.salesmanager.UI;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.salesmanager.source.SalesM_Suppliers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class smSuppsCtrl {
	@FXML
    private TableColumn<SalesM_Suppliers, String> SuppsID;

    @FXML
    private TableColumn<SalesM_Suppliers, String> Name;

    @FXML
    private TableColumn<SalesM_Suppliers, String> ContactNum;

    @FXML
    private TableColumn<SalesM_Suppliers, String> Address;

    @FXML
    private TableColumn<SalesM_Suppliers, String> Item_ID;
    
    @FXML
    private TableView<SalesM_Suppliers> viewSuppsTable;
    
    @FXML
    private TextField txtID;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private TextField txtContactN;
    
    @FXML
    private TextField txtAddress;
    
    @FXML
    private TextField txtItemID;
    
    private ObservableList<SalesM_Suppliers> cacheList = FXCollections.observableArrayList();
    
    public void initialize() throws IOException 
    {
    	SuppsID.setCellValueFactory(new PropertyValueFactory<>("id")); // Use the SalesM_PRs.getID method
    	Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ContactNum.setCellValueFactory(new PropertyValueFactory<>("contactNum"));
        Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        Item_ID.setCellValueFactory(new PropertyValueFactory<>("item"));
        
        load();
    }
    
    public void load() throws IOException 
    {
    	SalesM_Suppliers listed= new SalesM_Suppliers();
    	ObservableList<SalesM_Suppliers> itemList= FXCollections.observableArrayList();
    	String[] row= listed.ReadTextFile().toString().split("\n");
    	
    	for(String rows: row) 
    	{
    		String[] spl= rows.split(",");
    		if(spl.length==5) 
    		{
    			itemList.add(new SalesM_Suppliers(
    					
    					spl[0],
    					spl[1],
    					spl[2],
    					spl[3],
    					spl[4]
    					));
    		}
    	}
    	
    	cacheList = itemList;
    	viewSuppsTable.setItems(cacheList);
    }
    
    @FXML
    public void rowClick() {

        SalesM_Suppliers selectedItem = viewSuppsTable.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
        	
            String id = selectedItem.getId();
            String Name = selectedItem.getName();
            String ContactN = selectedItem.getContactNum();
            String Address = selectedItem.getAddress();
            String ItemID = selectedItem.getItem();
            
            txtID.setText(id);
            txtName.setText(Name);
            txtContactN.setText(ContactN);
            txtAddress.setText(Address);
            txtItemID.setText(ItemID);
            
            txtID.setEditable(false);
        }
    }
    
    private boolean containsID(ObservableList<SalesM_Suppliers> List, String id) {
        for (SalesM_Suppliers supplier : List) {
            if (supplier.getId().equals(id)) {
            	
                return true;
            }
        }
        return false;
    }
    
    @FXML
    public void addeditClick() {
    	
    	SalesM_Suppliers selectedSupp = viewSuppsTable.getSelectionModel().getSelectedItem();	
    	int selectedSuppIndex = viewSuppsTable.getSelectionModel().getSelectedIndex();
    	
    	try {
	    	if(containsID(cacheList, txtID.getText()) && selectedSupp != null) {
	
	    		SalesM_Suppliers dataEntry = new SalesM_Suppliers(txtID.getText(), txtName.getText(),txtContactN.getText(),txtAddress.getText(),txtItemID.getText(), cacheList, selectedSuppIndex);
		    	dataEntry.EditFunc();
		    	ObservableList<SalesM_Suppliers>  tempList = dataEntry.getCacheList();
		    	cacheList = tempList;
		    	viewSuppsTable.setItems(cacheList);
		    	clearTextField();
		    	
	    	} else if (!(containsID(cacheList, txtID.getText())) && selectedSupp == null){	
	    		
	    		SalesM_Suppliers dataEntry = new SalesM_Suppliers(txtID.getText(), txtName.getText(),txtContactN.getText(),txtAddress.getText(),txtItemID.getText(), cacheList, selectedSuppIndex);
			    dataEntry.AddFunc();
			    ObservableList<SalesM_Suppliers>  tempList = dataEntry.getCacheList();
			    cacheList = tempList;
			    viewSuppsTable.setItems(cacheList);
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
    	int selectedSuppIndex = viewSuppsTable.getSelectionModel().getSelectedIndex();
    	
    	SalesM_Suppliers delIndex = new SalesM_Suppliers(selectedSuppIndex, cacheList);
    	try {
    		
    		delIndex.DeleteFunc();
    		ObservableList<SalesM_Suppliers>  tempList = delIndex.getCacheList();
    		cacheList = tempList;
    		viewSuppsTable.setItems(cacheList);
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
    	for (SalesM_Suppliers supplier : cacheList) {
            
            result.append(supplier.getId()).append(",")
                  .append(supplier.getName()).append(",")
                  .append(supplier.getContactNum()).append(",")
                  .append(supplier.getAddress()).append(",")
                  .append(supplier.getItem()).append("\n");  
        }
    	
    	String netString = result.toString();
    	SalesM_Suppliers note = new SalesM_Suppliers(netString);
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
    	
    	TextField[] textFields = {txtID, txtName, txtContactN, txtAddress, txtItemID};
    	for (TextField field : textFields) {
    	    field.clear();      	
    	}
    }
}
