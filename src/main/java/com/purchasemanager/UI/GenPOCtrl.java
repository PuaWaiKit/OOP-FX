package com.purchasemanager.UI;

import java.io.IOException;

import com.PM.Sources.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class GenPOCtrl {

    @FXML
    private Button Addbtn;

    @FXML
    private Button DelBtn;

    @FXML
    private TextField IdTxtbx;

    @FXML
    private TextField ItemsNameTxt;

    @FXML
    private TextField PMtxt;

    @FXML
    private TableColumn<PurchaseOrderData, String> POIName;

    @FXML
    private TableColumn<PurchaseOrderData, String> POid;

    @FXML
    private TableColumn<PurchaseOrderData, Double> POprice;

    @FXML
    private TableColumn<PurchaseOrderData, Integer> POqty;

    @FXML
    private TableColumn<PurchaseOrderData, String> POso;

    @FXML
    private TextField Pricetxt;

    @FXML
    private TextField QtyTxt;

    @FXML
    private Button RefBtn;

    @FXML
    private Button UploadBtn;

    @FXML
    private TableView<PurchaseOrderData> ViewPO;

    @FXML
    private Button backBtn;

    
    public void initialize() throws IOException
    {
    	POid.setCellValueFactory(new PropertyValueFactory<>("id"));
    	POIName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	POqty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    	POprice.setCellValueFactory(new PropertyValueFactory<>("price"));
    	POso.setCellValueFactory(new PropertyValueFactory<>("pm"));
    	
    	load();
    }
    
    public void load() throws IOException 
    {
    	PurchaseOrderData data= new PurchaseOrderData();
    	ObservableList<PurchaseOrderData> obList= FXCollections.observableArrayList();
    	String[] rows= data.ViewPo().toString().split("\n");
    	
    	for(String row:rows) 
    	{	String[] split=row.split(",");
    		if(split.length==5) 
    		{
    			obList.add(new PurchaseOrderData(
    					split[0],
    					split[1],
    					Integer.parseInt(split[2]),
    					Double.parseDouble(split[3]),
    					split[4]
    					));
    		}
    	}
    	ViewPO.setItems(obList);
    	
    }
    
    
    @FXML
    public void AddClick(MouseEvent event) {
    	//String Id, String name, String Quantity, String Price, String Pm
    	PurchaseOrderData data= new PurchaseOrderData(IdTxtbx.getText(),ItemsNameTxt.getText(),QtyTxt.getText(),Pricetxt.getText(),PMtxt.getText());
    	data.AddFunc();
    	if(data.checkingFunc()) 
    	{
    		Alert alert= new Alert(AlertType.INFORMATION);
    		alert.setTitle("Adding Sucess");
    		alert.setHeaderText(null);
    		alert.setContentText("Adding Sucessfull !");
    		alert.showAndWait();
    	}
    	else 
    	{
    		Alert alert= new Alert(AlertType.INFORMATION);
    		alert.setTitle("Adding Failed");
    		alert.setHeaderText(null);
    		alert.setContentText("Adding Failed !");
    		alert.showAndWait();
    	}
    }

    @FXML
    void DelClick(MouseEvent event) {

    }

    @FXML
    public void RefClick(MouseEvent event) throws IOException {
    	load();
    }

    @FXML
    void backClick(MouseEvent event) {

    }

    @FXML
    void upClick(MouseEvent event) {

    }

}
