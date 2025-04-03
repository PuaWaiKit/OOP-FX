package com.salesmanager.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.salesmanager.source.*;

public class smItemsCtrl {
	@FXML
    private TableColumn<SalesM_Items, String> ItemsID;

    @FXML
    private TableColumn<SalesM_Items, String> ItemsName;

    @FXML
    private TableColumn<SalesM_Items, String> ItemsSupp;

    @FXML
    private TableColumn<SalesM_Items, Integer> itemsStock;

    @FXML
    private TableColumn<SalesM_Items, Double> itemsUP;

    @FXML
    private TableView<SalesM_Items> viewItemTable;
    
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
    
    private HashMap<String, Integer> chartStore = new HashMap<>();
    
    public void initialize() throws IOException 
    {
    	ItemsID.setCellValueFactory(new PropertyValueFactory<>("id")); // Use the ViewItemList.getID method
    	ItemsName.setCellValueFactory(new PropertyValueFactory<>("name"));// same as above but the method different
        ItemsSupp.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        itemsStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        itemsUP.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        
        load();
        chartload();
    }
    
    public void load() throws IOException 
    {
    	SalesM_Items listed= new SalesM_Items();
    	ObservableList<SalesM_Items> itemList= FXCollections.observableArrayList(); 
    	String[] row= listed.ReadTextFile().toString().split("\n");
    	
    	for(String rows: row) 
    	{
    		String[] spl= rows.split(",");
    		if(spl.length==5) 
    		{
    			itemList.add(new SalesM_Items(
    					spl[0],
    					spl[1],
    					spl[2],
    					Integer.parseInt(spl[3]),
    					Double.parseDouble(spl[4])
    					));
    			
    			chartStore.put(spl[1], Integer.parseInt(spl[3]));
    		}
    	}
    	
    	viewItemTable.setItems(itemList);
    }
    
    public void chartload() {
    	
    	XYChart.Series series = new XYChart.Series();
    	series.setName("Items Stock");
    	for (Map.Entry<String, Integer> entry : chartStore.entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }
    	
    	viewStockChart.getData().add(series);
    	
    }
    
    public void rowClick() {
    	
    	try {
	    	SalesM_Items selectedItem = viewItemTable.getSelectionModel().getSelectedItem();
	        
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
}
