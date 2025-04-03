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
    
    private ObservableList<SalesM_Suppliers> itemList= FXCollections.observableArrayList(); 
    
    private ObservableList<SalesM_Suppliers> cacheList = FXCollections.observableArrayList();
    
    private Map<String, String> dataRecord = new LinkedHashMap<>();
    
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
    
    @FXML
    public void addeditClick() {
    	
    	SalesM_Suppliers selectedSupp = viewSuppsTable.getSelectionModel().getSelectedItem();
    	int selectedSuppIndex = viewSuppsTable.getSelectionModel().getSelectedIndex();
    	
    	try {
	    	if (selectedSupp == null) {
	    		cacheList.add(new SalesM_Suppliers(		
	    				
						txtID.getText(),
						txtName.getText(),
						txtContactN.getText(),
						txtAddress.getText(),
						txtItemID.getText()
						));
	    		
	    		dataRecord.put(txtID.getText(), "add");
	    		viewSuppsTable.setItems(cacheList);
	    		
	    	} else {
	    		
	    		SalesM_Suppliers editData = new SalesM_Suppliers(txtID.getText(), txtName.getText(),txtContactN.getText(),txtAddress.getText(),txtItemID.getText());
	    		cacheList.set(selectedSuppIndex, editData);
	    		
	    		dataRecord.put(txtID.getText(), "edit");
	    		viewSuppsTable.setItems(cacheList);
	    		
	    	}
    	} catch (Exception e) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    	    alert.setContentText(String.format("Error: %s", e.getMessage()));
    	    alert.showAndWait();
    	}
    }
    
    @FXML
    public void deleteClick() {
    	int selectedSuppIndex = viewSuppsTable.getSelectionModel().getSelectedIndex();
    	try {
    		
    		cacheList.remove(selectedSuppIndex);
    		dataRecord.put(txtID.getText(), "del");
    		viewSuppsTable.setItems(cacheList);
    		
    	} catch (Exception e) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Okay this guy tried to remove something that doesnt exist");
    	}
    }
    
    @FXML
    public void saveClick() {
    	
    	StringBuilder result = new StringBuilder();
    	for (SalesM_Suppliers supplier : itemList) {
            // 拼接属性，属性之间用 "-" 分隔
            result.append(supplier.getId()).append("-")
                  .append(supplier.getName()).append("-")
                  .append(supplier.getAddress()).append("-")
                  .append(supplier.getContactNum()).append("-")
                  .append(supplier.getItem()).append("\n");  
        }
    	
    	String netString = result.toString();
    	
    	SalesM_Suppliers note = new SalesM_Suppliers(netString, dataRecord);
    	note.SaveFunc();
    }
    
    @FXML
    public void reloadClick() throws IOException {
    	cacheList.clear();
    	load();
    }
}
