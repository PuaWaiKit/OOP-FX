package com.purchasemanager.UI;

import java.io.IOException;

import com.groupfx.JavaFXApp.Purchase_Req;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PMViewReqCtrl {

    @FXML
    private TableColumn<Purchase_Req, String> ReqDate;

    @FXML
    private TableColumn<Purchase_Req, String> ReqId;

    @FXML
    private TableColumn<Purchase_Req, String> ReqItems;

    @FXML
    private TableColumn<Purchase_Req, Integer> ReqQty;

    @FXML
    private TableColumn<Purchase_Req, String> ReqSM;

    @FXML
    private TableColumn<Purchase_Req, String> ReqStatus;


    @FXML
    private TableView<Purchase_Req> ViewReq;
    
    
    
    public void initialize() throws IOException 
    {
    	//String Prid, String ItemsId, int Qty, String date, String SalesM ,String suppId,String status
    	ReqId.setCellValueFactory(new PropertyValueFactory<>("prId"));
    	ReqItems.setCellValueFactory(new PropertyValueFactory<>("itemsid"));
    	ReqQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    	ReqDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    	ReqSM.setCellValueFactory(new PropertyValueFactory<>("salesM"));
    	ReqStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	load();
    }
    
    
    public void load() throws IOException
    {
    	Purchase_Req req= new Purchase_Req();
    	ObservableList<Purchase_Req> ObList= FXCollections.observableArrayList();
    	String[] rows= req.ReadTextFile().toString().split("\n");
    	
    	for(String row:rows) 
    	{
    		String[] split= row.split(",");
    		if(split.length==6) 
    		{
    			ObList.add(new Purchase_Req(
    						split[0],
    						split[1],
    						Integer.parseInt(split[2]),
    						split[3],
    						split[4],
    						split[5]
    						
    					));
    		}
    	}
    	ViewReq.setItems(ObList);
    }

}
