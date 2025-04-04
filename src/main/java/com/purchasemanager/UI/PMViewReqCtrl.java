package com.purchasemanager.UI;

import java.io.IOException;

import com.PM.Sources.PMViewReq;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PMViewReqCtrl {

    @FXML
    private TableColumn<PMViewReq, String> ReqDate;

    @FXML
    private TableColumn<PMViewReq, String> ReqId;

    @FXML
    private TableColumn<PMViewReq, String> ReqItems;

    @FXML
    private TableColumn<PMViewReq, Integer> ReqQty;

    @FXML
    private TableColumn<PMViewReq, String> ReqSM;

    @FXML
    private TableColumn<PMViewReq, String> ReqStatus;

    @FXML
    private TableColumn<PMViewReq, String> ReqSuppID;

    @FXML
    private TableView<PMViewReq> ViewReq;
    
    
    
    public void initialize() throws IOException 
    {
    	//String Prid, String ItemsId, int Qty, String date, String SalesM ,String suppId,String status
    	ReqId.setCellValueFactory(new PropertyValueFactory<>("prId"));
    	ReqItems.setCellValueFactory(new PropertyValueFactory<>("itemsid"));
    	ReqQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    	ReqDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    	ReqSM.setCellValueFactory(new PropertyValueFactory<>("salesM"));
    	ReqSuppID.setCellValueFactory(new PropertyValueFactory<>("supp"));
    	ReqStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	load();
    }
    
    
    public void load() throws IOException
    {
    	PMViewReq req= new PMViewReq();
    	ObservableList<PMViewReq> ObList= FXCollections.observableArrayList();
    	String[] rows= req.ReadTextFile().toString().split("\n");
    	
    	for(String row:rows) 
    	{
    		String[] split= row.split(",");
    		if(split.length==7) 
    		{
    			ObList.add(new PMViewReq(
    						split[0],
    						split[1],
    						Integer.parseInt(split[2]),
    						split[3],
    						split[4],
    						split[5],
    						split[6]
    					));
    		}
    	}
    	ViewReq.setItems(ObList);
    }

}
