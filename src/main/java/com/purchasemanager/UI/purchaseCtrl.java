package com.purchasemanager.UI;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;



import javafx.fxml.Initializable;

public class purchaseCtrl implements Initializable{
	@FXML
    private Button POBtn;

    @FXML
    private Button ReqBtn;

    @FXML
    private Button SuppBtn;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private AnchorPane drawerPane;

//    @FXML
//    private FontIcon exitBtn;

    @FXML
    private Button exitIco;

    @FXML
    private Button infoBtn;

    @FXML
    private Button itemsBtn;

    @FXML
    private AnchorPane nvgPane;

    
	@Override
    public void initialize(URL location, ResourceBundle resources) {
        loadNewContent("/fxml/pmViewItems.fxml");
    }
	
	
	public void loadNewContent(String fxmlFile) {
		
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), contentPane);
        fadeOut.setFromValue(1.0);
       	fadeOut.setToValue(0.0); 
       	fadeOut.setCycleCount(1);

       	fadeOut.setOnFinished(event -> {
    	 try {
        	     AnchorPane newContent = FXMLLoader.load(getClass().getResource(fxmlFile));
        	     contentPane.getChildren().setAll(newContent);

        	      AnchorPane.setTopAnchor(newContent, 0.0);
        	      AnchorPane.setBottomAnchor(newContent, 0.0);
        	      AnchorPane.setLeftAnchor(newContent, 0.0);
        	      AnchorPane.setRightAnchor(newContent, 0.0);

        	      FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), contentPane);
        	      fadeIn.setFromValue(0.0);
        	      fadeIn.setToValue(1.0);
        	      fadeIn.setCycleCount(1);
        	      fadeIn.play();
        	} catch (IOException e) {
        	        e.printStackTrace();
        	}
        });
        fadeOut.play();
	}
	
	
    @FXML
    public void handleChangeItems(ActionEvent event) {
    	loadNewContent("/fxml/pmViewItems.fxml");
    }

    @FXML
    private void handleChangePO(ActionEvent event) {

    }

    @FXML
    private void handleChangePR(ActionEvent event) {

    }

    @FXML
    private void handleChangeSuppliers(ActionEvent event) {

    }
    private boolean drawerOpen = false;
    @FXML
    private void toggleDrawer(ActionEvent event) 
    {
    	TranslateTransition transition = new TranslateTransition(Duration.millis(300), drawerPane);

        if (drawerOpen) {
            transition.setToY(+300);
        } else {
            transition.setToY(0);
        }

        transition.play();
        drawerOpen = !drawerOpen;
    }
  }


