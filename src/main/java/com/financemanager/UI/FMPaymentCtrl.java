package com.financemanager.UI;

import java.io.IOException;

import com.financemanager.source.FMPayment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class FMPaymentCtrl {

    @FXML
    private Button ApproveBtn;

    @FXML
    private TableColumn<FMPayment, String> POId;

    @FXML
    private TextField PayId;

    @FXML
    private TextField PayItem;

    @FXML
    private TextField PayQty;

    @FXML
    private TextField PaySupp;

    @FXML
    private TextField PayTot;

    @FXML
    private TextField PayUp;

    @FXML
    private TableColumn<FMPayment, String> PoItem;

    @FXML
    private TableColumn<FMPayment, Integer> PoQty;

    @FXML
    private TableColumn<FMPayment, String> PoStat;

    @FXML
    private TableColumn<FMPayment, String> PoSupp;

    @FXML
    private TableColumn<FMPayment, Double> PoUp;

    @FXML
    private Button RefBtn;

    @FXML
    private Button SaveBtn;

    @FXML
    private TableView<FMPayment> ViewPO;
    
    
    public void initialize() throws IOException
    {
    	POId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	PoItem.setCellValueFactory(new PropertyValueFactory<>("name"));
    	PoQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    	PoUp.setCellValueFactory(new PropertyValueFactory<>("price"));
    	PoSupp.setCellValueFactory(new PropertyValueFactory<>("pm"));
    	PoStat.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	load();
    }
    
    public void load() throws IOException 
    {
    	FMPayment Source= new FMPayment();
    	ObservableList<FMPayment> obList= FXCollections.observableArrayList();
    	String[] firstData= Source.LoadData().toString().split("\n");
    	for(String rows: firstData) 
    	{
    		String[] realData= rows.split(",");
    		obList.add(new FMPayment(
    					realData[0],
    					realData[1],
    					Integer.parseInt(realData[2]),
    					Double.parseDouble(realData[3]),
    					realData[4],
    					realData[5]
    				));
    	}
    	ViewPO.setItems(obList);
    }
    
    

    @FXML
    void ApproveClick(MouseEvent event) {

    }

    @FXML
    void RefreshClick(MouseEvent event) {

    }

    @FXML
    void SaveClick(MouseEvent event) {

    }

}
