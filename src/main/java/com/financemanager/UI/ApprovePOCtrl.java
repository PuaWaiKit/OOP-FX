package com.financemanager.UI;

import com.financemanager.source.FMAppPO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ApprovePOCtrl {

    @FXML
    private Button AppEdit;

    @FXML
    private TableColumn<FMAppPO, String> AppID;

    @FXML
    private TextField AppIdBx;

    @FXML
    private TableColumn<FMAppPO, String> AppItem;

    @FXML
    private TableColumn<FMAppPO, String> AppPM;

    @FXML
    private TableColumn<FMAppPO, Double> AppPrice;

    @FXML
    private TableColumn<FMAppPO, Integer> AppQty;

    @FXML
    private TextField AppQtyBx;

    @FXML
    private Button AppRef;

    @FXML
    private Button AppSave;

    @FXML
    private TableColumn<FMAppPO, String> AppStat;

    @FXML
    private TextField AppSuppBx;

    @FXML
    private TableView<FMAppPO> ViewPO;
    
    
    public void initialize() 
    {
    	AppID.setCellValueFactory(new PropertyValueFactory<>("itemsId"));
    }

    @FXML
    void EditClick(MouseEvent event) {

    }

    @FXML
    void RefreshClick(MouseEvent event) {

    }

    @FXML
    void SaveClick(MouseEvent event) {

    }

}
